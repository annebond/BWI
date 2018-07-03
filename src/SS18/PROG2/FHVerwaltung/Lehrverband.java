package SS18.PROG2.FHVerwaltung;
import java.util.*;

public class Lehrverband implements Filterable, JsonFormat{
	private String studiengang;
	private String studiensemester;
	private int semester;
	private List<Student> studenten;
		
	public Lehrverband(String json) throws FhException {
		studenten = new ArrayList<Student>();
		fromJsonFormat(json);
	}
	
	public Lehrverband(String studiengang, String studiensemester) throws FhException {
		this(studiengang, studiensemester, 1);
	}
	
	public Lehrverband(String studiengang, String studiensemester, int semester) throws FhException {
		this.studiengang = studiengang;
		this.studiensemester = studiensemester;
		setSemester(semester);
		studenten = new ArrayList<Student>();
	}

	public String getStudiengang() {
		return studiengang;
	}
	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}
	public String getStudiensemester() {
		return studiensemester;
	}
	public void setStudiensemester(String studiensemester) {
		this.studiensemester = studiensemester;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) throws FhException {
		if(semester < 1 || semester > 6) throw new FhException(semester + " kann nicht als Semester gesetzt werden. Semester muss zwischen 1 und 6 sein.");
		this.semester = semester;
	}
	public List<Student> getStudenten(){
		return studenten;
	}
	public void addStudent(Student student){
		studenten.add(student);
	}
	
	@Override
	public boolean contains(String regex){
		return studiengang.contains(regex) || studiensemester.contains(regex) || Integer.toString(semester).contains(regex);
	}

	@Override
	public String toJsonFormat() {
		return String.format("{\"Studiengang\": \"%s\",\n\"Studiensemester\": \"%s\",\n\"Semester\": %d,\n\"Studenten\": [%s]}",
				studiengang, studiensemester, semester, JsonHelper.getJsonArray(studenten));
	}

	@Override
	public void fromJsonFormat(String json) throws FhException {
		studiengang = JsonHelper.getStringAttribute(json, "Studiengang");
		studiensemester = JsonHelper.getStringAttribute(json, "Studiensemester");
		setSemester(JsonHelper.getIntAttribute(json, "Semester"));
		
		String[] studenten = json.split("Studenten\":[ \n]*\\[")[1].split("}]")[0].split("},");
		for(String student : studenten){
			this.studenten.add(new Student(student + "}"));
		}		
	}
	
}
