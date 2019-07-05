package com.techelevator.Classes;

import java.time.LocalDate;

public class Reservation {
	private long reservationID;
	private long siteID;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate createDate;
	public long getReservationID() {
		return reservationID;
	}
	public void setReservationID(long reservationID) {
		this.reservationID = reservationID;
	}
	public long getSiteID() {
		return siteID;
	}
	public void setSiteID(long siteID) {
		this.siteID = siteID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	
}
