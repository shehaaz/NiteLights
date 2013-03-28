package com.android.nitelights.database;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.android.nitelights.R;
import com.android.nitelights.profile.ProfileFactory;
import com.android.nitelights.ui.MainActivity;
import com.android.nitelights.venues.VenuesFactory;
import com.android.nitelights.wire.WireFactory;

/**
 * Background Async Task to Load all product by making HTTP Request
 * */
public class LoadMySQLData extends AsyncTask<Object, String, String> {

	VenuesFactory data[];
	WireFactory wire_data[];
	ProfileFactory user_data;
	
	JSONParser jParser = new JSONParser();

	MainActivity callerActivity;

	//Data
	private String uid;
	private int venue_id;
	private String title;
	private String address;
	private String wire_name;
	private String wire_title;
	private String wire_timestamp;
	private double venue_lng;
	private double venue_lat; 
	private String number_commited; 
	private int rating;
	private String user_name;
	
	//service URLs
	private String serviceVenues;
	private String serviceWire;
	private String serviceUser;

	/**
	 * Before starting background thread Show Progress Dialog
	 * */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	/**
	 * getting All products from url
	 * */
	protected String doInBackground(Object... args) {

		callerActivity = (MainActivity) args[0];
		serviceVenues = (String) args[1];
		serviceWire = (String) args[2];
		uid = (String) args[3];
		serviceUser = (String) args[4];

/**************************************************GET VENUES*****************************************************************************************/
		// Building Parameters
		List<NameValuePair> venue_params = new ArrayList<NameValuePair>();

		// getting JSON string from URL
		JSONObject jObject_Venues = jParser.makeHttpRequest(serviceVenues, "GET", venue_params);

		// Check your log cat for JSON reponse
		Log.d("All Venues: ", jObject_Venues.toString());

		try {
			// Checking for SUCCESS TAG
			int success = jObject_Venues.getInt("success");

			if (success == 1) {
				// products found
				// Getting Array of venues
				JSONArray venues = jObject_Venues.getJSONArray("venues");
				data = new VenuesFactory[venues.length()];

				// looping through All Products
				for (int i = 0; i < venues.length(); i++) {

					venue_id = Integer.parseInt(venues.getJSONObject(i).getString("nid"));
					title = venues.getJSONObject(i).getString("title");
					address = venues.getJSONObject(i).getString("field_address_thoroughfare");
					venue_lng = Double.parseDouble(venues.getJSONObject(i).getString("field_geo_lon"));
					venue_lat = Double.parseDouble(venues.getJSONObject(i).getString("field_geo_lat"));
					number_commited = venues.getJSONObject(i).getString("Number_Commited");
					rating = Integer.parseInt(venues.getJSONObject(i).getString("Rating"));

					data[i] = new VenuesFactory(venue_id,title,address,rating,venue_lat,venue_lng,number_commited,R.drawable.letter_v);

				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
/**************************************************GET WIRE*****************************************************************************************/
		// Building Parameters
		List<NameValuePair> wire_params = new ArrayList<NameValuePair>();
		wire_params.add(new BasicNameValuePair("uid", uid)); //entering as USER_Id = 1

		// getting JSON string from URL
		JSONObject jObject_Wire = jParser.makeHttpRequest(serviceWire, "GET", wire_params);

		// Check your log cat for JSON reponse
		Log.d("All Wire_data: ", jObject_Wire.toString());

		try {
			// Checking for SUCCESS TAG
			int success = jObject_Wire.getInt("success");

			if (success == 1) {
				// products found
				// Getting Array of venues
				JSONArray wire = jObject_Wire.getJSONArray("wire");
				wire_data = new WireFactory[wire.length()];

				// looping through All Products
				for (int i = 0; i < wire.length(); i++) {


					wire_name = wire.getJSONObject(i).getString("name");
					wire_title = wire.getJSONObject(i).getString("title");
					wire_timestamp = wire.getJSONObject(i).getString("timestamp");
					wire_data[i] = new WireFactory(wire_name,wire_title,wire_timestamp);
				}
			}
			else{
				wire_data = new WireFactory[] {new WireFactory("No One","   Any Venues",null)};
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

/**************************************************GET USER*****************************************************************************************/
		// Building Parameters
		List<NameValuePair> user_params = new ArrayList<NameValuePair>();
		user_params.add(new BasicNameValuePair("uid", uid)); //entering as USER_Id = 1

		// getting JSON string from URL
		JSONObject jObject_User = jParser.makeHttpRequest(serviceUser, "GET", user_params);

		// Check your log cat for JSON reponse
		Log.d("All User_data: ", jObject_User.toString());

		try {
			// Checking for SUCCESS TAG
			int success = jObject_User.getInt("success");

			if (success == 1) {
				// products found
				// Getting Array of venues
				JSONArray user = jObject_User.getJSONArray("user");
				

				// looping through All Products
				for (int i = 0; i < user.length(); i++) {
					user_name = user.getJSONObject(i).getString("name");
					user_data = new ProfileFactory(user_name);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * After completing background task Dismiss the progress dialog
	 * **/
	protected void onPostExecute(String result) {
		callerActivity.setVenues(data);
		callerActivity.setWire(wire_data);
		callerActivity.setUser(user_data);
		callerActivity.setAdapter();
	}
}
