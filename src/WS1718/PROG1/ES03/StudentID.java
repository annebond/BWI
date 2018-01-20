package WS1718.PROG1.ES03;
import WS1718.PROG1.prog.utils.TextIO;
public class StudentID {

	public static void main(String[] args) {
		TextIO.put("enter studentID (wi**b***): ");
		String studID = TextIO.getln();
		TextIO.putf("ID valid: %s\n", validate(studID));
		TextIO.putf("graduation in: %d\n", graduation(studID));
	}
	
	public static int graduation(String studID) {
		if (validate(studID) == true) {
			int year = 2000 + Integer.parseInt(studID.substring(2,4)) + 3;
			return year;
		} else {
			return -1;
		}
	}

	/**
	 * Validates a student ID.
	 * Returns true if it is valid and false if it is not.
	 */
	public static boolean validate(String studID) {
			if(studID.length() != 8){
				return false;
			}
			if(studID.matches("wi[0-9]{2}b[0-9]{3}") == true){
				return true;
			}
			else{
				return false;
			}
	}
}
