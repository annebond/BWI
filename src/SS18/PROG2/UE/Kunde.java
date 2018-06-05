package SS18.PROG2.UE;

public abstract class Kunde implements Comparable<Kunde> {

	private String Vorname;
	private String Nachname;
	private Adresse Adresse;
	private int Treue;
	
	public Kunde (String vn, String nn, int t) {
		this.Vorname = vn;
		this.Nachname = nn;
		if (t <= 100 && t >= 0) {
			this.Treue = t;	
		}
		
	}
	
	public Kunde (String vn, String nn, int t, Adresse a) {
		this (vn, nn, t);
		this.Adresse = a;
	}
	
	@Override
	public int compareTo(Kunde o) {
		return this.Nachname.compareTo(o.Nachname);
	}
	
	public boolean setTreue (int t) {
		if (t <= 100 && t >= 0) {
			this.Treue = t;
			return true;
		} 
			return false;
	}
	
	public int getTreue() {
		return Treue;
	}

	public String getVorname() {
		return Vorname;
	}

	public String getNachname() {
		return Nachname;
	}

	public Adresse getAdresse() {
		return Adresse;
	}
	public abstract void contract ();
}
