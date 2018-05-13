package SS18.PROG2.MusicLandscape.util.comparators;

import SS18.PROG2.MusicLandscape.entities.Event;

public class MyEventAttendeesComparator extends MyEventComparator {

	@Override
	public int compare(Event e1, Event e2) {
		// TODO Auto-generated method stub
		
		if(e1==null && e2==null) {
			return 0;
		}
		if(e1==null && e2 != null) {
			return -e2.getAttendees();
		}
		if(e2==null && e1 != null) {
			return e1.getAttendees();
		}
		return e1.getAttendees()-e2.getAttendees();
	}
	
	public MyEventAttendeesComparator() {
		
	}

}
