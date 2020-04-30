/* Customer.java
 * Description: Customer Class for ISSM project
 * Status: Incomplete
 * Version: 2020.4.30-1
 * Authors: Christopher Herras-Antig, Dylan Ott
 */

import java.util.*;

public class Customer {

	private float serveTime, balkChance, jockeyChance, renegeChance;
	private String name;
	private int numItems;
	
	public Customer()
	{
		// set all fields to random #s within 0-1 (1-5 for serveTime)
		// hardcoded values for TESTING ONLY -- REPLACE WITH PRNG VALUES LATER
		serveTime = 2.1f;
		balkChance = 0.1f;
		jockeyChance = 0.3f;
		renegeChance = 0.5f;
		
		// Init
		name = "John Doe";
		numItems = 0;
	}
	
	public Customer (String name) {
		// set all fields to random #s within 0-1 (1-5 for serveTime)
		// hardcoded values for TESTING ONLY -- REPLACE WITH PRNG VALUES LATER
		serveTime = 2.1f;
		balkChance = 0.1f;
		jockeyChance = 0.3f;
		renegeChance = 0.5f;
		
		// Init
		name = "John Doe";
		numItems = 0;
	}
	
	public Customer (String name, int numItems) {
		// set all fields to random #s within 0-1 (1-5 for serveTime)
		// hardcoded values for TESTING ONLY -- REPLACE WITH PRNG VALUES LATER
		serveTime = 2.1f;
		balkChance = 0.1f;
		jockeyChance = 0.3f;
		renegeChance = 0.5f;
		
		// Init
		this.numItems = numItems;
		this.name = name;
	}	

	public Customer (String name, int numItems, float serveTime, float balkChance, float jockeyChance, float renegeChance) {
		// set all fields to random #s within 0-1 (1-5 for serveTime)
		// hardcoded values for TESTING ONLY -- REPLACE WITH PRNG VALUES LATER
		this.serveTime = serveTime;
		this.balkChance = balkChance;
		this.jockeyChance = jockeyChance;
		this.renegeChance = renegeChance;
		
		// Init
		this.numItems = numItems;
		this.name = name;
	}	
	
	// stub code
	public float getServeTime() {
		return serveTime;
	}

	public void setServeTime(float serveTime) {
		this.serveTime = serveTime;
	}

	public float getBalkChance() {
		return balkChance;
	}

	public void setBalkChance(float balkChance) {
		this.balkChance = balkChance;
	}

	public float getJockeyChance() {
		return jockeyChance;
	}

	public void setJockeyChance(float jockeyChance) {
		this.jockeyChance = jockeyChance;
	}

	public float getRenegeChance() {
		return renegeChance;
	}

	public void setRenegeChance(float renegeChance) {
		this.renegeChance = renegeChance;
	}
	
}