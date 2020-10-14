package database_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ie.cian.classes.Hero;
import ie.cian.service.HeroService;

// Extends junit5 with spring
@ExtendWith(SpringExtension.class)
//Which allows us to use the @ContextConfiguration to load the
@ContextConfiguration("classpath:beans.xml")

public class TestHeroService {
	@Autowired
	HeroService heroService;
	
	@Test
	public void testFindHeroByIdExists() {
		Hero c = heroService.findHero(1);
		assertEquals("Superman", c.getHeroName());
	}
	
	@Test
	public void testFindHeroByIdDoesNotExist() {
		Hero c = heroService.findHero(11);
		assertEquals(c, null);
	}
	
	@Test
	public void testHeroInsert() {
		String newHeroName = "Batman";
		Hero newHero = heroService.addAHero("Batman");
		assertEquals(newHero.getHeroName(), newHeroName);
	}
	
	@Test
	public void testHeroInsertAlreadyThere() {
		String newHeroName = "Cork";
		Hero newHero = heroService.addAHero("Superman");
		assertEquals(newHero, null);
	}

}
