package com.techelevator.Classes;

import java.time.LocalDate;

public class Park {
	
	private long parkId;
	private String name;
	private LocalDate establishedDate;
	private String location;
	private long area;
	private long visitor;
	private String description;
	public long getparkId() {
		return parkId;
	}
	public void setId(long id) {
		this.parkId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(LocalDate establishedDate) {
		this.establishedDate = establishedDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public long getArea() {
		return area;
	}
	public void setArea(long area) {
		this.area = area;
	}
	public long getVisitor() {
		return visitor;
	}
	public void setVisitor(long visitor) {
		this.visitor = visitor;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
