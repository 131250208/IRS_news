package com.irs_news.mapper;

import java.util.List;

import com.irs_news.pojo.News;

public interface NewsMapper {

	public News get(int id);

	// ���� ����id��list
	// ���� ���Ŷ��� �� ����list ������������list����service������ˣ�
	public List<News> search(List<Integer> list);

	// ���� ����id
	// ���� ��������list
	public List<News> get_simNews(int id);

	// ���� ������������
	// ���� ��������list ��heat��������
	public List<News> get_hotNews(int num);
}
