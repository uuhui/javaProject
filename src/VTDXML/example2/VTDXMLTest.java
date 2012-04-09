package VTDXML.example2;

import java.io.IOException;

import com.ximpleware.AutoPilot;
import com.ximpleware.IndexWriteException;
import com.ximpleware.NavException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;



public class VTDXMLTest {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		//showElementNs();
		//getElementByXPath();
		//writeXmlByIndex();
//		addAttr();
		deleteAndAddElement();
	}
	

	/**
	 * 显示ＮameSpace节点下的内容
	 */
	private static void showElementNs() {
		VTDGen vg = new VTDGen();
		try {
			if (vg.parseFile(getPath() + "input.xml", true)) {
				VTDNav vn = vg.getNav();
				// toElementNS is the namespace aware version of toElement which
				// navigates the cursor
				if (vn.toElementNS(VTDNav.FIRST_CHILD, "someURL", "b")) {
					int i = vn.getText(); // get the VTD record index
					if (i != -1) {
						// convert i into string before printing,
						// toNormalizedString(i) and toRawString(i) are two
						// other options
						System.out.println("the text node value at " + i
								+ " ==> " + vn.toString(i));
					}
				}
			}
		} catch (NavException ex) {
			ex.printStackTrace();
		}
	}

	public static void getElementByXPath(){
		VTDGen vg=new VTDGen();
		try {
			AutoPilot ap=new AutoPilot();
			ap.selectXPath("/a/b/text()");
			if(vg.parseFile(getPath()+"test.xml", true)){
				VTDNav vn=vg.getNav();
				ap.bind(vn);
				int i=-1;
				while((i=ap.evalXPath())!=-1){
					 System.out.println("the text node isndex val is " + 
			                    i + " the text string ==>" + vn.toString(vn.getCurrentIndex()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public static void writeXmlByIndex(){
		VTDGen vg=new VTDGen();
		try {
			if(vg.parseFile(getPath()+"test.xml", true)){
			vg.writeIndex(getPath()+"input_new.xml");
			}
		} catch (IndexWriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addAttr(){
		int i;
        VTDGen vg = new VTDGen();
        try {
			AutoPilot ap = new AutoPilot();
			XMLModifier xm = new XMLModifier();
			String str=getPath();
			System.out.println(str);
			// loadIndex must be a vxl file and not an xml file
			VTDNav vn = vg.loadIndex(getPath()+"input.vxl");
			ap.selectXPath("/a/b");
			ap.bind(vn);
			xm.bind(vn);
			while ((i = ap.evalXPath()) != -1){
			    // operate on the cursor element
			    xm.insertAttribute(" attr1='val' attr2='val2'");
			}
			xm.output(getPath()+"test_new.xml");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void deleteAndAddElement(){
		VTDGen vg=new VTDGen();
		try{
		if(vg.parseFile(getPath()+"test.xml", true)){
			VTDNav vn=vg.getNav();
			AutoPilot ap=new AutoPilot(vn);
			XMLModifier xm=new XMLModifier(vn);
			ap.selectXPath("/a/c");
			int i=-1;
			while((i=ap.evalXPath())!=-1){
				   xm.insertBeforeElement("<lala>lala</lala>\r\n");
				   xm.insertAfterElement("\r\n<lala/>");
			 	xm.remove();
			}
		  
             
			xm.output(getPath()+"modify.xml");
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static String getPath() {
		return VTDXMLTest.class.getResource("").getPath();
	}
}
