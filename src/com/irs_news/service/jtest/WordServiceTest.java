package com.irs_news.service.jtest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.irs_news.mapper.VocabularyMapper;
import com.irs_news.pojo.Word;
import com.irs_news.service.WordService;

import mybatis.inverted_element;

public class WordServiceTest extends ServiceTest {

	@Autowired
	private WordService wordService;

	@Autowired
	private VocabularyMapper vocabularyMapper;

	// @Test
	// public void testGet_words_byIds() {
	// List<Integer> id_list = new ArrayList<Integer>();
	// id_list.add(1);
	// id_list.add(3);
	// id_list.add(4);
	// System.out.println(wordService.get_words_byIds(id_list));
	// }
	//
	// @Test
	// public void testGet_simAndRela_words() {
	// System.out.println(wordService.get_simAndRela_words("test"));
	// }

	public void test() {
		Word w = vocabularyMapper.get_word_byString("今天");
		System.out.println(w);
		// String x = w.getWinner1st();
		try {

			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(w.getWinner1st()));
			@SuppressWarnings("unchecked")

			LinkedList<inverted_element> sd = (LinkedList<inverted_element>) in.readObject();
			System.out.println(sd.get(1).getDocID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void test_get_word_id() {
		int id = vocabularyMapper.get_id("明天");
		System.out.println(id);
	}

	@Test
	public void testGet_words_all() {
		List<Word> words = wordService.get_words_all();

		List<String> words_strList = new ArrayList<String>();
		for (Word w : words) {
			words_strList.add(w.getWord());
		}
		System.out.println(JSON.toJSONString(words_strList));
	}
}
