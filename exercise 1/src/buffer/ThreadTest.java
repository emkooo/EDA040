package buffer;

public class ThreadTest {
	
	public static void main (String[] args){
		
		GetTime20 getTime = new GetTime20();
		
		Runnable getMail = new getMail(10);
		
		Runnable getMailAgain = new getMail(20);
		
		new Thread(getTime).start();
		
		new Thread(getMail).start();
		new Thread(getMailAgain).start();
	}

}
