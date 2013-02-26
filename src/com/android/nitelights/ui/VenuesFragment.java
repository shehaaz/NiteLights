package com.android.nitelights.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.android.nitelights.R;



/**
 * Venue fragment
 */
public class VenuesFragment extends Fragment{

	static ArrayList<HashMap<String, String>> venueList;
	


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_venues,container, false);

		// Hashmap for ListView
		venueList = new ArrayList<HashMap<String, String>>();
		Intent intent = new Intent(getActivity(),VenuesActivity.class);
		startActivity(intent);
		
		return rootView;
	}


	public static class VenuesActivity extends ListActivity {

		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			setContentView(R.layout.list_venues);
			// Loading Venues in Background Thread
			new LoadVenues().execute();
		}
		
		class LoadVenues extends AsyncTask<String, String, String>{

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				return null;
			}

			protected void onPostExecute(String s){

				// updating UI from Background Thread
				runOnUiThread(new Runnable() {
					public void run() {
						/**
						 * Updating parsed JSON data into ListView
						 * */
						ListAdapter adapter = new SimpleAdapter(
								VenuesActivity.this,venueList,
								R.layout.list_item_venues, new String[] {"hello","world"},
								new int[] { R.id.title_venue, R.id.title_venue_address});
						// updating listview
						setListAdapter(adapter);
					}
				});
			}

		}
	}
}
