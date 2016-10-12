package todo;

import se.lth.cs.realtime.*;
import done.AbstractWashingMachine;

public class WaterController extends PeriodicThread {
	// TODO: add suitable attributes

	AbstractWashingMachine theMachine;
	
	private double targetLevel;
	
	private int currentMode = WaterEvent.WATER_IDLE;
	
	private RTThread source;

	// private int currentState = WaterEvent.WATER_IDLE; //We begin in a idle
	// state of water

	public WaterController(AbstractWashingMachine mach, double speed) {
		super((long) (1000 / speed)); // TODO: replace with suitable period
		theMachine = mach;
	}

	private void performMessage(WaterEvent message) {

		switch (message.getMode()) {

		case WaterEvent.WATER_DRAIN:
									//theMachine.setFill(false);
									theMachine.setDrain(true);
									break;
		
		case WaterEvent.WATER_FILL:
									//theMachine.setDrain(false);
									theMachine.setFill(true);
									break;
		
		case WaterEvent.WATER_IDLE:
									theMachine.setDrain(false);
									theMachine.setFill(false);
									// Acknowledge right away, we're done with this task
									((RTThread) message.getSource()).putEvent(new AckEvent(this));
									break; 
		}

	}

	public void perform() {

		WaterEvent msg = (WaterEvent) this.mailbox.tryFetch();

		if (msg != null) {
			targetLevel = msg.getLevel()/20;
			currentMode = msg.getMode();
			source = (RTThread) msg.getSource();

			// Perform decisions based on mode
			performMessage(msg);
			
			// If we are in WATER_FILL mode && there is enough water STOP and acknowledge
			System.out.println("kan vi komma hit");
			
			/*
			if(msg.getMode() == WaterEvent.WATER_FILL && theMachine.getWaterLevel() >= msg.getLevel() ){
				theMachine.setFill(false);
				((RTThread) msg.getSource()).putEvent(new AckEvent(this));
			}
			
			else if(msg.getMode() == WaterEvent.WATER_DRAIN && theMachine.getWaterLevel() <= msg.getLevel()){
				theMachine.setDrain(false);
				((RTThread) msg.getSource()).putEvent(new AckEvent(this));
			}
			
			*/
			

		}
		
		if(currentMode == WaterEvent.WATER_FILL && theMachine.getWaterLevel() >= targetLevel){
			theMachine.setFill(false);
			currentMode = WaterEvent.WATER_IDLE;
			source.putEvent(new AckEvent(this));
		}
		else if(currentMode == WaterEvent.WATER_DRAIN && theMachine.getWaterLevel() <= targetLevel){
			theMachine.setDrain(false);
			currentMode = WaterEvent.WATER_IDLE;
			source.putEvent(new AckEvent(this));
		}
		

		


		// TODO: implement this method

		// WaterEvent message = (WaterEvent) this.mailbox.tryFetch(); //here is
		// our message.

		// System.out.println( message.getMode() + " l-->"
		// +WaterEvent.WATER_FILL);
		/*
		 * if (message.getMode() == WaterEvent.WATER_FILL){
		 * System.out.print("We should fill with water");
		 * theMachine.setFill(true); }
		 */
	}
}
