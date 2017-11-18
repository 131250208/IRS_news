package com.irs_news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.irs_news.pojo.News;
import com.irs_news.service.NewsService;

@Controller
@RequestMapping("")
public class NewsController {
	@Autowired
    NewsService newsService;
	
    @RequestMapping("search")
    public ModelAndView search(){
    	ModelAndView mav = new ModelAndView();
    	
    	List<News> list_news = newsService.search();
    	mav.addObject("list_news", list_news);
    	mav.setViewName("listNews");
    	return mav;
    }
}
