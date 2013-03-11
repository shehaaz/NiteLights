package com.android.nitelights.ui;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Creates new Venue objects
 */
public class VenuesFactory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5918501069145727448L;
	/**
	 * 
	 */
	
	private String title;
	private String address;
	private int rating;
	private double lat;
	private double lng;
	
	public VenuesFactory(String pTitle, String pAddress, int pRating, double plat, double pLng){
		super();
		
		title = pTitle;
		address = pAddress;
		rating = pRating;
		lat = plat;
		lng = pLng;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getAddress(){
		return address;
	}
	
	public int getRating(){
		return rating;
	}
	
	public void setRating(int pRating){
		rating = pRating;
	}
	
	public double getLat(){
		return lat;
	}
	
	public double getLng(){
		return lng;
	}
}
