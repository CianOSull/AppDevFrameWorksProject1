package ie.cian;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.cian.classes.Household;
import ie.cian.classes.Occupant;
import ie.cian.service.HouseholdService;

public class MainApp {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		HouseholdService householdService = (HouseholdService) context.getBean("householdServiceImplementation");
		
		// Search for a household by Eircode, listing the details of the people in the household
		System.out.println("Household Occupants of eircode G91HI23: ");
		List<Occupant> occupants = householdService.findOccupantsHouseholdEircode("G91HI23");
		for(Occupant occupant:occupants) {
			System.out.println("\t" + occupant);
		}
		System.out.println("");
		
		//Add a household, along with its occupant(s)
		Household h = new Household("J45KL67", "5 Killarney", null);
		System.out.println("Add 'J45KL67' ==> " + householdService.addAHousehold(h));
		
	}

}
