package SS18.PROG2.UE;

import java.util.LinkedList;
import java.util.List;

public class Demo {
	
	
	public static void main(String[] args) {
		Private person1 = new Private ("Max", "Mustermann", 10, null);
		List<Kunde> KundenListe = new LinkedList<Kunde>();
		KundenListe.add(person1);
	}

}
