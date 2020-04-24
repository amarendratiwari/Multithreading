package multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintConsecutiveNumbersByThreeThreads {

	private AtomicInteger integer = new AtomicInteger(1);
	private Object obj = new Object();
	
	public static void main(String[] args) {
		
		PrintConsecutiveNumbersByThreeThreads print = new PrintConsecutiveNumbersByThreeThreads();
		
		new Thread(print.new PrintNumberUsingThreeThreads(0),"Thread3").start();
		new Thread(print.new PrintNumberUsingThreeThreads(1),"Thread1").start();
		new Thread(print.new PrintNumberUsingThreeThreads(2),"Thread2").start();

	}
	class PrintNumberUsingThreeThreads implements Runnable{
		
		int threadNumber;
		public PrintNumberUsingThreeThreads(int number) {
			this.threadNumber = number;
			
		}
		@Override
		public void run() {
			while(integer.get()<50 ) {
				synchronized (obj) {
					if(integer.get()%3 == threadNumber) {
						System.out.println(Thread.currentThread().getName()+" printing "+ integer.getAndIncrement());;
					}
				}
			}
			
		}
		
	}

}
