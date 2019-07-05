package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Classes.Park;

public class JDBCParkDAO implements ParkDAO {
	private JdbcTemplate jdbcTemplate;

	public  JDBCParkDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public List<Park> getAllParks() {
		String sql = "SELECT * FROM park";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		List<Park> allParks = new ArrayList<Park>();
		while(result.next()) {
			allParks.add(mapRowToPark(result));
		}
		
		return allParks;
	}
	
	public Park getParkById(long userInput) {
		String sql = "SELECT * FROM park WHERE park_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userInput);
		
		Park park = null;
		if(result.next()) {
			park = mapRowToPark(result);
		}
		return park;
	}
	
	private Park mapRowToPark(SqlRowSet result) {
		Park p = new Park();
		p.setId(result.getLong("park_id"));
		p.setName(result.getString("name"));
		p.setLocation(result.getString("location"));
		p.setEstablishedDate(result.getDate("establish_date").toLocalDate());
		p.setArea(result.getLong("area"));
		p.setVisitor(result.getLong("visitors"));
		p.setDescription(result.getString("description"));
		
		return p;
	}
	

}
