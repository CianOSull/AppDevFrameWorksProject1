package ie.cian.service;

import java.util.List;
import ie.cian.classes.Household;
import ie.cian.classes.Occupant;

public interface HouseholdService {
	//Add a household, along with its occupant(s)
	Household addAHousehold(final Household household);
	
	// Add a new person and assign that person to a household
	public Occupant addOccupant(final String occupantName, final int occupantAge, final String occupation, final int householdId);
	
	// Search for a household by Eircode, listing the details of the people in the household
	List<Occupant> findOccupantsHouseholdEircode(String eircode);
	
	// Move a person from one household to another
	int changeOccupantHousehold(int occupantId, int householdId); 
	
	// Delete a household, along with its occupants
	public int deleteHousehold(int householdId);
	
	// Delete a person
	int deleteOccupantId(int occupantId);
	
	// the average age of householders
	int averageAgeOfHouseholders();
	
	// the average age of householders
	int countStudentOccupants();
	
	// the average age of householders
	int numberOfOap();
	
	Household findHousehold(String eircode);
	
}
