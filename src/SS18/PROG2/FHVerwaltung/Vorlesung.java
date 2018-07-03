package SS18.PROG2.FHVerwaltung;
import java.util.*;

public class Vorlesung implements Filterable, JsonFormat{
	private String bezeichnung;
	private List<Einheit> vorlesungseinheiten;
	private Lektor vortragender;
	private Lehrverband lehrverband;
	
	public Vorlesung(String json) throws FhException{
		vorlesungseinheiten = new LinkedList<Einheit>();
		fromJsonFormat(json);
	}
	
	public Vorlesung(String bezeichnung, Lektor vortragender, Lehrverband lehrverband){
		vorlesungseinheiten = new LinkedList<Einheit>();
		setBezeichnung(bezeichnung);
		setVortragender(vortragender);
		setLehrverband(lehrverband);
	}
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public List<Einheit> getVorlesungseinheiten() {
		return vorlesungseinheiten;
	}
	public void addVorlesungseinheit(Einheit einheit) {
		vorlesungseinheiten.add(einheit);
	}
	public Lektor getVortragender() {
		return vortragender;
	}
	public void setVortragender(Lektor vortragender) {
		this.vortragender = vortragender;
	}
	public Lehrverband getLehrverband() {
		return lehrverband;
	}
	public void setLehrverband(Lehrverband lehrverband) {
		this.lehrverband = lehrverband;
	}
	
	@Override
	public boolean contains(String regex){
		return bezeichnung.contains(regex);
	}

	@Override
	public String toJsonFormat() {
		return String.format("{\"Bezeichnung\": \"%s\",\n\"Vortragender\": %s,\n\"Lehrverband\":\n%s,\n\"Vorlesungseinheiten\": [%s]}",
				bezeichnung, vortragender.toJsonFormat(), lehrverband.toJsonFormat(), JsonHelper.getJsonArray(vorlesungseinheiten));
	}

	@Override
	public void fromJsonFormat(String json) throws FhException {
		bezeichnung = JsonHelper.getStringAttribute(json, "Bezeichnung");
		
		vortragender = new Lektor(json.split("Vortragender\":[ \n]*\\{")[1].split("}")[0]);		
		lehrverband = new Lehrverband(json.split("Lehrverband\":[ \n]*\\{")[1].split("]}")[0] + "]}");
		
		String[] einheiten = json.split("Vorlesungseinheiten\":[ \n]*\\[")[1].split("\"}]}")[0].split("\"},");
		for(String einheit : einheiten){
			vorlesungseinheiten.add(new Einheit(einheit + "\"}"));
		}		
	}
}
