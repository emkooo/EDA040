package todo;

import done.*;

public class WashingController implements ButtonListener {
	AbstractWashingMachine theMachine;
	double theSpeed;

	TemperatureController tempController;
	WaterController waterController;
	SpinController spinController;
	// TODO: add suitable attributes

	public WashingController(AbstractWashingMachine theMachine, double theSpeed) {
		this.theMachine = theMachine;
		this.theSpeed = theSpeed;

		// TODO: implement this constructor
		// Create and start your controller threads here

		this.tempController = new TemperatureController(theMachine, theSpeed);
		this.waterController = new WaterController(theMachine, theSpeed);
		this.spinController = new SpinController(theMachine, theSpeed);

		tempController.start();
		waterController.start();
		spinController.start();

	}

	public void processButton(int theButton) {
		
		// TODO: implement this method
		// Handle button presses (0, 1, 2, or 3). A button press
		// corresponds to starting a new washing program. What should // happen
		// if there is already a running washing program?

		if (theButton == 0) {
			System.out.println("Everything should stop working here interupt or something");
		}

		// check if a program is running else check which program we should

		WashingProgram washProgram = null; // create temp variable

		switch (theButton) {
		case 1:
			washProgram = new WashingProgram1(theMachine, theSpeed, tempController, waterController, spinController);
			break;
		case 2:
			washProgram = new WashingProgram2(theMachine, theSpeed, tempController, waterController, spinController);
			break;
		case 3:
			washProgram = new WashingProgram3(theMachine, theSpeed, tempController, waterController, spinController);
			break;
		}

		washProgram.start(); // start the program intended

	}
}
