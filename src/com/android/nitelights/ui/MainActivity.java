package com.android.nitelights.ui;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.android.nitelights.R;
import com.android.nitelights.database.JSONParser;
import com.android.nitelights.profile.ProfileFragment;
import com.android.nitelights.venues.VenuesFactory;
import com.android.nitelights.venues.VenuesFragment;
import com.android.nitelights.wire.WireFragment;


/**
 * 
 * @author Shehaaz
 *The starting point of the application. It branches to the other view Fragments in the package
 */

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	AppSectionsPagerAdapter mAppSectionsPagerAdapter;
	JSONParser jParser = new JSONParser();
	public static VenuesFactory venue_data[];
	private ProgressDialog pDialog;
	private String url_all_venues = "http://niteflow.com/AndroidDB/get_all_venues.php";

	//Venue Data
	int venue_id;
	private String title = "";
	private String address = "";
	double venue_lng;
	double venue_lat; 

	/**
	 * ViewPager(Layout manager that allows the user to flip left and right through pages of data)
	 */		 
	ViewPager mViewPager;
	public static String committedVenue;



	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Load All venues in background thread
		new LoadAllVenues().execute(url_all_venues);
		

		//Set up action bar
		final ActionBar actionBar = getActionBar();

		//Specify that the Home/Up button should not be enabled, since there is no hierarchical parent
		actionBar.setHomeButtonEnabled(false);

		//specify that we will be displaying tabs in the action bar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		//Create the adapter that will return a Fragment(A Fragment is a piece of an application's
		//user interface or behavior that can be placed in an Activity) 
		//for each of the four primary sections of the app
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

		//set up the ViewPager, attaching the adapter and setting up a listener for when the user
		//swipes between sections.
		mViewPager = (ViewPager) findViewById(R.id.pager);

		//set a listener that will be invoked whenever the page changes or is incrementally scrolled
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			public void onPageSelected(int position){
				//when swiping between sections, select the corresponding tab
				actionBar.setSelectedNavigationItem(position);
			}
		});
		
		//naming the tabs of the ActionBar
		actionBar.addTab(actionBar.newTab()
				.setText(R.string.title_the_wire)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab()
				.setText(R.string.title_venues)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab()
				.setText(R.string.title_map)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab()
				.setText(R.string.title_profile)
				.setTabListener(this));
	}


	//*********Below are the Action Bar methods*****************//
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		// When the given tab is selected, switch to the corresponding page in the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}



	/**
	 * 
	 * @author Lenovo
	 * Implementation of PagerAdapter(Base Class providing the adapter to populate pages inside
	 * of a ViewPager) that represents each page as a Fragment that is persistently kept in 
	 * the fragment manager as long as the user can return to the page.
	 */
	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

		private static final int NUM_SECTIONS = 4;

		public AppSectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {

			switch(i) {

			case 0:
				Fragment wireFragment = new WireFragment();
				return wireFragment;

			case 1:
				Fragment venueFragment = new VenuesFragment();
				return venueFragment;
			case 2:
				Fragment mapButtonFragment = new MapButtonFragment();
				return mapButtonFragment;
			case 3: 	
				Fragment profileFragment = new ProfileFragment();
				return profileFragment;

			default:
				return null;
			}
		}

		@Override
		public int getCount() {
			return NUM_SECTIONS;
		}

	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllVenues extends AsyncTask<String, String, VenuesFactory[]> {

		VenuesFactory data[];

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Loading...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected VenuesFactory[] doInBackground(String... args) {

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			// getting JSON string from URL
			JSONObject jObject = jParser.makeHttpRequest(args[0], "GET", params);

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

						if(title.equals(committedVenue)){
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
			venue_data = pVenues;
			//After Loading the Venues. Set pager Adapter that will supply views for this pager as needed
			mViewPager.setAdapter(mAppSectionsPagerAdapter);
			// dismiss the dialog after getting all products
			pDialog.dismiss();

		}
	}

	@Override
	public void onSaveInstanceState(Bundle state){
		super.onSaveInstanceState(state);
		committedVenue = VenuesFragment.committedVenue;
	}
}
