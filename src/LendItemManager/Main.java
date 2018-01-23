package LendItemManager;

import java.util.ArrayList;
//import bwi.prog.utils.TextIO;
import WS1718.PROG1.prog.utils.TextIO;

public class Main {
	
	
	public static void main(String[] args) {
	
		LendItemArrayList TestData = new LendItemArrayList();
		TestData.resizeable = true;
		int format = 1;
		//int nextID = 0;
		
		
//		for (int i = 0; i < 25; i++) {
//			LendItem li = new LendItem();
//			li.id = nextID ++;
//			//li.description = "test";
//			li.description = String.format("%c_description", ((int) (i
//			* Math.PI * 10000)) % 15 + 'A');
//			li.lender = String.format("Gustav_%02d", ((int) (i
//			* Math.PI * 1000000)) % 10);
//			li.lendDate = new Date();
//			li.lendDate.year = 2010 - ((int) (i * Math.PI * 100)) % 100;
//			li.lendDate.month = ((int) (i * Math.PI * 1000000)) % 12 + 1;
//			li.lendDate.day = ((int) (i * Math.PI * 100000000)) % 28 + 1;
//			add(TestData, li);
//			}
		
		// Testabfragen während der entwicklung:
		// sort(TestData, 12);
		//String testdesc = "test";
		// printTable(TestData, 1);
		//printTable((filterByDescription(TestData, testdesc)), 1);
		
		int counter = 1;
		int id;
		
		while (true) {
			TextIO.putln("");
			TextIO.putln("############################");
			TextIO.putln("## Hello LendItemManager! ##");
			TextIO.putln("## 1) list                ##");
			TextIO.putln("## 2) add                 ##");
			TextIO.putln("## 3) remove              ##");
			TextIO.putln("## 4) sort                ##");
			TextIO.putln("## 5) filter              ##");
			TextIO.putln("## 6) set format          ##");
			TextIO.putln("## 7) help                ##");
			TextIO.putln("## 0) quit                ##");
			TextIO.putln("############################");
			
			
			int userInput=-1;
			userInput= TextIO.getlnInt();
			
			
			switch (userInput) {
			case 1:
				printTable(TestData, format);
				break;
			case 2:
				LendItem scanned = scanLendItem();
				scanned.id = counter;
				counter++;
				add(TestData, scanned);
				TextIO.putln("\n1 LendItem added. \n");
				break;
			case 3:
				TextIO.put("Enter ID of the LendItem to be removed: ");
				id = TextIO.getInt();
				int idx = findByID (TestData, id);
				if (idx < 0) {
					TextIO.putf("LendItem not found (ID %d).\n", id);
				} else {
					remove(TestData, idx);
					TextIO.putf("1 LendItem (ID=%d) removed.\n", id);
				}
				break;
			case 4:
				TextIO.putln("");
				TextIO.putln("#############################");
				TextIO.putln("## Choose your sort option: ##");
				TextIO.putln("## 1) by lend date          ##");
				TextIO.putln("## 2) by return date        ##");
				TextIO.putln("## 3) by lender             ##");
				TextIO.putln("## 4) by owner              ##");
				TextIO.putln("## 5) by description        ##");
				TextIO.putln("#############################");
				int sortorder = TextIO.getInt();
				sort(TestData, sortorder);
				switch (sortorder) {
				case 1: TextIO.put("see the list sorted by lend date: \n"); break;
				case 2: TextIO.put("see the list sorted by return date: \n"); break;
				case 3: TextIO.put("see the list sorted by lender: \n"); break;
				case 4: TextIO.put("see the list sorted by owner: \n"); break;
				case 5: TextIO.put("see the list sorted by description: \n"); break;
				default: TextIO.put("see the list sorted by description: \n"); break;
				}
				printTable(TestData, format);
				break;
			case 5:
				TextIO.putln("Enter your description for filter: ");
				String filterString = TextIO.getln();
				TextIO.put("displaying matches:\n\n");
				
				printTable(filterByDescription(TestData, filterString), format);
				//list(filterByDescription(TestData, filterString), format);
				break;
			case 6:
				TextIO.putln("");
				TextIO.putln("#########################");
				TextIO.putln("## Choose your format: ##");
				TextIO.putln("## 1) full format      ##");
				TextIO.putln("## 2) short format     ##");
				TextIO.putln("## 3) CSV format       ##");
				TextIO.putln("#########################");
				format = TextIO.getInt();
				TextIO.putln("Output format changed successfully.");
				break;
			case 7:
				help();
				break;
			case 0:
				return;
			default:
				TextIO.putln("not allowed entry!");
				break;
			}
		}	
	}
	
