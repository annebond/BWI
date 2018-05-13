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
 * This class represents the concept of comparison of two tracks. It has a
 * single abstract method that is to be implemented by concrete subclasses which
 * implement concrete modes of comparison, e.g. by title, by duration or other.
 * 
 * 
 * @author TeM, JS
 * @ProgrammingProblem.Category abstract class
 * @ProgrammingProblem.Introduced ExerciseSheet04
 */
public abstract class MyTrackComparator {
	/**
	 * 
	 * Compares two tracks.<br>
	 * 
	 * The result of the comparison of two tracks is a measure of "distance"
	 * between the tow. Distance is not literal (spatial) distance but more
	 * generally the difference between the two measured in some unit.
	 * 
	 * If the two tracks are equal in a certain sense their distance is zero, if
	 * the first argument is smaller than the second (again, in the sense of
	 * the (concrete) comparator) their distance is negative, positive
	 * otherwise.
	 * Example: compare two tracks by duration
	 * Track 1 (t1): duration 1200
	 * Track 2 (t2): duration 1300
	 * 
	 * Calling compare of a by-duration-comparator: 
	 * compare(t1,t2) would return a negative number (since t1 is shorter than t2)
	 * 
	 * 
	 * Comparators may choose to handle comparison of null tracks but are not forced to.
	 * 
	 * 
	 * @param t1
	 *            the one track to compare
	 * @param t2
	 *            the other track to which t1 is to be compared
	 * @return
	 * 			a measure of the distance between t1 and t2 in the sense of the comparator
	 * 

	 *
	 */
	public abstract int compare(Track t1, Track t2);
}
