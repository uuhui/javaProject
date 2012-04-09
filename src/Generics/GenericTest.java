package Generics;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GenericTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 范型是给java编译器"看"的我们可以通过反射绕过范型仍然可以编译通过并且通过
		// 如下面的例子：(字节码是相同的)
		Collection<Integer> collection1 = new ArrayList<Integer>();
		Collection<String> collection2 = new ArrayList<String>();
		// collection1与collection2的字节码是相同的
		System.out.println(collection1.getClass() == collection2.getClass());
		collection1.getClass().getMethod("add", Object.class).invoke(
				collection1, "abc");
		System.out.println(collection1.size());

		// ？通配符的使用 打印各种集合类型的数据
		printCollection(collection1);
		printCollection(collection1);
		// hashMap的遍历
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "a");
		map.put("2", "b");
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		String[] str = { "abc", "xyz", "ccc" };
		swap(str, 1, 2);
		for (String s : str) {
			System.out.println(s);
		}
		System.out.println("---------------------------------");
		/*
		 * 打印参数化的类型参数是什么数据类型
		 */
		Method method=GenericTest.class.getMethod("applyCollection", Collection.class);
	Type []types=	method.getGenericParameterTypes();
	ParameterizedType parameterizedType=(ParameterizedType)types[0];
	System.out.println(parameterizedType.getRawType());//得到什么类型的集合
	System.out.println(parameterizedType.getActualTypeArguments()[0]);//参数化的类型是什么类型
	
	}

	// 使用？通配符打印各种参数化的集合类型
	// ？通配符可以引用引用其它各种参数化的类型，
	// ?通配符定义的变量主要用作引用。
	// ？通配符可以调用与参数化无关的方法，不能调用与参数化有关的方法.
	// ?通配符可以设置它的上边界与下边界
	// 上边界是指参数化的类型的子类 下边界是指参数化的类型的父类
	// 上边界： Collection<? extends Number> x=new Collection<Integer>()
	// 上边界：不能是: collection<? extends Number> x=new Collection<String>()
	// 下边界：Collection<? supper Integer> x=new Collection<Number>();
	// 下边界：不能是：Collection<? supper Integer> x=new Collection<Byte>()

	public static void printCollection(Collection<?> collection) {
		for (Object obj : collection)
			System.out.println(obj);
	}

	// 使用自定义范型方法打印任意参数化的集合类型
	public static <T> void printCollection2(Collection<T> collection) {
		for (T t : collection)
			System.out.println(t);
	}

	public static <T> void swap(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// 使用自定义的范型方法
	public static <T> T autoConvert(T t) {
		return (T) t;
	}

	// 通过反射得到参数化的类型是什么类型
	public  void applyCollection( Collection<String> collection) {

	}

}
