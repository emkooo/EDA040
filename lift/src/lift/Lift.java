package lift;

public class Lift extends Thread{

	ElevatorMonitor elevatorMonitor;
	
	public Lift(ElevatorMonitor elevatorMonitor){
		this.elevatorMonitor = elevatorMonitor;
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			elevatorMonitor.getSome();
		}
	}
	
}
