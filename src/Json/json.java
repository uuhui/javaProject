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
		String s = "{name:'����',property:'p1'}";
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSONObject object = JSONObject.fromObject(s);
		String xmlObject = xmlSerializer.write(object);
		System.out.println(xmlObject);

		// json/��̬Bean
		String s2 = "{name:'����',property:'p1'}";
		JSONObject jsonObject = JSONObject.fromObject(s2);
		Object bean = JSONObject.toBean(jsonObject);
		System.out.println(jsonObject.get("name"));
		System.out.println(PropertyUtils.getProperty(bean, "name"));

		// json/List
		String s3 = "[{name:'����',property:'p1'},{name:'�ߴ�',property:'p2'},{name:'�Կ����� ',property:'p3'},{name:'Ӳ������',property:'p4'},{name:'������',property:'p5'}, {name:'�ڴ�',property:'p6'},{name:'�ͺ�',property:'p7'},{name:'���� ',property:'p8'},{name:'Ʒ��',property:'p9'}]";
		JSONArray jsonArray = JSONArray.fromObject(s3);
		List<Shop> shops = new ArrayList<Shop>();
		for (Object obj : jsonArray) {
			JSONObject job = jsonObject.fromObject(obj);
			Shop shop = (Shop) JSONObject.toBean(job, Shop.class);
			shops.add(shop);
		}
		System.out.println(shops);
		System.out.println("=====================");
		// json/��̬Bean
		String str = "{'shopList':[{name:'����',property:'p1'},{name:'�ߴ�',property:'p2'}, {name:'�Կ�����',property:'p3'},{name:'Ӳ������',property:'p4'},{name:'������ ',property:'p5'},{name:'�ڴ�',property:'p6'},{name:'�ͺ�',property:'p7'}, {name:'����',property:'p8'},{name:'Ʒ��',property:'p9'}]}";
		Map<String,Class>  map=new HashMap<String ,Class>();
		/*
		 * map�е�key �� str��json���ݵ�key�� JsonObject.toBean(obj,Class,Map)�е�Class�������Property
		 * ���߱�����ͬ������һ���Ĳ�ͬ���ᵼ��Exception
		 * str��json����key��Calss ��Propery����ͬ��ᵼ��
		 * net.sf.json.JSONException: java.lang.NoSuchMethodException: Unknown property 'properyname' on class calssName
		 * map�е�key �� 
		 * net.sf.ezmorph.bean.MorphDynaBean cannot be cast to Json.Shop
		 */
		map.put("shopList", Shop.class);
		
		//json/��̬Bean
		ShopList shopList = (ShopList) JSONObject.toBean(JSONObject.fromObject(str), ShopList.class, map); 
		System.out.println("json/��̬Bean:");
		for(Shop sp:shopList.getShopList())
			System.out.println(sp.getName());
		Object obj=JSONObject.toBean(JSONObject.fromObject(str), ShopList.class); 
		System.out.println(obj);
		System.out.println("java/��̬Bean:");
		for(Object objBean:((ShopList)obj).getShopList())
			System.out.println(PropertyUtils.getProperty(objBean, "name"));  

	}
}
