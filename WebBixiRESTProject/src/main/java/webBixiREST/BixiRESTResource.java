package webBixiREST;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("WebBixi")
public class BixiRESTResource {
	// REST Resources for Phase 3 of the project
	@GET
	@Path("/UserCheck/")
	@Produces(MediaType.TEXT_PLAIN)
	public String userCheck(@QueryParam("username") String username,
			@QueryParam("password") String password) {
		String result = "";
		Controller c = new Controller();
		HashMap<Integer, User> users = c.populateUserInfo();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		users.forEach((k, v) -> {
			if (v.getUsername().equals(username) && v.getPassword().equals(password))
				sb.append(v.getUsername());
		});
		if (!sb.toString().equals("")) result = "<h2><b>Welcome " + sb.toString() + "</b></h2>";
		return result;
	}
	// REST Resources for Phase 2 of the project
	@GET
	@Path("/StationMenu/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String stationMenuText(@PathParam("id") String id) {
		Controller c = new Controller();
		HashMap<Integer, StationInfo> m = c.populateStationInfo(); 
		StringBuilder sb = new StringBuilder();
		sb.append("<label for=\"" + id + "\">Enter the station ID:</label><select class=\"form-select-lg\" aria-label=\"Default select example\" id=\"" + id + "\">");
		sb.append("<option selected>Select a station</option>");
		m.forEach((k, v) -> sb.append("<option value=\"" + v.getId() + "\">" + 
				v.getId() + " - " + v.getName() + "</option>"));
		sb.append("</select>");
		return sb.toString();
	}
	@GET
	@Path("/StationList")
	@Produces(MediaType.TEXT_PLAIN)
	public String getStationInfoText() {
		Controller c = new Controller();
		HashMap<Integer, StationInfo> m = c.populateStationInfo(); 
		StringBuilder sb = new StringBuilder();
		sb.append("<h2  style=\"color:white;font-family:'Times New Roman';\"><b>The " + m.size() + " stations on the network:</b></h2>");
		m.forEach((k, v) -> sb.append("<a onclick=\"getStationDetailsID("+ v.getId() + ")\">" +
				"<div class=\"row rowStation\" >" +
				"<div class=\"col-md-2\"><h1><b>" + v.getId() + "</b></h1></div>" +
				"<div class=\"col-md-10\">" +
				"<h3>" + v.getName() + "</h3>" +
				"<p>Number of docks in total: " + v.getCapacity() + "</p>" +
				"</div></div><a>"
				));
		return sb.toString();
	}
	@GET
	@Path("/DetailsRequest/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getStationDetailsText(@PathParam("id") int id) {
		Controller c = new Controller();
		HashMap<Integer, StationInfo> m1 = c.populateStationInfo(); 
		HashMap<Integer, StationStatus> m2 = c.populateStationStatus();
		StationInfo s1 = m1.get(id);
		StationStatus s2 = m2.get(id);
		StringBuilder sb = new StringBuilder();
		sb.append("<h3><b>Stations info:</b></h3>");
		if (s1 == null) {
			sb.append("<p>No station identified by the ID: " + id + "</p>");
		}
		else {
			sb.append("<p><b>ID: </b>" + id + "</p>" + 
					"<p><b>Name: </b>" + s1.getName() + "</p>" +
					"<p><b>Total docks: </b>" + s1.getCapacity() + "</p>");
			if (s2 == null) {
				sb.append("<p>No status details available for this station</p>");
			}
			else {
				Date t = new Date(s2.getCheck() * 1000);
				sb.append("<h3><b>Status on " + t + ":</b></h3>" +
						"<p><b>Available bikes: </b>" + s2.getaBikes() + "</p>" + 
						"<p><b>Available Ebikes: </b>" + s2.getaEbikes() + "</p>" +
						"<p><b>Disabled bikes: </b>" + s2.getdBikes() + "</p>" +
						"<p><b>Available docks: </b>" + s2.getaDocks() + "</p>" +
						"<p><b>Disabled docks: </b>" + s2.getdDocks() + "</p>");
			}
		}
		sb.append("<button class=\"btn btn-primary\" onclick=\"clearLeftBody()\">Clear</button>");
		return sb.toString();
	}
	@GET
	@Path("/DynamicDist/")
	@Produces(MediaType.TEXT_PLAIN)
	public String stationToStationDistText(@QueryParam("station1") int s1ID,
			@QueryParam("station2") int s2ID) {
		Controller c = new Controller();
		HashMap<Integer, StationInfo> m = c.populateStationInfo(); 
		StationInfo s1 = m.get(s1ID);
		StationInfo s2 = m.get(s2ID);
		StringBuilder sb = new StringBuilder();
		if (s1 == null || s2 == null) {
			sb.append("<p>Invalid choices of stations; no distance calculated</p>");			
		}
		else {
			double dist = c.distanceCalc(s1.getLat(), s1.getLon(), s2.getLat(), s2.getLon(), 0);
			sb.append("<h3><b>Distance Estimate: </b></h3>" +
					"<p><b>From station: </b>" + s1.getId() + ", " + s1.getName() + "</p>" + 
					"<p><b>To station: </b>" + s2.getId() + "</a>, " + s2.getName() + "</p>" +
					"<p><b>Distance of: </b>" + String.format("%.0f", dist) + " m or " + String.format("%.3f", dist / 1000) + " km</p>");
		}
		sb.append("<button class=\"btn btn-primary\" onclick=\"clearLeftBody()\">Clear</button>");
		return sb.toString();
	}	
	@GET
	@Path("/DynamicRad/")
	@Produces(MediaType.TEXT_PLAIN)
	public String locationRadiusText(@QueryParam("lat") double lat,
			@QueryParam("lon") double lon, @QueryParam("rad") double rad) {
		Controller c = new Controller();
		ArrayList<StationInfo> list = c.radiusCalc(lat, lon, rad);
		StringBuilder sb = new StringBuilder();
		if (list.size() == 0) {
			sb.append("<p>No station available from you location within the defined radius</p>");			
		}
		else {
			sb.append("<h2 style=\"color:white;font-family:'Times New Roman';\"><b>Listing of the " + list.size() + " stations within " +
					rad + " m from your location:</b></h2>");
			list.forEach(v -> sb.append("<a onclick=\"getStationDetailsID("+ v.getId() + ")\">" +
					"<div class=\"row rowStation\" >" +
					"<div class=\"col-md-2\"><h1><b>" + v.getId() + "</b></h1></div>" +
					"<div class=\"col-md-10\">" +
					"<h3>" + v.getName() + "</h3>" +
					"<p>Number of docks in total: " + v.getCapacity() + "</p>" +
					"</div></div><a>"
					));
		}
		sb.append("<button class=\"btn btn-primary\" onclick=\"clearRightBody()\">Clear</button>");
		return sb.toString();
	}

