package lift;

public class Lift extends Thread{

	ElevatorMonitor elevatorMonitor;
	private LiftView liftView;
	int currentFloor = 0;
	
	public Lift(ElevatorMonitor elevatorMonitor, LiftView liftView){
		this.elevatorMonitor = elevatorMonitor;
		this.liftView = liftView;
	}
	
	public void run(){
		while(true){
			int nextFloor = elevatorMonitor.MoveElevatorFrom(currentFloor);		// gets the next floor
			liftView.moveLift(currentFloor, nextFloor);						// moves to next floor
			currentFloor = nextFloor;
		}
	}	
}
