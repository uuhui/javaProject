package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.execute(new Runnable(){
			public void run() {
				System.out.println("I dead");
			}		
			
		});
		//如果没有下面的代码，程序一直处于不结束状态，
		service.shutdown();
		//service.shutdownNow();
	}

}
