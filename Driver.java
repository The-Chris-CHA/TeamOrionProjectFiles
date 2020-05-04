/* Driver.java
 * Description: Driver class for ISSM project.
 * Status: Incomplete
 * Version: 2020.5.02-1
 * Authors: Christopher Herras-Antig, Dylan Ott, Kevin Bullock, Name 4
 */

import java.io.*;
import java.util.ArrayList;
//import java.util.Random; -- imported for ease of testing early on

public class Driver {
	
	// Declare all essentials
	static BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
	
	static int numServers, balkThreshold, jockeyDestThreshold, jockeyOriginThreshold;
	static float renegeChance, balkChance, totalTime, renegeThreshold;
	static String inputStr, inputVal;
	
	static Server server1 = new Server();
	static Server server2 = new Server();
	
	static EventLogArchive events = new EventLogArchive();
	
	//static Random rng = new Random(); // To Be Removed
	static PseudoRNG prng = new PseudoRNG();
	
	// Begin Subprograms
	public static void main(String[] args) throws IOException {
		// Init vars
		boolean exitLoop = false;
		totalTime = 0.0f; // This will be one of the most important vars
		numServers = 1;
		
		// Event Threshold -- STATIC FOR TESTING
		renegeChance = 0.75f; 
		renegeThreshold = 5.0f;
		balkChance = 0.25f;
		balkThreshold = 5; 
		jockeyOriginThreshold = 2;
		jockeyDestThreshold = 1;
		
		
		// Bootup Message
		System.out.println("Welcome to the SSM Queue and Server System!"
				+ "\nTeam: Team Orion."
				+ "\nMembers: ChrisH, DylanO, BrianR, KevinB"
				+ "\nSelect an Option for the System:\n");
		
		// Menu Inputs
		// Separated on different print functions for easy editing
		System.out.println("1. Create a Customer");
		System.out.println("2. Process a Customer");
		System.out.println("3. Open the second server");
		System.out.println("4. Close the second server");
		System.out.println("5. Find q-hat");
		System.out.println("6. Find u-hat");
		System.out.println("7. Find B(t)");
		System.out.println("8. Report on balking customers");
		System.out.println("9. Report on reneging customers");
		System.out.println("10. Report on jockeying customers");
		System.out.println("11. Quit\n");
		
		//Take user input
		while (exitLoop != true) {
			System.out.print("Make your selection now: ");
			inputStr = input.readLine();
			// DSA Habits System.out.println(inputStr);
			
			switch (inputStr) {
			case "1":
				createCustomer();
				break;
				
			case "2":
				if (server2.getSize() == 0 && server1.getSize() != 0) // Only people in Aisle 1
					processSingleCustomer(1);
				else
					if (server1.getSize() == 0 && server2.getSize() != 0) // Only people in Aisle 2
						processSingleCustomer(2);
					else
						if (server1.getSize() != 0 && server2.getSize() != 0) // People in both Aisles
							processTwoCustomers();
						else
							System.out.println("There is no one lined up for checkout.\n");
				break;
				
			case "3":
				// Code for opening second server
				openServer2();
				break;
				
			case "4":
				// Code for closing second server
				closeServer2();
				break;
				
			case "5":
				// Code for finding q-hat
				getQHat1();
				getQHat2();
				break;
				
			case "6": 
				// Code for finding u-hat
				float uhat = getBT();
				System.out.println("The u-hat of this server model is : " + uhat);
				break;
				
			case "7":
				// Code for finding b(t)
				float bt = getBT();
				System.out.println("The B(t) for this server model is : " + bt);
				break;
				
			case "8":
				// Code for reporting on balking
				balkReport();
				break;
				
			case "9":
				// Code for reporting on Reneging
				renegeReport();
				break;
				
			case "10":
				// Code for reporting on jockeying
				jockeyReport();
				break;
				
			case "11":
				// Code for graceful exit
				System.out.println("Exiting program.");
				exitLoop = true;
				break;
				
			default:
				System.out.println("Invalid input.\n");
			} // End Switch
		} // End While
		
		// Should be safe exit
		System.out.println("Debug: Graceful Exit.");
		System.exit(0);
	}


