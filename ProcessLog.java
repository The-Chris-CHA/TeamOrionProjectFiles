/* EventLogList.java
 * Description: List of event logs for ISSM Project
 * Status: Incomplete
 * Version: 2020.5.01-2
 * Authors: Christopher Herras-Antig
 */

public class ProcessLog {

	Customer customerID;
	float startTime, endTime, processDuration;
	int server;
	
	public ProcessLog(int server, Customer customerID, float startTime, float endTime) {
		this.server = server;
		this.customerID = customerID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.processDuration = (endTime - startTime);
	}
	
	public Customer getCustomer() {
		return customerID;
	}
	
	public float getStartTime() {
		return startTime;
	}
	
	public float getEndTime() {
		return endTime;
	}
	
	public float getProcessDuration() {
		return processDuration;
	}
	
	public float getTotalTimeInSystem() {
		return (endTime - customerID.getTime());
	}
}
