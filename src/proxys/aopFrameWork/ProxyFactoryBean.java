package proxys.aopFrameWork;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import proxys.MyAdvice;

public class ProxyFactoryBean {
	private MyAdvice advice;
	private Object target;

	public MyAdvice getAdvice() {
		return advice;
	}

	public void setAdvice(MyAdvice advice) {
		this.advice = advice;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Object getProxy() {
		// TODO Auto-generated method stub
		Object proxy3 = Proxy.newProxyInstance(target.getClass()
				.getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {

					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {

						advice.beforeMethod(method);
						Object retVal = method.invoke(target, args);
						advice.afterMethod(method);
						return retVal;

					}
				});
		return proxy3;
	}
}
