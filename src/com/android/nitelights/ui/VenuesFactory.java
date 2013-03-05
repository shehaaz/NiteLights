package com.android.nitelights.ui;


/**
 * Creates new Venue objects
 */
public class VenuesFactory {
	
	private String title;
	private String address;
	private int rating;
	
	public VenuesFactory(String pTitle, String pAddress, int pRating){
		super();
		
		title = pTitle;
		address = pAddress;
		rating = pRating;
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
}
