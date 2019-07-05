package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Classes.Reservation;
import com.techelevator.DAO.JDBCReservationDAO;
import com.techelevator.DAO.ReservationDAO;

public class JDBCREservationDAOIntegrationTest extends DAOIntegrationTest{
	private JdbcTemplate jdbcTemplate;
	private ReservationDAO dao;
	private long testId;
	
	@Before
	public void setUp() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		String sql = "insert into reservation (reservation_id, site_id, name, from_date, to_date, create_date) values (default, 1, 'testName', '2019-06-18', '2019-06-22', '2019-06-20') returning reservation_id";
		truncateTables();		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		result.next();
		testId = result.getLong(1);
		dao = new JDBCReservationDAO(getDataSource());
	}
	
	@Test 
	public void get_all_reservation() {
		List<Reservation> reservation = dao.getAllReservations();
		Assert.assertEquals(1, reservation.size());
	}
	
	private void truncateTables() {
		String truncateSql = "Truncate park Cascade";
		jdbcTemplate.update(truncateSql);
	}
}
