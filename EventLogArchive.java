/* EventLogList.java
 * Description: List of event logs for ISSM Project
 * Status: Incomplete
 * Version: 2020.4.30-1
 * Authors: Christopher Herras-Antig
 */

import java.util.*;

	

public class EventLogArchive {
	// Declare all Lists
	ArrayList<EventLog> balkList;
	ArrayList<EventLog> renegeList;
	ArrayList<EventLog> jockeyList;
	ArrayList<ProcessLog> processList;
	ArrayList<Float> busyTrack;
	ArrayList<EventLog> queue1Track;
	ArrayList<EventLog> queue2Track;
	
	public EventLogArchive() {
		// Init all Lists
		balkList = new ArrayList<EventLog>();
		renegeList = new ArrayList<EventLog>();
		jockeyList = new ArrayList<EventLog>();
		processList = new ArrayList<ProcessLog>();
		busyTrack = new ArrayList<Float>();
		queue1Track = new ArrayList<EventLog>();
		queue2Track = new ArrayList<EventLog>();
	}
	
	public void addBalkEvent(Customer input, float timeOfBalk) {
		balkList.add(new EventLog(input, timeOfBalk));
	}
	
	public void addRenegeEvent(Customer input, float timeOfRenege) {
		renegeList.add(new EventLog(input, timeOfRenege));
	}
	
	public void addJockeyEvent(Customer input, float timeOfJockey) {
		jockeyList.add(new EventLog(input, timeOfJockey));
	}
	
	public void addProcessEvent(int server, Customer input, float startTime, float endTime) {
		processList.add(new ProcessLog(server, input, startTime, endTime));
	}
	
	public void addBusyEvent(float time) {
		busyTrack.add(time);
	}
	
	public void trackQueue1(int customers, float time) {
		queue1Track.add(new EventLog(customers, time));
	}
	
	public void trackQueue2(int customers, float time) {
		queue2Track.add(new EventLog(customers, time));
	}
	
	public String getBalkSummary() {
		String output = "Balk Events:\n";
		for (EventLog i : balkList) {
			output += (i.getCustomer().getName() + " balked at " + i.getTimeOfEvent() +"\n");
		}
		output += ("Total customers balked: " + balkList.size());
		return output;
	}
	
	public String getRenegeSummary() {
		String output = "Renege Events:\n";
		for (EventLog i : renegeList) {
			output += (i.getCustomer().getName() + " reneged at " + i.getTimeOfEvent() +"\n");
		}
		output += ("Total customers reneged: " + renegeList.size());
		return output;
	}
	
	public String getJockeySummary() {
		String output = "Jockey Events:\n";
		for (EventLog i : jockeyList) {
			output += (i.getCustomer().getName() + " jockeyed at " + i.getTimeOfEvent() +"\n");
		}
		output += ("Total customers jockied: " + jockeyList.size());
		return output;
	}
	
	public String getProcessSummary() {
		String output = "Process Events:\n";
		for (ProcessLog i : processList) {
			output += (i.getCustomer().getName() + " was processed at " + i.getStartTime() +" and finished processing at " + i.getEndTime() + ". This took " + i.getProcessDuration() + " minutes.");
		}
		output += ("Total customers jockied: " + jockeyList.size());
		return output;
	}
	
	public ArrayList<Float> getBusyTrack() {
		if(busyTrack.size() >= 1)
		{
			return busyTrack;
		}
		else
		{
			return new ArrayList<Float>();
		}
	}
	
	public ArrayList<EventLog> getQueue1Track() {
		if(queue1Track.size() == 0)
		{
			return new ArrayList<EventLog>();
		}
		else
		{
			return queue1Track;
		}
	}
	
	public ArrayList<EventLog> getQueue2Track() {
		if(queue2Track.size() == 0)
		{
			return new ArrayList<EventLog>();
		}
		else
		{
			return queue2Track;
		}
	}
}
