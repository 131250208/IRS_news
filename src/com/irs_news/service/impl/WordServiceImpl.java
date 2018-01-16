package com.irs_news.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.binding.BindingException;
import org.lionsoul.jcseg.ASegment;
import org.lionsoul.jcseg.core.IWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irs_news.mapper.VocabularyMapper;
import com.irs_news.pojo.Word;
import com.irs_news.service.WordService;
import com.irs_news.tools.Tools;

@Service
public class WordServiceImpl implements WordService {
	@Autowired
	VocabularyMapper vocabularyMapper;

	private static ASegment iseg = null;
	// private WordService wordtool = null;

	@Override
	public List<Word> get_simAndRela_words(List<Integer> id_list, boolean same_search) {
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

		List<Word> list_sim_wordsList = new ArrayList<Word>();
		List<Word> list_rela_wordsList = new ArrayList<Word>();

		for (Integer id : id_list) {
			list_sim_wordsList.addAll(vocabularyMapper.get_words_sim(id));
			list_rela_wordsList.addAll(vocabularyMapper.get_words_rela(id));
		}

		List<Word> res = new ArrayList<Word>();
		int num_sim = Math.min(3, list_sim_wordsList.size());
		int num_rela = Math.min(3, list_rela_wordsList.size());

		res.addAll(list_rela_wordsList.subList(0, num_rela));
		res.addAll(list_sim_wordsList.subList(0, num_sim));

		return res;
	}

	@Override
	public List<Word> match_words(String key_word) {
		// TODO Auto-generated method stub
		// 查找通配符位置，假设搜索词中只有一个通配符
		int pos = key_word.indexOf("*");

		String target = key_word.substring(0, pos);
		List<String> list;
		if (pos < key_word.length()) {
			list = Arrays.asList(key_word.substring(pos + 1).split(""));
			Collections.reverse(list);
			target += list.toString();
		}

		ArrayList<String> res = new ArrayList<String>();
		Tools.search(target, JcsegServiceImpl.get_TrieRoot(), res);
		List<Word> wordlist = new ArrayList<Word>();
		for (String word : res) {
			wordlist.add(vocabularyMapper.get_word_byString(word));
		}
		return wordlist;
	}

	@Override
	public List<Word> get_words_byIds(List<Integer> id_list) {
		// TODO Auto-generated method stub
		return vocabularyMapper.get_words_byIDs(id_list);
	}

	// 暂时没用
	@Override
	public List<String> get_Term_byGword(String Gword) {
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

	// 20180114
	public boolean Exist_in_database(String search_text, List<Integer> id_list) {
		int id = 0;
		try {
			Date d1 = new Date();
			id = vocabularyMapper.get_id(search_text);
			Date d2 = new Date();
			System.out.println("查询搜索文本时间" + (d2.getTime() - d1.getTime()));
			id_list.add(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	// 20180114 处理通配符查询
	public String Handle_gtext(String search_text) {
		search_text += "$";
		String prefix = "";
		int pos = search_text.indexOf('*');
		String x = search_text.substring(pos + 1);
		if (pos != 0)
			prefix += search_text.substring(0, pos);
		return x + prefix;
	}

	public List<Integer> get_id_list(String search_text, boolean same_search) {
		// TODO Auto-generated method stub

		// 分词

		// new JcsegServiceImpl();
		// wordtool = new WordServiceImpl();
		List<String> search_words = new ArrayList<String>();

		List<Integer> id_list = new ArrayList<Integer>();

		// 20180114 通配符处理
		if (search_text.contains("*")) {
			ArrayList<String> res = new ArrayList<String>();
			Tools.search(Handle_gtext(search_text), JcsegServiceImpl.get_TrieRoot(), res);
			for (String string : res) {
				try {
					id_list.add(vocabularyMapper.get_id(string));
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(string + "查询异常");
				}

			}
			return id_list;
		}
		//// 20180114 如果数据库中存在整个词，不用切词
		if (Exist_in_database(search_text, id_list)) {
			return id_list;
		}
		iseg = JcsegServiceImpl.getSeg();
		try {
			iseg.reset(new StringReader(search_text));
			IWord word;
			int pos = 0;
			while ((word = iseg.next()) != null) {
				search_words.add(word.getValue());
				pos += word.getValue().length();
				// if (g_pos > 0 && pos >= g_pos ) {
				// System.out.println("给词项"+word.getValue()+"加通配符");
				// List<String> gword = get_Term_byGword(word.getValue()+"$");
				// for (String string : gword) {
				// id_list.add(vocabularyMapper.get_id(string));
				// }
				// continue;
				// }
				id_list.add(vocabularyMapper.get_id(word.getValue()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BindingException e) {
			// TODO: handle exception
			System.out.println("数据库中缺少查询词项");
		}
		System.out.print(" 查询词项为 ");
		for (String word : search_words) {
			System.out.print(word + " ");
		}
		return id_list;
	}

}
