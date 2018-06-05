package SS18.PROG2.UE;

public class Corporate extends Kunde {

	public Corporate (String vn, String nn, int t, Adresse a) {
		super (vn, nn, t);
	}
	
	@Override
	public void contract() {
		// TODO Auto-generated method stub
		
		String.format("Brief geht an: %s, %s, %s", getVorname(), getNachname(), getAdresse().Ort);

	}
	


}
