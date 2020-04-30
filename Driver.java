/* Driver.java
 * Description: Driver class for ISSM project.
 * Status: Incomplete
 * Version: 2020.4.30-3
 * Authors: Christopher Herras-Antig, Dylan Ott, Kevin Bullock, Name 4
 */

import java.io.*;
import java.util.Random;

public class Driver {
	
	// Declare all essentials
	static BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
	
	static int numServers, balkThreshold;
	static float renegeChance, totalTime, renegeThreshold;
	static String inputStr, inputVal;
	
	static Server server1 = new Server();
	static Server server2 = new Server();
	
	static EventLogArchive events = new EventLogArchive();
	
	static Random rng = new Random(); // To Be Removed
	
	// Begin Subprograms
	public static void main(String[] args) throws IOException {
		// Init vars
		boolean exitLoop = false;
		totalTime = 0.0f; // This will be one of the most important vars
		numServers = 1;
		
		renegeChance = 0.75f; // Static chance for testing.
		renegeThreshold = 5.0f;
		balkThreshold = 5; // Static chance for testing.
		
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
		System.out.println("8. Report on balking [customers/items]");
		System.out.println("9. Report on reneging [customers/items]");
		System.out.println("10. Report on jockeying [customers/items]");
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
				if (server2.getSize() == 0 && server1.getSize() != 0) 
					processSingleCustomer(1);
				else
					if (server1.getSize() == 0 && server2.getSize() != 0)
						processSingleCustomer(2);
					else
						if (server1.getSize() != 0 && server2.getSize() != 0)
							processTwoCustomers();
						else
							System.out.println("There is no one lined up for checkout.\n");
				break;
				
			case "3":
				// Code for opening second server
				break;
				
			case "4":
				// Code for closing second server
				break;
				
			case "5":
				// Code for finding q-hat
				break;
				
			case "6": 
				// Code for finding u-hat
				break;
				
			case "7":
				// Code for finding b(t)
				break;
				
			case "8":
				// Code for reporting on balking
				System.out.println(events.getBalkSummary());
				break;
				
			case "9":
				// Code for reporting on Reneging
				System.out.println(events.getRenegeSummary());
				break;
				
			case "10":
				// Code for reporting on jockeying
				System.out.println(events.getJockeySummary());
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
		return (rng.nextFloat());
	}
	
	public static float giveRandomTimeLong() {
		// Should output something between 1.0 - 2.5.
		// To be replaced with in-house code.
		return (rng.nextInt(2) + rng.nextFloat());
	}
	
	public static float giveRandomTimeShort() {
		// Should output something between 0.0 - 1.0
		// To be replaced with in-house code.
		return (rng.nextFloat());
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
	public static void createCustomer() throws IOException {
		System.out.print("Customer Name: ");
		inputStr = input.readLine();
		
		System.out.print("Number of Items: ");
		inputVal = input.readLine();
		
		if (numServers == 1) {
			if ((server1.getSize() > balkThreshold) && (rng.nextInt(2) == 1)) { // Balk calculation
				// Log balk
				System.out.println("Customer " + inputStr + " has balked with " + inputVal + " items.\n");
				
				// Add to log list. WIP
			}
			else {
				// Customer enters queue despite being greater than threshold. 50/50 chance.
				if (server1.getSize() == 0) {
					totalTime += giveRandomTimeLong(); // Simulating the fact that time passes between batches of customers
				}
				server1.enqueue(new Customer(inputStr, Integer.parseInt(inputVal), totalTime));
				System.out.println(inputStr + " got in line for checkout at " + totalTime + " with " + inputVal + " items.\n");
			}
		}
		else {
			// When 2 servers we need to check some things.
		}
	}
	
	public static void processSingleCustomer(int server) {
		Customer temp;
		if (server == 1) {
			if (server1.peek() != null) {
				temp = server1.dequeue();
				
				// Jockey Conditional
				if (numServers == 2 && server2.getSize() == 0 && server1.getSize() != 0) {
					// Jockey if server2 has noone in line and there's other people in your line.
					server2.enqueue(temp);
					
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
				if (numServers == 2 && server1.getSize() == 0 && server2.getSize() != 0) {
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
	}
	
	public static void processTwoCustomers() {
		Customer temp1, temp2;
		
		
	}
	
	public static void openServer2() {
		if (numServers == 2) {
			System.out.println("Second server is already opened!");
		}
		else {
			numServers += 1; // SHOULD equal 2. 
		}
	}
	
	public static void closeServer2() {
		if (numServers == 2) {
			numServers -= 1; // Should close one server and go back to one.
			
			// Code for dequeueing entire server and entering them into new server.
			// CHECK FOR BALK
		}
		else {
			System.out.println("Second server is already closed!");
		}
	}
}
