package SS18.PROG2.MusicLandscape.entities;

public class Album extends Release {

	private TrackListItem trackListHead;
	
	@Override
	public int totalTime() {
		// TODO Auto-generated method stub
		TrackListItem myTrack = trackListHead;
		int sumDuration = 0;
		
		for (int i = 0; i < nrTracks(); i++) {
			sumDuration += myTrack.track.getDuration();
			myTrack = myTrack.next;
		}
		return sumDuration;
	}
	
	private class TrackListItem {
		private TrackListItem next;
		private Track track; 
		
		
		public TrackListItem(Track t) {
			this.track = t;
			this.next = null;
		}
	}
	
	public Album() {
		super();
		this.trackListHead = null;
	}
	
	public Album(Album orig) {
		super(orig);
		this.trackListHead = orig.trackListHead;
	}
	
	public Album(String title, Artist artist, int year) {
		super(title, artist, year);
	}
	
	public boolean addTrack(Track t) {
		if (t == null) {
			return false;
		}
		
		TrackListItem newTrack = new TrackListItem(t);
		if (trackListHead == null) {
			trackListHead = newTrack;
		} else {
			TrackListItem myTrack = trackListHead;
			while (myTrack.next != null) {
				myTrack = myTrack.next;
			}
			
			myTrack.next = newTrack;
		}
		
		return true;
	}
	
	public Track removeTrack(int n) {
		TrackListItem removeTrack;
		
		if (n == 0) {
			removeTrack = trackListHead;
			trackListHead = trackListHead.next;
		} else if (n >= nrTracks() || n <0){
			return null;
		} else {
		
			TrackListItem myTrack = trackListHead;
			for (int i = 0; i < n-1; i++) {
				myTrack = myTrack.next;
			}
			
			removeTrack = myTrack.next;
			myTrack.next = removeTrack.next;
		}
		
		return removeTrack.track;
	}

	/**
	 * Gets the number of tracks on this album.
	 * @return
	 */
	public int nrTracks() {
		TrackListItem myTrack = trackListHead;
		int count = 1;
		if (trackListHead == null) {
			return 0;
		}
		while (myTrack.next != null) {
			myTrack = myTrack.next;
			count++;
		}
		return count;
	}
	
	public Track[] getTracks() {
		Track[] myarray = new Track[nrTracks()];
		
		TrackListItem myTrack = trackListHead;
		for (int i = 0; i < nrTracks(); i++) {
			myarray[i] = myTrack.track;
			myTrack = myTrack.next;
		}
		
		return myarray;
	}
	
	@Override
	public java.lang.String toString() {
		String formString = super.toString() + "\n[";
		
		TrackListItem myTrack = trackListHead;
		for (int i = 0; i < nrTracks(); i++) {
			formString += "[" + myTrack.track.getTitle() + "]";
			myTrack = myTrack.next;
		}
		
		return formString + "]";
	}
	

}
