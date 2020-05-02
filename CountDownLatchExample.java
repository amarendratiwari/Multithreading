package multithreading;

import java.util.concurrent.CountDownLatch;

/*A synchronization aid that allows one or more threads to wait until a set of operations being performed 
 * in other threads completes.
 * A CountDownLatch is initialized with a given count. The await methods block until the current count 
 * reaches zero due to invocations of the countDown() method, after which all waiting threads are 
 * released and any subsequent invocations of await return immediately. 
 * This is a one-shot phenomenon -- the count cannot be reset. 
 * If you need a version that resets the count, consider using a CyclicBarrier.*/

public class CountDownLatchExample {

	public static void main(String[] args) {
		
		final CountDownLatch latch = new CountDownLatch(3);
		Thread thread1 = new Thread(new CountDown("Gatherer 1", latch, 1000));
		Thread thread2 = new Thread(new CountDown("Gatherer 2", latch, 1000));
		Thread thread3 = new Thread(new CountDown("Gatherer 3", latch, 1000));
		thread1.start();
		thread2.start();
		thread3.start();
		System.out.println("All Threads are waiting here");
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Crossed all the threads countdown");
		
	}

}

class CountDown implements Runnable{
	
	private final String name;
	private final CountDownLatch latch;
	private final int timeout;
	
	public CountDown(String name, CountDownLatch latch, int timeout) {
		this.name = name;
		this.latch = latch;
		this.timeout = timeout;
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Passing the thread "+name);
		latch.countDown();
		System.out.println("Crossed the CountDown");
		
		
	}
	
}
