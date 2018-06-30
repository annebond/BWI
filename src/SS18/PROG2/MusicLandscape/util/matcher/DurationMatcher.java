package SS18.PROG2.MusicLandscape.util.matcher;

import SS18.PROG2.MusicLandscape.util.MyMatcher;
import SS18.PROG2.MusicLandscape.entities.Track;
import java.util.regex.Matcher;

public class DurationMatcher extends MyMatcher<Track> {

	private int lower;
	private int upper;
	
	public DurationMatcher(String pat) {
		super(pat);
		// TODO Auto-generated constructor stub
	}
	public DurationMatcher() {
		super(null);
		lower = 0;
		upper = Integer.MAX_VALUE;
	}
	
	@Override
	public boolean matches(Track t) {
		if(t.getDuration() >= lower && t.getDuration() <= upper) {
			return true; 
		}
		return false;
	}
	@Override
	public void setPattern(String pat) {
		if (pat == null)
			return;
		
		if (pat.matches("\\d* +\\d*") == true) {
			String[] split = pat.split(" ");
			int tempLower = Integer.parseInt(split[0]);
			int tempUpper = Integer.parseInt(split[1]);
			
			if(tempLower<=tempUpper) {
				lower=tempLower;
				upper=tempUpper;	
			}
			if(tempLower>tempUpper){
				lower=tempLower;
			}
		}
	}
	@Override
	public String getPattern() {
		return String.format("%d %d", lower, upper);
	}
	
	@Override
	public String toString() {
		return String.format("duration in range (%s)", getPattern());
	}

}
