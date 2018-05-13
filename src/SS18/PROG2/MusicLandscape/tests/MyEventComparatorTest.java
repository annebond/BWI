// **************************************************
//		
//       git.rev = 232
//  git.revision = 5758f042c648661b29a7471f428d9556f8ed5e72
//         stage = ES04
//
// ***************************************************
package SS18.PROG2.MusicLandscape.tests;
import static org.testng.Assert.fail;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.testng.annotations.Test;

import SS18.PROG2.MusicLandscape.entities.*;
import SS18.PROG2.MusicLandscape.util.comparators.*;



public class MyEventComparatorTest {

  @Test
  public void compare() {
	  try {
			Method m=MyEventComparator.class.getMethod("compare", Event.class, Event.class);
			//System.out.println(m.getName()+" "+m.getReturnType()+ " "+m.getParameterTypes()[0] );
			if (!Modifier.isAbstract(m.getModifiers())) {
				fail("method compare(Event e1, Event e2) should be abstract");
			}
		
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			fail("method compare(Event e1, Event e2) not available");
		} 
  }
}
