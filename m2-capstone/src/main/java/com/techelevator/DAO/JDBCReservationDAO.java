package com.techelevator.DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Classes.Reservation;

public class JDBCReservationDAO implements ReservationDAO{
private JdbcTemplate jdbcTemplate;

public JDBCReservationDAO(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);

}

@Override
public List<Reservation> getAllReservations() {
    String sql = "Select * From reservation where site_id = ?";
    SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
    List<Reservation> reservations = new ArrayList<Reservation>();
    while(result.next()) {
        reservations.add(mapRowToReservation(result));
    }
    return reservations;
}

@Override
public long makeYourReservation(long site_id, String name, LocalDate startDate, LocalDate endDate) {
    long yourReservationId = getYourReservationId();
    String sql = "insert into reservation (reservation_id, site_id, name, from_date, to_date) values (?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, yourReservationId, name, startDate, endDate);
    return yourReservationId;
}

public long getYourReservationId() { 
    SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("select nextval('reservation_reservation_id_seq')");
    if(nextIdResult.next()) {
        return nextIdResult.getLong(1);
    } else {
        throw new RuntimeException("Something went wrong while getting your reservation Id");
    }
}

private Reservation mapRowToReservation(SqlRowSet result) {
    Reservation r = new Reservation();
    r.setReservationID(result.getLong("reservation_id"));
    r.setSiteID(result.getLong("site_id"));
    r.setName(result.getString("name"));
    r.setCreateDate(result.getDate("create_date").toLocalDate());
    r.setStartDate(result.getDate("from_date").toLocalDate());
    r.setEndDate(result.getDate("to_date").toLocalDate());
    
    return r;
}
}