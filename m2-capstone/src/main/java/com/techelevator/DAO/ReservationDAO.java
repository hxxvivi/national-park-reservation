package com.techelevator.DAO;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.Classes.Reservation;

public interface ReservationDAO {
List<Reservation> getAllReservations();

public long makeYourReservation(long site_id, String name, LocalDate startDate, LocalDate endDate);

}
	
