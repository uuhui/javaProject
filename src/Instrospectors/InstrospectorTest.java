package Instrospectors;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.LazyDynaBean;

import reflect.Person;

public class InstrospectorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String propertyName = "name";
		String newValue = "李四";
		Person person = new Person("张三", "21");
		getProperty(propertyName, person);
		setProperty(propertyName, newValue, person);
		System.out.println(person.getName());

		// 使用beanUtils jar开源工具
		System.out.println(BeanUtils.getProperty(person, "name"));
		BeanUtils.setProperty(person, "name", "小明");
		System.out.println(BeanUtils.getProperty(person, "name"));
		System.out.println(BeanUtils.getProperty(person, "name").getClass()
				.getName());
	}

	private static void setProperty(String propertyName, String newValue,
			Object obj) throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		/*
		 * PropertyDescriptor pd=new
		 * PropertyDescriptor(propertyName,obj.getClass()); Method
		 * methodSetName=pd.getWriteMethod(); methodSetName.invoke(obj,
		 * newValue);
		 */
		// 使用内省方式
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			if (pd.getName().equals(propertyName)) {
				Method methodSetName = pd.getWriteMethod();
				methodSetName.invoke(obj, newValue);
				break;
			}
		}
		LazyDynaBean  hh=new LazyDynaBean();
		
	}

	private static void getProperty(String propertyName, Object obj)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, obj
				.getClass());
		Method methodGetName = pd.getReadMethod();
		Object returnVal = methodGetName.invoke(obj);
		System.out.println(returnVal);
	}

}
