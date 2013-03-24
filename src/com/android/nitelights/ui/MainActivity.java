package com.android.nitelights.ui;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.nitelights.R;
import com.android.nitelights.database.LoadMySQLData;
import com.android.nitelights.maps.MapActivity;
import com.android.nitelights.profile.ProfileFragment;
import com.android.nitelights.venues.VenuesFactory;
import com.android.nitelights.venues.VenuesFragment;
import com.android.nitelights.wire.WireFragment;
import com.android.nitelights.wire.WireFactory;

/**
 * 
 * @author Shehaaz
 *The starting point of the application. It branches to the other view Fragments in the package
 */
public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	static AppSectionsPagerAdapter mAppSectionsPagerAdapter;
	public static VenuesFactory venue_data[];
	public static WireFactory wire_data[];
	private String url_all_venues = "http://niteflow.com/AndroidDB/get_all_venues.php";
	private String url_all_wire_data = "http://niteflow.com/AndroidDB/get_user_wire_data.php";
	static ViewPager mViewPager;//ViewPager(Layout manager that allows the user to flip left and right through pages of data)
	public static String committedVenue;
	private ProgressDialog pDialog;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initializeDialog();
		startWebService();

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
				.setText(R.string.title_profile)
				.setTabListener(this));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_map, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		
		case R.id.menu_map:
			Intent i = new Intent(this,MapActivity.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void initializeDialog() {
		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();	
	}

	private void startWebService() {
		//Load All venues in background thread
		new LoadMySQLData().execute(this,url_all_venues,url_all_wire_data);
	}

	public void setVenues(VenuesFactory[] pVenues){
		venue_data = pVenues;

	}
	
	public void setWire(WireFactory[] pWire){
		wire_data = pWire;
	}
	
	public void setAdapter(){
		//After Loading the Venues. Set pager Adapter that will supply views for this pager as needed
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		pDialog.dismiss();
	}

	/**
	 * 
	 * @author Lenovo
	 * Implementation of PagerAdapter(Base Class providing the adapter to populate pages inside
	 * of a ViewPager) that represents each page as a Fragment that is persistently kept in 
	 * the fragment manager as long as the user can return to the page.
	 */
	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

		private static final int NUM_SECTIONS = 3;

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

	@Override
	public void onSaveInstanceState(Bundle state){
		super.onSaveInstanceState(state);
		committedVenue = VenuesFragment.committedVenue;
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
}
