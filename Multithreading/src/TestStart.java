
public class TestStart extends Thread{
	public void run()
	{
		try {
			System.out.println("Thread "+Thread.currentThread().getName()+" is running");
		}
		catch(Exception e)
		{
			System.out.println("Exception is caught");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TestStart object=new TestStart();
		object.start();
		System.out.println(object.getState());
		object.start();
	}

}
/*
 Output of above code :
 Thread Thread-0 is running
RUNNABLE
Exception in thread "main" java.lang.IllegalThreadStateException
	at java.lang.Thread.start(Thread.java:705)
	at TestStart.main(TestStart.java:18)
 */

/*
Explanation :
Thread.start() method
Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
The result is that two threads are running concurrently: the current thread (which returns from the call to the start method) and the other thread (which executes its run method).

It is never legal to start a thread more than once. In particular, a thread may not be restarted once it has completed execution.

Imp link : https://stackoverflow.com/questions/8072933/why-does-an-illegalthreadstateexception-occur-when-thread-start-is-called-again
*/