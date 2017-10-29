package bwi.prog1B.WS1718.wi17b089_Bondova.ExerciseSheet02;
import bwi.prog.utils.TextIO;
public class LeapChecker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TextIO.putln("enter year:");
		int year = TextIO.getlnInt();
		boolean isLeap;
		
		if (year %4==0){
			isLeap = true;
			if (year %100 == 0){
				isLeap = false;
				if (year %400==0){
					isLeap = true;
				}
			}
		}else {
			isLeap = false;
		}
		
		TextIO.put(year + " is/was ");
		if (!isLeap) TextIO.put("not ");
		TextIO.putln ("a leap year");
	}

}
