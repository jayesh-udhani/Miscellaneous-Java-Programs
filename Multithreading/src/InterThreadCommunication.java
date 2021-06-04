
import java.util.Scanner;

public class InterThreadCommunication {

	public static void main(String[] args) throws InterruptedException {
		final PC pc=new PC();
		
		// Anonymous inner class by using runnable interface
		// https://www.geeksforgeeks.org/anonymous-inner-class-java/
		Thread t1=new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					pc.produce();
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}
			
		});
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try
				{
					pc.consume();
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		System.out.println("Main thread terminates");
	}
	public static class PC
	{
		public void produce() throws InterruptedException
		{
			synchronized (this) {
				System.out.println("Producer thread running");
				wait();
				System.out.println("Resumed");
			}
		}
		public void consume() throws InterruptedException
		{
			Thread.sleep(1000);
			Scanner s=new Scanner(System.in);
			
			synchronized (this) {
				System.out.println("Waiting for return key");
				s.nextLine();
				System.out.println("Return key pressed");
				
				notify();
				Thread.sleep(2000);
				System.out.println("Passing control to producer");
			}
		}
	}
}

/*
Reference : https://www.geeksforgeeks.org/inter-thread-communication-java/

Output :
Producer thread running
Waiting for return key

Return key pressed
Passing control to producer
Resumed
Main thread terminates


If notify is commented, then the program never terminates since producer thread is waiting and is never terminated.
*/