package com.techelevator.Classes;

public class Campsite {
	private long campsiteID;
	private long campgroundID;
	private long siteNumber;
	private long maxOccupancy;
	
	public long getCampsiteID() {
		return campsiteID;
	}
	public void setCampsiteID(long campsiteID) {
		this.campsiteID = campsiteID;
	}
	public long getCampgroundID() {
		return campgroundID;
	}
	public void setCampgroundID(long campgroundID) {
		this.campgroundID = campgroundID;
	}
	public long getSiteNumber() {
		return siteNumber;
	}
	public void setSiteNumber(long siteNumber) {
		this.siteNumber = siteNumber;
	}
	public long getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(long maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	
}