	// REST Resources for Phase 1 of the project
	@GET
	@Path("/StationInfo")
	@Produces(MediaType.TEXT_HTML)
	public String getStationInfoHtml() {
		Controller c = new Controller();
		HashMap<Integer, StationInfo> m = c.populateStationInfo(); 
		StringBuilder sb = new StringBuilder();
		sb.append("<html lang=\"en\"><head><meta charset=\"UTF-8\" /><title>Web Services Project</title></head><body>"
				+ "<h2>Data obtained from Bixi Montreal open data access.</h2>"
				+ "<p>Check what Bixi does at "
				+ "<a href=\"http://montreal.bixi.com\">http://montreal.bixi.com</a></p>"
				+ "<h1>Listing of the " + m.size() + " stations on the network:</h1>");
		sb.append("<table border=\"1\"><tr><th>Station ID</th>" + 
				"<th>Station Name</th></th><th>Latitude</th><th>Longitude</th>" + 
				"<th>Total Docks</th></tr>");
		m.forEach((k, v) -> sb.append("<tr><td><a href=\"http://localhost:8080/WebBixiRESTProject/rest/WebBixi/SationDetails/" + v.getId() + "\">" + 
				v.getId() + "</a></td><td>" + 
				v.getName() + "</td><td>" + String.format("%.6f", v.getLat()) +  
				"</td><td>" + String.format("%.6f", v.getLon()) + 
				"</td><td>" + v.getCapacity() + "</td></tr>"));
		sb.append("</table></body></html>");
		return sb.toString();
	}
	@GET
	@Path("/SationDetails/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String getStationDetailsHtml(@PathParam("id") int id) {
		Controller c = new Controller();
		HashMap<Integer, StationInfo> m1 = c.populateStationInfo(); 
		HashMap<Integer, StationStatus> m2 = c.populateStationStatus();
		StationInfo s1 = m1.get(id);
		StationStatus s2 = m2.get(id);
		StringBuilder sb = new StringBuilder();
		sb.append("<html lang=\"en\"><head><meta charset=\"UTF-8\" /><title>Web Services Project</title></head><body>"
				+ "<h2>Data obtained from Bixi Montreal open data access.</h2>"
				+ "<p>Check what Bixi does at "
				+ "<a href=\"http://montreal.bixi.com\">http://montreal.bixi.com</a></p>");
		if (s1 == null) {
			sb.append("<p><b>No station identified by the ID: </b>" + id + "</p>");
		}
		else {
			sb.append("<h1>Station Info:</h1>" +
					"<p><b>Station ID: </b>" + id + "</p>" + 
					"<p><b>Station Name: </b>" + s1.getName() + "</p>" +
					"<p><b>Station Capacity (total docks): </b>" + s1.getCapacity() + "</p>");
			if (s2 == null) {
				sb.append("<p><b>No status details available for this station</b></p>");
			}
			else {
				Date t = new Date(s2.getCheck() * 1000);
				sb.append("<h1>Station Status on " + t + ":</h1>" +
						"<p><b>Number of available bikes: </b>" + s2.getaBikes() + "</p>" + 
						"<p><b>Number of available Ebikes: </b>" + s2.getaEbikes() + "</p>" +
						"<p><b>Number of disabled bikes: </b>" + s2.getdBikes() + "</p>" +
						"<p><b>Number of available docks: </b>" + s2.getaDocks() + "</p>" +
						"<p><b>Number of disabled docks: </b>" + s2.getdDocks() + "</p>");
			}
		}
		sb.append("</body></html>");
		return sb.toString();
	}
	@GET
	@Path("/Distance/")
	@Produces(MediaType.TEXT_HTML)
	public String stationToStationDist(@QueryParam("station1") int s1ID,
			@QueryParam("station2") int s2ID) {
		Controller c = new Controller();
		HashMap<Integer, StationInfo> m = c.populateStationInfo(); 
		StationInfo s1 = m.get(s1ID);
		StationInfo s2 = m.get(s2ID);
		StringBuilder sb = new StringBuilder();
		sb.append("<html lang=\"en\"><head><meta charset=\"UTF-8\" /><title>Web Services Project</title></head><body>"
				+ "<h2>Data obtained from Bixi Montreal open data access.</h2>"
				+ "<p>Check what Bixi does at "
				+ "<a href=\"http://montreal.bixi.com\">http://montreal.bixi.com</a></p>");
		if (s1 == null || s2 == null) {
			sb.append("<p><b>Invalid choices of stations: </b>No distance calculated</p>");			
		}
		else {
			double dist = c.distanceCalc(s1.getLat(), s1.getLon(), s2.getLat(), s2.getLon(), 0);
			sb.append("<h1>Distance Estimate: </h1>" +
					"<p><b>From station: </b><a href=\"http://localhost:8080/WebBixiRESTProject/rest/WebBixi/SationDetails/" + s1.getId() + "\">" + s1.getId() + "</a>, " + s1.getName() + "</p>" + 
					"<p><b>To station: </b><a href=\"http://localhost:8080/WebBixiRESTProject/rest/WebBixi/SationDetails/" + s2.getId() + "\">" + s2.getId() + "</a>, " + s2.getName() + "</p>" +
					"<p><b>Distance of: </b>" + String.format("%.0f", dist) + " m or " +
					String.format("%.3f", dist / 1000) + " km</p>");
		}
		sb.append("</body></html>");
		return sb.toString();
	}
	@GET
	@Path("/Radius/")
	@Produces(MediaType.TEXT_HTML)
	public String locationRadius(@QueryParam("lat") double lat,
			@QueryParam("lon") double lon, @QueryParam("rad") double rad) {
		Controller c = new Controller();
		ArrayList<StationInfo> list = c.radiusCalc(lat, lon, rad);
		StringBuilder sb = new StringBuilder();
		sb.append("<html lang=\"en\"><head><meta charset=\"UTF-8\" /><title>Web Services Project</title></head><body>"
				+ "<h2>Data obtained from Bixi Montreal open data access.</h2>"
				+ "<p>Check what Bixi does at "
				+ "<a href=\"http://montreal.bixi.com\">http://montreal.bixi.com</a></p>");
		if (list.size() == 0) {
			sb.append("<p><b>No station available from you location within the defined radius</b></p>");			
		}
		else {
			sb.append("<h1>Listing of the " + list.size() + " stations within " +
					rad + " m from your location:</h1>");
			sb.append("<table border=\"1\"><tr><th>Station ID</th>" + 
					"<th>Station Name</th></th><th>Latitude</th><th>Longitude</th>" + 
					"<th>Total Docks</th></tr>");
			list.forEach(v -> sb.append("<tr><td><a href=\"http://localhost:8080/WebBixiRESTProject/rest/WebBixi/SationDetails/" + v.getId() + "\">" + v.getId() + "</a></td><td>" + 
					v.getName() + "</td><td>" + String.format("%.6f", v.getLat()) +  
					"</td><td>" + String.format("%.6f", v.getLon()) + 
					"</td><td>" + v.getCapacity() + "</td></tr>"));
			sb.append("</table>");
		}
		sb.append("</body></html>");
		return sb.toString();
	}
}
