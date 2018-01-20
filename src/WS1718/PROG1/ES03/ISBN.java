package WS1718.PROG1.ES03;
import WS1718.PROG1.prog.utils.TextIO;
public class ISBN {

	public static void main(String[] args) {
		TextIO.put("enter ISBN: ");
		String isbn = TextIO.getln();
		
		TextIO.putf("valid pattern: %s\n", validatePattern(isbn));
		TextIO.putf("valid checkDigit: %s\n", validateCheckDigit(isbn));
	}
	static boolean validatePattern(String s) {
		boolean isValid = false;
		if (s.length() == 13) {
			isValid = true;
			return isValid;
		} else {
			isValid = false;
			return isValid;
		}	
	}
	static boolean validateCheckDigit(String s) {
		if (validatePattern(s) == true) {
			int sum = 0;
			int calcDigitCheck;
			for (int d = 0; d < 12; d++) {
				calcDigitCheck = Integer.parseInt(s.substring(d, d+1));
				if (d % 2 == 0) {
					sum += calcDigitCheck *1;
				} else {
					sum += calcDigitCheck *3;
				}
			}
			return ((10-(sum  % 10)) % 10) == Integer.parseInt(s.substring(12));
		} else {
			return false;
		}
		
	}
}
