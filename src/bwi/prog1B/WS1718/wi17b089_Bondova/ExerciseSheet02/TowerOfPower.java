package bwi.prog1B.WS1718.wi17b089_Bondova.ExerciseSheet02;
import bwi.prog.utils.TextIO;
public class TowerOfPower {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextIO.put("enter base:");
		int base = TextIO.getlnInt();
		TextIO.put("\n");
		
		int storedValue = 1;
		for (int numbersOfRuns = 1; numbersOfRuns <=7; numbersOfRuns++ ) {
			storedValue *= base;
			TextIO.putf("%8d\n", storedValue);
		}
	}

}
