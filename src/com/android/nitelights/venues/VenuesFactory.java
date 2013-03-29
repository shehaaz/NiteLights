package com.android.nitelights.venues;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.nitelights.R;

/**
 * Creates new Venue objects
 */
public class VenuesFactory implements Parcelable  {
	

	
	private int venue_id;
	private String title;
	private String address;
	private int rating;
	private double lat;
	private double lng;
	private int logo;
	private String num_commits;
	
	public VenuesFactory(int pVenue_id,String pTitle, String pAddress, int pRating, double plat, double pLng, String pNum_commited, int pLogo){
		super();
		
		title = pTitle;
		address = pAddress;
		rating = pRating;
		lat = plat;
		lng = pLng;
		logo = pLogo;
		venue_id = pVenue_id;
		num_commits = pNum_commited;
	}
	
	public VenuesFactory(Parcel source) {
		venue_id = source.readInt();
		title = source.readString();
		address = source.readString();
		rating =  source.readInt();
		lat= source.readDouble();
		lng= source.readDouble();
		logo =  source.readInt();
		num_commits= source.readString();
	}

	public String getTitle(){
		return title;
	}
	
	public String getAddress(){
		return address;
	}
	
	public int getRating(){
		
		
		switch(rating){
		
		case 0:
			return R.drawable.one_star;
		case 10:
			return R.drawable.one_star;
		case 20:
			return R.drawable.one_star;
		case 30: 
			return R.drawable.one_half_star;
		case 40:
			return R.drawable.two_star;
		case 50:
			return R.drawable.two_half_star;
		case 60:
			return  R.drawable.three_star;
		case 70:
			return  R.drawable.three_half_star;
		case 80:
			return R.drawable.four_star;
		case 90:
			return  R.drawable.four_half_star;
		case 100:
			return R.drawable.five_star;
		default: 
			return R.drawable.one_star;
		}
		
		
	}
	
	public void setRating(int pRating){
		rating = pRating;
	}
	
	public double getLat(){
		return lat;
	}
	
	public double getLng(){
		return lng;
	}
	
	public int getLogo(){
		return logo;
	}
	
	public void setLogo(int pLogo){
		logo = pLogo;
	}
	
	public int getVenueID(){
		return venue_id;
	}
	
	public String getNumCommits(){
		return num_commits;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeInt(venue_id);
		dest.writeString(title);
		dest.writeString(address);
		dest.writeInt(rating);
		dest.writeDouble(lat);
		dest.writeDouble(lng);
		dest.writeInt(logo);
		dest.writeString(num_commits);
	}
	
	 public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

		@Override
		public VenuesFactory createFromParcel(Parcel source) {
			return new VenuesFactory(source);
		}

		@Override
		public VenuesFactory[] newArray(int size) {
			return new VenuesFactory[size];
		}
		
	};
}
