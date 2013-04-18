package com.android.nitelights.profile;

public class ProfileFactory {

	String firstName;
	String lastName;
	String name;
	String picture;
	String picture_id;

	public ProfileFactory(String pName, String pictureUrl, String photoID){

		name = pName;
		picture = "http://niteflow.com/sites/default/files/pictures/"+pictureUrl;
		picture_id = photoID;
	}

	public String getName(){
		return name;
	}
	
	public String getProfilePhoto(){
		System.out.println(picture);
		return picture;
	}
	
	public String getPhotoID(){
		return picture_id;
	}
}
