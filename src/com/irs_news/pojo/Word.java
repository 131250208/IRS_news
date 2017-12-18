package com.irs_news.pojo;

public class Word {
	private int id;
	private String word;
	private double idf;
	private String inverted_list;
	private String vector;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id = " + id + "; word = " + word + "; idf = " + idf + "; inverted_list = " + inverted_list
				+ "; vector = " + vector;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public double getIdf() {
		return idf;
	}

	public void setIdf(double idf) {
		this.idf = idf;
	}

	public String getInverted_list() {
		return inverted_list;
	}

	public void setInverted_list(String inverted_list) {
		this.inverted_list = inverted_list;
	}

	public String getVector() {
		return vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}
}
