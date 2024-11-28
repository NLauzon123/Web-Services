package webBixiService;

import java.util.HashMap;

public class Controller {
	public HashMap<Integer, User> populateUserInfo() {
		HashMap<Integer, User> u = new HashMap<Integer, User>();
		u.put(1, new User(1, "nicolas", "nicolas123", "occasional", "Laval", "nicolas@Vanier.com"));
		u.put(2, new User(2, "samir", "samir123", "regular", "Montreal", "samir@Vanier.com"));
		u.put(3, new User(3, "kevin", "kevin123", "platinum", "Vancouver", "kevin@Vanier.com"));
		u.put(4, new User(4, "albina", "albina123", "regular", "Longueuil", "albina@Vanier.com"));
		u.put(5, new User(5, "choo", "choo123", "occasional", "Hudson", "choo@Vanier.com"));
		return u;
	}
	public HashMap<Integer, Ride> populateRideInfo() {
		HashMap<Integer, Ride> r = new HashMap<Integer, Ride>();
		r.put(1, new Ride(1, 1, 621, 501, 86400, 90000));
		r.put(2, new Ride(2, 1, 500, 621, 135000, 139000));
		r.put(3, new Ride(3, 1, 621, 1, 350000, 354000));
		r.put(4, new Ride(4, 1, 1, 621, 400000, 404000));
		r.put(5, new Ride(5, 1, 621, 832, 700000, 704000));
		r.put(6, new Ride(6, 1, 832, 621, 750000, 754000));
		r.put(7, new Ride(7, 2, 13, 501, 86400, 90000));
		r.put(8, new Ride(8, 2, 500, 13, 135000, 139000));
		r.put(9, new Ride(9, 2, 13, 1, 350000, 354000));
		r.put(10, new Ride(10, 2, 1, 13, 400000, 404000));
		r.put(11, new Ride(11, 2, 13, 124, 700000, 704000));
		r.put(12, new Ride(12, 2, 124, 13, 750000, 754000));
		r.put(13, new Ride(13, 3, 13, 207, 86400, 90000));
		r.put(14, new Ride(14, 3, 207, 13, 135000, 139000));
		r.put(15, new Ride(15, 3, 13, 269, 350000, 354000));
		r.put(16, new Ride(16, 3, 269, 13, 400000, 404000));
		r.put(17, new Ride(17, 3, 13, 124, 700000, 704000));
		r.put(18, new Ride(18, 3, 124, 13, 750000, 754000));
		r.put(19, new Ride(19, 4, 270, 336, 700000, 704000));
		r.put(20, new Ride(20, 4, 336, 270, 750000, 754000));
		return r;
	}
}
