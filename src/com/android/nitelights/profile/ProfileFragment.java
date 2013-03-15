package com.android.nitelights.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nitelights.R;
import com.android.nitelights.venues.VenuesFactory;

/**
 * Profile fragment
 */
public class ProfileFragment extends Fragment{
	
	public static ProfileFactory profile_data[] = new ProfileFactory[]{
		new ProfileFactory("Alexandre", "Flinois"),
		new ProfileFactory("Shehaaz","Saif"),
		new ProfileFactory("Adam", "Jones"),
		new ProfileFactory("John","Smith"),
		new ProfileFactory("Henry", "Fuz"),
		new ProfileFactory("Josh", "Liben"),
		new ProfileFactory("Michael", "Jordan"),
		new ProfileFactory("Bruce", "Lee"),
		new ProfileFactory("Francis", "Underwood"),
		new ProfileFactory("Claire ", "Underwood")
	};
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.profile,container, false);
		return rootView;
	}
}
