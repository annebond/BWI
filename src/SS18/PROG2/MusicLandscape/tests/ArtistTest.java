// **************************************************
// 
//       git.rev = 222
//  git.revision = 5606f8db8c00d9e1a60936282607a4f156a2c7c2
//         stage = ES03
//
// ***************************************************



package SS18.PROG2.MusicLandscape.tests;

import java.lang.reflect.Field;

import org.mockito.internal.util.reflection.Whitebox;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SS18.PROG2.MusicLandscape.entities.*;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

public class ArtistTest {

	
	
	
	
	
	
	
	

	
	@DataProvider(name = "names2")
	private static Object[][] names2() {
		return new Object[][] { { null, "unknown" }, { "kiss", "kiss" },
				{ "", "unknown" }, { "  ", "unknown" },
				{ "Jon Bon Jovi", "Jon Bon Jovi" } };
	}
		
	 
	
	/**************** ES 03 ***********************/
	@Test(dataProvider = "names2")
	public void testtoString(String in, String out) {
		
		boolean myTest=(new Artist(in).toString()==out)||
				(new Artist(in).toString()==in);
		assertEquals(myTest, true);
	}
	


	

	
}
