package com.irs_news.mapper;

import java.util.List;

import com.irs_news.pojo.Word;

public interface VocabularyMapper {

	public List<Word> get_words_list(List<Integer> id_list);
}
