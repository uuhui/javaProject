package Json;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.beanutils.PropertyUtils;

public class json {
	public static void main(String[] args) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// json/Xml
		String s = "{name:'重量',property:'p1'}";
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSONObject object = JSONObject.fromObject(s);
		String xmlObject = xmlSerializer.write(object);
		System.out.println(xmlObject);

		// json/动态Bean
		String s2 = "{name:'重量',property:'p1'}";
		JSONObject jsonObject = JSONObject.fromObject(s2);
		Object bean = JSONObject.toBean(jsonObject);
		System.out.println(jsonObject.get("name"));
		System.out.println(PropertyUtils.getProperty(bean, "name"));

		// json/List
		String s3 = "[{name:'重量',property:'p1'},{name:'尺寸',property:'p2'},{name:'显卡类型 ',property:'p3'},{name:'硬盘容量',property:'p4'},{name:'处理器',property:'p5'}, {name:'内存',property:'p6'},{name:'型号',property:'p7'},{name:'货号 ',property:'p8'},{name:'品牌',property:'p9'}]";
		JSONArray jsonArray = JSONArray.fromObject(s3);
		List<Shop> shops = new ArrayList<Shop>();
		for (Object obj : jsonArray) {
			JSONObject job = jsonObject.fromObject(obj);
			Shop shop = (Shop) JSONObject.toBean(job, Shop.class);
			shops.add(shop);
		}
		System.out.println(shops);
		System.out.println("=====================");
		// json/静态Bean
		String str = "{'shopList':[{name:'重量',property:'p1'},{name:'尺寸',property:'p2'}, {name:'显卡类型',property:'p3'},{name:'硬盘容量',property:'p4'},{name:'处理器 ',property:'p5'},{name:'内存',property:'p6'},{name:'型号',property:'p7'}, {name:'货号',property:'p8'},{name:'品牌',property:'p9'}]}";
		Map<String,Class>  map=new HashMap<String ,Class>();
		/*
		 * map中的key 与 str中json数据的key和 JsonObject.toBean(obj,Class,Map)中的Class类的属性Property
		 * 三者必须相同，任意一方的不同都会导致Exception
		 * str中json数据key与Calss 的Propery不相同则会导致
		 * net.sf.json.JSONException: java.lang.NoSuchMethodException: Unknown property 'properyname' on class calssName
		 * map中的key 与 
		 * net.sf.ezmorph.bean.MorphDynaBean cannot be cast to Json.Shop
		 */
		map.put("shopList", Shop.class);
		
		//json/静态Bean
		ShopList shopList = (ShopList) JSONObject.toBean(JSONObject.fromObject(str), ShopList.class, map); 
		System.out.println("json/静态Bean:");
		for(Shop sp:shopList.getShopList())
			System.out.println(sp.getName());
		Object obj=JSONObject.toBean(JSONObject.fromObject(str), ShopList.class); 
		System.out.println(obj);
		System.out.println("java/动态Bean:");
		for(Object objBean:((ShopList)obj).getShopList())
			System.out.println(PropertyUtils.getProperty(objBean, "name"));  

	}
}
