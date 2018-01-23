package WS1718.PROG1.LendItemManager.tests;
import static org.testng.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import WS1718.PROG1.LendItemManager.*;

public class LendItemArrayListTest {
	/**
	 * tries to get a private field of the given object
	 * 
	 * @param myObject
	 *            Object of which the field should be reached
	 * @param fieldName
	 *            String which defines the field to be retrieved
	 * @return new Field object if found, fails if NoSuchFieldException
	 */
	private <T> Field getPrivateField(T myObject, String fieldName) {
		Field privateStringField;
		try {
			privateStringField = myObject.getClass()
					.getDeclaredField(fieldName);
			privateStringField.setAccessible(true);
			return privateStringField;
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			fail("field \"" + fieldName + "\" not found");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	@Test(description="tests if all requested fields are available using the requested datatype")
	/**
	 * tests if all requested fields are available using the requested datatype
	 */
	public void LendItemArrayList() {
		LendItemArrayList toTest= new LendItemArrayList();
		String message = "";
		List<Object[]> myList = new ArrayList<Object[]>();

		myList.add(new Object[] { "int","INITIAL_SIZE"  });
		myList.add(new Object[] { "boolean","resizeable" });
		myList.add(new Object[] { "LendItem[]","lendItems" });
		myList.add(new Object[] { "int","next" });
		

		for (Object[] li : myList) {
			message = String.format("field %s should be of datatype %s\n",
					li[1], li[0]);
			try {
				assertEquals(
						getPrivateField(toTest, li[1].toString()).getType().getSimpleName(), li[0], message);

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * checks all initial values 
	 * 	-> 0 for numbers, 
	 * 	null for String, 
	 * 	any non-null value for reference members
	 */
	@Test(description="tests if all requested fields have requested initial value")
	public void checkInit(){
		LendItemArrayList toTest= new LendItemArrayList();
		String message="";
		List<Object[]> myList= new ArrayList<Object[]>();
		
		
		myList.add(new Object[]{"i", "INITIAL_SIZE",20});
		myList.add(new Object[]{"b","resizeable",false});
		myList.add(new Object[]{"a","lendItems",""});
		myList.add(new Object[]{"i", "next",0});
		
		
		for (Object[] li : myList){
			message=String.format("initial value for %s should be %s\n",li[1], li[2]);
			try {
				switch (li[0].toString()) {
				case "i":case "s":case "b":
					assertEquals(getPrivateField(toTest, li[1].toString()).get(toTest),li[2],message);
					break;
				case "nn":
					assertNotNull(getPrivateField(toTest, li[1].toString()).get(toTest),message);
					break;
				case "a":
					assertNotNull(getPrivateField(toTest, li[1].toString()).get(toTest),message);
					Object arr=getPrivateField(toTest, li[1].toString()).get(toTest);
					assertTrue(arr instanceof LendItem[], "lendItems has to be of type LendItem[]");
					assertEquals(((LendItem[])arr).length, 20, "lendItems length has to be 20 initially");
				}
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
	}
}
