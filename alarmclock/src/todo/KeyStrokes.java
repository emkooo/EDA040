package todo;

import done.ClockInput;
import se.lth.cs.realtime.semaphore.Semaphore;

public class KeyStrokes implements Runnable {
	
	private PassiveData sharedData;
	private Semaphore sem;
	private int prevCommand = 0;
	private int timeInitated;
	private ClockInput clockInput;
	
	public KeyStrokes(PassiveData sharedData,Semaphore sem, ClockInput clockInput){
		this.sharedData = sharedData;
		this.clockInput = clockInput;
		this.sem = sem;
	}
		
	public void keyPressed(int command, int timeInitated){
		
			switch(command){
			// 1 is setAlarm
			case 1:	System.out.println("1 was pressed and alarmtime is: " +timeInitated);
				sharedData.setAlarmTime(timeInitated);
				break;
			
				// 2 is setTime	
			case 2: System.out.println("2 was pressed and time set is: " +timeInitated);
				sharedData.setCurrentTime(timeInitated);
				break;
						}
			}
	
	public void run() {
		
		//ClockInput  clockInput = sharedData.getClockInput();
		int command = 0;
		
			while(true){
					
					sem.take();
					command = clockInput.getChoice();

					System.out.print("command is : " +command);
					sharedData.alarmStatus();
					/*	
					if (sharedData.alarmStatus() == true){
							//System.out.println("Alarm should be turned off--->>");
							sharedData.alarmOff();	
						}
						*/
						//keyPressed(command, clockInput);
						
					//}
					if(command != prevCommand){
						timeInitated = clockInput.getValue();
						//System.out.print("Nu släpper jag och command == " +prevCommand +"time is: "+timeInitated);
						
						keyPressed(prevCommand,timeInitated);
						prevCommand = command;
					}
						
					//keyPressed(command, clockInput);

//					System.out.println("känner den om ajg släpper SHIFT #13123123123123123");
//					System.out.print(clockInput.getValue());
					}
			}
}
