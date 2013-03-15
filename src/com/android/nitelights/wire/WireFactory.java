package com.android.nitelights.wire;

import com.android.nitelights.profile.ProfileFactory;
import com.android.nitelights.venues.VenuesFactory;

public class WireFactory {
	
	private String firstName;
	private String lastName;
	private String venueTitle;
	private int venueLogo;
	
	public WireFactory(ProfileFactory person, VenuesFactory venue){
		
		firstName = person.getFirstName();
		lastName = person.getLastName();
		venueTitle = venue.getTitle();
		venueLogo = venue.getLogo();
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getVenueTitle(){
		return venueTitle;
	}
	
	public int getVenueLogo(){
		return venueLogo;
	}
	
	public String toString(){
		return firstName+lastName+" committed to "+venueTitle;
	}

}
