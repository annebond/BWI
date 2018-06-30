// **************************************************
//		
//       git.rev = 222
//  git.revision = 5606f8db8c00d9e1a60936282607a4f156a2c7c2
//         stage = ES05
//
// ***************************************************
package SS18.PROG2.MusicLandscape.util;

/**
 * Encapsulates of concept of some object matching a pattern.<br>
 * 
 * Implementing, concrete classes are used to decide whether a given object
 * matches a pattern. More theoretically this class represents a generic predicate.
 * 
 * 
 * @author TeM, JS
 * 
 * @ProgrammingProblem.Category abstract generic class
 * @ProgrammingProblem.Introduced ExerciseSheet05
 * 
 */
public abstract class MyMatcher<T> {

	/**
	 * Matches a object against the pattern of this matcher.<br>
	 * 
	 * @param t the object to match
	 * @return whether t matches the pattern of this matcher. 
	 */
	public abstract boolean matches(T t);

	/**
	 * Creates a Matcher object with a specified pattern.<br>
	 * 
	 * @param pat the pattern of this matcher
	 */
	public MyMatcher(String pat) {
		setPattern(pat);
	}

	/**
	 * Sets the pattern of this matcher.<br>
	 * 
	 * 
	 * @param pat the pattern to set
	 */
	public abstract void setPattern(String pat);

	
	/**
	 * Gets the pattern of this matcher.<br>
	 * 
	 * The pattern is returned in a format that is considered valid in setPAttern.
	 * 
	 * @return the pattern
	 */
	public abstract String getPattern();

}
