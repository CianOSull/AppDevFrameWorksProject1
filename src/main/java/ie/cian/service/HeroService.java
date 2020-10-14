package ie.cian.service;

import java.util.List;

import ie.cian.classes.Hero;

public interface HeroService {
	int countHeros();
	
	int countTheCountriesThatStartWith(char letter);
	
	int deleteHeroById(int heroID);
	
	Hero findHero(int heroId);
	
	List<Hero> findAllCounties();
	
	Hero addAHero(String heroName);
	
	int changeHeroName(String oldHeroName, String newHeroName);
}
