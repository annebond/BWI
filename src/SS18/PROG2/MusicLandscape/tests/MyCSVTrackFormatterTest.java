// **************************************************
//		
//       git.rev = 232
//  git.revision = 5758f042c648661b29a7471f428d9556f8ed5e72
//         stage = ES04
//
// ***************************************************
package SS18.PROG2.MusicLandscape.tests;
import org.testng.annotations.Test;

import SS18.PROG2.MusicLandscape.entities.*;
import SS18.PROG2.MusicLandscape.util.formatters.*;

import static org.testng.Assert.*;

public class MyCSVTrackFormatterTest {

  @Test
  public void formatDefault() {
	  MyCSVTrackFormatter form= new MyCSVTrackFormatter();
	  String result= form.format(new Track());
	  System.out.println(result);
	  assertEquals(result, "unknown title,unknown,unknown,1900,0;", "Strings should be equal");
  }
  
  @Test
  public void formatAny() {
	  //"title","performer","writer","year","duration"
	  Track myTrack= new Track("Ain't No Sunshine");
	  myTrack.setPerformer(new Artist("me first and the gimme gimmes"));
	  myTrack.setWriter(new Artist("ghostwriter"));
	  myTrack.setYear(2015);
	  myTrack.setDuration(360);
	  
	  MyCSVTrackFormatter form= new MyCSVTrackFormatter();
	  String result= form.format(myTrack);
	  System.out.println(result);
	  assertEquals(result, "Ain't No Sunshine,me first and the gimme gimmes,ghostwriter,2015,360;", "Strings should be equal");

  }
  
  
}
