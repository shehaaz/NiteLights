package com.android.nitelights.venues;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nitelights.R;
/**
 * Extends the ArrayAdapter to take in VenueFactory objects (To obtain title and address)
 */
public class VenuesAdapter extends ArrayAdapter<VenuesFactory>{
	
	Context context;
	int layoutResourceId;
	VenuesFactory venueFactory[] = null;
	ImageView icon;
	boolean isDiscoball;
	
	
	
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
			holder.venueRating = (ImageView) row.findViewById(R.id.star_icon);

			
			row.setTag(holder);
		}
		else{
			holder = (VenueHolder)row.getTag();
		}
		//For every venue is the list. set Title, address and rating
		VenuesFactory venue = venueFactory[position];
		holder.venueTitle.setText(venue.getTitle());
		holder.venueAddress.setText(venue.getAddress());
		holder.venueRating.setImageResource(venue.getRating());
		
//		final TextView t = (TextView) row.findViewById(R.id.title_venue);
//		
//		icon = (ImageView) row.findViewById(R.id.listitem_icon);
//		String mDrawableName = "discoball";
//		int resID = context.getResources().getIdentifier(mDrawableName , "drawable", context.getPackageName());
//		isDiscoball = icon.getDrawable().getConstantState().equals(resID);
//		icon.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            	System.out.println(isDiscoball);
//            	if(isDiscoball){
//					icon.setImageResource(R.drawable.discoball_trans);
//					String result = "Committed to "+ (String) t.getText();
//					Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
//				}
//				else{
//					icon.setImageResource(R.drawable.discoball);
//					String result = "Uncommitted from "+(String) t.getText();
//					Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
//				}
//            }
//        });
	
		return row;
	}
	
	static class VenueHolder{
		TextView venueTitle;
		TextView venueAddress;
		ImageView venueRating;
	}
}
