package com.irs_news.mapper;

import java.util.List;

import com.irs_news.pojo.Comment;

public interface CommentMapper {
	public List<Comment> get_comments(int news_id, int emotion);

	public int count_comments(int news_id, int emotion);
}
