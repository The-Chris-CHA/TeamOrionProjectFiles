/* Driver.java
 * Description: Driver class for ISSM project.
 * Status: Incomplete
 * Version: 2020.4.30-1
 * Authors: Christopher Herras-Antig, Dylan Ott, Name 3, Name 4
 */

import java.io.*;
import java.util.Random;

public class Driver {
	
	static BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
	static float projRenegeChance;
	static String inputStr, inputVal;
	static Random rng = new Random();

	public static void main(String[] args) throws IOException {
		// Init vars
		boolean exitLoop = false;
		projRenegeChance = giveRandomChance();
		
		// Debug
		//System.out.println(rng.nextInt(2));
		//System.out.println(projBalkChance);
		//System.out.println(projRenegeChance);
		//System.out.println(projJockeyChance);
		
		// Bootup Message
		System.out.println("Welcome to the SSM Queue and Server System!"
				+ "\nTeam: Team Orion."
				+ "\nMembers: ChrisH, DylanO, BrianR, KevinB"
				+ "\nSelect an Option for the System:\n");
		
		// Menu Inputs
		// Separated on different print functions for easy editing
		System.out.println("1. Create a [Customer/Item]");
		System.out.println("2. Process a [Customer/Item]");
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
				// Code for entering Create method
				break;
				
			case "2":
				// Code for entering process method
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
				break;
				
			case "9":
				// Code for reporting on Reneging
				break;
				
			case "10":
				// Code for reporting on jockeying
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


	public static float giveRandomChance() {
		return (rng.nextFloat());
	}
	
	public float giveRandomTime() {
		return (rng.nextInt(6) + rng.nextFloat());
	}
	
}
