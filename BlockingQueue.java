package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BlockingQueue {

	public static void main(String[] args) {
		
		
		
		BlockingQueue queue = new BlockingQueue();
		CustomBlockingQueue<Integer> blockingQueue = queue.new CustomBlockingQueue<Integer>(5);

		Runnable producerRunnable= () -> {
			IntStream.range(1, 10)
			.forEach( (i) -> {
				try {
					System.out.println("Produces "+i);
					blockingQueue.enQueue(i);
				}catch (Exception e) {
					e.printStackTrace();
				}	

			});
		};

		Runnable consumberRunnable = ()->{
			IntStream.range(1,10)
			.forEach( (i) ->{
				try {
					Thread.sleep(1000);
					System.out.println("Consuming "+i);
					blockingQueue.deQueue();
				}catch (Exception e) {
					e.printStackTrace();
				}
			});
		};
		
		new Thread(producerRunnable).start();
		new Thread(consumberRunnable).start();
	}

	class CustomBlockingQueue<T>{

		private int maxSize;
		private List<T> queue;

		public CustomBlockingQueue(int size) {
			this.maxSize = size;
			queue = new ArrayList<T>();
		}

		public synchronized void enQueue(T object) throws InterruptedException{
			if(this.queue.size() == maxSize) {
				wait();
			}
			if(queue.isEmpty()) {
				notifyAll();
			}
			queue.add(object);

		}
		public synchronized T deQueue() throws InterruptedException{
			while(queue.isEmpty()) {
				wait();
			}
			if(this.queue.size() == maxSize) {
				notifyAll();
			}
			return queue.remove(0);
		}
	}

}
