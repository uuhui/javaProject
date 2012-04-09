package Enum;

public enum Week {
 SUN {
	@Override
	public Week nextDay() {
		// TODO Auto-generated method stub
		return MON;
	}
},MON {
	@Override
	public Week nextDay() {
		// TODO Auto-generated method stub
		return TUE;
	}
},TUE {
	@Override
	public Week nextDay() {
		// TODO Auto-generated method stub
		return WED;
	}
},WED {
	@Override
	public Week nextDay() {
		// TODO Auto-generated method stub
		return THI;
	}
},THI {
	@Override
	public Week nextDay() {
		// TODO Auto-generated method stub
		return FRI;
	}
},FRI {
	@Override
	public Week nextDay() {
		// TODO Auto-generated method stub
		return SAT;
	}
},SAT {
	@Override
	public Week nextDay() {
		// TODO Auto-generated method stub
		return SUN;
	}
};
 
 public abstract Week nextDay();
}
