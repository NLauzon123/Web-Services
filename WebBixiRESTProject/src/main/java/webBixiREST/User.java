package webBixiREST;

public class User {
	private int userID;
	private String username;
	private String password;
	private String usertype;
	private String address;
	private String email;
	public User() {}
	public User(int userID, String username, String password, String usertype, String address, String email) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.address = address;
		this.email = email;
	}
	public int getUserID() {return userID;}
	public void setUserID(int userID) {this.userID = userID;}
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getUsertype() {return usertype;}
	public void setUsertype(String usertype) {this.usertype = usertype;}
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
}
