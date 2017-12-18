package com.irs_news.service;

import java.util.List;

import com.irs_news.pojo.Word;

public interface WordService {
	// 获取相关的查询，即与查询的关键词 相似度（1-编辑距离／两词平均长度>a）高的词项 或者 在多篇文档中的权重相近的同义词词项，关键词可能有多个
	// 参数： 输入的关键词查询 e.g. 邓小平最后一次露面只对江泽民说句什么（先分词）
	// 返回： 相关词项list e.g. （江泽林，胡锦涛，主席，图样图森破，总理，见面……）
	public List<String> get_sim_words(String key_words);

	// 用带通配符的关键词查找所有的相关词项
	// 参数： 带通配符的关键词 e.g. 洗*机
	// 返回： 相关词项list e.g.（洗碗机，洗衣机，洗脚机…… ）
	public List<String> get_rele_words(String key_word);

	public List<Word> get_words_byIds(List<Integer> id_list);
}
