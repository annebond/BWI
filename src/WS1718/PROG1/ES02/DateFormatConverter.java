package WS1718.PROG1.ES02;
import WS1718.PROG1.prog.utils.TextIO;
public class DateFormatConverter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextIO.putln("enter date (yyyymmdd)");
		int date = TextIO.getlnInt();
		if (date >= 15821016 && date <= 21001231) {
			int year = date / 10000;
				int leftOverYear = date % 10000;
			int month = leftOverYear / 100;
			int day = leftOverYear % 100;
			TextIO.putf("%02d.%02d.%02d", day, month, year);
		}
		else TextIO.putln ("invalid date");	
	}

}
