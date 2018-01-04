package com.irs_news.mapper;

import java.util.LinkedList;
import java.util.List;

import com.irs_news.pojo.Word;

public interface VocabularyMapper {

	//得到某一个词项id对应的胜者表
//	public LinkedList<inverted_element> get_winner_list(int word_id);
	// 通过词项id
	public List<Word> get_words_byIDs(List<Integer> id_list);

	// ��ѯ����
	public Word get_word_byID(int id);

	public Word get_word_byString(String word);

	// ��ѯһ�������Ӧ�����ƴ��ƴ���༭�������ƣ�
	public List<Word> get_words_sim(int word_id);

	// ��ѯһ�������Ӧ��ͬ�����ڸ��ĵ��е�Ȩ���������ƵĴ��
	public List<Word> get_words_rela(int word_id);
	
	public int get_id(String word);
}
