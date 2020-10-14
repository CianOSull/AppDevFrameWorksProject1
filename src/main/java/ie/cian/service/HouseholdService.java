package ie.cian.service;

import java.util.List;
import ie.cian.classes.Household;
import ie.cian.classes.Occupant;

public interface HouseholdService {
	Household findHousehold(String eircode);
	
	Household addAHousehold(final Household household);
	
	List<Occupant> findOccupantsHouseholdEircode(String eircode);
}
