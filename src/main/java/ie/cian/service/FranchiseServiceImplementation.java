package ie.cian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cian.classes.Franchise;
import ie.cian.classes.Hero;
import ie.cian.dao.FranchiseDao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FranchiseServiceImplementation implements FranchiseService {
	
	@Autowired
	FranchiseDao franchiseDao; 
	
	public int countFranchises() {
		return franchiseDao.countFranchises();
	}
	
	public int countTheCountriesThatStartWith(char letter) {
		return franchiseDao.getFranchiseCountStartsWith(Character.toUpperCase(letter));
	}
	
	public int deleteFranchiseById(int franchiseId) {
		int numberDeleted = franchiseDao.deleteFranchiseById(franchiseId);
		if(numberDeleted == 0) {
			log.error("deleteFranchiseById: No such franchise with id " + franchiseId);
		}
		return numberDeleted;
	}
	
	public Franchise findFranchise(int franchiseId) {
		Franchise returnedFranchise = franchiseDao.findFranchiseByFranchiseId(franchiseId);
		if (returnedFranchise == null) {
			log.error("There is no franchise with ID " + franchiseId + " in the database.");
		}
		return returnedFranchise;
	}
	
	public Hero findFranchiseHero(int franchiseId) {
		Hero returnedFranchise = franchiseDao.findFranchiseHeroId(franchiseId);
		if (returnedFranchise == null) {
			log.error("There is no hero with franchise ID " + franchiseId + " in the database.");
		}
		return returnedFranchise;
		
	}
	
	public List<Franchise> findAllCounties(){
		return franchiseDao.findAllCounties();
	}
	
	public int changeFranchiseName(String oldFranchiseName, String newFranchiseName) {
		if(!franchiseDao.exists(oldFranchiseName)) {
			log.error(oldFranchiseName + " is not in database.");
			return 0;
		}
		if(franchiseDao.exists(newFranchiseName)) {
			log.error(newFranchiseName + " is already in the database.");
			return 0;
		}
		
		int countChanged = franchiseDao.changeFranchiseName(oldFranchiseName, newFranchiseName);
		if (countChanged == 0) {
			log.error(oldFranchiseName + " is not in the database.");
		}
		
		return countChanged;
	}
}
