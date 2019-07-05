package com.techelevator.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cglib.core.Local;

import com.techelevator.Classes.Campsite;

public interface CampsiteDAO {
	List<Campsite> getAllCampsite(long campGroundInput, LocalDate arrivalDateInput, LocalDate departureDateInput);
}
