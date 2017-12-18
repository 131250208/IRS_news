package com.irs_news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irs_news.mapper.VocabularyMapper;
import com.irs_news.pojo.Word;
import com.irs_news.service.WordService;

@Service
public class WordServiceImpl implements WordService {
	@Autowired
	VocabularyMapper vocabularyMapper;

	@Override
	public List<String> get_sim_words(String key_words) {
		// TODO Auto-generated method stub

		List<String> list_sim_wordsList = new ArrayList<String>();
		list_sim_wordsList.add("相关词项1");
		list_sim_wordsList.add("相关词项2");
		list_sim_wordsList.add("相关词项3");
		list_sim_wordsList.add("相关词项4");
		list_sim_wordsList.add("相关词项5");
		return list_sim_wordsList;
	}

	@Override
	public List<String> get_rele_words(String key_word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Word> get_words_byIds(List<Integer> id_list) {
		// TODO Auto-generated method stub
		return vocabularyMapper.get_words_list(id_list);
	}

}
