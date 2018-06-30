package SS18.PROG2.MusicLandscape.util.formatters;

import SS18.PROG2.MusicLandscape.entities.Track;
import SS18.PROG2.MusicLandscape.util.MyFormatter;

public class ShortTrackFormatter extends Object
implements MyFormatter<Track>{

	public ShortTrackFormatter() {
	}
	
	@Override
	public String header() {
		// TODO Auto-generated method stub
		return "Title      (min:sec)";
	}

	@Override
	public String format(Track t) {
		return String.format("%s (%02d:%02d)", paddingRight(t.getTitle(), 10), t.getDuration()/60, t.getDuration() % 60);
	}
	
	public static String paddingRight (String source, int toLength) {
		if(toLength < 0)
		{
			return null;
		}
		
		if(source.length()>toLength)
		{
			return source.substring(0, toLength);
		}
		for(int i=source.length(); i<toLength; i++) {
			source= source + " ";
		}
		return source;
	}

	@Override
	public String topSeparator() {
		return "--------------------";
	}
	
	@Override
	public String toString() {
		return"short format [Title (min:sec)]";
	}
	
	

}
