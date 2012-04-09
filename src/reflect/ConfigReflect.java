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
//		使用根目录
//	InputStream ips=ConfigReflect.class.getResourceAsStream("/config.properties");
		//使用相对目录(相对该类而言)
	//InputStream ips=ConfigReflect.class.getResourceAsStream("config2.properties");
		//使用根目录
		//InputStream ips=ConfigReflect.class.getClassLoader().getResourceAsStream("reflect/config.properties");
//		InputStream ips=new FileInputStream("src/config.properties");
		//针对src目录
		InputStream ips=ConfigReflect.class.getClassLoader().getResourceAsStream("config.properties");
		Properties props = new Properties();
		props.load(ips);
		ips.close();

		String className = (String) props.getProperty("className");
		System.out.println(className);
		
	}

}
