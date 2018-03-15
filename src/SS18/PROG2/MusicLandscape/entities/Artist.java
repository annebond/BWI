package SS18.PROG2.MusicLandscape.entities;

public class Artist {
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == "" || name == "  "){
			this.name = null;
		} else {
			this.name = name;
		}
	}

}
