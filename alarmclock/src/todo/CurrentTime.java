package todo;

public class CurrentTime implements Runnable{
	
	private PassiveData sharedData;
	
	public CurrentTime(PassiveData sharedData){
		
		this.sharedData = sharedData;

	}
	
	public void run() {
		long t, diff;
		t = System.currentTimeMillis();
		while(true){
			
			t += 1000;
			diff = t - System.currentTimeMillis();
			if(diff > 0)
				try {
					Thread.sleep(diff);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			sharedData.incrementTime(1);
		}
	}

}
