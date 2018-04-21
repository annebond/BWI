package SS18.PROG2.MusicLandscape.entities;

public class Artist {
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == null || name.trim().length() == 0){
			//this.name = null; --> if an invalid argument is passed to the method the state of the object remains unchanged
		} else {
			this.name = name;
		}
	}
	
	public Artist() {
		this.setName("unknown");
		
	}
	
	public Artist(String name) {
		//this.setName(name);
		this.name = name;
	}
	
	public Artist(Artist a) {
		this.setName(a.getName());
	}
	
	@Override
	public String toString() {
		String strArtist = this.name != null ? this.name : "unknown";
		return strArtist;
	}
}
