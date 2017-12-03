package com.irs_news.service;

import java.util.List;

import com.irs_news.pojo.Comment;
import com.irs_news.service.enums.Emotion;

public interface CommentService {
	//获取指定新闻的评论
	//参数： 新闻id， 褒贬（up,down）
	//返回： 评论list
	List<Comment> get_comments(int news_id, Emotion emotion);
}
