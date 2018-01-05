package com.irs_news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irs_news.mapper.VocabularyMapper;
import com.irs_news.pojo.Word;
import com.irs_news.service.WordService;
import com.irs_news.tools.Tools;
import com.irs_news.tools.Trie;

@Service
public class WordServiceImpl implements WordService {
	@Autowired
	VocabularyMapper vocabularyMapper;
	private static Trie root = new Trie('#');

	@Override
	public List<Word> get_simAndRela_words(String search_text) {
		// TODO Auto-generated method stub
		// @杨寿国
		// 输入内容可能为句子，要切词
		// 对于每一个切好的词
		// 1、要么在轮排索引树中直接找到id, 查库找到词项。
		// 2、要么找到某个子树，发现不能再往下遍历到下一个字了
		// 即把这个子树中的所有id找出了，用id_list搜索出所有词项进行编辑距离的比较
		// 返回最相似的词项。
		// 3、从1、2中得到的这个词项再进行找相似和同义词(如果从2得到的，本身也算一个相似词)
		// 相似和同义词各返回3个，最多6个（不是每一个词项，是整个输入而言，具体细节自己权衡）

		// 以一个临时词项为例
		Word word = new Word();
		word.setId(2);

		List<Word> list_sim_wordsList = vocabularyMapper.get_words_sim(word.getId());
		List<Word> list_rela_wordsList = vocabularyMapper.get_words_rela(word.getId());

		list_sim_wordsList.addAll(list_rela_wordsList);

		return list_sim_wordsList;
	}

	@Override
	public List<Word> match_words(String key_word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Word> get_words_byIds(List<Integer> id_list) {
		// TODO Auto-generated method stub
		return vocabularyMapper.get_words_byIDs(id_list);
	}

	@Override
	public List<String> get_IDs_byGword(String Gword) {
		// TODO Auto-generated method stub
		ArrayList<String> res = new ArrayList<String>();
		Tools.search(Gword, JcsegServiceImpl.get_TrieRoot(), res);

		return res;
	}

	@Override
	public List<Word> get_words_all() {
		// TODO Auto-generated method stub
		return vocabularyMapper.get_words_all();
	}

}
