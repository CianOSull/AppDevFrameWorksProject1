package ie.cian.dao;

import java.util.List;
import ie.cian.classes.Franchise;
import ie.cian.classes.Hero;

public interface FranchiseDao {
	int countFranchises();
	
	int getFranchiseCountStartsWith(char letter);
	
	boolean exists(String franchiseName);
	
	Franchise findFranchiseByFranchiseId(int franchiseId);
	
	List<Franchise> findAllCounties();
	
	int deleteFranchiseById(int franchiseId);
	
	int changeFranchiseName(String oldFranchiseName, String newFranchiseName);
	
	Hero findFranchiseHeroId(int franchiseId);
}
