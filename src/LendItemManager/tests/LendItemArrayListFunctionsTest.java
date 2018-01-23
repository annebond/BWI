package LendItemManager.tests;
import LendItemManager.*;
import java.lang.reflect.Field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class LendItemArrayListFunctionsTest {
	
	LendItemArrayList toTest;



	@BeforeMethod
	private void init() {
		toTest = new LendItemArrayList();

	}
	
	

  @Test(description="tests if one lendItem can be added to an empty LendItemArrayList")
  public void add() {
	  LendItem item=new LendItem();
	  assertEquals( Main.add(toTest, item), true, "LendItem should be added and method return true");
	  	  
	 
	  	  
	  try {
		//reflect private field next
		 Field f= getPrivateField(toTest, "next",true);   
	    
	    int ne=f.getInt(toTest);
	    //check length is increased by 1 (one LendItems in list)
		assertEquals(ne, 1, "one item has been added, next should be increased");
		
		//check copy by reference
		f= getPrivateField(toTest, "lendItems",true);
		LendItem[] arr=(LendItem[]) f.get(toTest);
	    
		//check copy by reference
		assertEquals(arr[0], item, "a LendItem should be added via reference!");
	    
	  } catch (Exception e) {} 	
  }
  
  @Test(description="tests if one lendItem can be added to a full not resizable LendItemArrayList")
  public void addToFullNotResizable() {
	  	  
	  /**
	   * add 20 new LendItems
	   */
	  for (int i=0; i<20; i++){
		  Main.add(toTest, new LendItem());
	  }
	  
	  /**
	   * add the 21st
	   */
	  assertEquals( Main.add(toTest, new LendItem()), false, "21st LendItem should NOT be added and method should return false");
  }
  
  @Test(description="tests if one lendItem can be added to a full RESIZABLE LendItemArrayList")
  public void addToFullResizable() {
	  //change to resizables
 	  
	  try {
		//reflect private field next
		 Field f= getPrivateField(toTest, "resizeable",true);   
		 f.set(toTest, true);	    
	  } catch (Exception e) {}
	  
	  //create 20 new LendItems
	  LendItem[] support= new LendItem[21];
	  for (int i=0; i<21; i++){
		  support[i]= new LendItem();
	  }
	  // add them to the list
	   
	  for (int i=0; i<20; i++){
		  Main.add(toTest, support[i]);
	  }
	  
	  /**
	   * add the 21st
	   */
	  assertEquals( Main.add(toTest, support[20]), true, "21st LendItem should be added and method should return TRUE");
	  
	  //check new length is 40
	  try {
		//reflect private field next
		
		//check copy by reference
		Field f= getPrivateField(toTest, "lendItems",true);
		LendItem[] arr=(LendItem[]) f.get(toTest);
	    
		//check length of arr
		assertEquals(arr.length, 40, "lendItem[] should have been doubled in size");
		
		//check all elements still available
		for (int i=0; i<21; i++){
			assertEquals(arr[i], support[i], "all LendItems should remain after resizing");
		 }
	    
	  } catch (Exception e) {} 	
  }

  @Test(description="test if an item can be removed by index. this test checks the last element was removed successfully(in full array); this test requires the add method is succesfull")
  public void remove() {
	  LendItem[] items= createHelpingList(15);
	  for (int i=0; i<15; i++){
		  Main.add(toTest, items[i]);
	  }
	  
	  //actual next should be 15
	  try {
		//reflect private field next
		 Field f= getPrivateField(toTest, "next",true);   
	    
	    int ne=f.getInt(toTest);
	    //check length is increased by 1 (one LendItems in list)
		assertEquals(ne, 15, "15 elements have been added, next should be 15");
	  } catch (Exception e) {} 
	  
	  
	  //remove item at 14 - check return is item
	  assertEquals (Main.remove(toTest, 14), items[14],"item of index 14 should be returned");
	  
	  //actual next should be 14
	  try {
			//reflect private field next
			 Field f= getPrivateField(toTest, "next",true);   
		    
		    int ne=f.getInt(toTest);
		    //check length is increased by 1 (one LendItems in list)
			assertEquals(ne, 14, "element at 14 has been removed, next value should be 14");
	  } catch (Exception e) {}
	  
	  //item at 14 is null
	  try {
			//reflect private field next
			
			//check copy by reference
			Field f= getPrivateField(toTest, "lendItems",true);
			LendItem[] arr=(LendItem[]) f.get(toTest);

			assertEquals(arr[14], null, "position 14 should be null (item removed)");
	  } catch (Exception e) {} 	
  }
  @Test(description="test if an item can be removed by index. this test check the last existing element (not in full array); this test requires the add method is succesfull")
  public void removeFromFullList() {
	  LendItem[] items= createHelpingList(20);
	  for (int i=0; i<20; i++){
		  Main.add(toTest, items[i]);
	  }
	  
	  //actual next should be 15
	  try {
		//reflect private field next
		 Field f= getPrivateField(toTest, "next",true);   
	    
	    int ne=f.getInt(toTest);
	    //check length is increased by 1 (one LendItems in list)
		assertEquals(ne, 20, "20 elements hav been added, next should be 20");
	  } catch (Exception e) {} 
	  
	  
	  //remove item at 14 - check return is item
	  assertEquals (Main.remove(toTest, 19), items[19],"item of index 19 should be returned");
	  
	  //actual next should be 14
	  try {
			//reflect private field next
			 Field f= getPrivateField(toTest, "next",true);   
		    
		    int ne=f.getInt(toTest);
		    //check length is increased by 1 (one LendItems in list)
			assertEquals(ne, 19, "element at 19 has been removed, next value should be 19");
	  } catch (Exception e) {}
	  
	  //item at 14 is null
	  try {
			//reflect private field next
			
			//check copy by reference
			Field f= getPrivateField(toTest, "lendItems",true);
			LendItem[] arr=(LendItem[]) f.get(toTest);

			assertEquals(arr[19], null, "position 14 should be null (item removed)");
	  } catch (Exception e) {} 	
  }

  @Test(description="test if an item can be removed by index. this test checks that the second element was removed successfully, copying all other items afterwards to an index-1; this test requires the add method is succesfull")
  public void removeSecondElement() {
	  LendItem[] items= createHelpingList(5);
	  for (int i=0; i<5; i++){
		  Main.add(toTest, items[i]);
	  }
	  
	  //actual next should be 5
	  try {
		//reflect private field next
		 Field f= getPrivateField(toTest, "next",true);   
	    
	    int ne=f.getInt(toTest);
	    //check length is increased by 1 (one LendItems in list)
		assertEquals(ne, 5, "5 elements hav been added, next should be 5");
	  } catch (Exception e) {} 
	  
	  
	  //remove item at 1 - check return is item
	  assertEquals (Main.remove(toTest, 1), items[1],"item of index 1 should be returned");
	  
	  //actual next should be 4
	  try {
			//reflect private field next
			 Field f= getPrivateField(toTest, "next",true);   
		    
		    int ne=f.getInt(toTest);
		    //check length is increased by 1 (one LendItems in list)
			assertEquals(ne, 4, "element at 1 has been removed, next value should be 4 (decreased by 1)");
	  } catch (Exception e) {}
	  
	  //all items are copied one place above
	  try {
			//reflect private field next
			
			//check copy by reference
			Field f= getPrivateField(toTest, "lendItems",true);
			LendItem[] arr=(LendItem[]) f.get(toTest);

			for (int i=1; i<5;i++){
				if (i+1>items.length-1){
					assertEquals(arr[i], null, "element should be null");
					break;
				}
				assertEquals(arr[i], items[i+1], "element should have been moved one index up");
				
			}


	  } catch (Exception e) {} 	
  }

  @Test(description="test if an item can be removed by index. this test checks an unexisting element can't be removed (method returns null, no changes); this test requires the add method is succesfull")
  public void removeNonExistingElement() {
	  LendItem[] items= createHelpingList(10);
	  for (int i=0; i<10; i++){
		  Main.add(toTest, items[i]);
	  }
	  
	  //actual next should be 15
	  try {
		//reflect private field next
		 Field f= getPrivateField(toTest, "next",true);   
	    
	    int ne=f.getInt(toTest);
	    //check length is increased by 1 (one LendItems in list)
		assertEquals(ne, 10, "10 elements have been added, next should be 1");
	  } catch (Exception e) {} 
	  
	  assertEquals (Main.remove(toTest, 17), null,"non existing id, null should be returned");
	//actual next should be 15
	  try {
		//reflect private field next
		 Field f= getPrivateField(toTest, "next",true);   
	    
	    int ne=f.getInt(toTest);
	    //check length is increased by 1 (one LendItems in list)
		assertEquals(ne, 10, "10 elements have been added, next should be 10 (no changes)");
	  } catch (Exception e) {} 
  }
  
  @Test(description="test if an item can be removed by index. this test checks an element with non existing id can't be removed (method returns null, no changes); this test requires the add method is succesfull")
  public void removeNonExistingID() {
	  LendItem[] items= createHelpingList(10);
	  for (int i=0; i<10; i++){
		  Main.add(toTest, items[i]);
	  }
	  
	  //actual next should be 15
	  try {
		//reflect private field next
		 Field f= getPrivateField(toTest, "next",true);   
	    
	    int ne=f.getInt(toTest);
	    //check length is increased by 1 (one LendItems in list)
		assertEquals(ne, 10, "10 elements have been added, next should be 1");
	  } catch (Exception e) {} 
	  
	  assertEquals (Main.remove(toTest, 42), null,"non existing id, null should be returned");
	//actual next should be 15
	  try {
		//reflect private field next
		 Field f= getPrivateField(toTest, "next",true);   
	    
	    int ne=f.getInt(toTest);
	    //check length is increased by 1 (one LendItems in list)
		assertEquals(ne, 10, "10 elements have been added, next should be 10 (no changes)");
	  } catch (Exception e) {} 
  }
@Test(description="tests if filter by description returns the defined result", dataProvider="filterDesc")
  public void filterByDescription(String desc) {
	 LendItem[] items= createHelpingList(20);
		int counter=0;
	  for (int i=0; i<20; i++){
		  Main.add(toTest, items[i]);
		  
		  //reflect private field next
		  Field f= getPrivateField(items[i], "description",true);   
		   
		  //check for contains
		  try {
			counter=(f.get(items[i]).toString().contains(desc))?counter+1:counter;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
	  System.out.printf("found %s %d times\n", desc, counter);
	  
	  LendItemArrayList result=Main.filterByDescription(toTest, desc);

		Field f= getPrivateField(result, "lendItems",true);
		LendItem[] arr;
		try {
			arr = (LendItem[]) f.get(result);
			
			//check by checking if element after counter is null while others have values
			for (int i=0; i<counter-1; i++){
				assertNotNull(arr[i], "LendItem should be stored at position "+i);
				
			}
			if (counter<20)
				assertEquals(arr[counter],null, "only those elements should be returned which contain the desc String ("+counter+")");
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

  }

	@Test(description="test if a LendItem can be found by ID", dataProvider="id")
	public void findByID(int ID) {
		LendItem[] items= createHelpingList(20);
		int counter=-1;
		  for (int i=0; i<20; i++){
			  Main.add(toTest, items[i]);
			  
			  //reflect private field next
			  Field f= getPrivateField(items[i], "id",true);   
			   
			  //check for contains
			  try {
				if (f.getInt(items[i])==ID&&counter==-1){
					counter=i;
				}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		  
	  System.out.printf("found element with id %d at  position %d \n", ID, counter);
	  
	  int result=Main.findByID(toTest, ID);
	  assertEquals(result, counter, "wrong index returned");
	}

	@DataProvider(name="id")
	private Object[][] dp_findID() {
		return new Object[][] {
				{9},
				{18},
				{6},
				{-15},
				{54}
		};
	}


	@DataProvider(name="filterDesc")
	private Object[][] dp_filterDesc() {
		return new Object[][] {
				{"_"},
				{"A_"},
				{"C"},
				{"F"},
				{"X"}
		};
	}

  @Test(description="tests if sorting is implemented as expected, prerequisite: compare function is used!", dataProvider="sortingOrder")
  public void sort(int order) {
	  //create local array
	  LendItem[] items= createHelpingList(20);
	  
	  //assign values to arraylist
	  for (int i=0; i<20; i++)
		  Main.add(toTest, items[i]);
	  
	  //local array
	  for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items.length- 1; j++)
				if (Main.compare(items[j], items[j + 1], order) > 0) {
					LendItem tmp = items[j];
					items[j] = items[j + 1];
					items[j + 1] = tmp;
				}
		}
	  
	  //sort arrayList
	  Main.sort(toTest, order);
	  
	  //get array from liest
	  	Field f= getPrivateField(toTest, "lendItems",true);
		LendItem[] arr;
		String orderString="";
		switch(order){
			case 1:orderString="lend date";break;
			case 2:orderString="return date";break;
			case 3:orderString="lender";break;
			case 4: orderString="owner";break;
			default: orderString="description";break;
		}
		try {
			arr = (LendItem[]) f.get(toTest);
			
			//check by checking if element after counter is null while others have values
			for (int i=0; i<arr.length; i++){
				assertEquals(arr[i],items[i], "sorting not as expected, sorted by "+orderString);
			}
		}catch(Exception ex){}

	  
  }
  	  @DataProvider(name="sortingOrder")
		private Object[][] dp_sortingc() {
			return new Object[][] {
					{0},
					{3},
					{4},
					{1},
					{2},
					{20}
			};
		}
  /**
   * tries to get a private field of the given object
   * @param myObject Object of which the field should be reached
   * @param fieldName String which defines the field to be retrieved
   * @return new Field object if found, fails if NoSuchFieldException
   */
	private <T> Field getPrivateField(T myObject, String fieldName, boolean isBase){
		Field privateStringField;
		try {
			privateStringField = (isBase)?myObject.getClass().getDeclaredField(fieldName):myObject.getClass().getSuperclass().getDeclaredField(fieldName);
			privateStringField.setAccessible(true);
			return privateStringField;
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			fail("member \""+fieldName+"\" not found");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private LendItem[] createHelpingList(int length){
		//create 20 new LendItems
		  LendItem[] support= new LendItem[length];
		  for (int i=0; i<length; i++){
			  support[i]= createLendItem(i);
		  }
		  return support;
	}
	
	
	private LendItem createLendItem(int id){

		LendItem li = new LendItem();
		
		try {
			//reflect private field next
			 Field f= getPrivateField(li, "id",true);   
			 f.set(li, (id+3)*3);	
			 
			//reflect private field next
			 f= getPrivateField(li, "description",true);   
			 f.set(li, String.format("%c_description", ((int) (id * Math.PI * 10000)) % 15 + 'A'));
			 
			 f= getPrivateField(li, "lender",true);   
			 f.set(li, String.format("Gustav_%02d", ((int) (id * Math.PI * 1000000)) % 10));
			 
			 f= getPrivateField(li, "owner",true);   
			 f.set(li, String.format("Susi_x%05d", ((int) (id * Math.PI * 10000)) % 15 + 'A'));
		
			 
			 Date d= new Date();
			 
			 f= getPrivateField(d, "year",true);   
			 f.set(d, 2010 - ((int) (id * Math.PI * 100)) % 100);
			 
			 f= getPrivateField(d, "month",true);   
			 f.set(d, ((int) (id * Math.PI * 1000000)) % 12 + 1);
			 
			 f= getPrivateField(d, "day",true);   
			 f.set(d, ((int) (id * Math.PI * 100000000)) % 28 + 1);
			 
			 f= getPrivateField(li, "lendDate",true);   
			 f.set(li, d);
			 
		} catch (Exception e) {}
		
		return li;
	}

	
}
