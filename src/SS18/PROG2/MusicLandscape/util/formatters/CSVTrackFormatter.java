package SS18.PROG2.MusicLandscape.util.formatters;

import SS18.PROG2.MusicLandscape.util.MyFormatter;
import SS18.PROG2.MusicLandscape.entities.Track;

public class CSVTrackFormatter implements MyFormatter<Track> {

	public CSVTrackFormatter() {
	}
	
	public String header() {
		return "Title,Writer,Performer,duration,year";
	}
	
	public String topSeparator() {
		return "";
	}
	
	public String format(Track t) {
		return String.format("%s,%s,%s,%d,%d", t.getTitle(), t.getPerformer(), t.getWriter(), t.getYear(), t.getDuration());
	}
}
