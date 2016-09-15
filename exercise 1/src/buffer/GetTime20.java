package buffer;
import java.util.*;
import java.text.DateFormat;

public class GetTime20 implements Runnable{
	
	public void run(){
		Date RightNow;
		Locale currentLocale;
		DateFormat timeFormatter;
		DateFormat dateFormatter; 
		String timeOutput;
		String dateOutput;
		
		for(int i = 0; i <= 10; i++){
			RightNow = new Date();
			currentLocale = new Locale("se");
			
			timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT,currentLocale);
			dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT,currentLocale);

			timeOutput = timeFormatter.format(RightNow);
			dateOutput = dateFormatter.format(RightNow);
			
			System.out.println(timeOutput);
			System.out.println(dateOutput);
			System.out.println();
			
			// need to handle any errors being thrown
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException e){
				
			}

					
		}
	}

}
