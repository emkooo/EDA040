package buffer;
import java.util.concurrent.Semaphore;

public class App extends Semaphore{

	public App(int permits) {
		super(permits);
		// TODO Auto-generated constructor stub
	}

	public static void main (String[] args){
		Semaphore sem = new Semaphore(1);
		
		printStuff s1 = new printStuff(10);
		printStuff s2 = new printStuff(5);
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s1.run();
		s2.run();
		
		System.out.println("Available permits: " +sem.availablePermits());
		
		
			

		
		
		
		//Connection.getInstance().Connect();
		
		/*Semaphore sem = new Semaphore(1);
		
		sem.release();
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Available permits: " +sem.availablePermits());
		*/
	}
}
