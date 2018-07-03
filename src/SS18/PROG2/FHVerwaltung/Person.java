package SS18.PROG2.FHVerwaltung;

public abstract class Person implements JsonFormat, Filterable{
	private String id;
	private String name;
	
	protected Person(){}
	
	public Person(String id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toJsonFormat() {		
		return String.format("{\"ID\": \"%s\", \"Name\": \"%s\"}", id, name);
	}

	@Override
	public void fromJsonFormat(String json) {
		String[] lines = json.split("\"");
		id = lines[3].trim();
		name = lines[7].trim();
	}
	
	@Override
	public boolean contains(String regex){
		return id.toLowerCase().contains(regex.toLowerCase()) || name.toLowerCase().contains(regex.toLowerCase());
	}
	
}