	public static String lendItemString(LendItem it, int format) {
		
		ArrayList<String> row = new ArrayList();
		
		/*
		 * abhängig vom format werden die entsprechenden felder in die array-liste hinzugefügt
		 * für die formatierung wird dann eine andere funktion aufgerufen --> formatRow
		 * da sich die formatierung in lendItemString und lendItemsHeadings wiederholt hätte (auskommentierter Teil), ist die formatierung in eine eigene funktion ausgelagert --> formatRow
		*/
		switch (format) {
		case 1: 																			//Full Format
			//return String.format("%3s %-15.15s %-10.10s %s %s %-10.10s", Integer.toString(it.id) , it.description,
			//		it.lender, dateString(it.lendDate), dateString(it.returnDate), it.owner);
			row.add(Integer.toString(it.id));
			row.add(it.description);
			row.add(it.lender);
			row.add(dateString(it.lendDate));
			row.add(dateString(it.returnDate));
			row.add(it.owner);	
		case 2: 																			// Short Format
			//return String.format("%-15.15s %-10.10s", it.description, it.lender);
			row.add(it.description);
			row.add(it.lender);
		default: 																			// CSV Format
			//return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", it.description,
			//		it.lender, dateString(it.lendDate), dateString(it.returnDate), it.owner);
			row.add(it.description);
			row.add(it.lender);
			row.add(dateString(it.lendDate));
			row.add(dateString(it.returnDate));
			row.add(it.owner);
			
		return formatRow(row, format);
		}

	}
	
