package com.android.nitelights.wire;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.nitelights.R;
import com.android.nitelights.profile.ProfileFragment;
import com.android.nitelights.ui.MainActivity;

public class WireFragment extends ListFragment{
	
WireFactory wire_data[];

//	public WireFragment(){
//		wire_data = new WireFactory[]{
//		new WireFactory(ProfileFragment.profile_data[0],MainActivity.venue_data[0]),
//		new WireFactory(ProfileFragment.profile_data[2],MainActivity.venue_data[5]),
//		new WireFactory(ProfileFragment.profile_data[4],MainActivity.venue_data[3]),
//		new WireFactory(ProfileFragment.profile_data[1],MainActivity.venue_data[2]),
//		new WireFactory(ProfileFragment.profile_data[3],MainActivity.venue_data[1]),
//		new WireFactory(ProfileFragment.profile_data[5],MainActivity.venue_data[4]),
//		new WireFactory(ProfileFragment.profile_data[6],MainActivity.venue_data[9]),
//		new WireFactory(ProfileFragment.profile_data[7],MainActivity.venue_data[8]),
//		new WireFactory(ProfileFragment.profile_data[8],MainActivity.venue_data[7]),
//		new WireFactory(ProfileFragment.profile_data[9],MainActivity.venue_data[6])
//	};
//	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_wire,container, false);
					
		return rootView;
	}
	
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
//		
//		WireAdapter adapter = new WireAdapter(getActivity(), R.layout.list_item_wire, wire_data);
//		    
//		setListAdapter(adapter);
			
	}
	

}
