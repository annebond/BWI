package SS18.PROG2.MusicLandscape.entities;

public class Track {

	private int duration;
	private Artist performer = new Artist();
	private String title;
	private Artist writer = new Artist();
	private int year;
	
	public int getDuration() {
		return duration;
	}

	public Artist getPerformer() {
		return performer;
	}
	
	public String getTitle() {
		if (title == null) {
			return "unknown title";
		} else {
			return title;
		}
	}
	
	public Artist getWriter() {
		return writer;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setDuration(int duration) {
		if (duration >= 0) {
			this.duration = duration;
		}
	}
	
	public void setPerformer(Artist performer) {
		if (performer != null) {
			this.performer = performer;
		}
	}
}
