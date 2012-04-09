package reflect;

public class MethodTest {
	public static void sayHello(String name) {
		System.out.println(name
				+ " said: 'this meoth is used by reflect method.'");
	}

	public static void main(String[] args) {
		for (String arg : args) {
			System.out.println(arg);
		}
	}
}
	