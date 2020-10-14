package ie.cian.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import ie.cian.classes.Hero;

public class HeroRowMapper implements RowMapper<Hero> {
	
	/*
	 * Row mapper is an interface added by spring that allows you to map rows from sql to objects.
	 * All you need is just to set any columns in the result set (contains anything gotten from a row in a table) to paramaters of the class.
	 */
	public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hero hero = new Hero();
		hero.setHeroId(rs.getInt("heroId"));
		hero.setHeroName(rs.getString("heroName"));
		return hero;
	}

}
