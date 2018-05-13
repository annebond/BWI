// **************************************************
//		
//       git.rev = 232
//  git.revision = 5758f042c648661b29a7471f428d9556f8ed5e72
//         stage = ES04
//
// ***************************************************
package SS18.PROG2.MusicLandscape.util.formatters;

import SS18.PROG2.MusicLandscape.entities.Track;

/**
 * This class represents the concept of short formatting of a track containing
 * only some information.
 * 
 * 
 * @author TeM, JS
 * @ProgrammingProblem.Category abstract class
 * @ProgrammingProblem.Introduced ExerciseSheet04
 */
public class MyShortTrackFormatter extends MyTrackFormatter {

	/**
	 * Creates a short format of a track.<br>
	 * 
	 * The short representation of a track is
	 * "title" "performer"
	 * (without quotes)
	 * Both title, and performer are exactly ten characters wide with leading blanks (if any).
	 * 
	 */
	@Override
	public String format(Track t) {
		return String.format("%10.10s %10.10s", t.getTitle(), t.getPerformer());

	}

}
