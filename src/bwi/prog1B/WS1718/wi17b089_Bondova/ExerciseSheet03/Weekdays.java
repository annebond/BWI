package bwi.prog1B.WS1718.wi17b089_Bondova.ExerciseSheet03;
import bwi.prog.utils.TextIO;
public class Weekdays {

	public static void main(String[] args) {
		TextIO.putln("enter date (yyyymmdd)");
		int date = TextIO.getInt();
		
		int inDay = date % 100, inMonth = date % 10000 / 100, inYear = date / 10000;
		
		if(validate(inYear, inMonth, inDay)) {
			int W = weekday(inDay, inMonth, inYear);
			String weekday = dayName(W);
			TextIO.putf("%02d.%02d.%4d was/is or will be a %s", date % 100, date % 10000 / 100, date / 10000, weekday);
		}else {
			TextIO.putf("invalid date (%d)\n", date);
		}
	}
	public static boolean validate (int year) {
	    if (year < 1582 || year > 2199) {
	        return false;
		    } else {
		    	return true;
		    }
	}
	public static boolean validate (int year, int month) {
	    if ((year < 1582 || year > 2199)) {
	        return false;
		    } else if (month < 1 || month > 12) {
		    	return false;
		    } else if (year == 1582 && month < 10) {
		        return false;
		    } else {
		        return true;
		    }
	}
	public static boolean isLeap (int inYear) {
	    return (((inYear % 4 == 0) && (inYear % 400 == 0 || inYear % 100 != 0) ));
	}
	public static int nDays (int month, int year) {
	    if ((((year % 4 == 0) && (year % 400 == 0 || year % 100 != 0) ))) {
	        if (month == 2) {
            return 29;
	        } else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
	        	return 30;
		      } else {
		    	  return 31;
		        }

	    } else if (year == 1582 && month == 10) {
	    	return 16;
		    } else {
		    	if (month == 2) {
		    		return 28;
		            } else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
		            	return 30;
		              }
		            else {
		            return 31;
		            }
		     }
	}
	public static boolean validate (int year, int month, int day) {
		if ((year == 1582 && month == 10) && day < 16) {
			return false;
		} else if ((year == 1582 && month == 10) && day <= 31) {
		  }
		return (validate(year,month) && day > 0 && day <= nDays(month,year));
	}
	public static int weekday (int inDay, int inMonth, int inYear) {
	    int  month, year, day,c;
	    year = inYear;
	    day = inDay;
	    if (inMonth < 3) {
	    	year--;
		    inMonth = inMonth +10;
		    } else {
		    	inMonth = inMonth -2;
		      }
	    month = inMonth;
		c = year / 100;
		year = year % 100;
		int monthTerm = (int) Math.floor((2.6 * month) - 0.2);
		int A = day + monthTerm + year + (year / 4) + (c / 4) - (2 * c);
		int x = A % 7;
		if (x < 0) {
			x += 7;
		}
		return x;
	}

		public static String dayName (int W) {

		    switch (W) {

		    case 0: return "Sunday";

		    case 1: return "Monday";

		    case 2: return "Tuesday";

		    case 3: return "Wednesday";

		    case 4: return "Thursday";

		    case 5: return "Friday";

		    case 6: return "Saturday";

		    default: return "invalid date (" +W+ ")";
		    }
		}
}
