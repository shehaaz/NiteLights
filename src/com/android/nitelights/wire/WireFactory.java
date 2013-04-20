package com.android.nitelights.wire;



public class WireFactory implements Comparable<WireFactory>{
	
	private String element1;
	private String element2;
	private String wireType;
	private String wire_timestamp;

	
	public WireFactory(String pElement1, String pElement2, String pWireType, String pTimestamp){
		element1 = pElement1;
		element2 = pElement2;
		wireType = pWireType;
		wire_timestamp = pTimestamp;
	}
	

	public String getElement1(){
		return element1;
	}
	
	public String getElement2(){
		return element2;
	}
	
	public String getWireType(){
		return wireType;
	}
	
	public String getTimestamp(){
		return wire_timestamp;
	}
	

	@Override
	public int compareTo(WireFactory comparedWireObject) {
		
		int compareQuantity;
		
		if(comparedWireObject.getTimestamp() != null){
			compareQuantity = Integer.parseInt(((WireFactory) comparedWireObject).getTimestamp()); 
			//descending order
			return compareQuantity - Integer.parseInt(this.getTimestamp());
		}
		else{
			compareQuantity = 0;
			return compareQuantity;
		}
		  	
	}
}
