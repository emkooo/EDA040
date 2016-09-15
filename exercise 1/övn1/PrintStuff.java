import se.lth.cs.realtime.semaphore.Semaphore;

public class PrintStuff extends Thread{
	
	private Semaphore sem;
	private String s;
	
	public PrintStuff(Semaphore sem, String s){
		this.sem = sem;
		this.s = s;
	}


	@Override
	public void run() {
		
		for(int i = 0; i < 100; i++){
			sem.take();
			System.out.print("Thread is " +"the string: " +s +" iteration is: " +i +"\n" );
			sem.give();
			//for(int j =0; j < 1000000000; j++) {}
			
		}
		


		
	}

}
