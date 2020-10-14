package ie.cian.dao;

import java.util.List;
import ie.cian.classes.Household;

public interface HouseholdDao {
	
	int getHouseholdCount();
	
	int getHouseholdCountStartsWith(char letter);
	
	boolean exists(String countyName);
	
	Household findHouseholdByHouseholdId(int countyId);
	
	List<Household> findAllCounties();
	
	int deleteHouseholdById(int countyId);
	
	int changeHouseholdName(String oldHouseholdName, String newHouseholdName);
	
	int addAHousehold(final String countyName);
}
