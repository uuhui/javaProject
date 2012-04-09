package Annotations;

import java.lang.reflect.Method;

import Enum.Week;

@CustomerAnnotation(members = { 3, 4, 5 }, myClass = java.io.FileInputStream.class, myWeek = Week.SUN, StrName = "我们", value = "中国")
public class AnnotationTest {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (AnnotationTest.class.isAnnotationPresent(CustomerAnnotation.class)) {
			CustomerAnnotation customerAnnotation = (CustomerAnnotation) AnnotationTest.class
					.getAnnotation(CustomerAnnotation.class);
			System.out.println("value="+customerAnnotation.value());
			System.out.println("members="+customerAnnotation.members());
			System.out.println(customerAnnotation.myWeek() + "  next Day is "+customerAnnotation.myWeek().nextDay());
			System.out.println("class ="+customerAnnotation.myClass());
			System.out.println("customerAnnotation's metaAnnotation ="+customerAnnotation.customerMetaAnnotation());
			System.out.println("==========================");
			Method [] methods=customerAnnotation.getClass().getDeclaredMethods();
			for(Method method:methods){
				System.out.println(method.getName());
			}
		}
	}

}
