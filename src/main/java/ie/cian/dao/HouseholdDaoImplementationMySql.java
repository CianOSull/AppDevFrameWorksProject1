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
	
	public int getHouseholdCount() {
		// this how you do sql queries. You supply the sql and set a return type which here is integer
		// Query for object is used when returning one object
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM household", Integer.class);
	}
	
	public int getHouseholdCountStartsWith(char letter) {
		/*
		 * To get specific words from a database we need to make a pattern which is just the order of characters
		 * and then attach the wildcard '%' to it.
		 */
		String pattern = letter + "%";
		/*
		 * '?' is a placeholder and we then set the pattern to be an object that will replace the placehodler ?
		 */
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM household WHERE household.householdName LIKE ?", new Object[] {pattern}, Integer.class);	
	}
	
	public boolean exists(String householdName) {
		return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM household WHERE household.householdName = ?", new Object[] {householdName}, Integer.class);
	}
	
	public Household findHouseholdByHouseholdId(int householdId) {
		/* 
		 * Normally  query for object only takes in 2 parameters but we need a row mapper to map values from the table to an actual class.
		 * Its creates a new class.
		 */
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM household WHERE household.householdId = ?", new Object[] {householdId}, new HouseholdRowMapper());
		}
		// This is here if there is now household with that id and the sevice class will handle that 
		catch(Exception ex) {
			return null;
		}
	}
	
	public List<Household> findAllCounties(){
		/* 
		 * This works because string runs a loop when doing this query,
		 * it constantly calls back and if its get a result like another row then it keeps
		 * running the mapper and then creates objects thus getting all the counties
		 */
		try {
			return jdbcTemplate.query("Select * FROM household", new HouseholdRowMapper());
		}
		// This catch is just for if the table is empty
		catch (Exception ex) {
			return null;
		}
	}
	
	public int deleteHouseholdById(int householdId) {
		final String SQL = "DELETE FROM household WHERE household.householdId = ?";
		// Update always returns the number of records it has edited
		return jdbcTemplate.update(SQL, new Object[] {householdId});
	}
	
	public int changeHouseholdName(String oldHouseholdName, String newHouseholdName) {
		final String SQL = "UPDATE household set household.householdName = ? WHERE household.householdName = ?";
		int numberChanged = jdbcTemplate.update(SQL, new Object[] {newHouseholdName, oldHouseholdName});
		if (numberChanged == 0) {
			log.error("changeHouseholdName: Changed no records - perhaps there is no household " + oldHouseholdName + "?");
		}
		return numberChanged;
	}
	
	public int addAHousehold(final String householdName) {
		final String INSERT_SQL = "INSERT INTO household(householdName) VALUES (?)";
		// This is for getting the primary key i think
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 //This is an older way of using spring, dont need to think about it too much
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"householdId"});
						ps.setString(1, householdName);
						return ps;
					}
				}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
}