	// Utility Functions
	public static float giveRandomChance() {
		// To be replaced with in-house code.
		return (prng.nextFloat());
	}
	
	public static float giveRandomTimeLong() {
		// Should output something between 1.0 - 2.5.
		// To be replaced with in-house code.
		return (1.0f + (2.5f - 1.0f)*prng.nextFloat());
	}
	
	public static float giveRandomTimeShort() {
		// Should output something between 0.0 - 1.0
		// To be replaced with in-house code.
		return (prng.nextFloat());
	}
	
	public static void checkRenege(int server) {
		int size = 0;
		Customer temp;
		
		// Check server 1 line
		if (server == 1) {
			size = server1.getSize();
			
			for(int i = 0; i < size; i++)
			{
				temp = server1.dequeue();
				
				if ((totalTime - temp.getTime()) > renegeThreshold) { // Renege check if time in queue is beyond threshold
					if (giveRandomChance() > renegeChance) { // Balk if RNG above renege chance
						// Log Renege
						events.addRenegeEvent(temp, totalTime);
						System.out.println(temp.getName() + " reneged at " + totalTime + " | They were in line for " + (totalTime - temp.getTime()) + " minutes.");
					}
					else 
						server1.enqueue(temp);
				}
				else {
					server1.enqueue(temp);
				} // End Master Conditional
			} // End For Loop
		} // End Server 1 Check
		
		// Check server 2 line
		if (server == 2) {
			size = server2.getSize();
	
			for(int i = 0; i < size; i++)
			{
				temp = server2.dequeue();
				
				if ((totalTime - temp.getTime()) > renegeThreshold) { // Renege check if time in queue is beyond threshold
					if (giveRandomChance() > renegeChance) { // Balk if RNG above renege chance
						// Log Renege
						events.addRenegeEvent(temp, totalTime);
						System.out.println(temp.getName() + " reneged at " + totalTime + " | They were in line for " + (totalTime - temp.getTime()) + " minutes.");
					}
					else 
						server2.enqueue(temp);
				}
				else {
					server2.enqueue(temp);
				} // End Master Conditional
			} // End For Loop
		} // End Server 2 Check
	} // End Renege Method
	
	// Main Methods
	// Case 1
	public static void createCustomer() throws IOException {
		System.out.print("Customer Name: ");
		inputStr = input.readLine();
		
		System.out.print("Number of Items: ");
		inputVal = input.readLine();
		
		if (numServers == 1) {
			if ((server1.getSize() > balkThreshold) && (giveRandomChance() < balkChance)) { // Balk calculation
				// Log balk
				System.out.println("Customer " + inputStr + " has balked with " + inputVal + " items.\n");
				events.addBalkEvent(new Customer(inputStr, Integer.parseInt(inputVal), totalTime), totalTime);
			}
			else {
				// Customer enters queue despite being greater than threshold. 50/50 chance.
				if (server1.getSize() == 0) {
					totalTime += giveRandomTimeLong(); // Simulating the fact that time passes between batches of customers
					
					events.addBusyEvent(totalTime); //Queue going from empty to 'busy'
				}
				server1.enqueue(new Customer(inputStr, Integer.parseInt(inputVal), totalTime));
				System.out.println(inputStr + " got in line 1 for checkout at " + totalTime + " with " + inputVal + " items.\n");
			}
		}
		
		// If there are two servers
		else {
			if (server1.getSize() <= server2.getSize()) { // Add to server 1 if line size is <= line 2
				if ((server1.getSize() > balkThreshold) && (giveRandomChance() < balkChance)) { // Balk calculation
					// Log balk
					System.out.println("Customer " + inputStr + " has balked with " + inputVal + " items.\n");
					events.addBalkEvent(new Customer(inputStr, Integer.parseInt(inputVal), totalTime), totalTime);
				}
				else {
					// Customer enters queue despite being greater than threshold. 50/50 chance.
					if (server1.getSize() == 0) {
						totalTime += giveRandomTimeLong(); // Simulating the fact that time passes between batches of customers
						
						events.addBusyEvent(totalTime); //Queue going from empty to "busy"
					}
					server1.enqueue(new Customer(inputStr, Integer.parseInt(inputVal), totalTime));
					System.out.println(inputStr + " got in line 1 for checkout at " + totalTime + " with " + inputVal + " items.\n");
				
					
				}
			} // End Server 1 Conditional
			else { // Add to server 2
				if ((server2.getSize() > balkThreshold) && (giveRandomChance() < balkChance)) { // Balk calculation
					// Log balk
					System.out.println("Customer " + inputStr + " has balked with " + inputVal + " items.\n");
					events.addBalkEvent(new Customer(inputStr, Integer.parseInt(inputVal), totalTime), totalTime);
				}
				else {
					// Customer enters queue despite being greater than threshold. 50/50 chance.
					server2.enqueue(new Customer(inputStr, Integer.parseInt(inputVal), totalTime));
					System.out.println(inputStr + " got in line 2 for checkout at " + totalTime + " with " + inputVal + " items.\n");
				}
			} // End Server 2 Conditional
		}
		
		events.trackQueue1(server1.getSize(), totalTime);//Log the current state of server1 and server 2 after customer creation
		events.trackQueue2(server2.getSize(), totalTime);
	} // End createCustomer
	
