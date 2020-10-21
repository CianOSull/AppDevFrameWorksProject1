package ie.cian;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.cian.classes.Household;
import ie.cian.classes.Occupant;
import ie.cian.service.HouseholdService;

public class MainApp {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		HouseholdService householdService = (HouseholdService) context.getBean("householdServiceImplementation");
		
		boolean done = false;
		
		// Test scanner
		Scanner scan = new Scanner(System.in);
		
		while(!done) {
			int input = 0;
			
			System.out.println("Enter a number from the list below for what you want to do:"
					+ "\n 1. Search for a household by Eircode, listing the details of the people in the household."
					+ "\n 2. Add a household, along with its occupant(s)."
					+ "\n 3. Add a new person and assign that person to a household."
					+ "\n 4. Move a person from one household to another."
					+ "\n 5. Delete a household, along with its occupants."
					+ "\n 6. Delete a person."
					+ "\n 7. Display some statistics:"
					+ "\n\tThe average age of householders."
					+ "\n\tThe number of students in the households."
					+ "\n\tThe number of OAPs (aged 65+) in the household."
					+ "\n 8. Stop."
					+ "\n>>>: ");
			
			input = scan.nextInt();
			// Clear the \n character that nextInt does not clear
			scan.nextLine();
			
			
			switch(input) {
			
			// Search for a household by Eircode, listing the details of the people in the household
			case 1:
				// Example eircode G91HI23
				System.out.println("Enter the eircode of the household (be mindful of spaces) >>>: ");
				
				// Input eircode
				String eircode = scan.nextLine();
				
				System.out.println("Household Occupants of eircode " + eircode + ": ");
				List<Occupant> occupants = householdService.findOccupantsHouseholdEircode(eircode);
				
				if (occupants == null) 
				{
					System.out.println("No household was found or the household has no occupants.");
				}
				else 
				{
					for(Occupant occupant:occupants) {
						System.out.println("\t" + occupant);
					}
				}
				
				System.out.println("");
				break;
				
			//Add a household, along with its occupant(s)
			case 2:
				System.out.println("Do you want to add occupant(s) to this household? y\\n: ");
				String confirm = scan.nextLine();
				
				// Create the occupant list but by default make it null
				List<Occupant> occList = null;
				
				if(confirm.equals("y"))
				{
					System.out.println("Enter the how many occupants you would like to add:");
					int occCount = scan.nextInt();
					// Clear the \n character that nextInt does not clear
					scan.nextLine();
					
					occList = new ArrayList();
					
					for(int i = 0; i < occCount; i++) {
						System.out.println("Enter the name of occupant " + (i+1) + ":");
						String name = scan.nextLine();
						System.out.println("Enter the age of occupant " + (i+1) + ":");
						int age = scan.nextInt();
						// Clear the \n character that nextInt does not clear
						scan.nextLine();
						System.out.println("Enter the occupation of occupant " + (i+1) + ":");
						String occupation = scan.nextLine();
						
						// Example occupants
//						Occupant occ = new Occupant("TestName1", 9, "scholar");
//						Occupant occ2 = new Occupant("TestName2", 3, "pre-school");
//						Occupant occ3 = new Occupant("TestName3", 18, "scholar");
						Occupant occ = new Occupant(name, age, occupation);
						occList.add(occ);
					}
				}
				
				System.out.println("Enter the eircode of the household:");
				String houseEircode = scan.nextLine();
				System.out.println("Enter the address of the household:");
				String address = scan.nextLine();
				
				// Example household
//				Household h = new Household("J45KL67", "5 Killarney", occList);
				Household household = new Household(houseEircode, address, occList);
				System.out.println("Add 'J45KL67' ==> " + householdService.addAHousehold(household));
				System.out.println("");
				break;
				
			// Add a new person and assign that person to a household
			case 3:
				System.out.println("Enter the name of occupant:");
				String name = scan.nextLine();
				System.out.println("Enter the age of occupant:");
				int age = scan.nextInt();
				// Clear the \n character that nextInt does not clear
				scan.nextLine();
				System.out.println("Enter the occupation of occupant:");
				String occupation = scan.nextLine();
				System.out.println("Enter household id of new occupant");
				int householdId = scan.nextInt();
				// Clear the \n character that nextInt does not clear
				scan.nextLine();
				
//				System.out.println("Add 'Joseph ken' ==> " + householdService.addOccupant("Joseph Ken", 20, "scholar", 2));
				System.out.println("Add '" + name + "' ==> " + householdService.addOccupant(name, age, occupation, householdId));
				System.out.println("");
				break;
				
			// Move a person from one household to another
			case 4:
				System.out.println("Enter the id of occupant:");
				int occId = scan.nextInt();
				System.out.println("Enter the id of household:");
				int houseId = scan.nextInt();
				
//				System.out.println("Change OccupantId 2 HouseholdId to 1 ==> " + householdService.changeOccupantHousehold(2, 1));
				System.out.println("Change OccupantId 2 HouseholdId to 1 ==> " + householdService.changeOccupantHousehold(occId, houseId));
				System.out.println("");
				break;
			case 5:
				// Delete a household, along with its occupants
				System.out.println("Delete household id = 4 ==> " + householdService.deleteHousehold(4));
				System.out.println("");
				break;
			case 6:
				// Delete a person
				System.out.println("Delete occupant id = 4 ==> " + householdService.deleteOccupantId(4));
				System.out.println("");
				break;
			case 7:
				// the average age of householders
				System.out.println("\nThe average age of householders ==> " + householdService.averageAgeOfHouseholders());
				System.out.println("Amount of occupants who are students in the households ==> " + householdService.countStudentOccupants());
				System.out.println("Amount of OAP ==> " + householdService.numberOfOap());
				System.out.println("");
				break;
			case 8:
				done = true;
				System.out.println("Thank you for putting in inputs.");
				break;
			default:
				System.out.println("You need to input a number a number between 1 and 8");
			}
		}
		
		/*
		 * 4 Test cases are in the src/test/java folder with 2 in each file
		 */
	}

}
