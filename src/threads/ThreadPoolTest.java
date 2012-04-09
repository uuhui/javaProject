package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

	/**
	 * @param args
	 */
	private static int count = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newFixedThreadPool(3);
		//ExecutorService service = Executors.newCachedThreadPool();
		
		for(int i=0;i<10;i++){
			//仔细品味runnable对象放到循环里面和外面的区别，为了让每个对象有自己独立的编号
			count = i;
			Runnable runnable = new Runnable(){
				private int mySequence = count;
				public void run() {
					for(int i=0;i<5;i++){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + 
								" is serving " + mySequence + " task,loop of " + i);
					}
				}
				
			};
			service.execute(runnable);
		}
	}

}
