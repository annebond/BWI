package SS18.PROG2.MusicLandscape.entities;

public class TVShow extends Event {

	private String name;
	private int viewers;
	

	/**
	 * creates a default TVShow a default TVShow is a default event with an unknown name and an unknown number of viewers.
	 */
	public TVShow() {
		this.name = null;
		this.viewers = 0;
	}
	
	/**
	 * creates a TV show from an event this constructor performs some kind of promotion such that it takes a generic event and creates a TV show which is a (deep) copy of the original event.
	 * @param e - the event to copy/promote to TV show
	 */
	public TVShow(Event e) {
		super(e);
		this.name = null;
		this.viewers = 0;
	}
	
	/**
	 * creates a deep copy of a TVShow
	 * @param tvs - the TV show to copy
	 */
	public TVShow(TVShow tvs) {
		super(tvs);
		this.name = tvs.name;
		this.viewers = tvs.viewers;
		
		TVShow copyTVShow = new TVShow();
		copyTVShow.getArtist();
		copyTVShow.getAttendees();
		copyTVShow.getDate();
		copyTVShow.getDescription();
		copyTVShow.getName();
		copyTVShow.getVenue();
		copyTVShow.getViewers();		
	}
	
	public int getViewers() {
		return this.viewers;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setViewers(int v) {
		if (v>=0) {
			this.viewers = v;
		}
	}
	
	public void setName(String name) {
		if (name=="" || name==" ") {
			return;
		} else {
			this.name = name;
		}
	}
	
	/**
	 * returns the impact of this event the impact is an estimation of the number of people who took notice of this event for a TV show event, the impact is the audience times 2. audience are all viewers and attendees of a show
	 */
	@Override
	public int impact() {
		return (this.viewers + this.getAttendees())*2; 
	}
	

	@Override
	public String toString() {
		return String.format("%s @ %s on %s\n%s\n(%s attending (%s))", this.getArtist(), this.getName(), this.getDate(), this.getDescription(), getViewers()+getAttendees(), impact());
	}
	
}
