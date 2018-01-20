package WS1718.PROG1.ES05;

public class LendItemFunctions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String lendItemString(LendItem it, int format) {
		switch (format) {
		case 1:
			return String.format("%-15.15s %-10.10s %s %s %-10.10s", it.description,
					it.lender, dateString(it.lendDate), dateString(it.returnDate), it.owner);
		case 2:
			return String.format("%-15.15s %-10.10s", it.description, it.lender);
		default:
			return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", it.description,
					it.lender, dateString(it.lendDate), dateString(it.returnDate), it.owner);
		}

	}
	
	private static String dateString(Date d) {
		return String.format("%04d. %02d. %02d", d.year, d.month, d.day);
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
		if (res>0) return 1;
		if (res<0) return -1;
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
		
		if (res>0) return 1;
		if (res<0) return -1;
		return 0;

	}
}
