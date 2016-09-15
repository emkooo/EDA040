package todo;
import done.*;
import se.lth.cs.realtime.semaphore.Semaphore;

public class AlarmClock extends Thread {

	private static ClockInput	input;
	private static ClockOutput	output;
	private static Semaphore	sem; 

	public AlarmClock(ClockInput i, ClockOutput o) {
		input = i;
		output = o;
		sem = input.getSemaphoreInstance();
	}

	// The AlarmClock thread is started by the simulator. No
	// need to start it by yourself, if you do you will get
	// an IllegalThreadStateException. The implementation
	// below is a simple alarmclock thread that beeps upon 
	// each keypress. To be modified in the lab.
	public void run() {
	
		PassiveData sharedData = new PassiveData(input,output);
		
		CurrentTime timeThread = new CurrentTime(sharedData);
		new Thread(timeThread).start();
		
		KeyStrokes keyReader = new KeyStrokes(sharedData,sem);
		new Thread(keyReader).start();
		
	}
	

}
