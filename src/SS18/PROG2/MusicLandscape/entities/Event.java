package SS18.PROG2.MusicLandscape.entities;

import SS18.PROG2.MusicLandscape.Date;
import SS18.PROG2.MusicLandscape.Venue;

public class Event {
	
	private Artist artist = new Artist();
	private Venue venue;
	private Date date;
	private String description = "";
	private int attendees;
	

	public Artist getArtist() {
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
	
	public Event() {
		this.setArtist(artist);
		this.setDescription(description);
	}
}
