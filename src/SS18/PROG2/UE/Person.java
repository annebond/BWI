package SS18.PROG2.UE;

public class Person implements Comparable<Person>{
	private String ID;
	private String name;
	
	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.name);
	}

}
