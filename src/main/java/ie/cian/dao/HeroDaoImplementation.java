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

import ie.cian.classes.Hero;
import ie.cian.classes.Hero;
import ie.cian.rowmappers.HeroRowMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class HeroDaoImplementation implements HeroDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int countHeros() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM hero", Integer.class);
	}
	public int getHeroCountStartsWith(char letter) {
		String pattern = letter + "%";
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM hero WHERE hero.heroName LIKE ?", new Object[] {pattern}, Integer.class);	
	}
	
	public boolean exists(String heroName) {
		return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM hero WHERE hero.heroName = ?", new Object[] {heroName}, Integer.class);
	}
	
	public Hero findHeroByHeroId(int heroId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM hero WHERE hero.heroId = ?", new Object[] {heroId}, new HeroRowMapper());
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public List<Hero> findAllCounties(){
		try {
			return jdbcTemplate.query("Select * FROM hero", new HeroRowMapper());
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	public int deleteHeroById(int heroId) {
		final String SQL = "DELETE FROM hero WHERE hero.heroId = ?";
		return jdbcTemplate.update(SQL, new Object[] {heroId});
	}
	
	public int changeHeroName(String oldHeroName, String newHeroName) {
		final String SQL = "UPDATE hero set hero.heroName = ? WHERE hero.heroName = ?";
		int numberChanged = jdbcTemplate.update(SQL, new Object[] {newHeroName, oldHeroName});
		if (numberChanged == 0) {
			log.error("changeHeroName: Changed no records - perhaps there is no hero " + oldHeroName + "?");
		}
		return numberChanged;
	}
	
	public int addAHero(final String heroName) {
		final String INSERT_SQL = "INSERT INTO hero(heroName) VALUES (?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"heroId"});
						ps.setString(1, heroName);
						return ps;
					}
				}, keyHolder);
		return keyHolder.getKey().intValue();
	}
}
