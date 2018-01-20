package bwi.WS1718.PROG1.ES02;
import bwi.prog.utils.TextIO;
public class LoopStatistics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int counter = 0;
		float insertedNumber, minNumber = Float.MAX_VALUE, maxNumber = 0, sumValues = 0;

		TextIO.putln("enter numbers:");
	
		do {
			insertedNumber = TextIO.getFloat();
			if (insertedNumber > 0) {
				counter++;
				sumValues += insertedNumber;
				if (insertedNumber < minNumber)
					minNumber = insertedNumber;
				if (insertedNumber > maxNumber)
					maxNumber = insertedNumber;
			}
		} while (insertedNumber > 0);
		if (counter == 0)
			TextIO.putln("no number entered.");
		else {
			TextIO.putln("numbers entered: " + counter);
			TextIO.putf("minimum: %.2f\n", minNumber);
			TextIO.putf("maximum: %.2f\n", maxNumber);
//			TextIO.putf("sum: %.2f\n", sumValues);
			TextIO.putf("mean: %7.2f\n", sumValues / counter);
		}
	}

}
