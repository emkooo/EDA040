package queue;

class YourMonitor {
	private int nCounters;
	
	private int nextQueueNumber = 0;
	private int highestHandledTicket = 0;
	private boolean[] countersFree;
	private int clerksFree = 0;
	// Put your attributes here...

	YourMonitor(int n) { 
		nCounters = n;
		this.countersFree = new boolean[nCounters]; // initiates all as false
		// Initialize your attributes here...
	}
	
	/**
	 * Return the next queue number in the intervall 0...99. 
	 * There is never more than 100 customers waiting.
	 */
	synchronized int customerArrived() { 
		nextQueueNumber++;
		if(nextQueueNumber== 100){
			nextQueueNumber = 0;
			return nextQueueNumber;
		}
		return nextQueueNumber-1;
		// Implement this method...
	//	return 0;
	}

	/**
	 * Register the clerk at counter id as free. Send a customer if any. 
	 */
	
	synchronized void clerkFree(int id) {
		
		//check against spam
		
		if(!countersFree[id]){			// if not already set as available
			countersFree[id] = true; 	// set as available
			clerksFree++;				// condition has changed
			//System.out.println("clerks free: " +clerksFree);
			notifyAll();				// notify threads in queue
		}
		// Implement this method...
	}

	/**
	 * Wait for there to be a free clerk and a waiting customer, then
	 * return the queue number of next customer to serve and the counter 
	 * number of the engaged clerk.
	 */
	synchronized DispData getDisplayData() throws InterruptedException { 
		// Implement this method...
		
		// do nothing if clarks are busy or there is no one to serve
		while(clerksFree == 0 || nextQueueNumber == highestHandledTicket){
			wait();
		}
		DispData display = new DispData();
		display.ticket = highestHandledTicket++; 
		for(int i =0; i < countersFree.length; i++){
			if(countersFree[i] == true){
				display.counter = i;
				countersFree[i] = false;
				//System.out.println("clerks free: " +clerksFree);
				clerksFree--;		// we need to decrease when busy
				break;
			}
		}
		return display;
	}
}
