package ie.cian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cian.MainApp;
import ie.cian.classes.Household;
import ie.cian.dao.HouseholdDao;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

// This makes this a bean for the service layer
@Slf4j
@Service
public class HouseholdServiceImplementation implements HouseholdService {
	
	@Autowired
	HouseholdDao householdDao;
	
	public int countTheCounties() {
		return householdDao.getHouseholdCount();
	}
	
	public int countTheCountriesThatStartWith(char letter) {
		return householdDao.getHouseholdCountStartsWith(Character.toUpperCase(letter));
	}
	
	public int deleteHouseholdById(int householdId) {
		int numberDeleted = householdDao.deleteHouseholdById(householdId);
		if(numberDeleted == 0) {
			log.error("deleteHouseholdById: No such household with id " + householdId);
		}
		return numberDeleted;
	}
	
	public Household findHousehold(int householdId) {
		Household returnedHousehold = householdDao.findHouseholdByHouseholdId(householdId);
		if (returnedHousehold == null) {
			log.error("There is no household with ID " + householdId + " in the database.");
		}
		return returnedHousehold;
	}
	
	public List<Household> findAllCounties(){
		return householdDao.findAllCounties();
	}
	
	public Household addAHousehold(String householdName) {
		// If household doesnt exist do if statement
		if(!householdDao.exists(householdName)) {
			// how this works is it checks if the household was added by trying to find its primary key id
			return householdDao.findHouseholdByHouseholdId(householdDao.addAHousehold(householdName));
		}
		log.error(householdName + " is already in the database.");
		return null;
	}
	
	public int changeHouseholdName(String oldHouseholdName, String newHouseholdName) {
		if(!householdDao.exists(oldHouseholdName)) {
			log.error(oldHouseholdName + " is not in database.");
			return 0;
		}
		if(householdDao.exists(newHouseholdName)) {
			log.error(newHouseholdName + " is already in the database.");
			return 0;
		}
		
		int countChanged = householdDao.changeHouseholdName(oldHouseholdName, newHouseholdName);
		if (countChanged == 0) {
			log.error(oldHouseholdName + " is not in the database.");
		}
		
		return countChanged;
	}

}
