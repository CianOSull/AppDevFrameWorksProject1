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
		
//		HeroService heroService = (HeroService) context.getBean("heroServiceImplementation");
//		FranchiseService franchiseService = (FranchiseService) context.getBean("franchiseServiceImplementation");
		
	}

}
