package SS18.PROG2.MusicLandscape.util.formatters;
import SS18.PROG2.MusicLandscape.entities.*;

public class MyCSVTrackFormatter extends MyTrackFormatter {
	
	public MyCSVTrackFormatter() {
		
	}

	public java.lang.String format(Track t) {
		return String.format("%s,%s,%s,%d,%d;", t.getTitle(), t.getPerformer(), t.getWriter(), t.getYear(), t.getDuration());
	}
}
