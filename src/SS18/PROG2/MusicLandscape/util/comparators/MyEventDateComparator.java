package SS18.PROG2.MusicLandscape.util.comparators;

import SS18.PROG2.MusicLandscape.entities.Event;

public class MyEventDateComparator extends MyEventComparator {

	public MyEventDateComparator() {
		
	}
	
	@Override
	public int compare(Event e1, Event e2) {
		// TODO Auto-generated method stub
		if(e1==null && e2==null) {
			return 0;
		}
		if(e1==null && e2 !=null) {
			if(e2.getDate()==null) {
				return -1;
			}
			return -e2.getDate().lilianDayNumber();
		}
		if(e2==null && e1 !=null) {
			if(e1.getDate()==null) {
				return 1;
			}
			return e1.getDate().lilianDayNumber();
		}
		
		
		
		if(e1.getDate()==null && e2.getDate()==null) {
			return 0;
		}
		if(e1.getDate()==null && e2.getDate()!=null) {
			return -e2.getDate().lilianDayNumber();
		}
		if(e2.getDate()==null && e1.getDate()!=null) {
			return e1.getDate().lilianDayNumber();
		}
		
		return e1.getDate().compareTo(e2.getDate());
	}

}
