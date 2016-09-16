package todo;

import done.ClockInput;
import se.lth.cs.realtime.semaphore.Semaphore;

public class KeyStrokes implements Runnable {

	private PassiveData sharedData;
	private Semaphore sem;
	private int prevCommand = 0;
	private int timeInitated;
	private ClockInput clockInput;

	public KeyStrokes(PassiveData sharedData, ClockInput clockInput) {
		this.sharedData = sharedData;
		this.clockInput = clockInput;
		this.sem = clockInput.getSemaphoreInstance();
	}

	public void keyPressed(int command, int timeInitated) {

		switch (command) {
		// 1 is setAlarm
		case 1: // System.out.println("1 was pressed and alarmtime is: "
				// +timeInitated);
			sharedData.setAlarmTime(timeInitated);
			break;

		// 2 is setTime
		case 2: // System.out.println("2 was pressed and time set is: "
				// +timeInitated);
			sharedData.setCurrentTime(timeInitated);
			break;
		}
	}

	public void run() {
		int command = 0;

		while (true) {

			sem.take();
			command = clockInput.getChoice();

			sharedData.alarmStatus();

			if (command != prevCommand) {
				timeInitated = clockInput.getValue();

				keyPressed(prevCommand, timeInitated);
				prevCommand = command;
			}

		}
	}
}
