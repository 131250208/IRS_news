package com.irs_news.mapper;

import java.util.List;

import com.irs_news.pojo.Word;

public interface VocabularyMapper {

	// 寰楀埌鏌愪竴涓瘝椤筰d瀵瑰簲鐨勮儨鑰呰〃
	// public LinkedList<inverted_element> get_winner_list(int word_id);
	// 閫氳繃璇嶉」id
	public List<Word> get_words_byIDs(List<Integer> id_list);

	// 锟斤拷询锟斤拷锟斤拷
	public Word get_word_byID(int id);

	public Word get_word_byString(String word);

	// 锟斤拷询一锟斤拷锟斤拷锟斤拷锟接︼拷锟斤拷锟斤拷拼锟斤拷睿ㄆ达拷锟斤拷嗉拷锟斤拷锟斤拷锟斤拷疲锟�
	public List<Word> get_words_sim(int word_id);

	// 锟斤拷询一锟斤拷锟斤拷锟斤拷锟接︼拷锟酵拷锟斤拷锟筋（锟节革拷锟侥碉拷锟叫碉拷权锟斤拷锟斤拷锟斤拷锟斤拷锟狡的达拷锟筋）
	public List<Word> get_words_rela(int word_id);

	// 查询所有词项，只返回word字段
	public List<Word> get_words_all();

	public int get_id(String word);

}