	// Case 2
	public static void processSingleCustomer(int server) {
		Customer temp;
		if (server == 1) {
			if (server1.peek() != null) {
				temp = server1.dequeue();
				
				// Jockey Conditional
				if (numServers == 2 && server2.getSize() <= jockeyDestThreshold && server1.getSize() >= jockeyOriginThreshold) {
					// Jockey if server2 has noone in line and there's other people in your line.
					server2.enqueue(temp);
					
					// Log Jockey
					events.addJockeyEvent(temp, totalTime); // Assuming jockey is near instant
					System.out.println(temp.getName() + "jockeyed over to server 2 at " + totalTime);
				}
				else {
					// Time calculation
					// TimeToComeUpToCheckout + TimeToScan + TimeToPay = ProcessTime
					float startTime = totalTime;
					totalTime += (giveRandomTimeShort() + ((giveRandomTimeShort()/2) * temp.getItems()) + giveRandomTimeLong());
					float endTime = totalTime;
					
					// Log event
					System.out.println("Aisle 1: " + temp.getName() + " began checking-out at " + startTime + " and finished checking-out at " + endTime + ". | This took " + (endTime-startTime) +" minutes.\n");
					events.addProcessEvent(1, temp, startTime, endTime);
					
					checkRenege(1);
					
				}
				
			}
			else {
				System.out.println("The queue is empty. No customers to process.\n"
						+ "If you're reading this, this is an error.");
			}
		}
		
		if (server == 2) {
			if (server2.peek() != null) {
				temp = server2.dequeue();
				
				// Jockey Conditional
				if (numServers == 2 && server1.getSize() <= jockeyDestThreshold && server2.getSize() >= jockeyOriginThreshold) {
					// Jockey if server2 has noone in line and there's other people in your line.
					server1.enqueue(temp);
					
					// Log Jockey
					events.addJockeyEvent(temp, totalTime); // Assuming jockey is near instant
					System.out.println(temp.getName() + "jockeyed over to server 1 at " + totalTime);
				}
				else {
					// Time calculation
					// TimeToComeUpToCheckout + TimeToScan + TimeToPay = ProcessTime
					float startTime = totalTime;
					totalTime += (giveRandomTimeShort() + ((giveRandomTimeShort()/2) * temp.getItems()) + giveRandomTimeLong());
					float endTime = totalTime;
					
					// Log event
					System.out.println("Aisle 2: " + temp.getName() + " began checking-out at " + startTime + " and finished checking-out at " + endTime + ". | This took " + (endTime-startTime) +" minutes.\n");
					events.addProcessEvent(1, temp, startTime, endTime);
					
					checkRenege(2);
				}
				
			}
			else {
				System.out.println("The queue is empty. No customers to process.\n"
						+ "If you're reading this, this is an error.");
			}
		}
		
		
		//Empty queue check for B(t)
		if(server1.getSize() == 0 && server2.getSize() == 0)
		{
			events.addBusyEvent(totalTime);
		}
		
		events.trackQueue1(server1.getSize(), totalTime);//Log the state of server1 and server 2 after processSingleCustomer\
		events.trackQueue2(server2.getSize(), totalTime);
	}
	
