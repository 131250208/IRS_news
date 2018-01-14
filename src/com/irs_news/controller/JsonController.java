package com.irs_news.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.irs_news.pojo.News;
import com.irs_news.pojo.Word;
import com.irs_news.service.NewsService;
import com.irs_news.service.WordService;

@Controller
@RequestMapping("")
public class JsonController {

	@Autowired
	WordService wordService;
	@Autowired
	NewsService newsService;

	@ResponseBody
	@RequestMapping(value = "/get_words", produces = "application/json; charset=utf-8")
	public String get_all_words(HttpServletRequest request) {
		List<Word> words = wordService.get_words_all();

		List<String> words_strList = new ArrayList<String>();
		for (Word w : words) {
			words_strList.add(w.getWord());
		}
		return JSON.toJSONString(words_strList);
	}

	@ResponseBody
	@RequestMapping(value = "/get_sim_news", produces = "application/json; charset=utf-8")
	public String get_sim_news(HttpServletRequest request) {
		String news_id = request.getParameter("news_id");

		List<News> sim_news = newsService.get_sim_news(Integer.parseInt(news_id));

		List<Map<String, String>> res_list = new ArrayList<Map<String, String>>();

		for (News news : sim_news) {
			Map<String, String> news_map = new HashMap<String, String>();
			news_map.put("datetime", news.getDatetime());
			news_map.put("url", news.getUrl());
			news_map.put("title", news.getTitle());
			news_map.put("heat", String.valueOf(news.getHeat()));
			res_list.add(news_map);
		}
		return JSON.toJSONString(res_list);
	}

}
