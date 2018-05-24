package spring.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greet {
	
	private String message = "Hello";
	private String user = "Vinod";
	
	
	// Writable property "venue" 
	// spring can use this for property injection
	public void setVenue(String venue) {
		System.out.printf("Spring passed '%s' for venue property\n", venue);
	}

}
