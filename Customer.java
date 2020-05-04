/* Customer.java
 * Description: Customer Class for ISSM project
 * Status: Incomplete
 * Version: 2020.4.30-3
 * Authors: Christopher Herras-Antig, Dylan Ott
 */

//import java.util.*; -- Unnecessary import

public class Customer {

	private String name;
	private int numItems;
	private float timeEntered;
	
	public Customer(float timeEntered) {
		// Init
		name = "John Doe";
		numItems = 0;
		this.timeEntered = timeEntered;
	}
	
	public Customer (String name, float timeEntered) {
	
		// Init
		name = "John Doe";
		numItems = 0;
		this.timeEntered = timeEntered;
	}
	
	public Customer (String name, int numItems, float timeEntered) {
		// Init
		this.numItems = numItems;
		this.name = name;
		this.timeEntered = timeEntered;
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
	
	public float getTime() {
		return timeEntered;
	}
}
