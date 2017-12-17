package com.irs_news.pojo;

import java.util.List;

public class News {
	private int id;
	private String title;
	private String date;
	private String content;
	private String abstract_;
	private String url;
	private int heat;
	private List<Comment> comments;
	private List<News> news_sim;

	private List<Comment> comments_up;
	private List<Comment> comments_down;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id = " + this.id + "; title = " + this.title + "; content = " + this.content;
	}

	public List<Comment> getComments_up() {
		return comments_up;
	}

	public void setComments_up(List<Comment> comments_up) {
		this.comments_up = comments_up;
	}

	public List<Comment> getComments_down() {
		return comments_down;
	}

	public void setComments_down(List<Comment> comments_down) {
		this.comments_down = comments_down;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getAbstract_() {
		return abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHeat() {
		return heat;
	}

	public void setHeat(int heat) {
		this.heat = heat;
	}

	public List<News> getNews_sim() {
		return news_sim;
	}

	public void setNews_sim(List<News> news_sim) {
		this.news_sim = news_sim;
	}
}
