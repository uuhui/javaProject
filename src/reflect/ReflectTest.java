package reflect;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;

public class ReflectTest {

	/**
	 * @param args
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(int.class == Integer.class);
		System.out.println(int.class == Integer.TYPE);
		System.out.println(int[].class.isArray());
		System.out.println("======================");

		Constructor[] constructors = int.class.getConstructors();
		if (constructors.length == 0)
			System.out.println("this classs not constructor");
		for (Constructor constructor : constructors) {
			System.out.println(constructor.toString());
		}

		Person person = new Person("张三", "男");
		Field fieldName = person.getClass().getField("name");
		System.out.println(fieldName.get(person));

		// 私有变量private
		Field fieldSex = person.getClass().getDeclaredField("sex");
		fieldSex.setAccessible(true);// 允许访问私有变量
		System.out.println(fieldSex.get(person));
		fieldSex.set(person, "男同志");
		System.out.println(person);

		Method methodTest = MethodTest.class
				.getMethod("sayHello", String.class);
		// this metohd is static metod invoke first parameter is null
		methodTest.invoke(null, "method");
		System.out.println("====================");
		// 通过反射调用类的main方法
		String startingName = args[0];
		Method mainMethod = Class.forName(startingName).getMethod("main",
				String[].class);
		mainMethod
				.invoke(null, new Object[] { new String[] { "a", "b", "c" } });
		// mainMethod.invoke(null, (Object)new String[]{"1","2","3"});
		
		int []a1=new int[]{1,2,3,4};
		String []a2={"a","b","c"};
		Object obj=(Object)a1;
		System.out.println(Arrays.asList(a2));
		//反射显示数组的个数
		printObject(a1);
		printObject("abc");
	}
	
	public static void printObject(Object obj){
		if(obj.getClass().isArray()){
			int len=Array.getLength(obj);
			for(int i=0;i<len;i++){
				System.out.println(Array.get(obj, i));
			}
		}else
			System.out.println(obj);
	}

}
