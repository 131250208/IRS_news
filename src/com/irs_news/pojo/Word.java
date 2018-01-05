package com.irs_news.pojo;

import java.sql.*;

public class Word {
	private int id;
	private String word;
	private double idf;
	public byte[] getInverted_list() {
		return inverted_list;
	}

	public void setInverted_list(byte[] inverted_list) {
		this.inverted_list = inverted_list;
	}

	public byte[] getVector() {
		return vector;
	}

	public void setVector(byte[] vector) {
		this.vector = vector;
	}



	public byte[] getWinner1st() {
		return winner1st;
	}

	public void setWinner1st(byte[] winner1st) {
		this.winner1st = winner1st;
	}

	public byte[] getWinner2nd() {
		return winner2nd;
	}

	public void setWinner2nd(byte[] winner2nd) {
		this.winner2nd = winner2nd;
	}

	public byte[] getWinner3rd() {
		return winner3rd;
	}

	public void setWinner3rd(byte[] winner3rd) {
		this.winner3rd = winner3rd;
	}

	private byte[] inverted_list;
	private byte[] vector;
	private byte[] winner1st;
	private byte[] winner2nd;
	private byte[] winner3rd;



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

	
}
