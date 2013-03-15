package com.android.nitelights.venues;

import java.io.Serializable;

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
	private int logo;
	
	public VenuesFactory(String pTitle, String pAddress, int pRating, double plat, double pLng, int pLogo){
		super();
		
		title = pTitle;
		address = pAddress;
		rating = pRating;
		lat = plat;
		lng = pLng;
		logo = pLogo;
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
	
	public int getLogo(){
		return logo;
	}
}
