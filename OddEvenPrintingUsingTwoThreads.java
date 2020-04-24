package multithreading;

public class OddEvenPrintingUsingTwoThreads {

	Object obj = new Object();
	int i=1;

	public static void main(String[] args) {

		OddEvenPrintingUsingTwoThreads oddEven= new OddEvenPrintingUsingTwoThreads();
		new Thread(oddEven.new OddEven(),"Odd").start();

		new Thread(oddEven.new OddEven(),"Even").start();

	}

	class OddEven implements Runnable{

		@Override
		public void run() {
			while(i<51) {
				if(Thread.currentThread().getName()=="Even" && i%2==0) {
					synchronized (obj) {
						System.out.println("Thread "+Thread.currentThread().getName()+" is printing "+i);
						i++;
						obj.notify();
					}

				}
				if(Thread.currentThread().getName()=="Odd" && i%2!=0) {
					synchronized (obj) {
						System.out.println("Thread "+Thread.currentThread().getName()+" is printing "+i);
						i++;
						try {
							obj.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}


		}

	}
}
