package com.irs_news.service.jtest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.irs_news.service.WordService;

public class WordServiceTest extends ServiceTest {

	@Autowired
	private WordService wordService;

	@Test
	public void testGet_words_byIds() {
		List<Integer> id_list = new ArrayList<Integer>();
		id_list.add(1);
		id_list.add(3);
		id_list.add(4);
		System.out.println(wordService.get_words_byIds(id_list));
	}

	@Test
	public void testGet_simAndRela_words() {
		System.out.println(wordService.get_simAndRela_words("test"));
	}

	@Test
	public void testGet_words_all() {
		System.out.println(wordService.get_words_all());
	}
}
