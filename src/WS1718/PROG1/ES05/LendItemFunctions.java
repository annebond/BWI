package WS1718.PROG1.ES05;
import WS1718.PROG1.prog.utils.TextIO;

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
		
		int res = it1.description.compareTo(it2.description);
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
	
	public static boolean isOut(LendItem li) {
		return li.returnDate == null;
	}
}
