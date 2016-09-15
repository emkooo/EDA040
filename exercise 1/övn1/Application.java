
import java.util.ArrayList;
import java.util.Collections;

import se.lth.cs.realtime.semaphore.*;

public class Application {
	
	public static void main (String [] args){
		Thread t1,t2;
		
		Semaphore s = new MutexSem();
		
		/*
		t1 = new PrintStuff(s,"1 1 2 2 3 3");
		t1.start();
		t2 = new PrintStuff(s,"a b c d");
		t2.start();
		*/
		
		ArrayList<Integer> temp = new ArrayList<Integer>(Collections.nCopies(60, 0));
		
		String[] v = new String[10];
		
		System.out.print(temp.size());
		t1 = new countStuff(s,temp,1);
		t1.start();
		t2 = new countStuff(s,temp,0);
		t2.start();
		

		
		
		

		
	}

}
