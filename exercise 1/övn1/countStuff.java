import java.util.ArrayList;

import se.lth.cs.realtime.semaphore.Semaphore;

public class countStuff extends Thread{
	private ArrayList<Integer> myList;
	private Semaphore sem;
	private int doIt;
	
	public countStuff(Semaphore sem, ArrayList<Integer> myList,int doIt){
		this.sem = sem;
		this.myList = myList;
		this.doIt = doIt;
	}
	
	public void run(){
		if(doIt == 1){
			for(int i = 0; i < myList.size(); i++){
				myList.add(1);
			}
		}
		else{
			for(int i = 0; i < myList.size(); i++){
				myList.add(2);
			}
		}
		
		for(int i = 0; i < myList.size(); i++){
			System.out.println(myList.get(i));
		}
	}


}
