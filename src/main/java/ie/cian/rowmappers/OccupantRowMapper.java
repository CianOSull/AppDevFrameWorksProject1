package ie.cian.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import ie.cian.classes.Occupant;

public class OccupantRowMapper implements RowMapper<Occupant> {
	
	/*
	 * Row mapper is an interface added by spring that allows you to map rows from sql to objects.
	 * All you need is just to set any columns in the result set (contains anything gotten from a row in a table) to paramaters of the class.
	 */
	public Occupant mapRow(ResultSet rs, int rowNum) throws SQLException {
		Occupant occupant = new Occupant();
		occupant.setOccupantId(rs.getInt("occupantId"));
		occupant.setOccupantName(rs.getString("occupantName"));
		occupant.setOccupantAge(rs.getInt("occupantAge"));
		occupant.setOccupation(rs.getString("occupation"));
		occupant.setHouseholdId(rs.getInt("householdId"));
		
		return occupant;
	}

}
