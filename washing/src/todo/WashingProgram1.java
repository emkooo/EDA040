/*
 * Real-time and concurrent programming course, laboratory 3
 * Department of Computer Science, Lund Institute of Technology
 *
 * PP 980812 Created
 * PP 990924 Revised
 */

package todo;

import done.*;

/**
 * Program 3 of washing machine. Does the following:
 * <UL>
 *   <LI>Switches off heating
 *   <LI>Switches off spin
 *   <LI>Pumps out water
 *   <LI>Unlocks the hatch.
 * </UL>
 */
class WashingProgram1 extends WashingProgram {

	// ------------------------------------------------------------- CONSTRUCTOR

	/**
	 * @param   mach             The washing machine to control
	 * @param   speed            Simulation speed
	 * @param   tempController   The TemperatureController to use
	 * @param   waterController  The WaterController to use
	 * @param   spinController   The SpinController to use
	 */
	public WashingProgram1(AbstractWashingMachine mach,
			double speed,
			TemperatureController tempController,
			WaterController waterController,
			SpinController spinController) {
		super(mach, speed, tempController, waterController, spinController);
	}

	// ---------------------------------------------------------- PUBLIC METHODS

	/**
	 * This method contains the actual code for the washing program. Executed
	 * when the start() method is called.
	 */
	protected void wash() throws InterruptedException {
		
		// Lock the hatch
		myMachine.setLock(true);
		
		// Fill the machine with water
		myWaterController.putEvent(new WaterEvent(this,WaterEvent.WATER_FILL,10.0));
		mailbox.doFetch();
		
		// Spin slowly 
		mySpinController.putEvent(new SpinEvent(this,SpinEvent.SPIN_SLOW));
		
		// Heat to 60 degree
		myTempController.putEvent(new TemperatureEvent(this,TemperatureEvent.TEMP_SET,60.0));
		mailbox.doFetch();
		
		// keep temperature for 30 minutes
		sleep((int) ((30*60*1000)/mySpeed));
		
		// Drain water
		myWaterController.putEvent(new WaterEvent(this,WaterEvent.WATER_DRAIN,0));
		mailbox.doFetch();
		
		// Rinse 5 times
		for(int i = 0; i < 5; i++){
			myWaterController.putEvent(new WaterEvent(this,WaterEvent.WATER_FILL,10.0));
			mailbox.doFetch();
			// do this for 2 minutes
			sleep((int) ((2*60*1000)/mySpeed));
			myWaterController.putEvent(new WaterEvent(this, WaterEvent.WATER_DRAIN,0.0));
			mailbox.doFetch();
			
		}
		
		// Centrifuge for 5 minutes
		myWaterController.putEvent(new SpinEvent(this,SpinEvent.SPIN_FAST));
		sleep((int) (2*60*1000));
		myWaterController.putEvent(new SpinEvent(this,SpinEvent.SPIN_OFF));
		
		// Unlock hatch
		myMachine.setLock(false);

	}
}
