package test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "1";
		changInt(a);
		System.out.println(a);

		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		Set<Entry<String, String>> s = map.entrySet();
		for (Iterator i = s.iterator(); i.hasNext();) {
			Entry<String, String> en = (Entry<String, String>) i.next();
			System.out.println(en.getKey());
		}
		for (Iterator i = map.entrySet().iterator(); i.hasNext();) {
			Map.Entry<String, String> me = (Map.Entry<String, String>) i.next();
			System.out.println(me.getKey() + "=" + me.getValue());
		}
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("c");

		Collection<?> c = new ArrayList<String>();
		for (Iterator i = list.iterator(); i.hasNext();)
			System.out.println(i.next());
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(1);
		List<String> l2 = new ArrayList<String>();
		l2.add("abc");
		List<?> l = l1;
		System.out.println(l.get(0));
		l.clear();
		l = l2;
		System.out.println(l.get(0));

		try {
			Class<?> clazz = Class.forName("java.lang.String");
			System.out.println(clazz.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	Type genType=B.class.getGenericSuperclass();
 if (genType instanceof ParameterizedType) {
	 Type param=((ParameterizedType) genType).getActualTypeArguments()[0];
	    System.out.println(param.toString());
		
	}
   
	}

	public static <T> void printAll(T obj) {
	}

	private static void changInt(String a) {
		a = "3";
	}
}

class A <Person>{

}
class B extends A{
	
}
