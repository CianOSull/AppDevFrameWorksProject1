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
	
	// Search for a household by Eircode, listing the details of the people in the household
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

	//Add a household, along with its occupant(s)
	public int addAHousehold(final Household household) {
		// The ? are placeholders for the column names
		final String INSERT_SQL = "INSERT INTO household (eircode, address) VALUES (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"householdId"});
						// The numbers reprsetn the indexes of the ? above
						ps.setString(1, household.getEircode());
						ps.setString(2, household.getAddress());
						return ps;
					}
				}, keyHolder);
		
		if(household.getOccupantList() != null) 
		{	
			for(int i = 0; i < household.getOccupantList().size(); i++) 
			{
				final String occupantName = household.getOccupantList().get(i).getOccupantName();
				final int occupantAge = household.getOccupantList().get(i).getOccupantAge();
				final String occupation = household.getOccupantList().get(i).getOccupation();
				final int householdId = keyHolder.getKey().intValue();
				
				// Just call the add occupant funciton multiple times
				this.addAOccupant(occupantName, occupantAge, occupation, householdId);
			}
		}
		
		return keyHolder.getKey().intValue();
	}
	
	// Add a new person and assign that person to a household
	public int addAOccupant(final String occupantName, final int occupantAge, final String occupation, final int householdId) {
		final String INSERT_SQL = "INSERT INTO occupants (occupantName, occupantAge, occupation, householdId) VALUES (?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"occupantId"});
						ps.setString(1, occupantName);
						ps.setInt(2, occupantAge);
						ps.setString(3, occupation);
						ps.setInt(4, householdId);
						return ps;
					}
				}, keyHolder);

		return keyHolder.getKey().intValue();
	}
	
	// Move a person from one household to another
	public int changeOccupantHousehold(int occupantId, int householdId) {
		final String SQL = "UPDATE occupants set occupants.householdId = ? WHERE occupants.occupantId = ?";
		int numberChanged = jdbcTemplate.update(SQL, new Object[] {householdId, occupantId});
		if (numberChanged == 0) {
			log.error("changeOccupantHousehold: Changed no records - perhaps there is no occupantId " + occupantId + "?");
		}
		return numberChanged;
	}
	
	// Delete a household, along with its occupants
	public int deleteHouseholdbyId(int householdId) {
		final String SQL = "DELETE FROM household WHERE household.householdId = ?";
		// Update always returns the number of records it has edited
		return jdbcTemplate.update(SQL, new Object[] {householdId});
	}
	
	// Delete a person
	public int deleteOccupant(int occupantId) {
		final String SQL = "DELETE FROM occupants WHERE occupants.occupantId = ?";
		// Update always returns the number of records it has edited
		return jdbcTemplate.update(SQL, new Object[] {occupantId});
	}
	
	// the average age of householders
	public int avgHouseholdersAge()
	{
		return jdbcTemplate.queryForObject("SELECT AVG(occupantAge) FROM occupants", Integer.class);
	}
	
	// the average age of householders
	public int countOccupantStudents()
	{
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM occupants WHERE occupants.occupation = 'scholar' OR occupants.occupation = 'pre-school'", Integer.class);
	}
	
	// the average age of householders
	public int oapNumber()
	{
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM occupants WHERE 65 <= occupants.occupantAge", Integer.class);
	}
	
	public boolean householdExists(String eircode) 
	{
		return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM household WHERE household.eircode = ?", new Object[] {eircode}, Integer.class);
	}
	
	public boolean occupantExists(String occupantName) 
	{
		return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM occupants WHERE occupants.occupantName = ?", new Object[] {occupantName}, Integer.class);
	}
	
	public boolean householdIdExists(int householdId) 
	{
		return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM household WHERE household.householdId = ?", new Object[] {householdId}, Integer.class);
	}
	
	public boolean occupantIdExists(int occupantId) 
	{
		return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM occupants WHERE occupants.occupantId = ?", new Object[] {occupantId}, Integer.class);
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
	
	public Occupant findOccupantById(int id) 
	{
		try 
		{
			
			return jdbcTemplate.queryForObject("SELECT * FROM occupants WHERE occupants.occupantId = ?", new Object[] {id}, new OccupantRowMapper());
		}
		catch(Exception ex) 
		{
			return null;
		}
	}
}
