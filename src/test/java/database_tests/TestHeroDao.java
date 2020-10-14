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

import ie.cian.classes.Hero;
import ie.cian.dao.HeroDao;

//Adds SpringExtension i.e. Spring stuff to junit5
// Adds Spring Extension i.e. Spring Stuff to junit5
@ExtendWith(SpringExtension.class)
//Tells it where the context is and class path is default
// Which allows us to use the @ContextConfiguration to load the context
@ContextConfiguration("classpath:beans.xml")
// Execute the tests in the order specified through annotations
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestHeroDao {
	@Autowired
	HeroDao herosDao;
	
	@Test
	@Order(1)
	public void testCountCounties() {
		int count = herosDao.countHeros();
		assertEquals(3, count);
	}
	
	@Test
	@Order(2)
	public void testFindHeroById() {
		Hero c = herosDao.findHeroByHeroId(1);
		assertEquals("Superman", c.getHeroName());
	}
	
	@Test
	@Order(3)
	public void testHeroInsert() {
		String newHeroName = "Donegal";
		int newId = herosDao.addAHero(newHeroName);
		Hero d = herosDao.findHeroByHeroId(newId);
		assertEquals(newHeroName, d.getHeroName());
	}
}
