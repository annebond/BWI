// **************************************************
//		
//       git.rev = 232
//  git.revision = 5758f042c648661b29a7471f428d9556f8ed5e72
//         stage = ES04
//
// ***************************************************
package SS18.PROG2.MusicLandscape.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SS18.PROG2.MusicLandscape.entities.*;
import SS18.PROG2.MusicLandscape.util.comparators.*;

import static org.testng.Assert.*;

import static org.mockito.Mockito.*;

public class MyEventAttendeesComparatorTest {

  @Test(dataProvider = "attendees")
  public void compare(int e1, int e2, int res) {
	  MyEventAttendeesComparator comp= new MyEventAttendeesComparator();
	  
	  Event left= mock(Event.class);
	  when(left.getAttendees()).thenReturn(e1);
	  Event right= mock(Event.class);
	  when(right.getAttendees()).thenReturn(e2);
	  
	  int result=comp.compare(left, right);
	  if(res>0){
		  assertTrue(result>0, "first should be bigger than second");  
	  }else if(res==0){
		  assertTrue(result==0, "both should be equal");
	  }else if(res<0){
		  assertTrue(result<0, "first should be smaller than second");
	  }

  }
  @Test
  public void compareNull() {
	  MyEventAttendeesComparator comp= new MyEventAttendeesComparator();

	  Event left= null;
	  Event right= null;
	    
	  assertEquals(comp.compare(left, right), 0, "both should be equal");
	  
	
	  
  }
  @Test
  public void compareNullLeft() {
	  MyEventAttendeesComparator comp= new MyEventAttendeesComparator();

	  Event left= null;
	  Event right= mock(Event.class);
	  when(right.getAttendees()).thenReturn(2);
	    
	  int res=comp.compare(left, right);
	  assertTrue(res <0, "first should be smaller than second");
  }
  @Test
  public void compareNullRight() {
	  MyEventAttendeesComparator comp= new MyEventAttendeesComparator();

	  Event right= null;
	  Event left= mock(Event.class);
	  when(left.getAttendees()).thenReturn(2);
	    
	  int res=comp.compare(left, right);
	  assertTrue(res >0, "first should be bigger than second");
  }
  
  @DataProvider(name = "attendees")
	private static Object[][] attendees() {
		return new Object[][] {
				{ 20, 20, 0}, 
				{20, 10, 10}, 
				{10, 20, -10} };
	}
}
