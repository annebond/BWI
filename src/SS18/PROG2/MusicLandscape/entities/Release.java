package SS18.PROG2.MusicLandscape.entities;

public abstract class Release {

	private Artist artist;
	private int year;
	private String title;
	
	public Release() {
		this.artist = new Artist();
		this.year = 1900;
		this.title = null;
	}
	
	/**
	 * Creates a copy of a release (deep copy constructor)
	 * @param orig - - the release to be copied
	 */
	public Release(Release orig) {
		this.artist = new Artist(orig.artist);
		this.year = orig.year;
		this.title = orig.title;
	}
	
	/**
	 * Create a release with a specific title of a specific artist in a specific year.
	 * @param title
	 * @param artist
	 * @param year
	 */
	public Release(java.lang.String title, Artist artist, int year) {
		this.title = title;
		this.artist = artist;
		this.year = year;
	}
	
	public java.lang.String getTitle() {
		return title;
	}
	
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	public Artist getArtist() {
		return artist;
	}
	
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		if (year >= 0) {
			this.year = year;
		}
	}
	
	public abstract int totalTime();
	
	@Override
	public java.lang.String toString() {
		String StringForm;
		StringForm = getTitle() == null ? "unknown-" : getTitle() + "-";
		StringForm += getArtist() == null ? "unknown-" : artist.toString() + "-";
		StringForm += getYear() <= 0 ? "unknown-" : Integer.toString(getYear()) + "-";
		StringForm += totalTime(); 
		
		return StringForm;
	}
	
}
