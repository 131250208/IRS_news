package com.irs_news.tools;

import java.util.Comparator;
import com.irs_news.pojo.inverted_element;

/*
作者：杨寿国
创建日期：2018年1月3日下午3:18:21
文件名：Inverted_elenment_Compare.java
TODO
*/
public class Inverted_elenment_Compare implements Comparator<inverted_element> {

	@Override
	public int compare(inverted_element o1, inverted_element o2) {
		// TODO Auto-generated method stub
		if (o1.getWf() > o2.getWf())
			return 1;
		else
			return -1;
	}

}
