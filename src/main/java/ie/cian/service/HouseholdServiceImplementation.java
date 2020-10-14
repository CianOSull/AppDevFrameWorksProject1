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
public class HouseholdServiceImplementation implements HouseholdService 
{
	
	@Autowired
	HouseholdDao householdDao;
	
	public Household findHousehold(String eircode) 
	{
		Household returnedHousehold = householdDao.findHouseholdByHouseholdEircode(eircode);
		if (returnedHousehold == null) 
		{
			log.error("There is no household with ID " + eircode + " in the database.");
		}
		return returnedHousehold;
	}
	
	public List<Occupant> findOccupantsHouseholdEircode(String eircode)
	{
		return householdDao.findHouseholdOccupants(eircode);
	}
	
	public Household addAHousehold(final Household household) 
	{
		// If household doesnt exist do if statement
		if(!householdDao.householdExists(household.getEircode())) 
		{
			// how this works is it checks if the household was added by trying to find its primary key id
			return householdDao.findHouseholdByHouseholdId(householdDao.addAHousehold(household));
		}
		log.error(household.getEircode() + " is already in the database.");
		return null;
	}
	
	// While occupant name isn't unique technically, its the most unique thing we can use for this case
	public Occupant addOccupant(final String occupantName, final int occupantAge, final String occupation, final int householdId) 
	{
		if(!householdDao.occupantExists(occupantName)) {
			// how this works is it checks if the household was added by trying to find its primary key id
			return householdDao.findOccupantById(
					householdDao.addAOccupant(occupantName, occupantAge, occupation, householdId)
					);
		}
		log.error(occupantName + " is already in the database.");
		return null;
	}
	
	public int changeHousehold(int occupantId, int householdId)
	{
		if(!householdDao.occupantIdExists(occupantId)) {
			log.error(occupantId + " is not in database.");
			return 0;
		}
		if(!householdDao.householdIdExists(householdId)) {
			log.error(householdId + " is householdId in the database.");
			return 0;
		}
		
		int countChanged = householdDao.changeOccupantHousehold(occupantId, householdId);
		if (countChanged == 0) {
			log.error(occupantId + " is not in the database.");
		}
		
		return countChanged;
	}
	
	public int deleteHousehold(int householdId) 
	{
		int numberDeleted = householdDao.deleteHouseholdbyId(householdId);
		if(numberDeleted == 0) {
			log.error("deleteHousehold: No such household with id " + householdId);
		}
		return numberDeleted;
	}
	
	public int deleteOccupantId(int occupantId) 
	{
		int numberDeleted = householdDao.deleteOccupant(occupantId);
		if(numberDeleted == 0) {
			log.error("deleteHousehold: No such occupant with id " + occupantId);
		}
		return numberDeleted;
	}
	
	public int averageAgeOfHouseholders() 
	{
		return householdDao.avgHouseholdersAge();
	}
	
	public int countStudentOccupants()
	{
		return householdDao.countOccupantStudents();
	}
}
