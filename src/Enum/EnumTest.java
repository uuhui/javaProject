package Enum;

public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WeekDay sun = WeekDay.Sun;
		System.out.println(sun);
		System.out.println("===================");
		// ʹ��jdk��ö��
		Week mon = Week.MON;
		System.out.println(mon.nextDay());
		
		
		System.out.println("----------------");
		Week [] days=Week.values();
		for(Week day:days){
			System.out.println(day);
		}
		
	}

}
