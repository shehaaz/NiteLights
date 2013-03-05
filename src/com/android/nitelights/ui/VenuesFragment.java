package com.android.nitelights.ui;

import java.util.ArrayList;
import java.util.HashMap;


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
				new VenuesFactory("Light Ultra Club","2020 Crescent Street, Montreal, QC, Canada", R.drawable.one_star),
				new VenuesFactory("Stereo Night Club","858 Sainte-Catherine St E, Montreal, QC, Canada", R.drawable.two_star),
				new VenuesFactory("Club La Boom Montreal","1254 Rue Stanley, Montreal, QC, Canada", R.drawable.three_half_star),
				new VenuesFactory("Altitude 737","1 Place Ville Marie, Montreal, Canada", R.drawable.five_star),
				new VenuesFactory("Bar Downtown", "1196 Sainte-Catherine West, Montreal, QC, Canada", R.drawable.four_star),
				new VenuesFactory("Bains Douches","390 Saint-Jacques Old MTL, Montreal, QC, Canada", R.drawable.four_half_star),
				new VenuesFactory("Radio Lounge","3553 Saint Laurent Boulevard, Montreal, QC, Canada", R.drawable.one_star),
				new VenuesFactory("1234 Club","1234 Rue de la Montagne, Montreal, QC, Canada", R.drawable.one_half_star),
				new VenuesFactory("Bar Salon Officiel","351 Rue Roy Est, Montreal, QC, Canada", R.drawable.five_star),
				new VenuesFactory("Tokyo Bar","3709 Saint Laurent Boulevard, Montreal, QC, Canada", R.drawable.three_star)	
		};
		
		VenuesAdapter adapter = new VenuesAdapter(getActivity(), R.layout.list_item_venues, venue_data);
		    
		setListAdapter(adapter);
		
	}
	

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		LinearLayout parent = (LinearLayout) v;
		ImageView icon = (ImageView) parent.findViewById(R.id.listitem_icon);
		
		boolean isDiscoball = icon.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.discoball).getConstantState());
		TextView t = (TextView) parent.findViewById(R.id.title_venue);
		if(isDiscoball){
			icon.setImageResource(R.drawable.discoball_trans);
			String result = "Committed to "+(String) t.getText();
			Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
		}
		else{
			icon.setImageResource(R.drawable.discoball);
			String result = "Uncommitted from "+(String) t.getText();
			Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
		}
	}
}
