package SS18.PROG2.MusicLandscape.entities;

import SS18.PROG2.MusicLandscape.Date;
import SS18.PROG2.MusicLandscape.Venue;

public class Event {
	
	private Artist artist = new Artist();
	private Venue venue;
	private Date date;
	private String description = "";
	private int attendees;
	

	/**
	 * gets the artist of this event
	 * @return the artist
	 */
	public Artist getArtist() {
		if (artist == null) {
			return new Artist();
		}
		return artist;
	}
	
	public int getAttendees() {
		return attendees;
	}
	
	public Date getDate() {
		if (date != null) {
			return new Date(date);
		}
		return null;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Venue getVenue() {
		return venue;
	}
	
	public void setArtist(Artist artist) {
		if (artist != null) {
			this.artist = artist;
		}
	}
	
	public void setAttendees(int attendees) {
		if (attendees >=0) {
			this.attendees = attendees;
		}
	}
	
	public void	setDate(Date date) {
		if (date != null) {
			this.date = new Date(date);
		}
	}
	
	public void	setDescription(String description) {
		if (description != null) {
			this.description = description;
		}
	}
	
	public void	setVenue(Venue venue) {
		this.venue = venue;
	}
	
	/**
	 * creates a default event a default event has a default artist and an empty description. all other information is unknown (See docu for fields)
	 */
	public Event() {
		this.setArtist(artist);
		this.setDescription(description);
	}
	/**
	 * creates a deep copy of an event
	 */
	public Event (Event e) {
		this.artist = new Artist(e.artist);
		this.venue = new Venue(e.venue);
		this.date = new Date(e.date);
		this.description = e.description;
		this.attendees = e.attendees;
	}
	
	public String toString() {
		String strArtist = this.artist != null ? this.artist.toString() : "unknown";
		String strVenue = this.venue != null ? this.venue.getName() : "unknown";
		String strDate = this.date != null ? this.date.toString() : "null";
		String strDescription = this.description != null ? this.description : "unknown";
		String output = String.format("%s @ %s on %s\n%s\n(%s attending (%s))", strArtist, strVenue, strDate, strDescription, this.attendees, this.impact());
		return output;
	}
	
	/**
	 * returns the impact of this event the impact is an estimation of the number of people who took notice of this event for a generic event, the impact is the number of attendees times 2.
	 * @return the impact
	 */
	public int impact() {
		return this.attendees*2;
	}
}
