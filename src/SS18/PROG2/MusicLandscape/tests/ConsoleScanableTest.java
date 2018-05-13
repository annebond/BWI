// **************************************************
//		
//       git.rev = 232
//  git.revision = 5758f042c648661b29a7471f428d9556f8ed5e72
//         stage = ES04
//
// ***************************************************
package SS18.PROG2.MusicLandscape.tests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SS18.PROG2.MusicLandscape.entities.Track;
import SS18.PROG2.MusicLandscape.util.ConsoleScanable;


public class ConsoleScanableTest {

  @Test(description="does Track implement interface?")
  public void interfaceInTrack() {
	  Track toTest= new Track();
	  if(!(toTest instanceof ConsoleScanable)){
		  fail("you should implement the interface ConsoleScanable in Track");
	  }
	  
  }
  
  
	

	@DataProvider(name="inout")
	public Object[][] inout() {
		return new Object[][] {
				{"\n\n", "unknown title", "0"},
				{"sample text\n-354\n2364", "sample text", "2364"},
				{"sample text\n\n", "sample text", "0"},
				{"titel\n15", "titel", "15"}
				
		};
	}


  @Test(dataProvider="inout", description="scan changes Track as expected?")
  public void testScan(String input, String title, String duration){
	  Track toTest= new Track();
	  	
		System.setIn(null);
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		try{
		// reset stdin in BinaryStdIn through reflection
		  java.lang.reflect.Field in = System.class.getDeclaredField("in");
		  in.setAccessible(true);
		  //in.set(null, new BufferedReader(new InputStreamReader(System.in)));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		toTest.scan();
		
		//check title value
		assertEquals(toTest.getTitle(),title );
		//check duration value
		assertEquals(toTest.getDuration(),Integer.parseInt(duration) );
  }
}
