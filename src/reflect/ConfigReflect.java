package reflect;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReflect {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		ʹ�ø�Ŀ¼
//	InputStream ips=ConfigReflect.class.getResourceAsStream("/config.properties");
		//ʹ�����Ŀ¼(��Ը������)
	//InputStream ips=ConfigReflect.class.getResourceAsStream("config2.properties");
		//ʹ�ø�Ŀ¼
		//InputStream ips=ConfigReflect.class.getClassLoader().getResourceAsStream("reflect/config.properties");
//		InputStream ips=new FileInputStream("src/config.properties");
		//���srcĿ¼
		InputStream ips=ConfigReflect.class.getClassLoader().getResourceAsStream("config.properties");
		Properties props = new Properties();
		props.load(ips);
		ips.close();

		String className = (String) props.getProperty("className");
		System.out.println(className);
		
	}

}
