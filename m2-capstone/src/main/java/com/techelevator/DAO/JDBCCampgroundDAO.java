package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Classes.Campground;
import com.techelevator.Classes.Park;

public class JDBCCampgroundDAO implements CampgroundDAO{
	private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getAllCampgrounds(long userInput) {
		String sql = "SELECT * FROM campground where park_id =?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userInput);
		List<Campground> allCampgrounds = new ArrayList<Campground>();
		while(result.next()) {
			allCampgrounds.add(mapRowToCampground(result));
		}
		
		return allCampgrounds;
	}
	
	
	public Campground getCampgroundById(long userInput) {
		String sql = "SELECT * FROM campground WHERE campground_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userInput);
		
		Campground campground = null;
		if(result.next()) {
			campground = mapRowToCampground(result);
		}
		return campground;
	}

	private Campground mapRowToCampground(SqlRowSet result) {
		Campground c = new Campground();
		c.setCampgroundID(result.getLong("campground_id"));
		c.setParkID(result.getLong("park_id"));
		c.setName(result.getString("name"));
		c.setOpenMonth(result.getLong("open_from_mm"));
		c.setCloseMonth(result.getLong("open_to_mm"));
		c.setDailyFee(result.getDouble("daily_fee"));
		
		return c;
	}


}
