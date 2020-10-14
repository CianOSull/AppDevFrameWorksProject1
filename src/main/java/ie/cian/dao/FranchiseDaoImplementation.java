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

import ie.cian.classes.Franchise;
import ie.cian.classes.Hero;
import ie.cian.classes.Franchise;
import ie.cian.rowmappers.FranchiseRowMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class FranchiseDaoImplementation implements FranchiseDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	HeroDao heroDao;
	
	public int countFranchises() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM franchise", Integer.class);
	}
	public int getFranchiseCountStartsWith(char letter) {
		String pattern = letter + "%";
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM franchise WHERE franchise.franchiseName LIKE ?", new Object[] {pattern}, Integer.class);	
	}
	
	public boolean exists(String franchiseName) {
		return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM franchise WHERE franchise.franchiseName = ?", new Object[] {franchiseName}, Integer.class);
	}
	
	public Franchise findFranchiseByFranchiseId(int franchiseId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM franchise WHERE franchise.franchiseId = ?", new Object[] {franchiseId}, new FranchiseRowMapper());
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public List<Franchise> findAllCounties(){
		try {
			return jdbcTemplate.query("Select * FROM franchise", new FranchiseRowMapper());
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	public Hero findFranchiseHeroId(int franchiseId) {
		try {
			return heroDao.findHeroByHeroId(this.findFranchiseByFranchiseId(franchiseId).getHeroId());
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	
	public int deleteFranchiseById(int franchiseId) {
		final String SQL = "DELETE FROM franchise WHERE franchise.franchiseId = ?";
		int changedRecords = 0;
		int heroId = this.findFranchiseByFranchiseId(franchiseId).getHeroId();
		changedRecords += jdbcTemplate.update(SQL, new Object[] {franchiseId});
		changedRecords += heroDao.deleteHeroById(heroId);
		return changedRecords;
	}
	
	public int changeFranchiseName(String oldFranchiseName, String newFranchiseName) {
		final String SQL = "UPDATE franchise set franchise.franchiseName = ? WHERE franchise.franchiseName = ?";
		int numberChanged = jdbcTemplate.update(SQL, new Object[] {newFranchiseName, oldFranchiseName});
		if (numberChanged == 0) {
			log.error("changeFranchiseName: Changed no records - perhaps there is no franchise " + oldFranchiseName + "?");
		}
		return numberChanged;
	}
}
