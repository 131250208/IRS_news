package com.irs_news.service;

import java.util.List;

import com.irs_news.pojo.News;

public interface NewsService {

	// 检索函数
	// 参数：关键词， 排序指标（相关度，热度，时间），页码。
	// K值取1000，每页10篇，所以页码参数取值为1～10
	// 返回： 新闻list
	List<News> search(List<Integer> id_list, String ranking_indicator, int page_index, boolean same_search);

	// 获取相似新闻
	// 参数： 新闻id
	// 返回： 相似新闻list
	List<News> get_sim_news(int news_id);

	// 获取热门新闻
	// 参数： 热门新闻数量
	// 返回： 热门新闻list
	List<News> get_hot_news(int num);

}
