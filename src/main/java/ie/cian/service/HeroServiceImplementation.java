package ie.cian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cian.classes.Hero;
import ie.cian.dao.HeroDao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HeroServiceImplementation implements HeroService {
	
	@Autowired
	HeroDao heroDao; 
	
	public int countHeros() {
		return heroDao.countHeros();
	}
	
	public int countTheCountriesThatStartWith(char letter) {
		return heroDao.getHeroCountStartsWith(Character.toUpperCase(letter));
	}
	
	public int deleteHeroById(int heroId) {
		int numberDeleted = heroDao.deleteHeroById(heroId);
		if(numberDeleted == 0) {
			log.error("deleteHeroById: No such hero with id " + heroId);
		}
		return numberDeleted;
	}
	
	public Hero findHero(int heroId) {
		Hero returnedHero = heroDao.findHeroByHeroId(heroId);
		if (returnedHero == null) {
			log.error("There is no hero with ID " + heroId + " in the database.");
		}
		return returnedHero;
	}
	
	public List<Hero> findAllCounties(){
		return heroDao.findAllCounties();
	}
	
	public Hero addAHero(String heroName) {
		if(!heroDao.exists(heroName)) {
			return heroDao.findHeroByHeroId(heroDao.addAHero(heroName));
		}
		log.error(heroName + " is already in the database.");
		return null;
	}
	
	public int changeHeroName(String oldHeroName, String newHeroName) {
		if(!heroDao.exists(oldHeroName)) {
			log.error(oldHeroName + " is not in database.");
			return 0;
		}
		if(heroDao.exists(newHeroName)) {
			log.error(newHeroName + " is already in the database.");
			return 0;
		}
		
		int countChanged = heroDao.changeHeroName(oldHeroName, newHeroName);
		if (countChanged == 0) {
			log.error(oldHeroName + " is not in the database.");
		}
		
		return countChanged;
	}
}
