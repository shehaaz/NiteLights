package com.android.nitelights.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.nitelights.R;



/**
 * Venue fragment
 */
public class VenuesFragment extends ListFragment{

	static ArrayList<HashMap<String, String>> venueList;


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_venues,container, false);
		return rootView;
	}
	
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		

		VenuesFactory venue_data[] = new VenuesFactory[]{
				new VenuesFactory("Light Ultra Club","2020 Crescent Street, Montreal, QC, Canada"),
				new VenuesFactory("Stereo Night Club","858 Sainte-Catherine St E, Montreal, QC, Canada"),
				new VenuesFactory("Club La Boom Montreal","1254 Rue Stanley, Montreal, QC, Canada"),
				new VenuesFactory("Altitude 737","1 Place Ville Marie, Montreal, Canada"),
				new VenuesFactory("Bar Downtown", "1196 Sainte-Catherine West, Montreal, QC, Canada"),
				new VenuesFactory("Bains Douches","390 Saint-Jacques Old MTL, Montreal, QC, Canada"),
				new VenuesFactory("Radio Lounge","3553 Saint Laurent Boulevard, Montreal, QC, Canada"),
				new VenuesFactory("1234 Club","1234 Rue de la Montagne, Montreal, QC, Canada"),
				new VenuesFactory("Bar Salon Officiel","351 Rue Roy Est, Montreal, QC, Canada"),
				new VenuesFactory("Tokyo Bar","3709 Saint Laurent Boulevard, Montreal, QC, Canada")	
		};
		
		VenuesAdapter adapter = new VenuesAdapter(getActivity(), R.layout.list_item_venues, venue_data);
		    
		setListAdapter(adapter);

	}

}
