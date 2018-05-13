package SS18.PROG2.MusicLandscape.util.comparators;

import SS18.PROG2.MusicLandscape.entities.Track;

public class MyTitleComparator extends MyTrackComparator {

	public MyTitleComparator() {
		
	}
	
	@Override
	public int compare(Track t1, Track t2) {
		// TODO Auto-generated method stub
		return t2.getTitle().length()-t1.getTitle().length();
	}
	
}
