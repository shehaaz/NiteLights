package com.android.nitelights.venues;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nitelights.R;
import com.android.nitelights.maps.MapActivity;
import com.google.android.gms.maps.model.LatLng;



/**
 * Venue fragment
 */
public class VenuesFragment extends ListFragment{

//	static ArrayList<HashMap<String, String>> venueList;
	
	private ImageView icon;
	private boolean isDiscoball;
	private TextView t;

	VenuesFactory venue_data[] = new VenuesFactory[]{
			new VenuesFactory("Light Ultra Club","2020 Crescent Street, Montreal, QC, Canada", R.drawable.one_star,45.498231,-73.577613),
			new VenuesFactory("Stereo Night Club","858 Sainte-Catherine St E, Montreal, QC, Canada", R.drawable.two_star, 45.516058,-73.558202),
			new VenuesFactory("Club La Boom Montreal","1254 Rue Stanley, Montreal, QC, Canada", R.drawable.three_half_star, 45.498988,-73.57308),
			new VenuesFactory("Altitude 737","1 Place Ville Marie, Montreal, Canada", R.drawable.five_star, 45.50173,-73.567814),
			new VenuesFactory("Bar Downtown", "1196 Sainte-Catherine West, Montreal, QC, Canada", R.drawable.four_star, 45.4988,-73.573986),
			new VenuesFactory("Bains Douches","390 Saint-Jacques Old MTL, Montreal, QC, Canada", R.drawable.four_half_star, 45.501839,-73.559669),
			new VenuesFactory("Radio Lounge","3553 Saint Laurent Boulevard, Montreal, QC, Canada", R.drawable.one_star, 45.51372,-73.571898),
			new VenuesFactory("1234 Club","1234 Rue de la Montagne, Montreal, QC, Canada", R.drawable.one_half_star, 45.497574,-73.57461),
			new VenuesFactory("Bar Salon Officiel","351 Rue Roy Est, Montreal, QC, Canada", R.drawable.five_star, 45.518816,-73.573006),
			new VenuesFactory("Tokyo Bar","3709 Saint Laurent Boulevard, Montreal, QC, Canada", R.drawable.three_star, 45.514936,-73.574459)	
	};


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_venues,container, false);

		return rootView;
	}
	
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		
		VenuesAdapter adapter = new VenuesAdapter(getActivity(), R.layout.list_item_venues, venue_data);
		    
		setListAdapter(adapter);
			
	}
	
	
	
//Here when the venue item is clicked open the map at the location of the venue
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		
		Intent i = new Intent(getActivity(), MapActivity.class);
		LinearLayout parent = (LinearLayout) v;
		
		t = (TextView) parent.findViewById(R.id.title_venue);
		String venueName = (String) t.getText();
		
		icon = (ImageView) parent.findViewById(R.id.listitem_icon);
		
		for(VenuesFactory vf : venue_data){
			if(vf.getTitle()==venueName){
				i.putExtra("VENUE_LOCATION",vf);
			}
		}
		
		boolean isDiscoball = icon.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.discoball).getConstantState());
		TextView t = (TextView) parent.findViewById(R.id.title_venue);
		if(isDiscoball){
			icon.setImageResource(R.drawable.discoball_trans);
			String result = "Committed to "+(String) t.getText();
			Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
			startActivity(i);
		}
		else{
			icon.setImageResource(R.drawable.discoball);
			String result = "Uncommitted from "+(String) t.getText();
			Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
		}
		
		
		
		


	}
	
}
