
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
}
