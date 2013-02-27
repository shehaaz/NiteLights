package com.android.nitelights.ui;
/**
 * Creates new Venue objects
 */
public class VenuesFactory {
	
	private String title;
	private String address;
	
	public VenuesFactory(String pTitle, String pAddress){
		super();
		
		title = pTitle;
		address = pAddress;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getAddress(){
		return address;
	}
}
