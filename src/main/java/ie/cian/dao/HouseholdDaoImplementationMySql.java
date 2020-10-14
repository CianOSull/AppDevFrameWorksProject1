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
import ie.cian.classes.Occupant;
import ie.cian.rowmappers.HouseholdRowMapper;
import ie.cian.rowmappers.OccupantRowMapper;
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
			Household household = jdbcTemplate.queryForObject("SELECT * FROM household WHERE household.eircode = ?", new Object[] {eircode}, new HouseholdRowMapper());
			// Add the occupants of the household to it
			household.setOccupantList(jdbcTemplate.query("SELECT * FROM occupants WHERE occupants.householdId = ?", new Object[] {household.getHouseholdId()}, new OccupantRowMapper()));
			return household;
		}
		catch(Exception ex) 
		{
			return null;
		}
	}
	
	public List<Occupant> findHouseholdOccupants(String eircode) {
		try 
		{	
			Household household = jdbcTemplate.queryForObject("SELECT * FROM household WHERE household.eircode = ?", new Object[] {eircode}, new HouseholdRowMapper());
			// Add the occupants of the household to it
			return jdbcTemplate.query("SELECT * FROM occupants WHERE occupants.householdId = ?", new Object[] {household.getHouseholdId()}, new OccupantRowMapper());
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
			
			Household household = jdbcTemplate.queryForObject("SELECT * FROM household WHERE household.householdId = ?", new Object[] {id}, new HouseholdRowMapper());
			// Add the occupants of the household to it
			household.setOccupantList(jdbcTemplate.query("SELECT * FROM occupants WHERE occupants.householdId = ?", new Object[] {household.getHouseholdId()}, new OccupantRowMapper()));
			return household;
		}
		catch(Exception ex) 
		{
			return null;
		}
	}

	
	public int addAHousehold(final Household household) {
		final String INSERT_SQL = "INSERT INTO household (eircode, address) VALUES (?, ?)";
		// This is for getting the primary key i think
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 //This is an older way of using spring, dont need to think about it too much
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"householdId"});
						ps.setString(1, household.getEircode());
						ps.setString(2, household.getAddress());
						return ps;
					}
				}, keyHolder);
		
		if(household.getOccupantList() != null) {
			
		}
		
		return keyHolder.getKey().intValue();
	}
	
}
