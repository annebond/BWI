package SS18.PROG2.FHVerwaltung;

public interface JsonFormat {
	String toJsonFormat();
	void fromJsonFormat(String json) throws FhException;
}
