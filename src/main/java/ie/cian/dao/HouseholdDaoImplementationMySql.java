package ie.cian.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ie.cian.classes.Household;
import ie.cian.rowmappers.HouseholdRowMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class HouseholdDaoImplementationMySql implements HouseholdDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean exists(String eircode) 
	{
		return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM household WHERE household.eircode = ?", new Object[] {eircode}, Integer.class);
	}
	
	public Household findHouseholdByHouseholdEircode(String eircode) 
	{
		try 
		{
			return jdbcTemplate.queryForObject("SELECT * FROM household WHERE household.eircode = ?", new Object[] {eircode}, new HouseholdRowMapper());
		}
		catch(Exception ex) 
		{
			return null;
		}
	}
	
	// This is just for household service implemenation to check that the row was added
	public Household findHouseholdByHouseholdId(int id) 
	{
		try 
		{
			return jdbcTemplate.queryForObject("SELECT * FROM household WHERE household.eircode = ?", new Object[] {id}, new HouseholdRowMapper());
		}
		catch(Exception ex) 
		{
			return null;
		}
	}

	
	public int addAHousehold(final Household household) {
		final String INSERT_SQL = "INSERT INTO household(householdName) VALUES (?)";
		// This is for getting the primary key i think
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 //This is an older way of using spring, dont need to think about it too much
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"householdId"});
						ps.setString(1, household.getEircode());
						return ps;
					}
				}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
}
