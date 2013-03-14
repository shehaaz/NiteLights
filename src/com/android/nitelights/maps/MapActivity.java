package com.android.nitelights.maps;

import com.android.nitelights.R;
import com.android.nitelights.R.layout;
import com.android.nitelights.R.menu;
import com.android.nitelights.ui.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MapActivity extends Activity {

	private GoogleMap map;
	final int RQS_GooglePlayServices = 1;
	
	  static final LatLng LightUltraClub = new LatLng(45.498231,-73.577613);
	  static final LatLng StereoNightClub = new LatLng(45.516058,-73.558202);
	  static final LatLng ClubLaBoomMontreal = new LatLng(45.498988,-73.57308);
	  static final LatLng Altitude737 = new LatLng(45.50173,-73.567814);
	  static final LatLng BarDowntown = new LatLng(45.4988,-73.573986);
	  static final LatLng BainsDouches = new LatLng(45.501839,-73.559669);
	  static final LatLng RadioLounge = new LatLng(45.51372,-73.571898);
	  static final LatLng Club1234 = new LatLng(45.497574,-73.57461);
	  static final LatLng BarSalonOfficiel = new LatLng(45.518816,-73.573006);
	  static final LatLng TokyoBar = new LatLng(45.514936,-73.574459);
	  
	  static final LatLng McGill = new LatLng(45.503107,-73.576201);
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_full);
		
		final ActionBar actionBar = getActionBar();
		
		//show the home button
		actionBar.setDisplayHomeAsUpEnabled(true);
		
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
		        .getMap();
	    
		map.setMyLocationEnabled(true);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	    map.getUiSettings().setZoomControlsEnabled(true);
	    map.getUiSettings().setCompassEnabled(true);
	    
		map.addMarker(new MarkerOptions().position(LightUltraClub)
		        .title("Light Ultra Club")
		        .snippet("Light Ultra Club is cool")
		        .icon(BitmapDescriptorFactory
		        		.fromResource(R.drawable.map_marker)));
		    
		    map.addMarker(new MarkerOptions().position(StereoNightClub)
		            .title("Stereo Night Club")
		            .snippet("Stereo Night Club is cool")
		            .icon(BitmapDescriptorFactory
		            		.fromResource(R.drawable.map_marker)));
		    
		    map.addMarker(new MarkerOptions().position(ClubLaBoomMontreal)
		            .title("Club La Boom Montreal")
		            .snippet("Club La Boom Montreal is cool")
		            .icon(BitmapDescriptorFactory
		            		.fromResource(R.drawable.map_marker)));
		    
		    map.addMarker(new MarkerOptions().position(Altitude737)
		            .title("Altitude 737")
		            .snippet("Altitude 737 is cool")
		            .icon(BitmapDescriptorFactory
		            		.fromResource(R.drawable.map_marker)));
		    
		    map.addMarker(new MarkerOptions().position(BarDowntown)
		            .title("Bar Downtown")
		            .snippet("Bar Downtown is cool")
		            .icon(BitmapDescriptorFactory
		            		.fromResource(R.drawable.map_marker)));
		    
		    map.addMarker(new MarkerOptions().position(BainsDouches)
		            .title("Bains Douches")
		            .snippet("Bains Douches is cool")
		            .icon(BitmapDescriptorFactory
		            		.fromResource(R.drawable.map_marker)));
		    
		    map.addMarker(new MarkerOptions().position(RadioLounge)
		            .title("Radio Lounge")
		            .snippet("Radio Lounge is cool")
		            .icon(BitmapDescriptorFactory
		            		.fromResource(R.drawable.map_marker)));
		    
		    map.addMarker(new MarkerOptions().position(Club1234)
		            .title("Club 1234")
		            .snippet("Club 1234 is cool")
		            .icon(BitmapDescriptorFactory
		            		.fromResource(R.drawable.map_marker)));
		    
		    map.addMarker(new MarkerOptions().position(BarSalonOfficiel)
		            .title("Bar Salon Officiel")
		            .snippet("Bar Salon Officiel is cool")
		            .icon(BitmapDescriptorFactory
		            		.fromResource(R.drawable.map_marker)));
		    
		    map.addMarker(new MarkerOptions().position(TokyoBar)
		            .title("Tokyo Bar")
		            .snippet("Tokyo Bar is cool")
		            .icon(BitmapDescriptorFactory
		            		.fromResource(R.drawable.map_marker)));


		    // Move the camera instantly to mcgill with a zoom of 15.
		    map.moveCamera(CameraUpdateFactory.newLatLngZoom(McGill, 15));

		    // Zoom in, animating the camera.
		    map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_map_full, menu);
		return true;
	}
	
	//This hook is called whenever an item in your opetions menu is selected
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
		case android.R.id.home:
				Intent upIntent = new Intent(this, MainActivity.class);
				startActivity(upIntent);
				break;
		}
		return true;
	}

}
