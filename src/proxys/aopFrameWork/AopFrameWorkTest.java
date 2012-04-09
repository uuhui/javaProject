package proxys.aopFrameWork;

import java.io.InputStream;
import java.util.List;

public class AopFrameWorkTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream ips = AopFrameWorkTest.class
				.getResourceAsStream("config.properties");
		BeanFactory beanFactory = new BeanFactory(ips);
		Object bean = beanFactory.getBean("bean");
		System.out.println(bean.getClass().getName());
		List list = (List) bean;
		list.add("abc");
	}

}
