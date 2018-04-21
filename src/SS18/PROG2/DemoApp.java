package SS18.PROG2;

import SS18.PROG2.MusicLandscape.entities.*;

public class DemoApp {
	
	public static void main(String[] args) {
		Concert testConcert = new Concert();
		testConcert.addTrack(new Track("track1"));
		testConcert.addTrack(new Track("track2"));
		testConcert.addTrack(new Track("track3"));
		Artist testArtist = new Artist("MaxArtist");
		TVShow testTVShow = new TVShow();
		testTVShow.setName("FriendsShow");
		
		Event[] events = {new Event(), testConcert, testTVShow};
		
		for (Event e : events) {
			e.setArtist(testArtist);
			e.setDescription("same description for all events");
			System.out.println(e);
			System.out.println();
		}
	}
	
	
}
