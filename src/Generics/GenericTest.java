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
		// �����Ǹ�java������"��"�����ǿ���ͨ�������ƹ�������Ȼ���Ա���ͨ������ͨ��
		// ����������ӣ�(�ֽ�������ͬ��)
		Collection<Integer> collection1 = new ArrayList<Integer>();
		Collection<String> collection2 = new ArrayList<String>();
		// collection1��collection2���ֽ�������ͬ��
		System.out.println(collection1.getClass() == collection2.getClass());
		collection1.getClass().getMethod("add", Object.class).invoke(
				collection1, "abc");
		System.out.println(collection1.size());

		// ��ͨ�����ʹ�� ��ӡ���ּ������͵�����
		printCollection(collection1);
		printCollection(collection1);
		// hashMap�ı���
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
		 * ��ӡ�����������Ͳ�����ʲô��������
		 */
		Method method=GenericTest.class.getMethod("applyCollection", Collection.class);
	Type []types=	method.getGenericParameterTypes();
	ParameterizedType parameterizedType=(ParameterizedType)types[0];
	System.out.println(parameterizedType.getRawType());//�õ�ʲô���͵ļ���
	System.out.println(parameterizedType.getActualTypeArguments()[0]);//��������������ʲô����
	
	}

	// ʹ�ã�ͨ�����ӡ���ֲ������ļ�������
	// ��ͨ����������������������ֲ����������ͣ�
	// ?ͨ�������ı�����Ҫ�������á�
	// ��ͨ������Ե�����������޹صķ��������ܵ�����������йصķ���.
	// ?ͨ����������������ϱ߽����±߽�
	// �ϱ߽���ָ�����������͵����� �±߽���ָ�����������͵ĸ���
	// �ϱ߽磺 Collection<? extends Number> x=new Collection<Integer>()
	// �ϱ߽磺������: collection<? extends Number> x=new Collection<String>()
	// �±߽磺Collection<? supper Integer> x=new Collection<Number>();
	// �±߽磺�����ǣ�Collection<? supper Integer> x=new Collection<Byte>()

	public static void printCollection(Collection<?> collection) {
		for (Object obj : collection)
			System.out.println(obj);
	}

	// ʹ���Զ��巶�ͷ�����ӡ����������ļ�������
	public static <T> void printCollection2(Collection<T> collection) {
		for (T t : collection)
			System.out.println(t);
	}

	public static <T> void swap(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// ʹ���Զ���ķ��ͷ���
	public static <T> T autoConvert(T t) {
		return (T) t;
	}

	// ͨ������õ���������������ʲô����
	public  void applyCollection( Collection<String> collection) {

	}

}
