package lift;

import java.util.Random;

public class Person extends Thread{
	
	ElevatorMonitor elevatorMonitor;
	
	public Person(ElevatorMonitor elevatorMonitor){
		this.elevatorMonitor = elevatorMonitor;
	}
	
	public void run(){
		while(true){
			int delay = 1000*((int)(Math.random()*46.0));
			//System.out.print("Time to sleep: " +delay);
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			elevatorMonitor.PersonCallElevator();
	

		}
	}
}
