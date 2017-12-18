package com.irs_news.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.irs_news.pojo.News;
import com.irs_news.pojo.Word;
import com.irs_news.service.NewsService;
import com.irs_news.service.WordService;

@Controller
@RequestMapping("")
public class NewsController {
	@Autowired
	NewsService newsService;
	@Autowired
	WordService wordService;

	@RequestMapping("search_results")
	public ModelAndView searchResults(HttpServletRequest request) {
		String ranking_indicator = request.getParameter("ranking_indicator");
		String page_index = request.getParameter("page_index");
		String search_text = request.getParameter("search_text");

		List<News> list_news = newsService.search(search_text, ranking_indicator, Integer.parseInt(page_index));
		List<Word> list_simAndRela_words = wordService.get_simAndRela_words(search_text);

		ModelAndView mav = new ModelAndView();
		mav.addObject("list_news", list_news);
		mav.addObject("list_simAndRela_words", list_simAndRela_words);
		mav.addObject("page_index", page_index);
		mav.addObject("search_text", search_text);
		mav.addObject("page_total", "10");

		mav.setViewName("search_results");
		return mav;
	}

	@RequestMapping("search_page")
	public ModelAndView get_search_page() {
		ModelAndView mav = new ModelAndView();

		List<News> list_hot_news = newsService.get_hot_news(8);
		List<News> articles_former = list_hot_news.subList(0, 4);
		List<News> articles_latter = list_hot_news.subList(4, 8);
		mav.addObject("articles_former", articles_former);
		mav.addObject("articles_latter", articles_latter);

		mav.setViewName("search_page");
		return mav;
	}

	@RequestMapping("hello")
	public ModelAndView hello(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("cookiehijack");
		return mav;
	}

	@RequestMapping("hijacktest")
	public ModelAndView hijack(HttpServletRequest request) {
		String cookie = request.getParameter("cookie");
		System.out.println("��ȡcookie------------->" + cookie);

		ModelAndView mav = new ModelAndView();

		return mav;
	}

}
