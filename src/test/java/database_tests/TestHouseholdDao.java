package database_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ie.cian.classes.Household;
import ie.cian.dao.HouseholdDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestHouseholdDao {
	@Autowired
	HouseholdDao householdDao;
	
	@Test
	@Order(1)
	public void testFindHouseholdById() 
	{
		Household first = householdDao.findHouseholdByHouseholdId(1);
		assertEquals("A12BC34", first.getEircode());
	}
	
	@Test
	@Order(2)
	public void testHouseholdInsert() 
	{
		Household newHousehold = new Household("F55FF55", "5 TestCase", null);
		int newId = householdDao.addAHousehold(newHousehold);
		Household foundHousehold = householdDao.findHouseholdByHouseholdId(newId);
		assertEquals(newHousehold.getEircode(), foundHousehold.getEircode());
	}
}
