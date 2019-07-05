package com.techelevator.DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Classes.Campground;
import com.techelevator.Classes.Campsite;

public class JDBCCampsiteDAO implements CampsiteDAO{
		private JdbcTemplate jdbcTemplate;
		
		public JDBCCampsiteDAO(DataSource dataSource) {
			jdbcTemplate = new JdbcTemplate(dataSource);
		}

		@Override
		public List<Campsite> getAllCampsite(long campGroundInput, LocalDate arrivalDateInput, LocalDate departureDateInput) {
			String sql = "SELECT * FROM site JOIN campground ON site.campground_id = campground.campground_id "
					+ "WHERE site.campground_id = ? AND site.site_id NOT IN ( SELECT s.site_id FROM site s " + 
					"JOIN reservation r ON s.site_id = r.site_id WHERE s.campground_id = ? AND " + 
					"(? > r.from_date AND ? < r.to_date OR "
					+ "? > r.from_date AND ? < r.to_date) OR " +
					"(r.from_date BETWEEN ? AND ? AND r.to_date BETWEEN ?"
					+ " AND ?))";
			SqlRowSet result = jdbcTemplate.queryForRowSet(sql, campGroundInput, campGroundInput, arrivalDateInput, arrivalDateInput, 
					departureDateInput, departureDateInput, arrivalDateInput, departureDateInput, arrivalDateInput, departureDateInput);

			List<Campsite> allCampsites = new ArrayList<Campsite>();

			while(result.next()) {
				allCampsites.add(mapRowToCampsite(result));
			}
			return allCampsites;
		}
		
		private Campsite mapRowToCampsite(SqlRowSet result) {
			Campsite c = new Campsite();
			c.setCampsiteID(result.getLong("campsite_id"));
			c.setCampgroundID(result.getLong("park_id"));
			c.setSiteNumber(result.getLong("site_number"));
			c.setMaxOccupancy(result.getLong("max_occupancy"));
			
			return c;
		}
	}
	


