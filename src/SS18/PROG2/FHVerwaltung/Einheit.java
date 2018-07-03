package SS18.PROG2.FHVerwaltung;


public class Einheit implements JsonFormat{
	private Datum datum;
	private int einheit = 1;
	private String raum;
	
	public Einheit(String json) throws FhException{
		fromJsonFormat(json);
	}
	
	public Einheit(Datum datum, int einheit, String raum) throws FhException {
		this.datum = datum;
		setEinheit(einheit);
		this.raum = raum;
	}
	
	public Datum getDatum() {
		return datum;
	}
	public void setDatum(Datum datum) {
		this.datum = datum;
	}
	public int getEinheit() {
		return einheit;
	}
	public void setEinheit(int einheit) throws FhException {
		if(einheit < 1 && einheit > 16) throw new FhException(einheit + " kann nicht als Unterrichtseinheit gesetzt werden. Unterrichtseinheit muss zwischen 1 und 16 sein,");
		this.einheit = einheit;
	}
	public String getRaum() {
		return raum;
	}
	public void setRaum(String raum) {
		this.raum = raum;
	}

	@Override
	public String toJsonFormat() {
		return String.format("{\"Datum\": %s, \"Einheit\": %d, \"Raum\": \"%s\"}", datum.toJsonFormat(), einheit, raum);
	}

	@Override
	public void fromJsonFormat(String json) throws FhException {
		datum = new Datum(json.split("Datum\":[ \n]*\\{")[1].split("}")[0]);
		
		setEinheit(JsonHelper.getIntAttribute(json, "Einheit"));
		raum = JsonHelper.getStringAttribute(json, "Raum");
	}	
	
}
