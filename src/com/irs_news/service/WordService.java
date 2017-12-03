package com.irs_news.service;

import java.util.List;

public interface WordService {
	//获取相关的查询，即与查询的关键词 相似度（1-编辑距离／两词平均长度>a）高的词项 或者 在多篇文档中的权重相近的同义词词项，关键词可能有多个
	//参数： 关键词数组 e.g. 江泽民
	//返回： 相关词项list e.g. （江泽林，胡锦涛，主席，图样图森破……）
	List<String> get_sim_words(String[] key_words);
	
	//用带通配符的关键词查找所有的相关词项
	//参数： 带通配符的关键词 e.g. 洗*机
	//返回： 相关词项list e.g.（洗碗机，洗衣机，洗脚机…… ）
	List<String> get_rele_words(String key_word);
}
