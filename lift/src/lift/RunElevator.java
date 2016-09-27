package lift;

public class RunElevator {
	// denna ska bara initiera allting
	public static void main (String [] args){
		LiftView v = new LiftView();
		ElevatorMonitor e = new ElevatorMonitor(v);
		
		//Person p = new Person(e);
		//p.start();
		Lift l = new Lift(e);
		l.start();

		Person[] persons = new Person[20];
		for(int i = 0; i < 20; i++) persons[i] = new Person(e);
		for(int j = 0; j < 20; j++) persons[j].start();	
				
	}

}
