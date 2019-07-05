package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.util.StringUtils;

import com.techelevator.Classes.Campground;
import com.techelevator.Classes.Campsite;
import com.techelevator.Classes.Park;
import com.techelevator.DAO.CampgroundDAO;
import com.techelevator.DAO.CampsiteDAO;
import com.techelevator.DAO.JDBCParkDAO;

public class Menu {
	
	private PrintWriter out;
	private Scanner in;
	private JDBCParkDAO parkDAO;

	
	public Menu(InputStream input, OutputStream output) {
		this.in = new Scanner(input);
		this.out = new PrintWriter(output);
	}
	
	public String makeParkChoice(JDBCParkDAO parks) {
		out.println("Select a park for further details: ");
		List<Park> parkList = parks.getAllParks();
		for(Park park: parkList) {
			out.println(park.getparkId() + ") " + park.getName());
		}
		out.println("Q) Quit");
		out.flush();
		return in.nextLine();
	}
	
	public void displayChosenPark(Park park) {
		out.println("Park Information Screen");
		out.println(park.getName() + " National Park");
		out.println("Location: " + park.getLocation());
		out.println("Established: " + park.getEstablishedDate());
		out.println("Area: " + park.getArea() + " sq km");
		out.println("Annual Visistors: " + park.getVisitor());
		out.println();
		String[] split = park.getDescription().split(" ");
		int firstCount = 0;
			for(String  s : split) {
				if (firstCount < 10) {
					firstCount ++;
				} else {
					firstCount =  1;
					out.println();
				}
				out.print(s + " ");
		}
		out.println();
		out.flush();
	}
	
	public String displayCampgrounds() {
		out.println();
		out.println("Select a Command");
		out.println("1) View Campgrounds");
		out.println("2) Return to Previous Screen");
		out.flush();
		return in.nextLine();
	}
	
	public void displayCampground(Park park, List<Campground> allCampgrounds) {
			out.println(park.getName() + " National Park Campgrounds");
			out.println( "      Name           Open        Close        Daily Fee");
			for(Campground campground: allCampgrounds) {
				out.println("#" + campground.getCampgroundID() + "   " + campground.getName()
				+ "         " + campground.getOpenMonth() + "         " + campground.getCloseMonth()
				+ "          "+ campground.getDailyFee());
				out.flush();
			}
		}
	public String choosingCampground() {
		out.println();
		out.println("Select a Command");
		out.println("1) Search for Available Reservation");
		out.println("2) Return to Previous Screen");
		out.flush();
		return in.nextLine();
	}
	
	public String getCampgroundChoice() {
		out.println();
		out.println("Which campground(enter 0 to cancel)?");
//		out.println("What is the arrival date? MM/DD/YYYY");
//		out.println("What is the departure date? MM/DD/YYYY");
		out.flush();
		return in.nextLine();
	}
	public String getArrivalDate() {
		out.println();
		out.println("What is the arrival date? YYYY-MM-DD");
		out.flush();
		return in.nextLine();
	}
	public String getDepartureDate() {
		out.println();
		out.println("What is the departure date? YYYY-MM-DD");
		out.flush();
		return in.nextLine();
	}
	public void displayAllCampsite(Campground campground, List<Campsite> allCampsites) {
		out.println();
		out.println("Results Matching Your Search Criteria");
		out.println("Campground        Site No.       Cost");
		for(Campsite campsite: allCampsites) {
			out.println(campground.getName() + " " + campsite.getCampsiteID() + " " + campground.getDailyFee());
		}
		
	}
	
	
	

		
		
	
}
