// **************************************************
//		
//       git.rev = 232
//  git.revision = 5758f042c648661b29a7471f428d9556f8ed5e72
//         stage = ES04
//
// ***************************************************
package SS18.PROG2.MusicLandscape.util.comparators;

import SS18.PROG2.MusicLandscape.entities.Track;
/**
 * This class represents the concept of comparison of two track by title. 
 * 
 * @author TeM, JS
 * @ProgrammingProblem.Category concrete subclass
 * @ProgrammingProblem.Introduced ExerciseSheet04
 */
public class MyDurationComparator extends MyTrackComparator {
	/**
	 * Compares two tracks by duration.<br>
	 * 
	 * This comparator assumes non-null arguments.
	 * 
	 * @ProgrammingProblem.Aspect concrete method implementation
	 */
	@Override
	public int compare(Track t1, Track t2) {
		return t1.getDuration()-t2.getDuration();
	}

}
