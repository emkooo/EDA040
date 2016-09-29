package lift;

public class RunElevator {
	// denna ska bara initiera allting
	public static void main (String [] args){
		LiftView liftView = new LiftView();
		ElevatorMonitor elevatorMonitor = new ElevatorMonitor(liftView);

		Lift l = new Lift(elevatorMonitor,liftView);
		l.start();
		
		Person[] persons = new Person[20];
		for(int i = 0; i < persons.length; i++) persons[i] = new Person(elevatorMonitor);
		for(int j = 0; j < persons.length; j++) persons[j].start();	
	 }
}
