package com.android.nitelights.wire;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.nitelights.R;



public class WireAdapter extends ArrayAdapter<WireFactory> {

	Context context;
	int layoutResourceId;
	WireFactory wireFactory[] = null;

	public WireAdapter(Context context, int layoutResourceId, WireFactory[] wireFactory){
		super(context, layoutResourceId, wireFactory);

		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.wireFactory = wireFactory;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View row = convertView;
		WireHolder holder = null;

		if(row == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new WireHolder();
			holder.venueTitle = (TextView) row.findViewById(R.id.venue_wire_title);
			holder.personName = (TextView) row.findViewById(R.id.person_wire_name);
			holder.venueLogo = (ImageView) row.findViewById(R.id.listitem_wire_icon);

			row.setTag(holder);
		}
		else{
			holder = (WireHolder)row.getTag();
		}
		//For every item in the list. set Title, address and rating
		WireFactory wire = wireFactory[position];
		holder.personName.setText(wire.getFirstName()+" "+wire.getLastName()+" ");
		holder.venueTitle.setText(wire.getVenueTitle());
		holder.venueLogo.setImageResource(wire.getVenueLogo());

		return row;
	}

	static class WireHolder{
		TextView venueTitle;
		TextView personName;
		ImageView venueLogo;
	}
}
