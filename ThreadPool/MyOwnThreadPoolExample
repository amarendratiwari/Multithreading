package multithreading;

import java.util.stream.IntStream;

public class MyOwnThreadPoolExample {

	public static void main(String[] args) {
		
		ThreadPool threadPool = new ThreadPool(5,10);
		Runnable runnable = ()->{
			System.out.println(Thread.currentThread().getName()+" started");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" completed");
		};

		IntStream.rangeClosed(0, 10).forEach((i)-> {
			try {
				threadPool.execute(runnable);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		threadPool.stop();
	}
	

}
