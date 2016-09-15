package todo;

import done.ClockInput;
import done.ClockOutput;
import se.lth.cs.realtime.semaphore.Semaphore;

public class KeyStrokes implements Runnable {
	
	private PassiveData sharedData;
	private Semaphore sem;
	
	public KeyStrokes(PassiveData sharedData,Semaphore sem){
		this.sharedData = sharedData;
		this.sem = sem;
	}
		
	public void keyPressed(int command, int time){
		
			switch(command){
			// 1 is setAlarm
			case 1:	System.out.print("1 was pressed and alarmtime is: " +time);
				sharedData.setAlarmTime(time);
				break;
			
				// 2 is setTime	
			case 2: System.out.print("2 was pressed and time set is: " +time);
				sharedData.setCurrentTime(time);
				break;
						}
			}
		
	
	public void run() {
	//	ClockOutput clockOutput = sharedData.getClockOutput();
		ClockInput  clockInput = sharedData.getClockInput();
		int timeInitated;
		//Boolean alarmFlag;
		int command = clockInput.getChoice();
		
			while(true){
												
					sem.take();
			
					if (sharedData.alarmStatus() == true){
						//System.out.println("Alarm should be turned off--->>");
						sharedData.alarmOff();	
					}
					command = clockInput.getChoice();
					System.out.println("Choice is: " +command);
					timeInitated = clockInput.getValue();
					//if(command!=0) {
					keyPressed(command, timeInitated);
						//prevCommand = 0;
					//}
					
				}
			}
}
