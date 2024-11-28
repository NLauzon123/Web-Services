package webBixiREST;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;

import org.glassfish.jersey.client.ClientConfig;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

@Path("BixiJsonTest")
public class BixiJSONResources {
	@GET
	@Path("/StationInfoTest")
	@Produces(MediaType.TEXT_PLAIN)
	public String stationInfoJsonTest() {
		String uri = "http://gbfs.velobixi.com/gbfs/fr/station_information.json";
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(uri);
		String response = target.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		JsonParser parser = Json.createParser(new StringReader(response));
		String key = "";
		int count = 0;
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> lat = new ArrayList<String>();
		ArrayList<String> lon = new ArrayList<String>();
		ArrayList<String> cap = new ArrayList<String>();
		while (parser.hasNext()) {
			   JsonParser.Event event = parser.next();
			   switch(event) {
			      case START_ARRAY: break;
			      case END_ARRAY: break;
			      case START_OBJECT: break;
			      case END_OBJECT: break;
			      case VALUE_FALSE: break;
			      case VALUE_NULL: break;
			      case VALUE_TRUE: break;
			      case KEY_NAME:
			         key = parser.getString();
			         break;
			      case VALUE_STRING:
			    	 if (key.equals("station_id")) {id.add(parser.getString()); count = 0;}
			    	 else if (key.equals("name") && count == 0) {name.add(parser.getString()); count++;}
			    	 else if (key.equals("lat")) lat.add(parser.getString());
			    	 else if (key.equals("lon")) lon.add(parser.getString());
			    	 else if (key.equals("capacity")) cap.add(parser.getString());
			         break;
			      case VALUE_NUMBER:
			    	 if (key.equals("station_id")) {id.add(parser.getString()); count = 0;}
			    	 else if (key.equals("name") && count == 0) {name.add(parser.getString()); count++;}
			    	 else if (key.equals("lat")) lat.add(parser.getString());
			    	 else if (key.equals("lon")) lon.add(parser.getString());
			    	 else if (key.equals("capacity")) cap.add(parser.getString());
			         break;
			   }
			}
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=\"1\"><tr><th>Station ID</th>" + 
				"<th>Station Name</th></th><th>Latitude</th><th>Longitude</th>" + 
				"<th>Total Docks</th></tr>");
		for (int i = 0; i < id.size(); i++)
			sb.append("<tr><td>" + id.get(i) + "</td><td>" + name.get(i) + "</td><td>" +
					lat.get(i) + "</td><td>" + lon.get(i) + "</td><td>" + cap.get(i) + 
					"</td></tr>");
		sb.append("</table>");
		return sb.toString();
	}
	@GET
	@Path("/StationStatusTest")
	@Produces(MediaType.TEXT_PLAIN)
	public String stationStatusJsonTest() {
		String uri = "http://gbfs.velobixi.com/gbfs/en/station_status.json";
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(uri);
		String response = target.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		JsonParser parser = Json.createParser(new StringReader(response));
		String key = "";
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<String> aBikes = new ArrayList<String>();
		ArrayList<String> aEbikes = new ArrayList<String>();
		ArrayList<String> dBikes = new ArrayList<String>();
		ArrayList<String> aDocks = new ArrayList<String>();
		ArrayList<String> dDocks = new ArrayList<String>();
		ArrayList<String> check = new ArrayList<String>();
		while (parser.hasNext()) {
			   JsonParser.Event event = parser.next();
			   switch(event) {
			      case START_ARRAY: break;
			      case END_ARRAY: break;
			      case START_OBJECT: break;
			      case END_OBJECT: break;
			      case VALUE_FALSE: break;
			      case VALUE_NULL: break;
			      case VALUE_TRUE: break;
			      case KEY_NAME:
			         key = parser.getString();
			         break;
			      case VALUE_STRING:
			    	 if (key.equals("station_id")) id.add(parser.getString());
			    	 else if (key.equals("num_bikes_available")) aBikes.add(parser.getString());
			    	 else if (key.equals("num_ebikes_available")) aEbikes.add(parser.getString());
			    	 else if (key.equals("num_bikes_disabled")) dBikes.add(parser.getString());
			    	 else if (key.equals("num_docks_available")) aDocks.add(parser.getString());
			    	 else if (key.equals("num_docks_disabled")) dDocks.add(parser.getString());
			    	 else if (key.equals("last_reported")) check.add(parser.getString());
			         break;
			      case VALUE_NUMBER:
			    	 if (key.equals("station_id")) id.add(parser.getString());
			    	 else if (key.equals("num_bikes_available")) aBikes.add(parser.getString());
			    	 else if (key.equals("num_ebikes_available")) aEbikes.add(parser.getString());
			    	 else if (key.equals("num_bikes_disabled")) dBikes.add(parser.getString());
			    	 else if (key.equals("num_docks_available")) aDocks.add(parser.getString());
			    	 else if (key.equals("num_docks_disabled")) dDocks.add(parser.getString());
			    	 else if (key.equals("last_reported")) check.add(parser.getString());
			         break;
			   }
			}
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=\"1\"><tr><th>Station ID</th>" + 
				"<th>Available bikes</th></th><th>Available Ebikes</th><th>Disabled bikes</th>" + 
				"<th>Available docks</th><th>Disabled docks</th><th>Last checked</th></tr>");
		for (int i = 0; i < id.size(); i++) {
			Date t = new Date(Long.parseLong(check.get(i)) * 1000);
			sb.append("<tr><td>" + id.get(i) + "</td><td>" + aBikes.get(i) + "</td><td>" + aEbikes.get(i) + "</td><td>" +
					dBikes.get(i) + "</td><td>" + aDocks.get(i) + "</td><td>" + dDocks.get(i) + 
					"</td><td>" + t + "</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

}
