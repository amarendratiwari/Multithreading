package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
	
	private LinkedBlockingQueue<Runnable> taskQueue = null;
	private List<CustomThread> listThreads = new ArrayList<CustomThread>();
	private boolean isStopped = false;
	
	public ThreadPool(int threads,int tasks) {
		//Queue for all runnable tasks
		taskQueue = new LinkedBlockingQueue<Runnable>(tasks);
		//Adding all the threads
		for(int i=0;i<threads;i++) {
			listThreads.add(new CustomThread(taskQueue));
		}
		//Starting all the threads
		listThreads.stream().forEach((thread)-> thread.start());
	}
	
	//Custom execute method
	public void execute(Runnable runnableTask) throws InterruptedException {
		if(isStopped)
			throw new IllegalStateException("Thread is already stopped");
		taskQueue.put(runnableTask);
	}
	
	public synchronized void stop() {
		while(!taskQueue.isEmpty()) {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		this.isStopped = true;
		for(CustomThread thread : listThreads) {
			thread.doStop();
		}
	}
}
