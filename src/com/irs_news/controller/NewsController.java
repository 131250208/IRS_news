package com.irs_news.controller;

import java.util.List;
import java.util.Map;

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

	private static String old_text = "";

	@RequestMapping("search_results")
	public ModelAndView get_search_results(HttpServletRequest request) {
		String ranking_indicator = request.getParameter("ranking_indicator");
		String page_index = request.getParameter("page_index");
		String search_text = request.getParameter("search_text");
		// String page_total = request.getParameter("page_total");
		boolean same_search = false;
		// if (old_text.equals(search_text)) {
		// same_search = true;
		// }else {
		// old_text = search_text;
		// }
		ModelAndView mav = new ModelAndView();

		try {
			List<Integer> id_list = wordService.get_id_list(search_text, same_search);
			Map<String, Object> resMap = newsService.search(id_list, ranking_indicator, Integer.parseInt(page_index),
					same_search);
			List<News> list_news = (List<News>) resMap.get("news_list");
			String size = (String) resMap.get("total_size");
			int size_int = Integer.parseInt(size);
			int page_total = size_int / 10;
			if (size_int % 10 != 0)
				page_total += 1;

			List<Word> list_simAndRela_words = wordService.get_simAndRela_words(id_list, same_search);
			mav.addObject("list_news", list_news);
			mav.addObject("list_simAndRela_words", list_simAndRela_words);
			mav.addObject("page_index", page_index);
			mav.addObject("search_text", search_text);
			mav.addObject("ranking_indicator", ranking_indicator);
			mav.addObject("page_total", String.valueOf(page_total));
			mav.addObject("results_size", size);

			mav.setViewName("search_results");
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}

		return mav;
	}

	@RequestMapping("/")
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
