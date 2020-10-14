package ie.cian.classes;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Franchise {

	private String franchiseName;
	private int franchiseId;
	private int heroId;
	
	public Franchise(String franchiseName) {
		this.franchiseName = franchiseName;
	}
}
