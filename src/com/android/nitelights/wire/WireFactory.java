package com.android.nitelights.wire;


public class WireFactory {
	
	private String name;
	private String venueTitle;

	
	public WireFactory(String person, String venue){
		
		name = person;
		venueTitle = venue;
	}
	
	public String getName(){
		return name;
	}
	
	public String getVenueTitle(){
		return venueTitle;
	}
	
	public String toString(){
		return name+" committed to "+venueTitle;
	}

}
