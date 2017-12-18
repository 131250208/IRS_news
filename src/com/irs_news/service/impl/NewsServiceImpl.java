package com.irs_news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irs_news.mapper.NewsMapper;
import com.irs_news.pojo.Comment;
import com.irs_news.pojo.News;
import com.irs_news.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	NewsMapper newsMapper;

	// 获取相关情绪的评论数量
	public int count_comments(List<Comment> comments, int emotion) {
		int count = 0;
		for (Comment c : comments) {
			if (c.getEmotion() == emotion)
				count++;
		}
		return count;
	}

	@Override
	public List<News> search(String search_text, String ranking_indicator, int page_index) {
		// TODO Auto-generated method stub

		// @杨寿国
		// 1、用工具将search_text切词
		// 2、用切好的词搜索 轮排索引树 找到 所有的词项id
		// 3、用词项id的list搜数据库找到所有词项对应的倒排索引
		// 4、快速合并倒排索引并返回排名最高的1000个文档的id
		// 5、文档id的list赋值给以下id_list

		List<Integer> id_list = new ArrayList<Integer>();
		id_list.add(1);
		id_list.add(2);
		id_list.add(4);
		id_list.add(7);

		List<News> news_list = newsMapper.search(id_list);
		for (News n : news_list) {
			// 将新闻里的评论分成两个list，分别为褒贬，方便前端调用
			List<Comment> com_up = new ArrayList<Comment>();
			List<Comment> com_down = new ArrayList<Comment>();
			for (Comment c : n.getComments()) {
				if (c.getEmotion() == 1)
					com_up.add(c);
				else {
					com_down.add(c);
				}
			}
			n.setComments_up(com_up);
			n.setComments_down(com_down);

			// 按id查找相关新闻
			n.setNews_sim(newsMapper.get_simNews(n.getId()));
		}

		return news_list;
	}

	@Override
	public List<News> get_sim_news(int news_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> get_hot_news(int num) {
		// TODO Auto-generated method stub
		return newsMapper.get_hotNews(8);
	}

}
