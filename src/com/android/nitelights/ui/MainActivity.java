package com.android.nitelights.ui;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.nitelights.R;
import com.android.nitelights.profile.ProfileFragment;
import com.android.nitelights.venues.VenuesAdapter;
import com.android.nitelights.venues.VenuesFactory;
import com.android.nitelights.venues.VenuesFragment;
import com.android.nitelights.wire.WireFragment;
import com.android.nitelights.maps.MapActivity;

/**
 * 
 * @author Shehaaz
 *The starting point of the application. It branches to the other view Fragments in the package
 */

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	AppSectionsPagerAdapter mAppSectionsPagerAdapter;

	public static VenuesFactory venue_data[];

	final static int VenueJson = R.raw.venues;
	private String jsonString;
	private String title = "";
	private String address = "";
	private Double lat;
	private Double lng;
	private Double rating;
	private JSONObject geometry;
	private JSONObject location;


	/**
	 * ViewPager(Layout manager that allows the user to flip left and right through pages of data)
	 */		 
	ViewPager mViewPager;
	



	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		jsonString = getStringFromResource(VenueJson);

		try {
			venue_data = createVenues(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		//Create the adapter that will return a Fragment(A Fragment is a piece of an application's
		//user interface or behavior that can be placed in an Activity) 
		//for each of the three primary sections of the app
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

		//Set up action bar
		final ActionBar actionBar = getActionBar();

		//Specify that the Home/Up button should not be enabled, since there is no hierarchical parent
		actionBar.setHomeButtonEnabled(false);

		//specify that we will be displaying tabs in the action bar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		//set up the ViewPager, attaching the adapter and setting up a listener for when the user
		//swipes between sections.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		//set Adapter that will supply views for this pager as needed
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		//set a listener that will be invoked whenever the page changes or is incrementally scrolled
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			public void onPageSelected(int position){
				//when swiping between sections, select the corresponding tab
				actionBar.setSelectedNavigationItem(position);
			}
		});

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
				Fragment wireFragment = new WireFragment(venue_data);
				return wireFragment;

			case 1:
				Fragment venueFragment = new VenuesFragment(venue_data);
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

	//Get Data From Text Resource File Contains Json Data.

	public String getStringFromResource(int resource){
		InputStream inputStream = getResources().openRawResource(resource);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int ctr;
		try {
			ctr = inputStream.read();
			while (ctr != -1) {
				byteArrayOutputStream.write(ctr);
				ctr = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream.toString();
	}

	public VenuesFactory[] createVenues(String pJsonString) throws JSONException{
		VenuesFactory data[] = null;
		try {            
			// Parse the data into jsonobject to get original data in form of json.  
			JSONObject jObject = new JSONObject(pJsonString);
			//result is taken as an array because we need to loop through it
			JSONArray result = jObject.getJSONArray("results");
			data = new VenuesFactory[result.length()];


			for (int i = 0; i < result.length(); i++) {

				title = result.getJSONObject(i).getString("name");
				address = result.getJSONObject(i).getString("formatted_address");
				rating = result.getJSONObject(i).getDouble("rating");

				//				geometry and location are JSONObjects themselves within the result JSONArray                 
				//                "geometry": {
				//                    "location": {
				//                        "lat": 45.516058,
				//                        "lng": -73.558202
				//                    }
				//                }
				geometry = result.getJSONObject(i).getJSONObject("geometry");
				location = geometry.getJSONObject("location");
				lat = location.getDouble("lat");
				lng = location.getDouble("lng");

				data[i] = new VenuesFactory(title,address,R.drawable.five_star,lat,lng,R.drawable.letter_v);

				Log.v("Venue Name", title);
				Log.v("Venue address", address);
				Log.v("Venue lat", lat.toString());
				Log.v("Venue lng", lng.toString());
				Log.v("Venue rating", rating.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
