package threads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		ExecutorService service = Executors.newFixedThreadPool(2);
		Runnable runnable = new Runnable(){
			A a = new A();
			public void run() {
				//MyData.x.set(new Random().nextInt(10000));
				MyData.getMyDate().setY(new Random().nextInt(10000));
				a.say();
			}
		};
		
		service.execute(runnable);
		service.execute(runnable);
		service.shutdown();
	}

}

class  MyData{
	public static ThreadLocal x = new ThreadLocal(); 	
	public static void set(Object value){
		x.set(value);
	}
	public static Object get(){
		return x.get();
	}
	
	
	private static ThreadLocal data = new ThreadLocal();
	
	/*对于不同的线程来说，getMyData拿到的对象都不相同，
	 * 对同一个线程来说，不管getMyData多少次和在哪里getMyData，拿到的都是同一个*/
	public static MyData getMyDate(){
		MyData myData = (MyData)data.get();
		if(myData ==  null){
			myData = new MyData();
			data.set(myData);
		}
		return myData;
	}
	

	private MyData(){}
	private Integer y;
	public void setY(Integer y){
		this.y = y;
	}
	public Integer getY(){
		return y;
	}
}

class A{
	public void say(){
		//System.out.println("say " + Thread.currentThread().getName() + " geted " + MyData.x.get());
		System.out.println("say " + Thread.currentThread().getName() + " geted " + MyData.getMyDate().getY());
		
		new B().sayHello();
	}
}

class B{

	public void sayHello() {
		//System.out.println("sayHello " +Thread.currentThread().getName() + " geted " + MyData.x.get());
		System.out.println("sayHello " + Thread.currentThread().getName() + " geted " + MyData.getMyDate().getY());
		
	}
	
}
