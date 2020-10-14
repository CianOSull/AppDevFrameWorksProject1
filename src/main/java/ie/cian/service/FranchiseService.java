package ie.cian.service;

import java.util.List;

import ie.cian.classes.Franchise;
import ie.cian.classes.Hero;

public interface FranchiseService {
	int countFranchises();
	
	int countTheCountriesThatStartWith(char letter);
	
	int deleteFranchiseById(int franchiseID);
	
	Franchise findFranchise(int franchiseId);
	
	List<Franchise> findAllCounties();
	
	int changeFranchiseName(String oldFranchiseName, String newFranchiseName);
	
	Hero findFranchiseHero(int franchiseId);
}
