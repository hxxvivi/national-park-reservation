package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Classes.Campground;
import com.techelevator.DAO.CampgroundDAO;
import com.techelevator.DAO.JDBCCampgroundDAO;

public class JDBCCampgroundDAOIntegrationTest extends DAOIntegrationTest{
	private JdbcTemplate jdbcTemplate;
	private CampgroundDAO dao;
	private long testId;
	
	@Before
	public void setUp() {
		String sql = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (default, 1, 'abc', '03', '11', '35.00') returning campground_id";
		jdbcTemplate = new JdbcTemplate(getDataSource());
		truncateTables();		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		result.next();
		testId = result.getLong(1);
		dao = new JDBCCampgroundDAO(getDataSource());
	}
	
	@Test 
	public void get_all_parks() {
		String sql2 = "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) VALUES (Default, 'TestName', 'TestLocation', '2012-09-09', 10, 10, 'TestDescription') RETURNING park_id";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql2);
		result.next();
		long parkId = result.getLong("park_id");
		result = jdbcTemplate.queryForRowSet(sql2, parkId);
		List<Campground> campground = dao.getAllCampgrounds(parkId);
		Assert.assertEquals(1, campground.size());
	}
	private void truncateTables() {
		String truncateSql = "Truncate park Cascade";
		jdbcTemplate.update(truncateSql);
	}
}
