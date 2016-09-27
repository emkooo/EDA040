package lift;

import java.util.Random;

public class ElevatorMonitor {
	
	//this class should only have 2-4 methods

	private int here, next, load, pplWaiting;
	private int[] waitEntry, waitExit;
	private LiftView liftView;
	private boolean goingUp = true;
	private Random randGenerator = new Random();
	
	public ElevatorMonitor(LiftView liftView){
		waitEntry = new int[7];
		waitExit = new int[7];
		this.liftView = liftView;
	}
	
	synchronized void PersonCallElevator(){
		
		int startFloor = randGenerator.nextInt(7); // random which floor ppl enter
		++waitEntry[startFloor]; // increase nbr of people waiting at startFloor	
		++pplWaiting;
		
		liftView.drawLevel(startFloor, waitEntry[startFloor]); // draw the ppl on that floor

		notifyAll();		// increased amount of ppl waiting. notify
	}
	
	synchronized void getSome(){

		// if no one is waiting, and no one is in the elevator.. then wait()
	while(pplWaiting == 0 && load == 0){
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//titta på denna
	if(load > 0 && waitExit[here] > 0){
		
		System.out.println("load is : load " + "waitexit here is : " +waitExit[here]);
		
		for(int i = 1; i <= waitExit[here]; i++){
			liftView.drawLift(here, load-i);
			
		}
		System.out.println(waitExit[here] +" ppl should have gone out");
		load -= waitExit[here];
		waitExit[here] = 0;
		if (load <= 0) System.out.println("<------- load became " +load);
				
	}
	
	System.out.println("The load is : " +load);
		if(4 - load > 0 && waitEntry[here] > 0){
			int nbrOfPersonEntering = 4 - load;
			
			while(nbrOfPersonEntering > 0 && waitEntry[here] > 0){
				waitEntry[here]--;
				nbrOfPersonEntering--;
				pplWaiting--;
				load++;
				int nextFloor;
				while((nextFloor = randGenerator.nextInt(7)) == here){
					nextFloor = randGenerator.nextInt(7);
				}
				waitExit[nextFloor] += 1;
			}
			
			System.out.println("the level we are on is: " +here);
			System.out.println("The load is : " +load);
			liftView.drawLevel(here, waitEntry[here]);
			
		} 
		
		if(goingUp){
			here++;
			next++;
			if(here == 6) goingUp = false;
			liftView.drawLift(here-1, load);
			liftView.moveLift(here-1, next);
			
		} 
		else if(!goingUp){
			here--;
			next--;
			if(next == 0) goingUp = true;
			liftView.drawLift(here+1, load);
			liftView.moveLift(here+1, next);
			
		}
	}
	
}
