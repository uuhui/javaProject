package box;

public class AutoBox {
public static void main(String[] args) {
	
	Integer i1=3;
	Integer i2=3;
	//显示为true 在-128~127字节之间的数采用了缓存（享元模式），之外的数显示为False
	System.out.println(i1==i2);  
Integer i=139;
Integer j=139;
	System.out.println(i==j);
	
}
}
