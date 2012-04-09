package proxys;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// ��ӡ�������collection������Ϣ
		// CollectionProxy();

		// ����proxyʵ��
		CreateProxy();
	}

	@SuppressWarnings("unchecked")
	private static void CreateProxy() throws Exception {
		// TODO Auto-generated method stub
		System.out
				.println("--------------- create instance object--------------------");
		Class clazzProxy = Proxy.getProxyClass(Collection.class
				.getClassLoader(), Collection.class);

		Constructor constructor = clazzProxy
				.getConstructor(InvocationHandler.class);
		class MyInvokehandler1 implements InvocationHandler {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}

		}
		// ���Ѵ������ࣨInvokehand��
		Collection proxy1 = (Collection) constructor
				.newInstance(new MyInvokehandler1());
		System.out.println(proxy1.toString());
		// ʹ��������ķ�ʽ��
		Collection proxy2 = (Collection) constructor
				.newInstance(new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						// TODO Auto-generated method stub
						return null;
					}

				});
		// ʹ��һ����λ�ķ�ʽ
		Collection proxy3 = (Collection) Proxy.newProxyInstance(
				Collection.class.getClassLoader(),
				new Class[] { Collection.class }, new InvocationHandler() {

					ArrayList target = new ArrayList();

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						// TODO Auto-generated method stub
						return method.invoke(target, args);
					}
				});
		proxy3.add("12");
		proxy3.add("32");
		System.out.println(proxy3.size());

		final List target = new ArrayList();
		List proxy = (List) getProxy(target, new MyAdviceImpl());
		proxy.add("abc");
	}

	public static Object getProxy(final Object target, final MyAdvice advice) {
		Object proxyObj = Proxy.newProxyInstance(target.getClass()
				.getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						// TODO Auto-generated method stub
						advice.beforeMethod(method);
						Object returnObj = method.invoke(target, args);
						advice.afterMethod(method);
						return returnObj;
					}
				});
		return proxyObj;
	}

	@SuppressWarnings("unchecked")
	private static void CollectionProxy() {
		Class classProxy = Proxy.getProxyClass(Collection.class
				.getClassLoader(), Collection.class);
		Constructor[] Construtors = classProxy.getConstructors();
		System.out
				.println("-------------------constructor list ------------------");
		for (Constructor constructor : Construtors) {
			// ��ʾconstructor
			String name = constructor.getName();
			StringBuilder stringBuilder = new StringBuilder(name);
			stringBuilder.append('(');
			// ��ʾconstructor�Ĳ���
			Class[] classParams = constructor.getParameterTypes();
			for (Class classParam : classParams) {
				stringBuilder.append(classParam.getName() + ",");

			}
			if (classParams != null && classParams.length != 0)
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			stringBuilder.append(')');
			System.out.println(stringBuilder.toString());
		}

		Method[] methods = classProxy.getMethods();
		System.out
				.println("-----------------------------Method list------------------------");
		for (Method method : methods) {
			// ��ʾmethod��
			String name = method.getName();
			StringBuilder stringBuilder = new StringBuilder(name);
			// ��ʾmethod �ڵĲ���
			stringBuilder.append('(');
			Class[] clazzParams = method.getParameterTypes();
			for (Class clazzParam : clazzParams) {
				stringBuilder.append(clazzParam.getName() + ",");
			}
			if (stringBuilder != null && stringBuilder.length() != 0)
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			stringBuilder.append(')');
			System.out.println(stringBuilder.toString());
		}
	}
}
