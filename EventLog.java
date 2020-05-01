/* EventLog.java
 * Description: List of event logs for ISSM Project
 * Status: Incomplete
 * Version: 2020.4.30-1
 * Authors: Christopher Herras-Antig
 */

public class EventLog {
	Customer customerID;
	float timeOfEvent;
	int numCustomers;
	
	public EventLog(Customer customerID, float timeOfEvent) {
		this.customerID = customerID;
		this.timeOfEvent = (timeOfEvent);
	}
	
	//Secondary constructor, only for use with queue tracking
	public EventLog(int numCustomers, float timeOfEvent) {
		this.numCustomers = numCustomers;
		this.timeOfEvent = timeOfEvent;
	}
	
	public Customer getCustomer() {
		return customerID;
	}
	
	public float getTimeOfEvent() {
		return timeOfEvent;
	}
	
	public int getNumCustomers() {
		return numCustomers;
	}
}
