package com.irs_news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irs_news.mapper.NewsMapper;
import com.irs_news.pojo.News;
import com.irs_news.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	NewsMapper newsMapper;
	
	@Override
	public List<News> search() {
		// TODO Auto-generated method stub
			
		return newsMapper.search();
	}

}
