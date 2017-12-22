package com.irs_news.mapper;

import java.util.List;

import com.irs_news.pojo.Word;

public interface VocabularyMapper {

	// 查询一组id对应的词项
	public List<Word> get_words_byIDs(List<Integer> id_list);

	// 查询词项
	public Word get_word_byID(int id);

	public Word get_word_byString(String word);

	// 查询一个词项对应的相似词项（拼音编辑距离相似）
	public List<Word> get_words_sim(int word_id);

	// 查询一个词项对应的同义词项（在各文档中的权重向量相似的词项）
	public List<Word> get_words_rela(int word_id);

	// 查询所有词项，只返回word字段
	public List<Word> get_words_all();

}
