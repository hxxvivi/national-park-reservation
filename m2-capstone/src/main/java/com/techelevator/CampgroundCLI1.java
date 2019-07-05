package com.techelevator;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.Classes.Campground;
import com.techelevator.Classes.Campsite;
import com.techelevator.Classes.Park;
import com.techelevator.DAO.JDBCCampgroundDAO;
import com.techelevator.DAO.JDBCCampsiteDAO;
import com.techelevator.DAO.JDBCParkDAO;

public class CampgroundCLI1 {

	private Menu menu;
	private JDBCParkDAO parkDAO;
	private JDBCCampgroundDAO campgroundDAO;
	private JDBCCampsiteDAO campsiteDAO;
	
	
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundCLI1 application = new CampgroundCLI1(dataSource);
		application.run();
	}

	public CampgroundCLI1(DataSource datasource) {
		menu = new Menu(System.in, System.out);
		parkDAO = new JDBCParkDAO(datasource);
		campgroundDAO = new JDBCCampgroundDAO(datasource);
		campsiteDAO = new JDBCCampsiteDAO(datasource);
	}
	
	private void campGroundMenuLoop(Park chosenPark) {
		String userInput = menu.displayCampgrounds();
		while (!userInput.equals("2")) {
			if (userInput.equals("1")) {
				//Action start
				menu.displayCampground(chosenPark, 
						campgroundDAO.getAllCampgrounds(chosenPark.getparkId()));
				campSiteMenuLoop(chosenPark);
				//Action end
			} else {
				System.out.println("Invalid Selection!");
			}
			menu.displayChosenPark(chosenPark);
			userInput = menu.displayCampgrounds();
		}
	}
	
	private void campSiteReservationLoop(Park chosenPark) {
		String userInput = menu.getCampgroundChoice();
		if (!userInput.equals("0")) {
			try {
			String arrivalDate = menu.getArrivalDate();
			String departureDate = menu.getDepartureDate();
			LocalDate aDt = LocalDate.parse("2019-06-23");
			LocalDate dDt = LocalDate.parse("2019-06-25");
			campsiteDAO.getAllCampsite(Long.parseLong(userInput), aDt, dDt);
		}catch (Exception e) {
			System.out.println("Input unparsable to long");
		}
		}
	}
	
	private void campSiteMenuLoop(Park chosenPark) {
		String userInput = menu.choosingCampground();
		while(!userInput.equals("2")) {
			if(userInput.equals("1")) {
				//make reservation
				campSiteReservationLoop(chosenPark);
				} else {
				System.out.println("Invalid Selection!");
			}
			menu.displayCampground(chosenPark, 
					campgroundDAO.getAllCampgrounds(chosenPark.getparkId()));
			userInput = menu.choosingCampground();
		}
	}
	
	private void mainMenuLoop() {
		String userInput = menu.makeParkChoice(parkDAO);
		while(!userInput.equalsIgnoreCase("Q")){
			try {
				Park userPark = parkDAO.getParkById(Long.parseLong(userInput));
				if(userPark != null) {
					//Action start
					menu.displayChosenPark(userPark);
					campGroundMenuLoop(userPark);
					//Action end
				} else {
					System.out.println("Invalid selection, please try again.");
				}
			} catch (Exception e) {
				System.out.println("Input unparsable to long");
			}
			userInput = menu.makeParkChoice(parkDAO);
		}
	}

	public void run() {
		mainMenuLoop();
	}
}
