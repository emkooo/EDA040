package todo;

import done.ClockInput;
import done.ClockOutput;
import se.lth.cs.realtime.semaphore.MutexSem;
import se.lth.cs.realtime.semaphore.Semaphore;

public class PassiveData {
	
	private int alarmTime;  // the time when beep should happen
	private ClockInput clockInput;
	private ClockOutput clockOutput;
	private static int currentTime;
	private boolean alarmIsOn = false;
	private Semaphore sem;
	
	public PassiveData(ClockInput clockInput, ClockOutput clockOutput){
		this.clockInput = clockInput;
		this.clockOutput = clockOutput;
		sem = new MutexSem();
	}
	
	public void setAlarmTime(int time){	
		this.alarmTime = DecodeTime(time);
	}
	
	public void setCurrentTime(int time){
		sem.take();
		currentTime = DecodeTime(time);
		updateGui();
		sem.give();
	}
	
	private int DecodeTime(int time) {
		int hours, minutes, seconds;
		hours = (time / 10000);
		minutes = (time / 100) - (hours * 100) ;
		seconds = time - (hours * 10000 + minutes * 100);
		//System.out.println("the time we would get is, " +hours +":" +minutes + ":" +seconds);
		int totalSeconds = (seconds + minutes*60 + hours*3600);
		return totalSeconds;
	}

	public boolean alarmStatus(){
		return alarmIsOn;
	}
	public void alarmOn(){
		alarmIsOn = true;
	}
	
	public void alarmOff(){
		alarmIsOn = false;
	}
	
	public void updateGui(){
		clockOutput.showTime(formatTime());
	}
	
	public int formatTime(){
		
		int time = currentTime;
		//System.out.println("the time logic ::" +time);
		
		int hours = 0, seconds = 0, minutes = 0;
		
		seconds = time % 60;
		minutes = (time / 60) % 3600;
		hours = (time / 60) / 60;
				
	//	System.out.println("time is: " +hours +":" +minutes +":" +seconds);
		return hours*10000 + minutes*100 + seconds;
	}
	
	
	public void incrementTime(int time){
		//sem.take();
		sem.take();
		currentTime += time;
		
		if(currentTime == getAlarmTime()){
			startTheAlarm();
		}
		
		if(alarmStatus() == true){
			clockOutput.doAlarm();
			if(currentTime >= getAlarmTime() + 20){
				alarmOff();
			}
				
		}
		updateGui();
		sem.give();
	}
	
	public void startTheAlarm(){
		//System.out.println("ALARM SHOULD START");
		alarmOn();
		//clockOutput.doAlarm();
	}
	
	
	public ClockOutput getClockOutput(){
		return clockOutput;
	}
	
	public ClockInput getClockInput(){
		return clockInput;
	}
	
	public int getAlarmTime(){
		sem.take();
		Integer temp = new Integer(alarmTime);
		sem.give();
		return 	temp.intValue();
	}

}
