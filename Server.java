/* Driver.java
 * Description: Server class for ISSM project.
 * Status: Incomplete
 * Version: 2020.4.30-1
 * Authors: Christopher Herras-Antig
 */

import java.util.*;

public class Server {
	Queue<Customer> server;
	
	public Server() {
		server = new LinkedList<>();
	}
	
	public void enqueue(Customer input) {
		server.add(input);
	}
	
	public Customer dequeue() {
		Customer outputDequeue = server.poll();
		return outputDequeue;
	}
	
	public Customer peek() {
		Customer outputPeek = server.peek();
		return outputPeek;
	}
	
	public int getSize() {
		return server.size();
	}
}
