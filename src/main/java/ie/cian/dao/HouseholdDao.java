package ie.cian.dao;

import java.util.List;
import ie.cian.classes.Household;
import ie.cian.classes.Occupant;

public interface HouseholdDao {
	
	//Add a household, along with its occupant(s)
	int addAHousehold(final Household household);
	
	// Add a new person and assign that person to a household
	int addAOccupant(final String occupantName, final int occupantAge, final String occupation, final int householdId);
	
	// Search for a household by Eircode, listing the details of the people in the household
	List<Occupant> findHouseholdOccupants(String eircode);
	
	// Move a person from one household to another
	int changeOccupantHousehold(int occupantId, int householdId);
	
	// Delete a household, along with its occupants
	int deleteHouseholdbyId(int householdId);
	
	// Delete a person
	int deleteOccupant(int occuapntId);
	
	// the average age of householders
	int avgHouseholdersAge();
	
	// the average age of householders
	int countOccupantStudents();
	
	// the average age of householders
	int oapNumber();
	
	boolean householdExists(String eircode);
	
	boolean occupantExists(String occupantName);
	
	boolean householdIdExists(int householdId);
	
	boolean occupantIdExists(int occupantId);
	
	Household findHouseholdByHouseholdEircode(String eircode);
	
	Household findHouseholdByHouseholdId(int id);
	
	Occupant findOccupantById(int id);
	
}
