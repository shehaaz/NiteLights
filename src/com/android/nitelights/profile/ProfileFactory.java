package com.android.nitelights.profile;

public class ProfileFactory {

	String firstName;
	String lastName;
	String name;
	String picture;

	public ProfileFactory(String pName, String pictureUrl){

		name = pName;
		picture = "http://niteflow.com/sites/default/files/pictures/"+pictureUrl;
	}

	public String getName(){
		return name;
	}
	
	public String getProfilePhoto(){
		return picture;
	}
}
