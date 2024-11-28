package webBixiREST;

public class StationStatus {
	private int id;
	private int aBikes;
	private int aEbikes;
	private int dBikes;
	private int aDocks;
	private int dDocks;
	private long check;
	public StationStatus() {}
	public StationStatus(int id, int aBikes, int aEbikes, int dBikes, int aDocks, int dDocks, long check) {
		this.id = id;
		this.aBikes = aBikes;
		this.aEbikes = aEbikes;
		this.dBikes = dBikes;
		this.aDocks = aDocks;
		this.dDocks = dDocks;
		this.check = check; 
	}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public int getaBikes() {return aBikes;}
	public void setaBikes(int aBikes) {this.aBikes = aBikes;}
	public int getaEbikes() {return aEbikes;}
	public void setaEbikes(int aEbikes) {this.aEbikes = aEbikes;}
	public int getdBikes() {return dBikes;}
	public void setdBikes(int dBikes) {this.dBikes = dBikes;}
	public int getaDocks() {return aDocks;}
	public void setaDocks(int aDocks) {this.aDocks = aDocks;}
	public int getdDocks() {return dDocks;}
	public void setdDocks(int dDocks) {this.dDocks = dDocks;}
	public long getCheck() {return check;}
	public void setCheck(long check) {this.check = check;}
	public String toString() {
		return "{id:" + id + ",aBikes:" + aBikes + ",aEbikes:" + aEbikes + 
				",dBikes:" + dBikes + ",aDocks:" + aDocks + ",dDocks:" + dDocks +
				",check:" + check + "}";
	}
}
