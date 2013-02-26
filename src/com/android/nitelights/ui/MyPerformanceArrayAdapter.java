package com.android.nitelights.ui;

import com.android.nitelights.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPerformanceArrayAdapter extends ArrayAdapter<String> {
	  private final Activity context;
	  private final String[] names;

	  static class ViewHolder {
	    public TextView text;
	    public ImageView image;
	  }

	  public MyPerformanceArrayAdapter(Activity context, String[] names) {
	    super(context, R.layout.list_item_venues, names);
	    this.context = context;
	    this.names = names;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    View rowView = convertView;
	    if (rowView == null) {
	      LayoutInflater inflater = context.getLayoutInflater();
	      rowView = inflater.inflate(R.layout.list_item_venues, null);
	      ViewHolder viewHolder = new ViewHolder();
	      viewHolder.text = (TextView) rowView.findViewById(R.id.title_venue);
	      rowView.setTag(viewHolder);
	    }

	    ViewHolder holder = (ViewHolder) rowView.getTag();
	    String s = names[position];
	    holder.text.setText(s);

	    return rowView;
	  }
	} 
