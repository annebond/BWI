package SS18.PROG2.MusicLandscape.util.comparators;

import SS18.PROG2.MusicLandscape.entities.Track;

public class WriterComparator extends Object
implements java.util.Comparator<Track>{

	public WriterComparator()  {
	}
	
	@Override
	public int compare(Track o1, Track o2) {
		// TODO Auto-generated method stub
		if(o1 ==null && o2==null) {
			return 0;
		}
		if(o1 ==null && o2!=null) {
			return 1;
		}
		if(o2 ==null && o1!=null) {
			return -1;
		}
		return o1.getWriter().compareTo(o2.getWriter());
	}

	public String toString() {
		return "by writer";
	}
}
