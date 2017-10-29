package bwi.prog1B.WS1718.wi17b089_Bondova.ExerciseSheet01;
import bwi.prog.utils.TextIO;
public class Swapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int firstNumber, secondNumber, storeNumber;
		
		TextIO.put("enter first number (a):");
		firstNumber = TextIO.getlnInt();
		TextIO.put("enter second number (b):");
		secondNumber = TextIO.getlnInt();
		TextIO.putln("a=" + firstNumber + ", b=" + secondNumber);
		
		storeNumber = firstNumber;
		firstNumber = secondNumber;
		secondNumber = storeNumber;
		
		TextIO.putln("swapped:\na=" + firstNumber + ", b=" + secondNumber);
	}

}
