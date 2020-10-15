package database_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ie.cian.classes.Occupant;
import ie.cian.service.HouseholdService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestHouseholdService {
	@Autowired
	HouseholdService householdService;
	
	@Test
	@Order(1)
	public void testFindHouseholdOccupantsByEirecode() 
	{
		List<Occupant> occList = householdService.findOccupantsHouseholdEircode("A12BC34");
		String occName = "";
		for(int i = 0; i < occList.size(); i++)
		{
			if(occList.get(i).getOccupantName().equals("Sarah Johnson"))
			{
				occName = occList.get(i).getOccupantName();
			}
		}
		assertEquals("Sarah Johnson", occName);
	}
	
	@Test
	@Order(2)
	public void testHouseholdChanged() 
	{
		int recChanged = householdService.changeOccupantHousehold(1, 2);
		assertEquals(1, recChanged);
	}
	


}
