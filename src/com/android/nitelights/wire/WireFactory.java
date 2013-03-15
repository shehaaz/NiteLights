package com.android.nitelights.wire;

import com.android.nitelights.profile.ProfileFactory;
import com.android.nitelights.venues.VenuesFactory;

public class WireFactory {
	
	String firstName;
	String lastName;
	String venueTitle;
	
	public WireFactory(ProfileFactory person, VenuesFactory venue){
		
		firstName = person.getFirstName();
		lastName = person.getLastName();
		venueTitle = venue.getTitle();
	}
	
	public String getFirstName (){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getVenueTitle(){
		return venueTitle;
	}
	
	public String toString(){
		
		return firstName+lastName+" committed to "+venueTitle;
		
	}

}
