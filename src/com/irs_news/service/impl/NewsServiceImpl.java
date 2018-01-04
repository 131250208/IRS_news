package com.irs_news.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.lionsoul.jcseg.ASegment;
import org.lionsoul.jcseg.core.IWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irs_news.mapper.NewsMapper;
import com.irs_news.mapper.VocabularyMapper;
import com.irs_news.pojo.Comment;
import com.irs_news.pojo.News;
import com.irs_news.pojo.Word;
import com.irs_news.service.NewsService;
import com.irs_news.service.WordService;
import com.irs_news.tools.Inverted_elenment_Compare;

import mybatis.inverted_element;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	NewsMapper newsMapper;
	@Autowired
	VocabularyMapper wordMapper;
	
	private static List<Integer> old_doc_list = null;
	
	@Override
	public List<News> search(List<Integer> id_list, String ranking_indicator, int page_index, boolean same_search) {
		// TODO Auto-generated method stub
		// @杨寿国
		// 1、用工具将search_text切词
		// 2、用切好的词搜索 轮排索引树 找到 所有的词项id
		// 3、用词项id的list搜数据库找到所有词项对应的倒排索引
		// 4、快速合并倒排索引并返回排名最高的1000个文档的id
		// 5、文档id的list赋值给以下id_list
		
		//通过词典找到词项对象
		List<Word> word_list = wordMapper.get_words_byIDs(id_list);
		
		if (word_list.size() == 0)
			return null;
		
		//对得到的胜者表进行合并，并且排序
		List <inverted_element> final_winner = getListFrombyte(word_list.get(0).getWinner1st());
		
		System.out.println(word_list.get(0).getWord()+"胜者表长度为"+final_winner.size());
		
		double last_idf = word_list.get(0).getIdf();
		for (int i = 1 ; i < word_list.size() ; ++i) {
			System.out.print(word_list.get(i).getWord()+"胜者表长度为");
			final_winner = getUnionbyDocID(final_winner, getListFrombyte(word_list.get(i).getWinner1st()) , last_idf , word_list.get(i).getIdf());
		}
		

		//对合并后的胜者表进行按照 wfidf 排序
		Collections.sort(final_winner,new Comparator<inverted_element>() {

			@Override
			public int compare(inverted_element o1, inverted_element o2) {
				// TODO Auto-generated method stub
				if (o1.getWf() >= o2.getWf()) {
					return 1;
				}
				return -1;
			}
		});
		System.out.println("排序合并之后的文档记录长度为"+final_winner.size());
		if (final_winner.isEmpty())
			return null;
		
		List<Integer> doc_list = new ArrayList<Integer>();
		for (int i = 0 ; i < final_winner.size() && i < 1000 ; ++i) {
			doc_list.add(final_winner.get(i).getDocID());
		}
		
		
		
		// @杨寿国，提供相关度前1000的新闻的id_list, 按相关度顺序排列
		List<News> news_list = newsMapper.search(doc_list);
		
		
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

		// @杨寿国
		// 若是按时间和热度排序
		// news_list还需要在这里再重新排序

		// 排序后按页码返回10个
		return news_list;
	}
	
	
	//取两个胜者表的并集
	public List<inverted_element> getUnionbyDocID(List<inverted_element> u1, List<inverted_element> u2, double u1_idf, double u2_idf) {
		
		System.out.println(u2.size());
		List<inverted_element> newlist = new LinkedList<inverted_element>();
		
		//按照docid排序
		Collections.sort(u1);
		Collections.sort(u2);
		
		//取并集
		int l1 = 0, doc1;
		int l2 = 0, doc2;
		while (l1 < u1.size() && l2 < u2.size()) {
			doc1 = u1.get(l1).getDocID();
			doc2 = u2.get(l2).getDocID();
			if (doc1 == doc2) {
				u1.get(l1).setWf(u1.get(l1).getWf()*u1_idf + u2.get(l2).getWf()*u2_idf); //更新文档的wf = wf1 * idf1 + wf2 * idf2
				newlist.add(u1.get(l1));
				l1++;
				l2++;
			}else if(doc1 > doc2) {
				l2++;
			}else {
				l1++;
			}
		}
		return newlist;
		
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<inverted_element> getListFrombyte(byte [] data){
		
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new ByteArrayInputStream(data));
			return (LinkedList<inverted_element>) in.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public List<News> get_sim_news(int news_id) {
		// TODO Auto-generated method stub
		System.out.println("get_sim_news");
		return null;
	}

	// 这个已经写完了
	@Override
	public List<News> get_hot_news(int num) {
		// TODO Auto-generated method stub
		return newsMapper.get_hotNews(num);
	}

}
