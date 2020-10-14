package ie.cian.service;

import java.util.List;
import ie.cian.classes.Household;
import ie.cian.classes.Occupant;

public interface HouseholdService {
	Household findHousehold(String eircode);
	
	Household addAHousehold(final Household household);
	
	public Occupant addOccupant(final String occupantName, final int occupantAge, final String occupation, final int householdId);
	
	List<Occupant> findOccupantsHouseholdEircode(String eircode);
	
	int changeHousehold(int occupantId, int householdId); 
}
