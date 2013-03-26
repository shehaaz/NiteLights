package com.android.nitelights.venues;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nitelights.R;
import com.android.nitelights.ui.MainActivity;
import com.android.nitelights.maps.MapVenueActivity;

public class VenueActivity extends Activity {
	
	VenuesFactory venue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue);

		final ActionBar actionBar = getActionBar();

		//show the home button
		actionBar.setDisplayHomeAsUpEnabled(true);

		TextView venue_title = (TextView) findViewById(R.id.activity_venue_title);
		TextView venue_address = (TextView) findViewById(R.id.activity_venue_address);
		TextView venue_commits = (TextView) findViewById(R.id.venue_num_commits);
		ImageView venue_rating = (ImageView) findViewById(R.id.venue_activity_rating);

		Intent i = getIntent();
		venue = (VenuesFactory)i.getSerializableExtra("THE_VENUE");


		venue_title.setText(venue.getTitle());
		venue_address.setText(venue.getAddress());
		venue_commits.setText(venue.getNumCommits()+" Attending");
		venue_rating.setImageResource(venue.getRating());
		
		ListenToButtons();

	}

	public void ListenToButtons() {
 
		ImageButton commitButton = (ImageButton) findViewById(R.id.venue_page_commit_button);
 
		commitButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
			   Toast.makeText(VenueActivity.this,"commit clicked!", Toast.LENGTH_SHORT).show();
 
			}
 
		});
		
		ImageButton mapButton = (ImageButton) findViewById(R.id.venue_page_open_map_button);
		 
		mapButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
			   Intent i = new Intent(VenueActivity.this, MapVenueActivity.class);
			   i.putExtra("VENUE_LOCATION",venue);
			   startActivity(i);
			}
		});
	}
 


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_venue, menu);
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
