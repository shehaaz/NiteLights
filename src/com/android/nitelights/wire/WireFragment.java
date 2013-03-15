package com.android.nitelights.wire;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nitelights.R;
import com.android.nitelights.profile.ProfileFragment;
import com.android.nitelights.venues.VenuesFragment;

public class WireFragment extends ListFragment{
	
	WireFactory wire_data[] = new WireFactory[]{
		new WireFactory(ProfileFragment.profile_data[0],VenuesFragment.venue_data[0]),
		new WireFactory(ProfileFragment.profile_data[2],VenuesFragment.venue_data[5]),
		new WireFactory(ProfileFragment.profile_data[4],VenuesFragment.venue_data[3]),
		new WireFactory(ProfileFragment.profile_data[1],VenuesFragment.venue_data[2]),
		new WireFactory(ProfileFragment.profile_data[3],VenuesFragment.venue_data[1]),
		new WireFactory(ProfileFragment.profile_data[5],VenuesFragment.venue_data[4]),
	};
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_wire,container, false);
				
		return rootView;
	}
	
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		
		WireAdapter adapter = new WireAdapter(getActivity(), R.layout.list_item_wire, wire_data);
		    
		setListAdapter(adapter);
			
	}
	

}
