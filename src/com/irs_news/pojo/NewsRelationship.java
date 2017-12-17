package com.irs_news.pojo;

public class NewsRelationship {

	private News news1;
	private News news2;
	private double sim;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "news1_id = " + news1.getId() + "; news2_id" + news2.getId();
	}

	public News getNews1() {
		return news1;
	}

	public void setNews1(News news1) {
		this.news1 = news1;
	}

	public News getNews2() {
		return news2;
	}

	public void setNews2(News news2) {
		this.news2 = news2;
	}

	public double getSim() {
		return sim;
	}

	public void setSim(double sim) {
		this.sim = sim;
	}
}
