package multithreading;

import java.util.concurrent.LinkedBlockingQueue;

public class CustomThread extends Thread{
	
	private LinkedBlockingQueue<Runnable> tasksQueue= null;
	private boolean isStopped = false;
	
	public CustomThread(LinkedBlockingQueue<Runnable> tasks) {
		this.tasksQueue =  tasks;
	}
	
	@Override
	public void run() {
		
		while(!isStopped) {
			
			try {
				Runnable runnable = tasksQueue.take();
				runnable.run();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

	public synchronized void doStop() {
		this.isStopped = true;
		this.interrupt();
		
	}
	public synchronized boolean isStopped() {
		return this.isStopped;
	}

}
