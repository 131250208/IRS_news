package com.irs_news.pojo;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class News {
	private int id;
	private String title;
	private String profile;
	
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
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
}
