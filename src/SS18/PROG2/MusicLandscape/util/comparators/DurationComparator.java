package SS18.PROG2.MusicLandscape.util.comparators;

import SS18.PROG2.MusicLandscape.entities.Track;

public class DurationComparator extends Object
implements java.util.Comparator<Track> {

	public DurationComparator() {	
	}
	
	@Override
	public int compare(Track o1, Track o2) {
		// TODO Auto-generated method stub
		if (o1 == null && o2 == null) {
			return 0;
		}
		
		if (o1 == null && o2 != null) {
			return -o2.getDuration();
		}
		
		if (o2 == null && o1 != null) {
			return o1.getDuration();
		}
		
		return o1.getDuration() - o2.getDuration();
		
	}
	
	@Override
	public String toString() {
		return "by duration";
	}
	
	
}
