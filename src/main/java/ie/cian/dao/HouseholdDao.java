package ie.cian.dao;

import java.util.List;
import ie.cian.classes.Household;
import ie.cian.classes.Occupant;

public interface HouseholdDao {
	
	boolean exists(String eircode);
	
	Household findHouseholdByHouseholdEircode(String eircode);
	
	Household findHouseholdByHouseholdId(int id);
	
	int addAHousehold(final Household household);
	
	List<Occupant> findHouseholdOccupants(String eircode);
}
