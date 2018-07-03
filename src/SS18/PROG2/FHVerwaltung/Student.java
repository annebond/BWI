package SS18.PROG2.FHVerwaltung;

public class Student extends Person {

	public Student(String id, String name) {
		super(id, name);
	}
	
	public Student(String json){
		fromJsonFormat(json);
	}

}
