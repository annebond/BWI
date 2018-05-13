// **************************************************
//		
//       git.rev = 232
//  git.revision = 5758f042c648661b29a7471f428d9556f8ed5e72
//         stage = ES04
//
// ***************************************************
package SS18.PROG2.MusicLandscape.tests;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SS18.PROG2.MusicLandscape.entities.*;
import SS18.PROG2.MusicLandscape.util.comparators.*;



public class MyTitleComparatorTest {
	 
	
	  @Test(dataProvider="title")
	  public void compare(String t1, String t2, int res) {

		  MyTitleComparator com= new MyTitleComparator();
		  
		  int result= com.compare(new Track(t1), new Track(t2));
		  
		  if(res>0){
			  assertTrue(result>0, "first should be bigger than second");  
		  }else if(res==0){
			  assertTrue(result==0, "both should be equal");
		  }else if(res<0){
			  assertTrue(result<0, "first should be smaller than second");
		  }
	  }
	  
	  @DataProvider(name = "title")
		private static Object[][] title() {
			return new Object[][] {
					{ "same", "same", 0}, 
					{"same", "other", 4}, 
					{"other", "same", -4} 
					};
		}
}
