import java.util.*;

public class TestRun extends Thread{
	
	public void run()
	{
		try
		{
			System.out.println("Thread "+Thread.currentThread().getId()+" having name "+Thread.currentThread().getName()+" is running");
		}
		catch(Exception e)
		{
			System.out.println("Exception is caught");
		}
	}

	public static void main(String[] args) {
		int i,n=8;
		for(i=0;i<n;i++)
		{
			TestRun object=new TestRun();
			//System.out.println("Current state of newly created thread : "+object.getState());
			object.run();
		}
	}
}

/*
The purpose of start() is to create a separate call stack for the thread. 
A separate call stack is created by it, and then run() is called by JVM.

If run method is called, below is the sample output
Thread 1 having name main is running
Thread 1 having name main is running
Thread 1 having name main is running
Thread 1 having name main is running
Thread 1 having name main is running
Thread 1 having name main is running
Thread 1 having name main is running
Thread 1 having name main is running


If start method is called, below is the sample output
Thread 9 having name Thread-1 is running
Thread 11 having name Thread-3 is running
Thread 12 having name Thread-4 is running
Thread 10 having name Thread-2 is running
Thread 13 having name Thread-5 is running
Thread 8 having name Thread-0 is running
Thread 14 having name Thread-6 is running
Thread 15 having name Thread-7 is running

object.getState() will print NEW
 */