	// Case 2
	public static void processTwoCustomers() {
		Customer temp1, temp2;
		float server1Start, server1End, server2Start, server2End;
		
		server1End = 0;
		server2End = 0;
		
		// Server 1 handles first
		temp1 = server1.dequeue();
		
		if (numServers == 2 && server2.getSize() <= jockeyDestThreshold && server1.getSize() >= jockeyOriginThreshold) {
			// Jockey
			server2.enqueue(temp1);
			
			// Log Jockey
			events.addJockeyEvent(temp1, totalTime); // Assuming jockey is near instant
			System.out.println(temp1.getName() + "jockeyed over to server 2 at " + totalTime);
		}
		else {
			server1Start = totalTime;
			server1End = (server1Start + (giveRandomTimeShort() + ((giveRandomTimeShort()/2) * temp1.getItems()) + giveRandomTimeLong()));
			
			// Log Event/Process
			System.out.println("Aisle 1: " + temp1.getName() + " began checking-out at " + server1Start + " and finished checking-out at " + server1End + ". | This took " + (server1End-server1Start) +" minutes.\n");
			events.addProcessEvent(1, temp1, server1Start, server1End);
		} // End of Server 1 Process
		
		// Server 2 Handling
		temp2 = server2.dequeue();
		if (numServers == 2 && server1.getSize() <= jockeyDestThreshold && server2.getSize() >= jockeyOriginThreshold) {
			// Jockey
			server1.enqueue(temp2);
			
			events.addJockeyEvent(temp2, totalTime); // Assuming jockey is near instant
			System.out.println(temp2.getName() + "jockeyed over to server 1 at " + totalTime);
		}
		else {
			server2Start = totalTime;
			server2End = (server2Start + (giveRandomTimeShort() + ((giveRandomTimeShort()/2) * temp2.getItems()) + giveRandomTimeLong()));
			
			// Log Event/Process
			System.out.println("Aisle 2: " + temp2.getName() + " began checking-out at " + server2Start + " and finished checking-out at " + server2End + ". | This took " + (server2End-server2Start) +" minutes.\n");
			events.addProcessEvent(1, temp1, server2Start, server2End);
		}
		
		// Figure out which event took longer so we know how much time elapsed.
		if (server1End > server2End)	
			totalTime = server1End;
		else	
			totalTime = server2End;
		
		// Finally check for reneges.
		checkRenege(1);
		checkRenege(2);
		
		
		//Empty queue check
		if(server1.getSize() == 0 && server2.getSize() == 0)
		{
			events.addBusyEvent(totalTime);
		}
		events.trackQueue1(server1.getSize(), totalTime);//Log the state of server1 and server2 after processTwoCustomers
		events.trackQueue2(server2.getSize(), totalTime);
	}
	
	// Case 3
	public static void openServer2() {
		if (numServers == 2) {
			System.out.println("Second aisle is already opened!\n");
		}
		else {
			numServers += 1; // SHOULD equal 2. 
			System.out.println("Aisle 2 has been opened!\n");
		}
	}
	
	// Case 4
	public static void closeServer2() {
		if (numServers == 2) {
			numServers -= 1; // Should close one server and go back to one.
			
			System.out.println("Aisle 2 is closing. Customers are being shifted to aisle 1.");
			Customer temp;
			// Code for dequeueing entire server and entering them into new server.
			// CHECK FOR BALK
			while(server2.peek() != null) {
				temp = server2.dequeue();
				if ((server1.getSize() > balkThreshold) && (giveRandomChance() > 0.75f)) {
					// Balk
					System.out.println("Customer " + temp.getName() + " has balked with " + temp.getItems() + " items at " + totalTime);
					events.addBalkEvent(temp, totalTime);
				}
				else {
					// Enqueue into Line 1
					server1.enqueue(temp);
					System.out.println(temp.getName() + " moved to line 1 for checkout at " + totalTime + " with " + temp.getItems() + " items.\n");
				}
			}
		}
		else {
			System.out.println("Second server is already closed!\n");
		}
	}
	
