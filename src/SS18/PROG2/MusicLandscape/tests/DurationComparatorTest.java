// **************************************************
//		
//       git.rev = 222
//  git.revision = 5606f8db8c00d9e1a60936282607a4f156a2c7c2
//         stage = ES05
//
// ***************************************************
package SS18.PROG2.MusicLandscape.tests;
import static org.testng.Assert.*;

import java.util.Comparator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SS18.PROG2.MusicLandscape.entities.Track;
import SS18.PROG2.MusicLandscape.util.comparators.DurationComparator;



public class DurationComparatorTest {
	
	@Test(description="checks if the interface java.util.Comparator has been used")
	public void checkType(){
		DurationComparator com= new DurationComparator();
		assertTrue((com instanceof Comparator), "you should implement generic interface Comparator<Track>");
		
	}

	@Test(dataProvider="duration", description="checks if comparison is done correctly ")
	  public void compare(int t1, int t2, int res)  {
		DurationComparator com= new DurationComparator();
	  
	  Track left= new Track();
	  Track right= new Track();
	  left.setDuration(t1);
	  right.setDuration(t2);
	  
	  int result= com.compare(left, right);

	  if(res>0){
		  assertTrue(result>0, "first should be bigger than second");  
	  }else if(res==0){
		  assertTrue(result==0, "both should be equal");
	  }else if(res<0){
		  assertTrue(result<0, "first should be smaller than second");
	  }
}

  @Test(description="checks return value of toString")
  public void testtoString() {
	  DurationComparator com= new DurationComparator();
	  assertEquals(com.toString(),"by duration", "toString() should return another value" );
  }
  
  
  @DataProvider(name = "duration")
	private static Object[][] duration() {
		return new Object[][] {
				{ 60, 60, 0}, 
				{120, 60, 60}, 
				{60, 120, -60} 
				};
	}
}
