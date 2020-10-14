package ie.cian.dao;

import java.util.List;
import ie.cian.classes.Hero;

public interface HeroDao {
	int countHeros();
	
	int getHeroCountStartsWith(char letter);
	
	boolean exists(String heroName);
	
	Hero findHeroByHeroId(int heroId);
	
	List<Hero> findAllCounties();
	
	int deleteHeroById(int heroId);
	
	int changeHeroName(String oldHeroName, String newHeroName);
	
	int addAHero(final String heroName);
}
