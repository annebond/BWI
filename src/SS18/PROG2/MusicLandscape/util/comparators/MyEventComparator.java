package SS18.PROG2.MusicLandscape.util.comparators;
import SS18.PROG2.MusicLandscape.entities.*;

public abstract class MyEventComparator {

	public MyEventComparator() {
		
	}
	
	public abstract int compare(Event e1, Event e2);
}
