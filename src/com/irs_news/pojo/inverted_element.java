package com.irs_news.pojo;

public class inverted_element implements java.io.Serializable, Comparable<inverted_element> {
	int docID;
	double wf;
	private static final long serialVersionUID = -5809782578272943999L;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "docid = "+ docID + " , wf = " + wf;
	}

	@Override
	public int compareTo(inverted_element o) {
		// TODO Auto-generated method stub
		if (o != null)
			return this.getDocID() - o.getDocID();
		else
			return 0;
	}
	
	public int getDocID() {
		return docID;
	}

	public void setDocID(int docID) {
		this.docID = docID;
	}

	public double getWf() {
		return wf;
	}

	public void setWf(double wf) {
		this.wf = wf;
	}
	

	public inverted_element(int newDocID, double newWf)
	{
		docID = newDocID;
		wf = newWf;
	}
}
