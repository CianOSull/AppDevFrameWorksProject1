package ie.cian;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.cian.classes.Hero;
import ie.cian.classes.Hero;
import ie.cian.service.FranchiseService;
import ie.cian.service.HeroService;

public class MainApp {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		HeroService heroService = (HeroService) context.getBean("heroServiceImplementation");
		FranchiseService franchiseService = (FranchiseService) context.getBean("franchiseServiceImplementation");
		
		System.out.println("There are " + heroService.countHeros() + " heros in the database");
		System.out.println("There are " + heroService.countTheCountriesThatStartWith('c') + " counties in the database that start with 'c'");
		System.out.println("");
		
		List<Hero> heros = heroService.findAllCounties();
		for(Hero hero:heros) {
			System.out.println("\t" + hero);
		}
		System.out.println("");
		
		System.out.println("Find id = 1 ==> " + heroService.findHero(1));
		System.out.println("Find id = 1 ==> " + heroService.findHero(11));
		System.out.println("");
		
		// This parts works its just that since hero id is now a foreign key it has delete protection so causes a crash
		System.out.println("Delete id = 1 ==> " + heroService.deleteHeroById(1));
		System.out.println("Find id = 1 ==> " + heroService.findHero(1));
		System.out.println();
		
		System.out.println("Change 'Batman' to 'Aquaman' ==> " + heroService.changeHeroName("Batman", "Aquaman"));
		System.out.println("Change 'Batman' to 'Aquaman' ==> " +  heroService.changeHeroName("Batman", "Aquaman"));
		System.out.println("Change 'Batman' to 'Superman' ==> " +  heroService.changeHeroName("Batman", "Superman"));
		System.out.println();
		
		System.out.println("Add 'Flash' ==> " + heroService.addAHero("Flash"));
		System.out.println("Add 'Flash' ==> " + heroService.addAHero("Flash"));
		System.out.println();
		
		// Franchise tests
		System.out.println("Find Franchise id = 2 ==> " + franchiseService.findFranchise(2));
		System.out.println("");
		
		// Franchise tests
		System.out.println("Hero under franchise id = 2 ==> " + franchiseService.findFranchiseHero(2));
		System.out.println("");
		
		// Franchise tests
		System.out.println("Records changed ==> " + franchiseService.deleteFranchiseById(2));
		System.out.println("");
	}

}
