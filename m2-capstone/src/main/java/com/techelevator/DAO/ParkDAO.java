package com.techelevator.DAO;

import java.util.List;

import com.techelevator.Classes.Park;

public interface ParkDAO {
	List<Park> getAllParks();
	Park getParkById(long userInPut);

}