	// Case 5
	public static void getQHat1() {
		//1*(time of one customer in queue) + 2*(time of two in queue) + etc...
		
		ArrayList<EventLog> queueTrack = events.getQueue1Track();
		
		
		if(queueTrack.size() == 0)
		{
			System.out.println("The server hasn't been used!");
		}
		else
		{
			float qhat = 0;
			int size = server1.getSize();
			float startingTime = 0;
			
			for(int i = 0; i < size; i++)
			{
				EventLog curr = queueTrack.get(i);
				
				if(queueTrack.get(i+1) != null) //NOT the final pass yet
				{
					EventLog next = queueTrack.get(i+1);
					startingTime = curr.getTimeOfEvent();
					
					if(curr.getNumCustomers() == next.getNumCustomers())
					{
						//no change in queue length
						//do nothing
					}
					else
					{
						//do qhat calculation for that chunk of time with x customers
						qhat += curr.getNumCustomers()*(next.getTimeOfEvent() - startingTime);
						//reset starting point to new time
						startingTime = next.getTimeOfEvent();
					}
				}
				else //Last pass (i == size - 1)
				{
					qhat += curr.getNumCustomers()*(totalTime - startingTime);
				}
				
			}
			
			
			
			System.out.println("The q-hat of Aisle 1 is : " + qhat + ".");
			
		}
		
	}
	
	// Case 5
	public static void getQHat2() {
	//1*(time of one customer in queue) + 2*(time of two in queue) + etc...
		
		ArrayList<EventLog> queueTrack = events.getQueue2Track();
			
		
		if(queueTrack.size() == 0)
		{
			System.out.println("The server hasn't been used!");
		}
		else
		{
			float qhat = 0;
			int size = server2.getSize();
			float startingTime = 0;
			
			for(int i = 0; i < size; i++)
			{
				EventLog curr = queueTrack.get(i);
				
				if(queueTrack.get(i+1) != null) //NOT the final pass yet
				{
					EventLog next = queueTrack.get(i+1);
					startingTime = curr.getTimeOfEvent();
				
					if(curr.getNumCustomers() == next.getNumCustomers())
					{
						//no change in queue length
						//do nothing
					}
					else
					{
						//do qhat calculation for that chunk of time with x customers
						qhat += curr.getNumCustomers()*(next.getTimeOfEvent() - startingTime);
						//reset starting point to new time
						startingTime = next.getTimeOfEvent();
					}
				}
				else //Last pass (i == size - 1)
				{
					qhat += curr.getNumCustomers()*(totalTime - startingTime);
				}
					
			}
				
				
				
			System.out.println("The q-hat of Aisle 2 is : " + qhat + ".");
				
		}
			
	}
	
	// Case 7
	public static float getBT() {
		//Total time of customers in queue / Total time of queue running
		ArrayList<Float> busyTrack = events.getBusyTrack();
		
		if(busyTrack.size() == 0)
		{
			//no customers ever entered queue
			System.out.println("The server has not been used!");
			return 0.0f;
		}
		else
		{
			int i = 0;
			float totalBusyTime = 0;
			int size = busyTrack.size();
				
			//calculate all busy times, sum them
			while(i < size)
			{
				totalBusyTime += busyTrack.get(i+1) - busyTrack.get(i);
				i += 2; //i should only be even values
			}
			
			if(size%2 != 0) //account for odd # of events (queue not empty at time of execution)
			{
				totalBusyTime += totalTime - busyTrack.get(size - 1);
			}
			
			
			return totalBusyTime / totalTime;
			
		}
	}
	
	
	// Case 8
	public static void balkReport() {
		System.out.println(events.getBalkSummary());
	}
	
	// Case 9
	public static void renegeReport() {
		System.out.println(events.getRenegeSummary());
	}
	
	// Case 10
	public static void jockeyReport() {
		System.out.println(events.getJockeySummary());
	}
}
