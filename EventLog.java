/* EventLog.java
 * Description: List of event logs for ISSM Project
 * Status: Incomplete
 * Version: 2020.4.30-1
 * Authors: Christopher Herras-Antig
 */

public class EventLog {
	Customer customerID;
	float timeOfEvent;
	
	public EventLog(Customer customerID, float timeOfEvent) {
		this.customerID = customerID;
		this.timeOfEvent = (timeOfEvent);
	}
	
	public Customer getCustomer() {
		return customerID;
	}
	
	public float getTimeOfEvent() {
		return timeOfEvent;
	}
}
