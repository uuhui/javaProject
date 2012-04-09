package reflect;

public class Person {
public String name;
private String sex;

public Person(String name,String sex) {
	this.name = name;
	this.sex=sex;
}

@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Sex:"+sex;
	}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
}

}
