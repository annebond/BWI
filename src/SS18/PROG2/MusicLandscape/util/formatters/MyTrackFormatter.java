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
 * This class represents the concept of formatting a track. It has a
 * single abstract method that is to be implemented by concrete subclasses which
 * implement concrete formats of String representations of tracks.
 * 
 * 
 * @author TeM, JS
 * @ProgrammingProblem.Category abstract class
 * @ProgrammingProblem.Introduced ExerciseSheet04
 * 
 */
public abstract class MyTrackFormatter {
	/**
	 * Creates a String representation for a Track.
	 * 
	 * @param t
	 * 		the track to be formatted
	 * @return
	 * 		the formatted String representing the track
	 * @ProgrammingProblem.Aspect abstract method
	 * 
	 */
	public abstract String format(Track t);
}
