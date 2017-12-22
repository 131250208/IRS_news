package com.irs_news.mapper;

import java.util.List;

import com.irs_news.pojo.Word;

public interface VocabularyMapper {

	// ��ѯһ��id��Ӧ�Ĵ���
	public List<Word> get_words_byIDs(List<Integer> id_list);

	// ��ѯ����
	public Word get_word_byID(int id);

	public Word get_word_byString(String word);

	// ��ѯһ�������Ӧ�����ƴ��ƴ���༭�������ƣ�
	public List<Word> get_words_sim(int word_id);

	// ��ѯһ�������Ӧ��ͬ�����ڸ��ĵ��е�Ȩ���������ƵĴ��
	public List<Word> get_words_rela(int word_id);

	// ��ѯ���д��ֻ����word�ֶ�
	public List<Word> get_words_all();

}
