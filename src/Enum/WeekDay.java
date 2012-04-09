package Enum;

public  abstract class WeekDay {
	
public abstract WeekDay nextDay();
public final static WeekDay Sun=new WeekDay() {

	@Override
	public WeekDay nextDay() {
		// TODO Auto-generated method stub
		return Mon;
	}
};

public final static WeekDay Mon=new WeekDay() {
	
	@Override
	public WeekDay nextDay() {
		// TODO Auto-generated method stub
		return Tue;
	}
}; 

public final static WeekDay Tue=new WeekDay() {

	@Override
	public WeekDay nextDay() {
		// TODO Auto-generated method stub
		return Sun;
	}
};

public String toString() {
	return this.nextDay()==Sun?"sun":"Mon";
}
}
