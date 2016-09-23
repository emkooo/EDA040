package queue;

class YourMonitor {
	private int nCounters;
	
	private int nextQueueNumber = -1;
	private int highestHandledTicket = 0;
	private boolean[] countersFree;
	private int clerksFree = 0;
	// Put your attributes here...

	YourMonitor(int n) { 
		nCounters = n;
		this.countersFree = new boolean[nCounters]; // initiates all as busy
		// Initialize your attributes here...
	}
	
	/**
	 * Return the next queue number in the intervall 0...99. 
	 * There is never more than 100 customers waiting.
	 */
	synchronized int customerArrived() { 
		// increment nextTicketNumber
		nextQueueNumber++;	
		// one-liner : reset if equals 100, else return nextQueueNumber;
		return (nextQueueNumber == 100) ? nextQueueNumber = 0 : nextQueueNumber;
	}

	/**
	 * Register the clerk at counter id as free. Send a customer if any. 
	 */
	
	synchronized void clerkFree(int id) {
		
		//check against spam
		if(!countersFree[id]){			// if not already set as available
			countersFree[id] = true; 	// set as available
			clerksFree++;				// condition has changed
			// since a condition has been changed, we have to call notifyAll()
			notifyAll();				// notify threads in queue
		}
	}

	/**
	 * Wait for there to be a free clerk and a waiting customer, then
	 * return the queue number of next customer to serve and the counter 
	 * number of the engaged clerk.
	 */
	synchronized DispData getDisplayData() throws InterruptedException { 
		
		// do nothing if clerks are busy or there is no one to serve
		while(clerksFree == 0 || nextQueueNumber == highestHandledTicket){
			wait(); // wait until conditions above are changed
		}
		DispData display = new DispData();
		display.ticket = highestHandledTicket++;
		display.counter = FindFreeClerk();
		return display;
	}
	
	private int FindFreeClerk(){
		int i = 0;
		boolean found = false;
		while(i < countersFree.length && !found){
			if(countersFree[i]){
				found = true;
				countersFree[i] = false;
				clerksFree--;
			} else {
				i++;
			}
		}
		return i;
	}
}
