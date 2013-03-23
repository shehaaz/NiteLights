package com.android.nitelights.database;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.android.nitelights.R;
import com.android.nitelights.ui.MainActivity;
import com.android.nitelights.venues.VenuesFactory;

/**
 * Background Async Task to Load all product by making HTTP Request
 * */
public class LoadMySQLData extends AsyncTask<Object, String, VenuesFactory[]> {

	VenuesFactory data[];
	JSONParser jParser = new JSONParser();
	
	MainActivity callerActivity;
	
	//Venue Data
	int venue_id;
	private String title = "";
	private String address = "";
	double venue_lng;
	double venue_lat; 

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
	protected VenuesFactory[] doInBackground(Object... args) {
		
		callerActivity = (MainActivity) args[0];
		String serviceUrl = (String) args[1];
		
		
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		// getting JSON string from URL
		JSONObject jObject = jParser.makeHttpRequest(serviceUrl, "GET", params);

		// Check your log cat for JSON reponse
		Log.d("All Venues: ", jObject.toString());

		try {
			// Checking for SUCCESS TAG
			int success = jObject.getInt("success");

			if (success == 1) {
				// products found
				// Getting Array of venues
				JSONArray venues = jObject.getJSONArray("venues");
				data = new VenuesFactory[venues.length()];

				// looping through All Products
				for (int i = 0; i < venues.length(); i++) {

					venue_id = Integer.parseInt(venues.getJSONObject(i).getString("nid"));
					title = venues.getJSONObject(i).getString("title");
					address = venues.getJSONObject(i).getString("field_address_thoroughfare");
					venue_lng = Double.parseDouble(venues.getJSONObject(i).getString("field_geo_lon"));
					venue_lat = Double.parseDouble(venues.getJSONObject(i).getString("field_geo_lat"));

					if(title.equals(MainActivity.committedVenue)){
						data[i] = new VenuesFactory(venue_id,title,address,R.drawable.five_star,venue_lat,venue_lng,R.drawable.committed_check);
					}
					else{
						data[i] = new VenuesFactory(venue_id,title,address,R.drawable.five_star,venue_lat,venue_lng,R.drawable.letter_v);
					}

				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * After completing background task Dismiss the progress dialog
	 * **/
	protected void onPostExecute(VenuesFactory[] pVenues) {
		callerActivity.setVenues(pVenues);
	}
}
