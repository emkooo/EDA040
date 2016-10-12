package todo;


import se.lth.cs.realtime.*;
import done.AbstractWashingMachine;


public class WaterController extends PeriodicThread {
	// TODO: add suitable attributes
	
	
	
	AbstractWashingMachine theMachine;
	
	
	
	// private int currentState = WaterEvent.WATER_IDLE;		//We begin in a idle state of water

	public WaterController(AbstractWashingMachine mach, double speed) {
		super((long) (1000/speed)); // TODO: replace with suitable period
		theMachine = mach;
	}

	public void perform() {
		// TODO: implement this method
		
		// WaterEvent message = (WaterEvent) this.mailbox.tryFetch(); //here is our message.
		 
		// System.out.println( message.getMode() + " l-->" +WaterEvent.WATER_FILL);
		 /*
		 if (message.getMode() == WaterEvent.WATER_FILL){
			 System.out.print("We should fill with water");
			 theMachine.setFill(true);
		 }
		 */
	}
}
