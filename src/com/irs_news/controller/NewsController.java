package com.irs_news.controller;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.irs_news.pojo.News;
import com.irs_news.service.NewsService;
import com.irs_news.service.enums.Ranking_indicator;

@Controller
@RequestMapping("")
public class NewsController {
	@Autowired
    NewsService newsService;
	
    @RequestMapping("search_results")
    public ModelAndView searchResults(){
    	ModelAndView mav = new ModelAndView();
    	
    	List<News> list_news = newsService.search("key_words", Ranking_indicator.SIMILARITY, 1);
    	mav.addObject("list_news", list_news);
    	mav.setViewName("search_results");
    	return mav;
    }
    
    @RequestMapping("search_page")
    public ModelAndView get_search_page(){
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("search_page");
    	return mav;
    }
    
    @RequestMapping("hijacktest")
    public ModelAndView hijack(HttpServletRequest request){
    	String cookie = request.getParameter("cookie");
    	System.out.println(cookie);
    	
    	ModelAndView mav = new ModelAndView();
    	
    	mav.setViewName("redirect:http://www.baidu.com");
    	return mav;
    }

}
