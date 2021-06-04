import java.util.Scanner;

public class PrintNNumbers {
// Study about InterruptedException and IllegalMonitorStateException
	public static void main(String[] args) throws InterruptedException {
		Scanner in=new Scanner(System.in);
 // Why below 2 fields have to marked as final? Otherwise we get "Cannot refer to the non-final local variable n defined in an enclosing scope" compiler error
		final int n=in.nextInt();
		final Printer printer =new Printer();
		Thread oddPrinter=new Thread(new Runnable() {
			
			@Override
			public void run() {
// Why can't we use throws InterruptedException declaration here instead of below try catch ??
				try {
					printer.printOddNumbers(n);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread evenPrinter=new Thread(new Runnable() {
			
			@Override
			public void run(){
				try {
					printer.printEvenNumbers(n);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		oddPrinter.start();
		evenPrinter.start();
		
		oddPrinter.join();
		evenPrinter.join();

	}
	public static class Printer
	{
		public void printOddNumbers(int n) throws InterruptedException
		{
			synchronized (this) {
				for(int i=1;i<=n;i+=2)
				{
					System.out.print(i+" ");
					notify();
					wait();
				}
			}
		}
		
		public void printEvenNumbers(int n) throws InterruptedException
		{
			Thread.sleep(100);
			synchronized (this) {
				for(int i=2;i<=n;i+=2)
				{
					System.out.print(i+" ");
					notify();
					wait();
				}
			}
		}
	}
}
