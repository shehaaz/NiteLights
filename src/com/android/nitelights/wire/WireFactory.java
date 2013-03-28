package com.android.nitelights.wire;


public class WireFactory {
	
	private String name;
	private String venueTitle;
	private String wire_timestamp;
	private String wire_friendship;

	
	public WireFactory(String pName, String pVenue, String pTimestamp){
		
		name = pVenue;
		venueTitle = pVenue;
		wire_timestamp = pTimestamp;
	}
	
	public WireFactory(String pFriendship, String pTimestamp){
		
		wire_friendship = pFriendship;
		wire_timestamp = pTimestamp;
	}
	
	public String getName(){
		return name;
	}
	
	public String getVenueTitle(){
		return venueTitle;
	}
	
	public String getTimestamp(){
		return wire_timestamp;
	}
}
