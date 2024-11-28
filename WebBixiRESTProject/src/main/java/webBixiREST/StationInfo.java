package webBixiREST;

public class StationInfo {
	private int id;
	private String name;
	private double lat;
	private double lon;
	private int capacity;
	public StationInfo() {}
	public StationInfo(int id, String name, double lat, double lon, int capacity) {
		this.id = id;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.capacity = capacity;
	}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public double getLat() {return lat;}
	public void setLat(double lat) {this.lat = lat;}
	public double getLon() {return lon;}
	public void setLon(double lon) {this.lon = lon;}
	public int getCapacity() {return capacity;}
	public void setCapacity(int capacity) {this.capacity = capacity;}
	public String toString() {
		return "{id:" + id + ",name:" + name + ",lat:" + lat + ",lon:" + lon +
				",capacity:" + capacity + "}";
	}
}
