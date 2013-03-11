package com.android.nitelights.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.android.nitelights.R;
import com.android.nitelights.venues.VenuesFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends Activity {


	 
	  private GoogleMap map;
	  private LatLng venueLocation;
	  double lat;
	  double lng;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_map);
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	        .getMap();
		
		map.setMyLocationEnabled(true);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	    map.getUiSettings().setZoomControlsEnabled(true);
	    map.getUiSettings().setCompassEnabled(true);
	    
	    Intent i = getIntent();
		VenuesFactory venue = (VenuesFactory)i.getSerializableExtra("VENUE_LOCATION");
		lat = venue.getLat();
		lng = venue.getLng();
		venueLocation = new LatLng(lat,lng);
		
		
	    map.addMarker(new MarkerOptions().position(venueLocation)
	            .title(venue.getTitle())
	            .snippet("This is "+venue.getTitle())
	            .icon(BitmapDescriptorFactory
	            		.fromResource(R.drawable.map_marker)));

	    // Move the camera instantly to mcgill with a zoom of 15.
	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(venueLocation, 15));

	    // Zoom in, animating the camera.
	    map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
	  }

	  @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.activity_map, menu);
	    return true;
	  }

}
