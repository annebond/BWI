package WS1718.PROG1.TUT;

public class Schuhmodell {

	public String Modell;
	public String Herstellermarke;
	public float Preis;

	public String toString () {
		return String.format("Modell: %s\nHersteller: %s\nPreis: %.2f", Modell, Herstellermarke, Preis);
	}
}
