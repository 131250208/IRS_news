package com.irs_news.service;
/*
作者：杨寿国
创建日期：2017年12月18日下午4:23:41
文件名：JcsegService.java
TODO
*/
public interface JcsegService {
	
	//
	void loadConfig();
	
	void loadDic();
	//创建分词实例
	void CreateIseg();
}
