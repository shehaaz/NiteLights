package com.android.nitelights.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nitelights.maps.MapActivity;
import com.android.nitelights.R;

public class MapButtonFragment extends Fragment{

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_map_button,container, false);

		rootView.findViewById(R.id.open_map_button).setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){

				Intent intent = new Intent(getActivity(),MapActivity.class);
				startActivity(intent);
			}
		});

		return rootView;
	}

}
