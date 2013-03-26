package com.android.nitelights.profile;

public class ProfileFactory {

	String firstName;
	String lastName;
	String name;

	public ProfileFactory(String pName){

		name = pName;
	}

	public String getName(){
		return name;
	}
}
