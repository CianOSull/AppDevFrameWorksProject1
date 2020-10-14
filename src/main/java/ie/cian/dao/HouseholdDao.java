package ie.cian.dao;

import java.util.List;
import ie.cian.classes.Household;
import ie.cian.classes.Occupant;

public interface HouseholdDao {
	
	boolean householdExists(String eircode);
	
	boolean occupantExists(String occupantName);
	
	boolean householdIdExists(int householdId);
	
	boolean occupantIdExists(int occupantId);
	
	Household findHouseholdByHouseholdEircode(String eircode);
	
	Household findHouseholdByHouseholdId(int id);
	
	Occupant findOccupantById(int id);
	
	int addAHousehold(final Household household);
	
	int addAOccupant(final String occupantName, final int occupantAge, final String occupation, final int householdId);
	
	List<Occupant> findHouseholdOccupants(String eircode);
	
	int changeOccupantHousehold(int occupantId, int householdId);
	
	int deleteHouseholdbyId(int householdId);
	
	int deleteOccupant(int occuapntId);
	
	int avgHouseholdersAge();
	
	int countOccupantStudents();
	
	int oapNumber();
	
}
