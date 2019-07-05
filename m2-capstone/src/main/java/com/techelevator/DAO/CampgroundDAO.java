package com.techelevator.DAO;

import java.util.List;

import com.techelevator.Classes.Campground;


public interface CampgroundDAO {

	List<Campground> getAllCampgrounds(long userInput);
}
