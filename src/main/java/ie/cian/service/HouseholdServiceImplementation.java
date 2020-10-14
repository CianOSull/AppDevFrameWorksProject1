package ie.cian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cian.MainApp;
import ie.cian.classes.Household;
import ie.cian.classes.Occupant;
import ie.cian.dao.HouseholdDao;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

// This makes this a bean for the service layer
@Slf4j
@Service
public class HouseholdServiceImplementation implements HouseholdService {
	
	@Autowired
	HouseholdDao householdDao;
	
	public Household findHousehold(String eircode) {
		Household returnedHousehold = householdDao.findHouseholdByHouseholdEircode(eircode);
		if (returnedHousehold == null) {
			log.error("There is no household with ID " + eircode + " in the database.");
		}
		return returnedHousehold;
	}
	
	public List<Occupant> findOccupantsHouseholdEircode(String eircode){
		return householdDao.findHouseholdOccupants(eircode);
	}
	
	public Household addAHousehold(final Household household) {
		// If household doesnt exist do if statement
		if(!householdDao.exists(household.getEircode())) {
			// how this works is it checks if the household was added by trying to find its primary key id
			return householdDao.findHouseholdByHouseholdId(householdDao.addAHousehold(household));
		}
		log.error(household.getEircode() + " is already in the database.");
		return null;
	}

}
