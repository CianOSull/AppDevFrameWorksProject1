package ie.cian.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import ie.cian.classes.Franchise;

public class FranchiseRowMapper implements RowMapper<Franchise> {
	
	/*
	 * Row mapper is an interface added by spring that allows you to map rows from sql to objects.
	 * All you need is just to set any columns in the result set (contains anything gotten from a row in a table) to paramaters of the class.
	 */
	public Franchise mapRow(ResultSet rs, int rowNum) throws SQLException {
		Franchise franchise = new Franchise();
		franchise.setFranchiseId(rs.getInt("franchiseId"));
		franchise.setFranchiseName(rs.getString("franchiseName"));
		franchise.setHeroId(rs.getInt("heroId"));
		return franchise;
	}

}
