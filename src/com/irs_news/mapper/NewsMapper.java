package com.irs_news.mapper;

import java.util.List;

import com.irs_news.pojo.News;

public interface NewsMapper {

	public News get(int id);

	// 参数 新闻id的list
	// 返回 新闻对象 带 评论list 不带相似新闻list（在service里加上了）
	public List<News> search(List<Integer> list);

	// 参数 新闻id
	// 返回 相似新闻list
	public List<News> get_simNews(int id);

	// 参数 热门新闻数量
	// 返回 热门新闻list 按heat降序排列
	public List<News> get_hotNews(int num);
}
