package com.android.nitelights.wire;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nitelights.R;
import com.android.nitelights.profile.ProfileFragment;
import com.android.nitelights.venues.VenuesFactory;
import com.android.nitelights.venues.VenuesFragment;

public class WireFragment extends ListFragment{
	
WireFactory wire_data[];

	public WireFragment(){
		System.out.println("hello");
	}
	
	public WireFragment(VenuesFactory[] venue_data) {
		
		wire_data = new WireFactory[]{
		new WireFactory(ProfileFragment.profile_data[0],venue_data[0]),
		new WireFactory(ProfileFragment.profile_data[2],venue_data[5]),
		new WireFactory(ProfileFragment.profile_data[4],venue_data[3]),
		new WireFactory(ProfileFragment.profile_data[1],venue_data[2]),
		new WireFactory(ProfileFragment.profile_data[3],venue_data[1]),
		new WireFactory(ProfileFragment.profile_data[5],venue_data[4]),
		new WireFactory(ProfileFragment.profile_data[6],venue_data[9]),
		new WireFactory(ProfileFragment.profile_data[7],venue_data[8]),
		new WireFactory(ProfileFragment.profile_data[8],venue_data[7]),
		new WireFactory(ProfileFragment.profile_data[9],venue_data[6])
	};
		
	}

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
