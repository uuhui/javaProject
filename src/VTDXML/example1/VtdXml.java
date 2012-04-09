package VTDXML.example1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.ximpleware.AutoPilot;
import com.ximpleware.FastLongBuffer;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

public class VtdXml {
	public static void main(String[] args) {

		String fileName = getPath() + "test.xml";
		// selectVtdXMLByCondition(fileName);
		String newFileName = "c:/cd_New.xml";
		// 将一个xml文件写入另一个xml中
		// addXml();
		// updateXml(getPath()+"cd_update.xml");

		// splitXml();

		 vtdXmlIterator(getPath()+"cd.xml");
//		modifyXml(getPath() + "oldpo.xml", getPath() + "newpo.xml");
	}

	public static void testAddFile() {

		String sContent = "市场调研公司Forrester Research（以下简称“Forrester”）的分析师分析师莎拉·罗特曼-埃普斯（Sarah Rotman Epps）周四发布报告称，今年美国市场平板电脑销量将达到350万台。到2013年，美国平板电脑销售总量将超越台式机。";
		System.out.println(getPath());
		String sDestFile = getPath() + "myWrite.txt";
		File destFile = new File(sDestFile);

		if (!destFile.exists()) {

			try {
				System.out.println("--------");
				// destFile.createNewFile();
				writeByOutputStreamWrite(sDestFile, sContent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getPath() {
		String strPath = VtdXml.class.getResource("").getPath();
		return strPath;
		// return strPath.substring(1);
	}

	public static void vtdXmlIterator(String fileName) {
		try {
			VTDGen vg = new VTDGen();
			if (vg.parseFile(fileName, false)) {
				VTDNav vn = vg.getNav();
				AutoPilot ap = new AutoPilot(vn);
				ap.selectXPath("/CATALOG/CD");
				int i=-1;
				while ((i=ap.evalXPath())!=-1) {
					System.out.println(vn.getCurrentIndex() + vn.toString(vn.getCurrentIndex()));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 查询xml文件中的节点信息
	 * 
	 * @param fileName
	 */
	private static void selectVtdXMLByCondition(String fileName) {
		try {
			File f = new File(fileName);
			FileInputStream fis = new FileInputStream(f);
			byte[] ba = new byte[(int) f.length()];
			fis.read(ba);
			VTDGen vg = new VTDGen();
			vg.setDoc(ba);
			vg.parse(false);
			VTDNav vn = vg.getNav();
			if (vn.matchElement("purchaseOrder")) {
				System.out.println(" orderDate==>"
						+ vn.toString(vn.getAttrVal("orderDate")));
				if (vn.toElement(VTDNav.FIRST_CHILD, "item")) {
					if (vn.toElement(VTDNav.FIRST_CHILD)) {
						do {
							System.out.print(vn.toString(vn.getCurrentIndex()));
							System.out.print("==>");

							System.out.println(vn.toString(vn.getText()));
						} while (vn.toElement(VTDNav.NEXT_SIBLING));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("exception occurred ==>" + e);
		}
	}

	/**
	 * 查询xml相应的节点写入一个新的xml文件
	 * 
	 * @param fileName
	 */
	public static void updateXml(String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(fileName));
			VTDGen vg = new VTDGen();
			String soucefile = getPath() + "cd.xml";
			if (vg.parseFile(soucefile, false)) {
				VTDNav vn = vg.getNav();
				AutoPilot ap = new AutoPilot(vn);
				String selectXpath = "/CATALOG/CD[PRICE > 10]";
				ap.selectXPath(selectXpath);
				FastLongBuffer flb = new FastLongBuffer(4);
				int i = 0;
				byte[] xml = vn.getXML().getBytes();
				while ((i = ap.evalXPath()) != -1) {
					flb.append(vn.getElementFragment());
				}
				int size = flb.size();
				if (size == 0) {
					fos.write(xml); // no change needed
				} else {
					int os1 = 0;
					for (int k = 0; k < size; k++) {
						fos.write(xml, os1, flb.lower32At(k) - 1 - os1);
						os1 = flb.upper32At(k) + flb.lower32At(k);
					}
					fos.write(xml, os1, xml.length - os1);
					fos.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将xml文件根据节点分成几个xml文件
	 */
	public static void splitXml() {
		try {
			VTDGen vg = new VTDGen();
			if (vg.parseFile(getPath() + "cd.xml", false)) {
				VTDNav vn = vg.getNav();
				AutoPilot ap = new AutoPilot(vn);
				String selectXpath = "/CATALOG/CD[PRICE > 10]";
				ap.selectXPath(selectXpath);
				byte[] xml = vn.getXML().getBytes();
				FastLongBuffer flb = new FastLongBuffer(4);
				while (ap.evalXPath() != -1)
					flb.append(vn.getElementFragment());
				int size = flb.size();
				if (size != 0) {
					for (int k = 0; k < size; k++) {
						FileOutputStream fos = new FileOutputStream(new File(
								"c:/cd_" + k + ".xml"));
						fos.write(xml, flb.lower32At(k), flb.upper32At(k));
						fos.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void modifyXml(String FileName, String desFileName) {
		try {
			VTDGen vg = new VTDGen();
			File fo = new File(desFileName);
			FileOutputStream fos = new FileOutputStream(fo);
			if (vg.parseFile(FileName, false)) {
				VTDNav vn = vg.getNav();
				AutoPilot ap = new AutoPilot(vn);
				XMLModifier xm = new XMLModifier(vn);
				ap.selectXPath("/purchaseOrder/items/item[@partNum='872-AA']");
				int i = -1;
				if ((i = ap.evalXPath()) != -1) {
					xm.remove();
					xm.insertBeforeElement("<something>ads</something>\n");
				}
				xm.output(fos);
				fos.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void addXml() {
		try {

			VTDGen vg = new VTDGen();
			File fo = new File(getPath() + "add.xml");
			FileOutputStream fos = new FileOutputStream(fo);
			if (vg.parseFile(getPath() + "test.xml", false)) {
				VTDNav vn = vg.getNav();
				if (vn.matchElement("purchaseOrder")) {
					int i = vn.getAttrVal("orderDate");
					if (i != -1) {
						// get the starting offset of "1999-10-21"
						int os1 = vn.getTokenOffset(i);
						// get the ending offset of "1999-10-21"
						int os2 = vn.getTokenOffset(i) + vn.getTokenLength(i);
						// get the total number of bytes of XML
						int os3 = vn.getXML().length();
						byte[] xml = vn.getXML().getBytes();

						// write everything before "1999-10-21"
						fos.write(xml, 0, os1);
						// write "2006-6-17"
						fos.write("2006-6-17".getBytes());
						// write everyting after
						fos.write(xml, os2, os3 - os2);
						fos.close();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("exception occurred ==>" + e);
		}
	}

	public static void writeByOutputStreamWrite(String _sDestFile,

	String _sContent) throws IOException {

		OutputStreamWriter os = null;

		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(_sDestFile);

			os = new OutputStreamWriter(fos, "UTF-8");

			os.write(_sContent);

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (os != null) {

				os.close();

				os = null;

			}
			if (fos != null) {

				fos.close();

				fos = null;

			}
		}
	}

}
