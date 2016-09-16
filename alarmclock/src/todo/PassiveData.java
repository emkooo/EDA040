package todo;

import done.ClockInput;
import done.ClockOutput;
import se.lth.cs.realtime.semaphore.MutexSem;
//import se.lth.cs.realtime.semaphore.Semaphore;

public class PassiveData {
	
	private int alarmTime;  // the time when beep should happen
	private ClockInput clockInput;
	private ClockOutput clockOutput;
	private static int currentTime;
	private boolean alarmIsOn = false;
	private MutexSem sem;
	
	public PassiveData(ClockInput clockInput, ClockOutput clockOutput){
		this.clockInput = clockInput;
		this.clockOutput = clockOutput;
		sem = new MutexSem();
	}
	
	public void setAlarmTime(int time){
		sem.take();
		this.alarmTime = DecodeTime(time);
		sem.give();
	}
	
	public void setCurrentTime(int time){
		sem.take();
		//int temp = time;
		System.out.println(currentTime +"    - this is currentim");
		currentTime = DecodeTime(time);
		
		updateGui();
		sem.give();
	}
	
	private int DecodeTime(int time) {
		int hours, minutes, seconds;
		hours = (time / 10000);
		minutes = (time / 100) - (hours * 100) ;
		seconds = time - (hours * 10000 + minutes * 100);
		
		System.out.println("the time we would get is, " +hours +":" +minutes + ":" +seconds);
		int totalSeconds = (seconds + minutes*60 + hours*3600);
		System.out.println(totalSeconds +"    - this is totalseconds");
		return totalSeconds;
	}

	public void alarmStatus(){
		sem.take();
		if(alarmIsOn){
			alarmIsOn = false;
		}
		sem.give();
		
	}
	public void alarmOn(){
		sem.take();
		alarmIsOn = true;
		sem.give();
	}
	
	public void alarmOff(){
		sem.take();
		alarmIsOn = false;
		sem.give();
	}
	
	public void updateGui(){
		sem.take();
		clockOutput.showTime(formatTime());
		sem.give();
	}
	
	private int formatTime(){
		int time = currentTime;
		
		int hours = 0, seconds = 0, minutes = 0;
		
		seconds = time % 60;
		minutes = (time % 3600) / 60;
		hours = (time / 60) / 60;
				
	//	System.out.println("time is: " +hours +":" +minutes +":" +seconds);
		return hours*10000 + minutes*100 + seconds;
	}
	
	
	public void incrementTime(int time){
		//sem.take();
		sem.take();
		
		currentTime += time;
		
		if(currentTime == 86400) currentTime = 0;
		
		System.out.println("currenttime: "+currentTime +"alarmtime: " +alarmTime);

		if(currentTime == alarmTime){
			startTheAlarm();
		}
		
		if(alarmIsOn == true){
			//System.out.println("currenttime: "+currentTime +"alarmtime: " +getAlarmTime());
			clockOutput.doAlarm();
			if(currentTime >= getAlarmTime() + 20){
				//System.out.print("currenttime: "+currentTime +"alarmtime: " +getAlarmTime());
				alarmOff();
			}
				
		}
		updateGui();
		sem.give();
	}
	
	public void startTheAlarm(){
		//System.out.println("ALARM SHOULD START");
		sem.take();
		alarmOn();
		sem.give();
		//clockOutput.doAlarm();
	}
	
	//public ClockOutput getClockOutput(){
	//	return clockOutput;
	//}
	
	//public ClockInput getClockInput(){
	//	return clockInput;
	//}
	
	public int getAlarmTime(){
		/*
		sem.take();
		Integer temp = new Integer(alarmTime);
		sem.give();
		return 	temp.intValue();
		*/
		return alarmTime;
	}

}
