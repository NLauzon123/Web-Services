package webBixiService;

import java.util.ArrayList;
import java.util.HashMap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class WebBixi {
	@WebMethod
	public String webBixiTest() {return "I am in the right place. WebBixi works";}
	@WebMethod
	public int checkUser(@WebParam(name="username") String username,
			@WebParam(name="password") String password) {
		Controller c = new Controller();
		HashMap<Integer, User> users = c.populateUserInfo();
		int result = -1;
		StringBuilder sb = new StringBuilder();
		users.forEach((k, v) -> {
			if (v.getUsername().equals(username) && v.getPassword().equals(password))
				sb.append(v.getUserID());
		});
		if (sb.length() > 0) result = Integer.parseInt(sb.toString());
		return result;
	}
	@WebMethod
	public User getUserInfo(@WebParam(name="userID") int userID) {
		Controller c = new Controller();
		HashMap<Integer, User> users = c.populateUserInfo();
		return users.get(userID);
	}
	@WebMethod
	public RideList getUserRides(@WebParam(name="userID") int userID) {
		Controller c = new Controller();
		HashMap<Integer, Ride> rides = c.populateRideInfo();
		ArrayList<Ride> userRides = new ArrayList<Ride>();
		rides.forEach((k, v) -> {
			if (v.getUserID() == userID) 
				userRides.add(v);
		});
		RideList rideList = new RideList();
		rideList.setRides(userRides);
		return rideList;
	}
}
