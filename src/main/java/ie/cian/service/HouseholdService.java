package ie.cian.service;

import java.util.List;
import ie.cian.classes.Household;

public interface HouseholdService {
	Household findHousehold(String eircode);
	
	Household addAHousehold(final Household household);
}
