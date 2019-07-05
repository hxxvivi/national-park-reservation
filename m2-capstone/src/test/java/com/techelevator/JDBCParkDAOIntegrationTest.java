package com.techelevator;

import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Classes.Park;
import com.techelevator.DAO.JDBCParkDAO;
import com.techelevator.DAO.ParkDAO;


public class JDBCParkDAOIntegrationTest extends DAOIntegrationTest {
	private JdbcTemplate jdbcTemplate;
	private ParkDAO dao;
	private long testId;
	
	@Before
	public void setUp() {
		String sql= "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) VALUES (Default, 'TestName', 'TestLocation', '2012-09-09', 10, 10, 'TestDescription') RETURNING park_id";
		jdbcTemplate = new JdbcTemplate(getDataSource());
		truncateTables();		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		result.next();
		testId = result.getLong(1);
		dao = new JDBCParkDAO(getDataSource());
	}
	
	@Test 
	public void get_all_parks() {
		List<Park> park = dao.getAllParks();
		Assert.assertEquals(1, park.size());
	}

	@Test
	public void get_park_by_id() {
		
		Park park = dao.getParkById(testId);
		long fakeParkId = park.getparkId();
		
		Assert.assertEquals(fakeParkId, testId);
		
		
	}
	
	private void truncateTables() {
		String truncateSql = "Truncate park Cascade";
		jdbcTemplate.update(truncateSql);
	}
}
