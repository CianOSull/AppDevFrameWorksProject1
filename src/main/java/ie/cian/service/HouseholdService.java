package ie.cian.service;

import java.util.List;
import ie.cian.classes.Household;

public interface HouseholdService {
	int countTheCounties();
	
	int countTheCountriesThatStartWith(char letter);
	
	int deleteHouseholdById(int countyID);
	
	Household findHousehold(int countyId);
	
	List<Household> findAllCounties();
	
	Household addAHousehold(String countyName);
	
	int changeHouseholdName(String oldHouseholdName, String newHouseholdName);
}
