package ie.cian.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import ie.cian.classes.Household;

public class HouseholdRowMapper implements RowMapper<Household> {
	
	public Household mapRow(ResultSet rs, int rowNum) throws SQLException {
		Household household = new Household();
		household.setHouseholdId(rs.getInt("householdId"));
		household.setEircode(rs.getString("eircode"));
		household.setAddress(rs.getString("address"));
		return household;
	}

}
