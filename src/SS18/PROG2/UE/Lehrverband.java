package SS18.PROG2.UE;

import java.util.*;

public class Lehrverband {
	private String Studiengang;
	private String Studiensemester;
	private int Semester = 1;
	// Set: HashSet oder TreeSet. Set verwende ich wenn ich einen Stundenten nur einmal im Set haben will. Sonst auch Liste
	private Set<Student> studenten = new HashSet<>();
	
	public Set<Student> getStudenten() {
		return studenten;
	}
	public void setStudenten(Set<Student> studenten) {
		this.studenten = studenten;
	}
	public int getSemester() {
		return Semester;
	}
	public void setSemester(int semester) {
		if (semester >= 1 && semester <= 6 ) {
			Semester = semester;	
		}
		
	}
	public String getStudiengang() {
		return Studiengang;
	}
	public void setStudiengang(String studiengang) {
		Studiengang = studiengang;
	}
	public String getStudiensemester() {
		return Studiensemester;
	}
	public void setStudiensemester(String studiensemester) {
			Studiensemester = studiensemester;	
		
	}
	
	public Lehrverband vorruecken(Lehrverband lv) {
		Lehrverband neu = new Lehrverband();
		neu.Semester = Semester + 1;
		studenten = new TreeSet<Student>(this.studenten);
		// TODO winter/sommer
		
		return neu;
	}
	
	public List<Student> anwesenheitsliste() {
		List<Student> list = new LinkedList<>();
		list.addAll(studenten);
		Collections.sort(list);
		return list;
		
	}
	

}
