package com.irs_news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irs_news.mapper.NewsMapper;
import com.irs_news.pojo.News;
import com.irs_news.service.NewsService;
import com.irs_news.service.enums.Ranking_indicator;

@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	NewsMapper newsMapper;
	
	@Override
	public List<News> search(String key_words, Ranking_indicator ranking_indicator, int page_index) {
		// TODO Auto-generated method stub
		
		//根据参数返回结果
		
		return newsMapper.search();
	}

	@Override
	public List<News> get_sim_news(String news_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> get_hot_news(int num) {
		// TODO Auto-generated method stub
		return null;
	}

}
