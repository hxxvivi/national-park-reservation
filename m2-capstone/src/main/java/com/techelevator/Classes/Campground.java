package com.techelevator.Classes;

public class Campground {
	private long campgroundID;
	private long parkID;
	private String name;
	private long openMonth;
	private long closeMonth;
	private double dailyFee;
	
	public long getCampgroundID() {
		return campgroundID;
	}
	public void setCampgroundID(long campgroundID) {
		this.campgroundID = campgroundID;
	}
	public long getParkID() {
		return parkID;
	}
	public void setParkID(long parkID) {
		this.parkID = parkID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getOpenMonth() {
		return openMonth;
	}
	public void setOpenMonth(long openMonth) {
		this.openMonth = openMonth;
	}
	public long getCloseMonth() {
		return closeMonth;
	}
	public void setCloseMonth(long closeMonth) {
		this.closeMonth = closeMonth;
	}
	public double getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(double dailyFee) {
		this.dailyFee = dailyFee;
	}
	
	
}
