package ie.cian.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Occupant {
	private int occupantId;
	private String occupantName;
	private int occupantAge;
	private String occupation;
	private int householdId;
	
	public Occupant(String occName, int occAge, String occ, int houseId) {
		this.occupantName = occName;
		this.occupantAge = occAge;
		this.occupation = occ;
		this.householdId = houseId;
	}
}
