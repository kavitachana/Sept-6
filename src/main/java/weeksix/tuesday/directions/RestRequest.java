package weeksix.tuesday.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 
 * Sample class that makes a simple request to Google Maps Directions
 * 
 * @author Kavita Somavarapu
 *
 */

public class RestRequest {

	/**
	 * The URL of the API we want to connect to
	 */
	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";

	/**
	 * The character se to use when encoding URL parameters
	 */
	protected static String charset = "UTF-8";

	/**
	 * API key used for making requests to API
	 */
	protected static String key = "AIzaSyAI-b0OwKFzq2tHeLht0JiYzgN2kF6k_l8";

	public static void main(String[] args) {

		try {

			// The origin or starting point for directions
			String origin = "Laurel MD";

			// The destination or end point for directions
			String destination = "Ocean City MD";
			
			//Stops along the way
			String waypoints = "optimize: true|Ikea College Park MD|Columbia Mall Columbia MD|Prime Outlets";
			
			//change units to metric
			String units = "metric";

			// The return type of the response: xml || json
			String returnType = "json";

			// Creates the url parameters as a string encoding them with the
			// defined charset
			String queryString = String.format("origin=%s&destination=%s&key=%s&waypoints=%s&units=%s", URLEncoder.encode(origin, charset),
					URLEncoder.encode(destination, charset), URLEncoder.encode(key, charset), URLEncoder.encode(waypoints, charset), URLEncoder.encode(units, charset));

			// Creates a new URL out of the endpoint, returnType, and
			// queryString
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");

			// If we did not get a 200 (success), throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			// Read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			// Loop of buffer line by line until it returns null meaning there
			// are no more lines
			while (br.readLine() != null) {
				// print out each line to the screen
				System.out.println(br.readLine());
			}

			// Close connection to API

			connection.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
