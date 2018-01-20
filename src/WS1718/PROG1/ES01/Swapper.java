package WS1718.PROG1.ES01;
import WS1718.PROG1.prog.utils.TextIO;
public class Swapper {

	public static void main(String[] args) {

		int firstNumber, secondNumber, storeNumber;
		
		TextIO.put("enter first number (a): ");
		firstNumber = TextIO.getInt();
		TextIO.put("enter second number (b): ");
		secondNumber = TextIO.getInt();
		TextIO.putln("a=" + firstNumber + ", b=" + secondNumber);
		
		storeNumber = firstNumber;
		firstNumber = secondNumber;
		secondNumber = storeNumber;
		
		TextIO.putln("swapped:\na=" + firstNumber + ", b=" + secondNumber);

	}

}
