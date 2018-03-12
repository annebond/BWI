// **************************************************
// 
//       git.rev = 222
//  git.revision = 5606f8db8c00d9e1a60936282607a4f156a2c7c2
//         stage = ES01
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

	
	
	@Test
	/**
	 * checks if initial value of name is null
	 */
	public void checkInit() {
		Artist toTest = new Artist();
		// reflect private field name

		try {
			Field privateStringField = Artist.class.getDeclaredField("name");
			privateStringField.setAccessible(true);
			assertEquals(privateStringField.get(toTest), null,
					"initial value should be null");
		} catch (Exception e) {
		}
	}
	
	
	
	
	
	@Test(dataProvider = "names")
	public void getName(String in, String out) {
		Artist toTest = new Artist();
		// reflect private field name

		try {
			Field privateStringField = Artist.class.getDeclaredField("name");
			privateStringField.setAccessible(true);
			privateStringField.set(toTest, in);
		} catch (Exception e) {
		}

		assertEquals(toTest.getName(), in);
	}
	
	@Test(dataProvider = "names")
	public void setName(String in, String out) {
		Artist toTest = new Artist();
		toTest.setName(in);
		try {
			Field privateStringField = Artist.class.getDeclaredField("name");
			privateStringField.setAccessible(true);

			assertEquals(privateStringField.get(toTest), out);
		} catch (Exception e) {
		}
	}
	@DataProvider(name = "names")
	private static Object[][] names() {
		return new Object[][] { { null, null }, { "kiss", "kiss" },
				{ "", null }, { "  ", null },
				{ "Jon Bon Jovi", "Jon Bon Jovi" } };
	}
	
	
	

		
	 
	

	
}
