package proxys;

import java.lang.reflect.Method;

public class MyAdviceImpl  implements MyAdvice{
long beginTime=0;
	@Override
	public void afterMethod(Method method) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("����"+method.getName()+"���������ˡ���");
		long endTime=System.currentTimeMillis();
		System.out.println(method.getName()+"����������" +(endTime-beginTime) +"ms");
	}

	@Override
	public void beforeMethod(Method method) {
		// TODO Auto-generated method stub
		System.out.println("����"+method.getName()+"������ʼ�ˡ���");
		beginTime=System.currentTimeMillis();
	}


}
