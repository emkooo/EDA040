package buffer;

public class getMail implements Runnable{
	
	private int startTime;
	
	public getMail(int startTime){
		this.startTime = startTime; 
	}

	@Override
	public void run() {
		
		try{
			Thread.sleep(1000 * startTime);
		} catch (InterruptedException e) 
		{}
		
		System.out.println("Checking email with StartTime:  " +startTime);
		System.out.println();
		// TODO Auto-generated method stub
		
	}

}
