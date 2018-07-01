package SS18.PROG2.MusicLandscape.util.matcher;

import SS18.PROG2.MusicLandscape.entities.Track;
import SS18.PROG2.MusicLandscape.util.MyMatcher;

public class TitleMatcher extends MyMatcher<Track> {

	private String pattern;
	
	public TitleMatcher(String pat) {
		super(pat);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean matches(Track t) {
		if (t.getTitle().indexOf(pattern) == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void setPattern(String pat) {
		if (pat != null) {
			this.pattern = pat;
		}
		
	}

	@Override
	public String getPattern() {
		return pattern;
	}
	
	@Override
	public String toString() {
		return String.format("title starts with (%s)", pattern);
		
	}

}
