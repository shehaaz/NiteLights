package com.android.nitelights.venues;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nitelights.R;
import com.android.nitelights.maps.MapVenueActivity;



/**
 * Venue fragment
 */
public class VenuesFragment extends ListFragment{

	//	static ArrayList<HashMap<String, String>> venueList;
	private TextView t;
	VenuesFactory venue_data[];
	private VenuesAdapter adapter;
	public static boolean changeCommit = true;
	public static String committedVenue;
	


	public VenuesFragment(){
		System.out.println("hello");
	}

	public VenuesFragment(VenuesFactory[] venue_data) {
		this.venue_data = venue_data;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_venues,container, false);
		return rootView;
	}

	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		adapter =  new VenuesAdapter(getActivity(), R.layout.list_item_venues, this.venue_data);
		setListAdapter(adapter);
		setRetainInstance(true);
	}



	//Here when the venue item is clicked open the map at the location of the venue
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
	
		
		final Intent i = new Intent(getActivity(), MapVenueActivity.class);
		LinearLayout parent = (LinearLayout) v;
		


		t = (TextView) parent.findViewById(R.id.title_venue);
		String venueName = (String) t.getText();



		for(VenuesFactory vf : venue_data){
			if(vf.getTitle()==venueName){
				i.putExtra("VENUE_LOCATION",vf);
			}
		}

		TextView t = (TextView) parent.findViewById(R.id.title_venue);
		ImageView logo = (ImageView) parent.findViewById(R.id.listitem_venue_icon);
		
		if(changeCommit){
			venue_data[position].setLogo(R.drawable.committed_check);
			logo.setImageResource(R.drawable.committed_check);
			String result = "Committed to "+(String) t.getText();
			Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
			changeCommit = false;
			committedVenue = (String) t.getText();
			
//			new AlertDialog.Builder(getActivity())
//		    .setTitle("Open Map")
//		    .setMessage("Do you want to view this Venue in the Map?")
//		    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//		        public void onClick(DialogInterface dialog, int which) { 
//		        	startActivity(i);
//		        }
//		     })
//		    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//		        public void onClick(DialogInterface dialog, int which) { 
//		            // do nothing
//		        }
//		     })
//		     .show();
		}
		else{
			if((boolean) t.getText().equals(committedVenue)){
			venue_data[position].setLogo(R.drawable.letter_v);	
			logo.setImageResource(R.drawable.letter_v);	
			String result = "Uncommitted from "+(String) t.getText();
			Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
			changeCommit = true;
			}
			else{
				String result = "You Cannot Commit to two Venues";
				Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
			}
		}

	}	
}
