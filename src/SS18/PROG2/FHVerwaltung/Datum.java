package SS18.PROG2.FHVerwaltung;


public class Datum implements JsonFormat{
	private int tag;
	private int monat;
	private int jahr;
	
	public Datum(String json){
		fromJsonFormat(json);
	}
	
	public Datum(int tag, int monat, int jahr){
		this.tag = tag;
		this.monat = monat;
		this.jahr = jahr;
	}
	
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public int getMonat() {
		return monat;
	}
	public void setMonat(int monat) {
		this.monat = monat;
	}
	public int getJahr() {
		return jahr;
	}
	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	@Override
	public String toJsonFormat() {		
		return String.format("{\"Tag\": %d,\"Monat\": %d,\"Jahr\": %d}", tag, monat, jahr);
	}

	@Override
	public void fromJsonFormat(String json) {
		String[] lines = json.split(":");
		tag = Integer.parseInt(lines[1].split(",")[0].trim());
		monat = Integer.parseInt(lines[2].split(",")[0].trim());
		jahr = Integer.parseInt(lines[3].split("}")[0].trim());
	}	
	
}
