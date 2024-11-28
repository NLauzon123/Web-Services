package webBixiService;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RideList {
	@XmlElementWrapper(name = "ride_list")
	@XmlElement(name = "ride")
	private ArrayList<Ride> rides = new ArrayList<Ride>();
	public RideList( ) {}
	public ArrayList<Ride> getRides() {return rides;}
	public void setRides(ArrayList<Ride> rides) {this.rides = rides;}
}
