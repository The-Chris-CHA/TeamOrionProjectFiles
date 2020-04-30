/* Customer.java
 * Description: Customer Class for ISSM project
 * Status: Incomplete
 * Version: 2020.4.30-1
 * Authors: Christopher Herras-Antig, Dylan Ott
 */

import java.util.*;

public class Customer {

	private String name;
	private int numItems;
	
	public Customer() {
		// Init
		name = "John Doe";
		numItems = 0;
	}
	
	public Customer (String name) {
	
		// Init
		name = "John Doe";
		numItems = 0;
	}
	
	public Customer (String name, int numItems) {
		// Init
		this.numItems = numItems;
		this.name = name;
	}	
	
	// stub code
	
	public void setName (String name) {
		this.name = name;
	}
	
	public void setItems (int numItems) {
		this.numItems = numItems;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getItems () {
		return this.numItems;
	}
}
