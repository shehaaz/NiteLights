package com.android.nitelights.ui;

import com.android.nitelights.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
/**
 * Extends the ArrayAdapter to take in VenueFactory objects (To obtain title and address)
 */
public class VenuesAdapter extends ArrayAdapter<VenuesFactory>{
	
	Context context;
	int layoutResourceId;
	VenuesFactory venueFactory[] = null;
	
	public VenuesAdapter(Context context, int layoutResourceId, VenuesFactory[] venueFactory){
		super(context, layoutResourceId, venueFactory);
		
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.venueFactory = venueFactory;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View row = convertView;
		VenueHolder holder = null;
		
		if(row == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new VenueHolder();
			holder.venueTitle = (TextView) row.findViewById(R.id.title_venue);
			holder.venueAddress = (TextView) row.findViewById(R.id.title_venue_address);
			
			row.setTag(holder);
		}
		else{
			holder = (VenueHolder)row.getTag();
		}
		
		VenuesFactory venue = venueFactory[position];
		holder.venueTitle.setText(venue.getTitle());
		holder.venueAddress.setText(venue.getAddress());
		
		return row;
	}
	
	static class VenueHolder{
		TextView venueTitle;
		TextView venueAddress;
	}
}
