package com.android.nitelights.ui;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.nitelights.R;
import com.android.nitelights.venues.VenuesFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * 
 * @author Shehaaz
 *The starting point of the application. It branches to the other view Fragments in the package
 */

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	AppSectionsPagerAdapter mAppSectionsPagerAdapter;



	/**
	 * ViewPager(Layout manager that allows the user to flip left and right through pages of data)
	 */		 
	ViewPager mViewPager;


	
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	
		
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
				.setText(R.string.title_profile)
				.setTabListener(this));
//		actionBar.addTab(actionBar.newTab()
//				.setText(R.string.title_map)
//				.setTabListener(this));
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

			//			case 0:
			//				//This is the Wire

			case 1:
				Fragment venueFragment = new VenuesFragment();
				return venueFragment;
			case 2:
				Fragment profileFragment = new ProfileFragment();
				return profileFragment;

//			case 3: 	
//				Fragment MapFragment = new MapFragments();
//				return MapFragment;

			default:
				//dummy place holders
				Fragment fragment = new DummySectionFragment();
				//Bundle: A mapping from String values to various Parcelable types
				Bundle args = new Bundle();
				//Inserts an int value into the mapping of htis Bundle, replacing any existing value for 
				//the given key
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i+1);
				//Supply the construction arguments for this fragment. Called immediately after constructing
				//the fragment. The arguments supplied here will be retained across fragment "destroy" and "creation"
				fragment.setArguments(args);
				return fragment;	
			}
		}

		@Override
		public int getCount() {
			return NUM_SECTIONS;
		}

	}





	/**
	 * a fragment that launches other parts of the demo application
	 */
	public static class LaunchpadSectionFragment extends Fragment{

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate){
			//Instantiates a layout XML file into its corresponding View objects. 
			//inflate a new view hierarchy from the specified XML node
			View rootView = inflater.inflate(R.layout.fragment_section_launchpad, container, false);

			//demonstration of a collection-browsing activity.
			rootView.findViewById(R.id.demo_collection_button).setOnClickListener(new View.OnClickListener(){
				public void onClick(View view){

					/*What are intents?
					 * An intent can be thought of as the glue between activities.
					 * It is basically a passive data stucture holding an abstract description of an action 
					 * to be performed
					 * Intents are asynchoronous messages which allow Android components to request 
					 * functionality from other components of the Android system. Intents can
					 * be used to signal to the android system that a certain event has occurred.
					 * Other components in Android can register to this event via an intent filter.
					 * 
					 * Intents are sent to the Android system via a method call,
					 * e.g. via the "startActivity()" method you can start activities.
					 * 
					 * In the following code, when the "demo_collection_button"
					 * is clicked, the MainActivity sends an intent to the android
					 * system which starts the "CollectionDemoActivity" class 
					 * 
					 * An Intent can contain data. e.g sending data to browser
					 * 		String url = "http://www.google.com
					 * 		Intent i = new Intent("Intent.ACTION_VIEW);
					 * 		i.SetData(uri.parse(url));
					 * 		startActivity(i);
					 * 
					 *Implicit Intents
					 *	for example the following tells the Android system to view a webpage.
					 *		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
					 *		startActivity(i);
					 * 
					 * Data Transfer
					 * 
					 * An implicit Intent contains the action and optionally additional data. 
					 * The receiving component can get this information via the getAction() and getData()
					 * methods on the Intent object. The Intent object can be retrieved via the 
					 * getIntent() method.
					 * 
					 * 	e.g Transferring from one activity to the next
					 * 		
					 * 	Activity 1		
					 * 		//if it is a fragment use getActivity() instead of "this"
					 * 		Intent intent = new Intent(this,Activity2.class) 
					 * 		intent.putExtra(key, "String");
					 * 
					 * 	Activity 2
					 * 		Intent intent = getIntent();
					 * 		String message = intent.getStringExtra(key);
					 * 
					 * Explicit Intents
					 * 
					 * 	e.g: 
					 * 	Activity 1
					 * 		Intent i = new Intent(this, Activity2.class);
					 * 		i.putExtra("Value1", "This is value one");
					 * 		i.putExtra("Value2", "This is value two");
					 * 
					 * 	Activity 2
					 * 		Bundle extras = getIntent().getExtras();
					 * 		String value1 = extras.getString("Value1");
					 * 		String value2 = extras.getString("Value2");
					 * 
					 * 
					 */

					Intent intent = new Intent(getActivity(),CollectionDemoActivity.class);
					startActivity(intent);
				}
			});

			//demonstration of navigating to external activities.
			rootView.findViewById(R.id.demo_external_activity).setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent externalActivityIntent = new Intent(Intent.ACTION_PICK);
					externalActivityIntent.setType("image/*");
					externalActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					startActivity(externalActivityIntent);
				}
			});
			return rootView;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply displays dummy text
	 */
	public static class DummySectionFragment extends Fragment{

		public static final String ARG_SECTION_NUMBER = "section_number";

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			//Instantiates an XML file into the corresponding View object
			//Inflate a new view hierarchy from the spedified XML node
			View rootView = inflater.inflate(R.layout.fragment_section_dummy,container, false);
			//Return the arguments supplied when the fragment was instantiated, if any.
			Bundle args = getArguments();
			((TextView) rootView.findViewById(android.R.id.text1)).setText(
					getString(R.string.dummy_section_text, args.getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}
	}



}
