package com.irs_news.pojo;

public class Comment {
	private int id;
	private String content;
	private int emotion;
	private String username;
	private String date_time;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id = " + this.id + "; content = " + this.content + "; datetime = " + this.date_time;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getEmotion() {
		return emotion;
	}

	public void setEmotion(int emotion) {
		this.emotion = emotion;
	}

}