	private static String formatRow(ArrayList<String> row, int format) {
		
		// abhängig vom format spuckt diese funktion die richtige formatierung der einzelnen felder der array-liste aus
		switch (format) {
		case 1: 																			//Full Format
			return String.format("%3s %-15.15s %-10.10s %s %s %-10.10s ", row.get(0) , row.get(1),
					row.get(2), row.get(3), row.get(4), row.get(5));
		case 2: 																			// Short Format
			return String.format("%-15.15s %-10.10s ", row.get(0), row.get(1));
		default: 																			// CSV Format
			return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", row.get(0),
					row.get(1), row.get(2), row.get(3), row.get(4)); 
		}
	} 
	
	public static String lendItemSeparator(int format) {
		
		//abhängig vom format wird eine gewisse anzahl an "-" zurückgegeben
		switch (format) {
		case 1: 																			//Full Format
			return new String(new char[67]).replace("\0", "-");
		case 2: 																			// Short Format
			return new String(new char[30]).replace("\0", "-");
		default: 																			// CSV Format
			return new String(new char[59]).replace("\0", "-");
		}
	}
	
	private static String dateString(Date d) {

		//übergebenes datum-objekt soll im format <yyyy>.<mm>.<dd> zurückgegeben werden 
		if (d == null) {
			return String.format("<not set>");
		}
		return String.format("%04d.%02d.%02d", d.year, d.month, d.day);
	}
	
	
	public static String lendItemHeadings(int format) {
		
		//mit dieser funktion werden die Headings der Liste definiert, abhängig vom Format
		ArrayList<String> row = new ArrayList();
		
		switch (format) {
		case 1: 																			//Full Format
			row.add("ID");
			row.add("description");
			row.add("lender");
			row.add("lend date");
			row.add("return date");
			row.add("owner");	
		case 2: 																			// Short Format
			row.add("description");
			row.add("lender");
		default: 																			// CSV Format
			row.add("description");
			row.add("lender");
			row.add("lend date");
			row.add("return date");
			row.add("owner");
			
		return formatRow(row, format);
		}
	}
	
	
	public static boolean add(LendItemArrayList list, LendItem p) {
		
		//wenn next kleiner ist als länge vom array, dann füge lenditem hinzu
		if (list.next < list.lendItems.length) {
			list.lendItems[list.next] = p;
			list.next++;
			return true;
		
		/* else überprüfe ob array resizeable ist
		 * wenn ja resize: neues array als temp in dem alles ausm vollen array reinkopiert wird, dann noch neues array zum array von lenditemarraylist machen
		 * sonst wird nur false zurückgegeben und es wird nichts geadded
		 */
		} else {
			if (list.resizeable) {
				LendItem[] newData = new LendItem[list.lendItems.length * 2];
				for (int i = 0; i < list.next; i++) {
					newData[i] = list.lendItems[i];
					 
				}
				list.lendItems = newData;
				
				list.lendItems[list.next] = p;
				list.next++;
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static LendItem remove(LendItemArrayList list, int n) {
		// prüfung ob index kein blödsinn ist
		if (n < 0 || n > list.next) { 
			return null;
		}
		
		// durchs removen wird next kleiner,for schleife verschiebt alles nach unten, letztes weglöschen/null setzen, gelöschtes element wird retourniert 
		LendItem removeItem = list.lendItems[n];
		list.lendItems[n] = null;
			
		for (int i = n; i < list.lendItems.length-1; i++ ) {
			list.lendItems[i] = list.lendItems[i+1];
		}
		list.next--;
		return removeItem;
	}
	
	
	public static int list(LendItemArrayList list, int format) {
		int i;
		for (i = 0; i < list.next; i++) {
			TextIO.put(lendItemString(list.lendItems[i], format));
			TextIO.putf("(%d)\n", i);
		}
		return i;
	}
	
	public static void printTable(LendItemArrayList list, int format)
	{
		TextIO.putln("\n" + lendItemHeadings(format));
		TextIO.putln(lendItemSeparator(format));
		int count = list(list, format);
		TextIO.putln(lendItemSeparator(format));
		TextIO.putf("%3d LendItems(s) in list, %3d free.\n\n",count,(list.lendItems.length - count));
	}
	
	public static void sort(LendItemArrayList list, int order) {
		//insertion sort, compare aus LendItemFunctions weiter drunter alles kopiert
		 /* i is the first idx of unsorted partition*/
		 for (int i = 1; i < list.next; i++) {
			 
			 int j = i;
			 /*tmp holds value of next element to insert*/
			 LendItem tmp = list.lendItems[i];
			 
			 /*find correct position for tmp*/
			 
			 while (j > 0 && compare(tmp, list.lendItems[j-1], order) < 0) {
				 /*on the way there shift right*/
				 list.lendItems[j] = list.lendItems[j-1];
				 j--;
			 }
			 
			 list.lendItems[j] = tmp;
		 }	
	}
	
	public static int compare(LendItem it1, LendItem it2, int method) {
		int returnvalue;
		switch (method) {
		case 1:
			returnvalue = compareByLendDate(it1, it2);
			break;
		case 2:
			returnvalue = compareByReturnDate(it1, it2);
			break;
		case 3:
			returnvalue = compareByLender(it1, it2);
			break;
		case 4:
			returnvalue = compareByOwner(it1, it2);
			break;
		default:
			returnvalue = compareByDescription(it1, it2);
			break;
		}
		return returnvalue;
		}
	
	public static int compareByLendDate(LendItem it1, LendItem it2) {
		if (it1 == null && it2 == null)
			return 0;
		if (it1 == null)
			return -1;
		if (it2 == null)
			return 1;

		return compare(it1.lendDate, it2.lendDate);
	}
	
	public static int compareByReturnDate(LendItem it1, LendItem it2) {
		if (it1 == null && it2 == null )
			return 0;
		if (it1 == null)
			return -1;
		if (it2 == null)
			return 1;

		return compare(it1.returnDate, it2.returnDate);
	}
	
	public static int compareByDescription(LendItem it1, LendItem it2) {
		if (it1 == null && it2 == null)
			return 0;
		if (it1 == null)
			return -1;
		if (it2 == null)
			return 1;

		int res=it1.description.compareTo(it2.description);
		if (res>0) 
			return 1;
		if (res<0) 
			return -1;
		return 0;
	}
	
	public static int compare(Date d1, Date d2) {
		if (d1 == null && d2 == null)
			return 0;
		if (d1 == null)
			return -1;
		if (d2 == null)
			return 1;
		
		int res= (d1.day + d1.month * 100 + d1.year * 10000) - (d2.day + d2.month
				* 100 + d2.year * 10000);
		
		if (res>0) 
			return 1;
		if (res<0) 
			return -1;
		return 0;

	}
	
	public static int compareByLender(LendItem it1, LendItem it2) {
		if (it1 == null && it2 == null)
			return 0;
		if (it1 == null)
			return -1;
		if (it2 == null)
			return 1;
		
		int res = it1.lender.compareTo(it2.lender);
		if (res>0)
			return 1;
		if (res<0)
			return -1;
		return 0;
	}
	
	public static int compareByOwner(LendItem it1, LendItem it2) {
		if (it1 == null && it2 == null)
			return 0;
		if (it1 == null)
			return -1;
		if (it2 == null)
			return 1;
		
		int res=it1.owner.compareTo(it2.owner);
		if (res>0) 
			return 1;
		if (res<0) 
			return -1;
		return 0;
	}

	public static boolean equals(LendItem it1, LendItem it2) {
		if (it1 == null && it2 == null)
			return true;
		if (it1 == null || it2 == null)
			return false;

		return it1.description == it2.description
				&& it1.lender.equals(it2.lender) && it1.owner.equals(it2.owner)
				&& it1.lendDate.equals(it2.lendDate) 
				//&& equals(it1.lendDate, it2.lendDate)
				//&& equals(it1.returnDate, it2.returnDate);
				&& it1.returnDate.equals(it2.returnDate);

	}
	
	public static boolean equals(Date d1, Date d2) {
		if (d1 == null && d2 == null)
			return true;
		if (d1 == null || d2 == null)
			return false;

		return d1.day == d2.day && d1.month == d2.month && d1.year == d2.year;
	}

	public static LendItemArrayList filterByDescription(LendItemArrayList list,String
			desc) {
		LendItemArrayList filter = new LendItemArrayList();
		for (int i = 0; i < list.next; i++) {
			if (list.lendItems[i].description.contains(desc) == true) {
				//list.lendItems[i] = filter.lendItems[idx];
				add(filter, list.lendItems[i]);
			}
		}
		return filter;
		
	}
	
	public static int findByID(LendItemArrayList list, int id) {
		
		for (int i = 0; i < list.next; i++) {
			if (list.lendItems[i].id == id) {
				return i;
			} 
		
		}
		return -1;
	}

	public static LendItem scanLendItem() {
		String desc = "", lender = "", owner = "";
		do {
			TextIO.putf("description: ");
			desc = TextIO.getln();
			TextIO.putf("lender: ");
			lender = TextIO.getln();
			TextIO.putf("owner: ");
			owner = TextIO.getln();

			if (desc.isEmpty()) {
				TextIO.putf("description cannot be empty!\n");
				continue;
			}
			if (lender.isEmpty()) {
				TextIO.putf("lender cannot be empty!\n");
				continue;
			}

			break;
		} while (true);

		LendItem it = new LendItem();
		it.description = desc;
		it.lender = lender;
		it.owner = owner;
		TextIO.putf("lend date:\n");
		it.lendDate = scanDate();
		TextIO.putf("return date:\n");
		it.returnDate = scanDate();

		return it;
	}
	
	public static Date scanDate() {
		int y, m, d;
		do {
			TextIO.putf("year: ");
			y = TextIO.getlnInt();
			TextIO.putf("month:");
			m = TextIO.getlnInt();
			TextIO.putf("day:");
			d = TextIO.getlnInt();

			if (y < 1582) {
				TextIO.putf("year cannot be before 1582!\n");
				continue;
			}
			if (m < 1 || m > 12) {
				TextIO.putf("month must be 1-12!\n");
				continue;
			}
			if (d < 1 || d > 31) {
				TextIO.putf("day must be 1-31!\n");
				continue;
			}

			break;
		} while (true);
		Date dat = new Date();
		dat.day = d;
		dat.month = m;
		dat.year = y;
		return dat;
	}
	
	private static void help() {
		//TextIO.putln("InProgress...");
		TextIO.putln("");
		TextIO.putln("## This is a help-page with a short description of all options in the programm. ##\n");
		TextIO.putln("1) list: Displays all items in the list in a specified tabular format.\n\n"
				+ "Available formats:\n"
				+ "1: full format\r\n" + 
				"2: short format\r\n" + 
				"3 (and in all other cases): csv format\r\n\n" + 
				"Full Format: <id> <description> <lender> <lend date> <return date> <owner> where\r\n" + 
				"description is exactly 15 characters wide, left aligned\r\n" + 
				"lender and owner are exactly 10 characters wide, left aligned\r\n" + 
				"dates are in their default format: <yyyy>.<mm>.<dd>\r\n\n" + 
				"Short Format: <description> <lender> where\r\n" + 
				"description is exactly 15 characters wide, left aligned\r\n" + 
				"lender are exactly 10 characters wide, left aligned\r\n\n" + 
				"CSV Format: <description>,<lender>,<lend date>,<return date>,<owner> where all\r\n" + 
				"values are surrounded by double quotes and dates are in their default format.\n");
		TextIO.putln("2) add: Adds a LendItem to the end of the list.");
		TextIO.putln("3) remove: Removes a LendItem at a specified (index) position.");
		TextIO.putln("4) sort: Sorts the list in a specified order.\n\n"
				+ "Available sorting methods:\n"
				+ "by lend date\r\n" + 
				"by return date\r\n" + 
				"by lender\r\n" + 
				"by owner\n");
		TextIO.putln("5) filter: Filters a list returning only those items that match a specified description.");
		TextIO.putln("6) set format: Sets the format for list. For more details see help fopr 1) list.");
		TextIO.putln("7) help: Displays a short help-page.");
		TextIO.putln("0) quit: The programm runs until quit is selected.\n");
		TextIO.putln("## If this help-page is not enough, please have a look into the code: https://github.com/annebond/BWI ##");
	}
}
