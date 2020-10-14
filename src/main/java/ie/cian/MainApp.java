package ie.cian;

import java.util.ArrayList;
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
		Occupant occ1 = new Occupant("TestName1", 9, "scholar");
		Occupant occ2 = new Occupant("TestName2", 3, "pre-school");
		Occupant occ3 = new Occupant("TestName3", 18, "scholar");
		List<Occupant> occ = new ArrayList();
		occ.add(occ1);
		occ.add(occ2);
		occ.add(occ3);
		Household h = new Household("J45KL67", "5 Killarney", occ);
		System.out.println("Add 'J45KL67' ==> " + householdService.addAHousehold(h));
		System.out.println("");
		
		// Add a new person and assign that person to a household
		System.out.println("Add 'Joseph ken' ==> " + householdService.addOccupant("Joseph Ken", 20, "scholar", 2));
		System.out.println("");
		
		// Move a person from one household to another
		System.out.println("Change OccupantId 2 HouseholdId to 1 ==> " + householdService.changeHousehold(2, 1));
		System.out.println("");
		
		// Delete a household, along with its occupants
		System.out.println("Delete household id = 4 ==> " + householdService.deleteHousehold(4));
		System.out.println("");
		
		// Delete a person
		System.out.println("Delete occupant id = 4 ==> " + householdService.deleteOccupantId(4));
		System.out.println("");
		
		// the average age of householders
		System.out.println("The average age of householders ==> " + householdService.averageAgeOfHouseholders());
		System.out.println("");
		
		// the average age of householders
		System.out.println("Amount of occupants who are students in the households ==> " + householdService.countStudentOccupants());
		System.out.println("");
		
		// the average age of householders
		System.out.println("Amount of OAP ==> " + householdService.numberOfOap());
		System.out.println("");
	}

}
