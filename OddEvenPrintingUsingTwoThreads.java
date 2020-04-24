package multithreading;

public class OddEvenPrintingUsingTwoThreads {

	Object obj = new Object();
	volatile int i=1;

	public OddEvenPrintingUsingTwoThreads( Object o) {
		this.obj=o;
	}
	public static void main(String[] args) {

		OddEvenPrintingUsingTwoThreads oddEven= new OddEvenPrintingUsingTwoThreads(new Object());
		Thread t1 = new Thread(oddEven.new OddEven());
		t1.setName("Odd");

		Thread t2 = new Thread(oddEven.new OddEven());
		t2.setName("Even");
		t1.start();
		t2.start();

	}

	class OddEven implements Runnable{

		@Override
		public void run() {
			while(i<21) {
				if(Thread.currentThread().getName()=="Even" && i%2==0) {
					synchronized (obj) {
						System.out.println("Thread name is "+Thread.currentThread().getName()+" number is "+i);
						i++;
						obj.notify();
					}

				}
				if(Thread.currentThread().getName()=="Odd" && i%2!=0) {
					synchronized (obj) {
						System.out.println("Thread name is "+Thread.currentThread().getName()+" number is "+i);
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
