package SS18.PROG2.FHVerwaltung;

public class Lektor extends Person {

	public Lektor(String id, String name) {
		super(id, name);
	}
	
	public Lektor(String json){
		fromJsonFormat(json);
	}
	
}
