package com.android.nitelights.profile;

public class ProfileFactory {
	
	String firstName;
	String lastName;
	
	public ProfileFactory(String pfirstName, String plastName){
		
		firstName = pfirstName;
		lastName = plastName;
	}

	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
}
