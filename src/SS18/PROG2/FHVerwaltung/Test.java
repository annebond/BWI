package SS18.PROG2.FHVerwaltung;

public class Test implements JsonFormat{
	private Datum datum;
	private int note;
	private int antritt;
	private Student student;
	private Vorlesung vorlesung;
	
	public Test(String json) throws FhException{
		fromJsonFormat(json);
	}
	public Test(Datum datum, Student student, Vorlesung vorlesung, int note) throws FhException {
		this.datum = datum;
		setNote(note);
		this.note = note;
		this.antritt = 1;
		this.student = student;
		this.vorlesung = vorlesung;
	}
	public Test(Datum datum, Student student, Vorlesung vorlesung, int note, int antritt) throws FhException {
		this(datum, student, vorlesung, note);
		this.antritt = antritt;
	}
	
	public Datum getDatum() {
		return datum;
	}
	public void setDatum(Datum datum) {
		this.datum = datum;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) throws FhException {
		if(note < 1 || note > 5) throw new FhException(note + " kann nicht als Note gesetzt werden. Note kann nur zwischen 1 und 5 sein");
		this.note = note;
	}
	public int getAntritt() {
		return antritt;
	}
	public void setAntritt(int antritt) {
		this.antritt = antritt;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Vorlesung getVorlesung() {
		return vorlesung;
	}
	public void setVorlesung(Vorlesung vorlesung) {
		this.vorlesung = vorlesung;
	}
	@Override
	public String toJsonFormat() {
		return String.format("{\"Datum\": %s,\n\"Note\": %d,\n\"Antritt\": %d,\n\"Student\": %s,\n\"Vorlesung\":\n%s}", datum.toJsonFormat(), note, antritt, student.toJsonFormat(), vorlesung.toJsonFormat());
	}
	@Override
	public void fromJsonFormat(String json) throws FhException {
		datum = new Datum(json.split("Datum\":[ \n]*\\{")[1].split("}")[0]);		
		setNote(JsonHelper.getIntAttribute(json, "Note"));
		antritt = JsonHelper.getIntAttribute(json, "Antritt");		
		student = new Student(json.split("Student\":[ \n]*\\{")[1].split("}")[0]);
		String tmp = json.split("Vorlesung\":[ \n]*\\{")[1];
		vorlesung = new Vorlesung(tmp.substring(0, tmp.length()-1));		
	}	
}
