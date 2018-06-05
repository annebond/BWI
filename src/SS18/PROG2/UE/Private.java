package SS18.PROG2.UE;

public class Private extends Kunde {
	
	public String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Private(String vn, String nn, int t, SS18.PROG2.UE.Adresse a) {
		super(vn, nn, t, a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void contract() {
		// TODO Auto-generated method stub
		String.format("Wir schreiben ein E-Mail: %s", this.email);
	}

}
