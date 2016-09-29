package lift;

public class Person extends Thread{
	
	ElevatorMonitor elevatorMonitor;
	
	public Person(ElevatorMonitor elevatorMonitor){
		this.elevatorMonitor = elevatorMonitor;
	}
	
	public void run(){
		while(true){
			int delay = 1000*((int)(Math.random()*46.0));				// create random delay between (1-45) seconds
			try {Thread.sleep(delay);} catch (InterruptedException e) {
				e.printStackTrace();
			}
			elevatorMonitor.PersonCallElevator();						// Person has arrived
		}
	}
}
