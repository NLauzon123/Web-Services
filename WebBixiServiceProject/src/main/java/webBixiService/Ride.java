package webBixiService;

public class Ride {
	private int rideID;
	private int userID;
	private int origID;
	private int destID;
	private long start;
	private long end;
	public Ride() {}
	public Ride(int rideID, int userID, int origID, int destID, long picked, long returned) {
		this.rideID = rideID;
		this.userID = userID;
		this.origID = origID;
		this.destID = destID;
		this.start = picked;
		this.end = returned;
	}
	public int getRideID() {return rideID;}
	public void setRideID(int rideID) {this.rideID = rideID;}
	public int getUserID() {return userID;}
	public void setUserID(int userID) {this.userID = userID;}
	public int getOrigID() {return origID;}
	public void setOrigID(int origID) {this.origID = origID;}
	public int getDestID() {return destID;}
	public void setDestID(int destID) {this.destID = destID;}
	public long getPicked() {return start;}
	public void setPicked(long picked) {this.start = picked;}
	public long getReturned() {return end;}
	public void setReturned(long returned) {this.end = returned;}
}
