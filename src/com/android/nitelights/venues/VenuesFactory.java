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
	
	private int venue_id;
	private String title;
	private String address;
	private int rating;
	private double lat;
	private double lng;
	private int logo;
	
	public VenuesFactory(int pVenue_id,String pTitle, String pAddress, int pRating, double plat, double pLng, int pLogo){
		super();
		
		title = pTitle;
		address = pAddress;
		rating = pRating;
		lat = plat;
		lng = pLng;
		logo = pLogo;
		venue_id = pVenue_id;
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
	
	public void setLogo(int pLogo){
		logo = pLogo;
	}
	
	public int getVenueID(){
		return venue_id;
	}
}
