package lift;

import java.util.Random;

public class ElevatorMonitor {
	
	//this class should only have 2-4 methods

	private int here, next, load;
	
	private int[] waitEntry, waitExit;
	private LiftView liftView;
	private boolean goingUp = true;
	
	public ElevatorMonitor(LiftView liftView){
		waitEntry = new int[7];
		waitExit = new int[7];
		this.liftView = liftView;
	}
	
	private int GetNextFloor(int nextFloor){
		/*
		 * Need to check if we reached top
		 * or bottom floor and choose direction
		 */
		if(goingUp){
			nextFloor++;
			if(nextFloor == 6){
				goingUp = !goingUp;
			}
		} 
		else{
			nextFloor--;
			if(nextFloor == 0) {
				goingUp = !goingUp;
			}
		}
		return nextFloor;
	}
	
	synchronized int MoveElevatorFrom(int currentFloor){
		
		here = currentFloor;			// we've reached to next floor since last time
		notifyAll();
		int theNextFloor = GetNextFloor(currentFloor);
		
		/*
		 * If no one is waiting to exit
		 * OR
		 * If Someone is waiting to enter and there is space to enter
		 * wait()
		 */
		while(waitExit[here] > 0 || (waitEntry[here] > 0 && load < 4) ){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.next = theNextFloor;
		return theNextFloor;
	}
	
	synchronized void PersonCallElevator(){
		Random randGenerator = new Random();
		
		/*
		 * Randomizes the floor in which people enter
		 * And makes sure the destFloor != startFloor
		 */
		int startFloor = randGenerator.nextInt(7);
		int destFloor;
		while((destFloor = randGenerator.nextInt(7)) == startFloor){
			destFloor = randGenerator.nextInt(7);
		}

		waitEntry[startFloor]++; // increase nbr of people waiting at startFloor
		liftView.drawLevel(startFloor, waitEntry[startFloor]); // draw the ppl on that floor
		notifyAll();
		
		/*
		 * If elevator is standing still
		 * & thisFloor is our floor
		 * & there is space 
		 * we will wake up at this point
		 */
		while( !(here == next && here == startFloor && load < 4) ){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			load++;
			waitEntry[startFloor]--;
			waitExit[destFloor]++;
			
			liftView.drawLevel(startFloor, waitEntry[startFloor]);
			liftView.drawLift(startFloor, load);
			notifyAll();
		
			// when we reach destFloor wake up
		while(here != destFloor ){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			waitExit[destFloor]--;
			load--;
			liftView.drawLift(destFloor, load);
			notifyAll();
	}
}
	