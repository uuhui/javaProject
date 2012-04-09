import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.beanutils.PropertyUtils;



public class json {
public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	String s = "{name:'重量',property:'p1'}";
	XMLSerializer xmlSerializer = new XMLSerializer();
	JSONObject object = JSONObject.fromObject(s);
	String xmlObject = xmlSerializer.write(object);
	System.out.println(xmlObject);
	
	String s2 = "{name:'重量',property:'p1'}";
	JSONObject jsonObject = JSONObject.fromObject(s);
	Object bean = JSONObject.toBean(jsonObject);
  System.out.println(jsonObject.get("name"));
  System.out.println(PropertyUtils.getProperty(bean, "name"));
}
}
