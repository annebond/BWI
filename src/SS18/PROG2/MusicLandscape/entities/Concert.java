package SS18.PROG2.MusicLandscape.entities;

public class Concert extends Event {
	private int nextIdx = 0;
	private Track [] setList = new Track[0];
	
	public Concert() {
	}
	
	/**
	 * adds a track to the set list Tracks are added to the end of the list with the first track played at the concert being stored at the beginning of the list.
	 * @param t - the track to add
	 * @return true if the track was added, false otherwise
	 */
	public boolean addTrack(Track t) {
		if (t == null) return false;
		
		if (nextIdx < setList.length) {
			ensureCapacity(nextIdx+1);
			setList[nextIdx] = t;
			nextIdx++;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ensures sufficient storage for a specific number of tracks in the setlist
	 * @param length - the maximum number of tracks this concert must be able to keep in the setlist
	 */
	private void ensureCapacity(int length) {
		if (length > setList.length) {
			Track[] newSetList = new Track[length];
			for (int i = 0; i < nextIdx; i++) {
				newSetList[i] = setList[i];
			}
			setList = newSetList;
		}
	}
	
	/**
	 * deepcopy of all non-null tracks of a Track array
	 * @param inputTracks - tracks to be copied
	 * @return deepcopy of inputTracks
	 */
	private Track[] deepCopy(Track[] inputTracks) {
		if(inputTracks == null) {
			return new Track[0];
		}
		
		int newLength = inputTracks.length;
		for (int i=0; i < inputTracks.length; i++) {
			if(inputTracks[i] == null) {
				newLength--;
			}
		}
		
		Track[] newSetList = new Track[newLength];
		int count = 0;
		for(int i=0; i<inputTracks.length; i++) {
			if(inputTracks[i] != null) {
				newSetList[count]= new Track(inputTracks[i]);
				count++;
			}
		}
		
		return newSetList;
	}
	/**
	 * sets the setList This method creates a defensive copy, meaning it sets the setlist of this concert to contain (deep copies of) all non-null tracks of the argument (and only those) thereby preserving the relative ordering of entries.
	 * @param tracks - the tracks for the setlist
	 */
	public void setSetList(Track[] tracks) {
		setList = deepCopy(tracks);
		nextIdx = setList.length;
	}
	
	/**
	 * gets the setlist This method returns a defensive copy, meaning it returns a copy of the setlist, which contains (deep) copies of the tracks in the setlist.
	 * @return the setlist of this concert
	 */
	public Track[] getSetList() {
		return deepCopy(setList);
	}
	
	public void resetSetList() {
		Track[] emptyList = new Track[0];
		setList = emptyList;
		nextIdx = 0;
	}
	
	/**
	 * get the length of the playlist the length of the playlist is the number of entries in the setlist.
	 * @return the number of tracks in the setlist
	 */
	public int nrTracks() {
		return nextIdx;
	}
	
	/**
	 * calculates the total duration (in seconds) of all tracks in the setlist More specifically the method returns an estimation (lower bound) since tracks with unknown duration are treated having duration 0.
	 * @return the total duration of the setlist in seconds
	 */
	public int duration() {
		int count = 0;
		for (int i = 0; i < nextIdx; i++) {
			count += setList[i].getDuration(); 
		}
		return count;
	}
	
	/**
	 * E.G: 400 people attending the concert. 75 minutes duration; duration factor=3 (two full half hours, plus one started half hour) impact therefore is 400*3
	 */
	@Override
	public int impact() {
		return this.getAttendees()*(1+this.duration()/1800);
	}
	
	@Override
	public String toString() {
		int h = this.duration()/3600;
		int m = this.duration()/60-h*60; //duration in s umgerechnet auf min, h in min abgezogen
		return super.toString() + String.format("\n%d tracks played, total duration %02d:%02d.", nextIdx, h, m);
		
	}
	
}
