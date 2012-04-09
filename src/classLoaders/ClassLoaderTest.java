package classLoaders;

import java.util.HashMap;
import java.util.Map.Entry;

public class ClassLoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
		while (classLoader != null) {
			System.out.println(classLoader.getClass().getName());
			classLoader = classLoader.getParent();
		}
		System.out.println(classLoader);
	}

}
