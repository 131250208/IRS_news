package mybatis;

public class inverted_element implements java.io.Serializable, Comparable<inverted_element> {
	int docID;
	double wf;
	private static final long serialVersionUID = -5809782578272943999L;

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

	@Override
	public int compareTo(inverted_element o) {
		// TODO Auto-generated method stub
		if (o != null)
			return this.getDocID() - o.getDocID();
		else
			return 0;
	}
}
