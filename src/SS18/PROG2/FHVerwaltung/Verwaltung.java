package SS18.PROG2.FHVerwaltung;
import java.io.*;
import java.util.*;

public class Verwaltung implements JsonFormat{

	private List<Lektor> lektoren;
	private List<Lehrverband> lehrverbände;
	private List<Vorlesung> vorlesungen;
	private List<Test> tests;

	public Verwaltung() {
		lektoren = new ArrayList<Lektor>();
		lehrverbände = new ArrayList<Lehrverband>();
		vorlesungen = new ArrayList<Vorlesung>();
		tests = new ArrayList<Test>();
	}

	public static void main(String[] args) {
		Verwaltung v = new Verwaltung();		
		v.konsolenausgabe();
	}

	public void konsolenausgabe() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Willkommen in der FH Verwaltung!");

		while (true) {
			System.out.println("wähle eine Funktion aus:\n" + "1 - Liste aller Lektoren\n"
					+ "2 - Liste aller Lehrverbände\n" + "3 - Liste aller Vorlesungen\n" + "4 - Liste aller Tests\n" 
					+ "5 - Suche\n" + "6 - Speicher Daten\n" + "7 - Lade Daten\n" + "8 - Erstelle Testdaten");
			switch (sc.next()) {
			case "1":
				System.out.println("Liste aller Lektoren:\n");
				for (Lektor l : lektoren) {
					System.out.println(l.toJsonFormat() + "\n");
				}
				break;
			case "2":
				System.out.println("Liste aller Lehrverbände:\n");
				for (Lehrverband l : lehrverbände) {
					System.out.println(l.toJsonFormat() + "\n");
				}
				break;
			case "3":
				System.out.println("Liste aller Vorlesungen:\n");
				for (Vorlesung v : vorlesungen) {
					System.out.println(v.toJsonFormat() + "\n");
				}
				break;
			case "4":
				System.out.println("Liste aller Tests:\n");
				for (Test t : tests) {
					System.out.println(t.toJsonFormat() + "\n");
				}
				break;
			case "5":
				System.out.println("Gib einen Suchbegriff ein:");
				volltextsuche(sc.next());
				break;
			case "6":
				System.out.print("Dateiname: ");
				saveJson(sc.next());
				break;
			case "7":
				System.out.print("Dateiname: ");
				String s = sc.next();
				loadJson(s);
				break;
			case "8":
				init();
				break;
			default:
				return;
			}
		}
	}

	private void volltextsuche(String s) {
		int cnt = 0;
		System.out.println("Liste aller Lektoren mit '" + s + "':\n");
		for (Lektor l : filter(lektoren, s)) {
			System.out.println(l.toJsonFormat() + "\n");
			cnt++;
		}
		System.out.println("Es " + (cnt == 1? "wurde " : "wurden ") + cnt + (cnt == 1? " Lektor" : " Lektoren") + " gefunden.\n");
		
		cnt = 0;
		System.out.println("Liste aller Studenten mit '" + s + "':\n");
		List<Student> studenten = new ArrayList<Student>();
		for(Lehrverband l : lehrverbände){
			studenten.addAll(filter(l.getStudenten(), s));
		}
		for (Student st : studenten) {
			System.out.println(st.toJsonFormat() + "\n");
			cnt++;
		}
		System.out.println("Es " + (cnt == 1? "wurde " : "wurden ") + cnt + (cnt == 1? " Student" : " Studenten") + " gefunden.\n");
		
		cnt = 0;
		System.out.println("Liste aller Lehrverbände mit '" + s + "':\n");
		for (Lehrverband l : filter(lehrverbände, s)) {
			System.out.println(l.toJsonFormat() + "\n");
			cnt++;
		}
		System.out.println("Es " + (cnt == 1? "wurde " : "wurden ") + cnt + (cnt == 1? " Lehrverband" : " Lehrverbände") + " gefunden.\n");
		
		cnt = 0;
		System.out.println("Liste aller Vorlesungen mit '" + s + "':\n");
		for (Vorlesung v : filter(vorlesungen, s)) {
			System.out.println(v.toJsonFormat() + "\n");
			cnt++;
		}
		System.out.println("Es " + (cnt == 1? "wurde " : "wurden ") + cnt + (cnt == 1? " Vorlesung" : " Vorlesungen") + " gefunden.\n");
	}
	
	public <T extends Filterable> List<T> filter(List<T> list, String string){
		List<T> retList = new ArrayList<T>();
		for(T item : list){
			if(item.contains(string)) retList.add(item);
		}
		return retList;
	}
	
	// diese Methode wurde hinzugefügt um die Frage zu beantworten
	// wie aus einer Liste in einer Schleife entfernt werden kann
	// und zu zeigen wie Iteratoren funktionieren.
	public static <T extends Filterable> void removeFromList (List<T> list, String s){
		//die foreach Schleife erzeugt zwar einen Iterator
		//es kann allerdings nicht die remove Methode des Iterators
		//verwendet werden. Sie sollte daher nicht verwendet werden
		//um Elemente während der Schleife aus der Liste zu löschen.
		/*for(T element : list){
			if(element.contains(s)){
				// hier wird nicht die remove Methode des Iterators
				// sondern die der Liste verwendet, es kann zu unerwarteten
				// Verhalten und Exceptions kommen.
				list.remove(element);
			}
		}*/
			
		// die korrekte Methode um aus einer Liste zu entfernen ist die
		// remove Methode des Iterators zu verwenden.
		for (Iterator<T> iterator = list.iterator(); iterator.hasNext();) {
			 T element = iterator.next();
			 if(element.contains(s)){
				 iterator.remove();
			 }
		}
	}

	public void init() {	
		try {
			lektoren.add(new Lektor("001", "Thomas Mandl"));
			lektoren.add(new Lektor("002", "Andreas Cerny"));
			lehrverbände.add(new Lehrverband("BWI", "SS2018", 2));
			lehrverbände.get(0).addStudent(new Student("101", "Max Mustermann"));
			lehrverbände.get(0).addStudent(new Student("102", "John Doe"));
			lehrverbände.get(0).addStudent(new Student("103", "Jane Doe"));
			vorlesungen.add(new Vorlesung("Programmierung 2", lektoren.get(0), lehrverbände.get(0)));
			vorlesungen.add(new Vorlesung("Objektorientierte Modellierung", lektoren.get(1), lehrverbände.get(0)));
			vorlesungen.get(0).addVorlesungseinheit(new Einheit(new Datum(21, 06, 2018), 8, "F1.01"));
			vorlesungen.get(0).addVorlesungseinheit(new Einheit(new Datum(21, 06, 2018), 9, "F1.01"));
			vorlesungen.get(1).addVorlesungseinheit(new Einheit(new Datum(21, 06, 2018), 4, "F1.01"));
			vorlesungen.get(1).addVorlesungseinheit(new Einheit(new Datum(21, 06, 2018), 5, "F1.01"));
			tests.add(new Test(new Datum(26, 6, 2018), lehrverbände.get(0).getStudenten().get(0), vorlesungen.get(0), 5));
			tests.add(new Test(new Datum(4, 7, 2018), lehrverbände.get(0).getStudenten().get(0), vorlesungen.get(0), 2, 2));
		} catch (FhException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void saveJson(String filename){
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
			bw.write(toJsonFormat());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadJson(String filename){
		try(BufferedReader br = new BufferedReader(new FileReader(filename))){
			String json = "";
			for(int c = br.read(); c != -1; c = br.read()){
				json += (char) c;
			}
			fromJsonFormat(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toJsonFormat() {
		String json = String.format("{\"Lektoren\": [%s],\n\"Lehrverbände\": [%s],\n\"Vorlesungen\": [%s],\n\"Tests\": [%s]}", 
				JsonHelper.getJsonArray(lektoren), JsonHelper.getJsonArray(lehrverbände), JsonHelper.getJsonArray(vorlesungen), JsonHelper.getJsonArray(tests));
		return json;
	}

	@Override
	public void fromJsonFormat(String json) {
		try{
			String[] lektoren = json.split("\"Lektoren\":.*\\[")[1].split("]")[0].split(",\n");
			for(String lektor : lektoren){
				this.lektoren.add(new Lektor(lektor));
			}
			
			String[] lehrverbände = json.split("\"Lehrverbände\":.*\\[")[1].split("]}]")[0].split("]},\n");
			for(String lehrverband : lehrverbände){
				lehrverband += "]}";
				this.lehrverbände.add(new Lehrverband(lehrverband));
			}
			
			String[] vorlesungen = json.split("\"Vorlesungen\":.*\\[")[1].split("],[ \n]*\"Tests")[0].split("]},\n\\{");
			for(String vorlesung : vorlesungen){
				if(vorlesung.length() < 5) break;
				if(vorlesung.charAt(0) != '{') vorlesung = "{" + vorlesung;
				if(vorlesung.charAt(vorlesung.length()-1) != '}') vorlesung += "]}";
				this.vorlesungen.add(new Vorlesung(vorlesung));
			}
			
			String tmp = json.split("\"Tests\":.*\\[")[1];
			String[] tests = tmp.substring(0, tmp.length()-2).split("}]}}");
			for(String test : tests){
				if(test.length() < 10) break;
				test += "}]}}";
				this.tests.add(new Test(test));
			}
		}
		catch(FhException e){
			e.printStackTrace();
		}
	}

}
