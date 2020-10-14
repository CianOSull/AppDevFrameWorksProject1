package ie.cian.classes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Household {

	private int householdId;
	private String eircode;
	private String address;
	private List<Occupant> occupantList;
	
	public Household(String eir, String add, List<Occupant> occ) {
		this.eircode = eir;
		this.address = add;
		this.occupantList = occ;
	}
}
