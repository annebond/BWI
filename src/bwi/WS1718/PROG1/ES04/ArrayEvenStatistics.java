package bwi.WS1718.PROG1.ES04;

import bwi.prog.utils.TextIO;
public class ArrayEvenStatistics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int count = 0;
		int countEven = 0;
		int sum = 0;
		float mean = 0f;
		int input;
		int[] numbArray = new int[1024];
		
		TextIO.putln("Input");
		
		while (count < 1024) {
			TextIO.put(": ");
			input = TextIO.getInt();
			
			if (input == 0) {
				TextIO.put("re-enter 0 to store as value.\nany other value ends input: ");
				input = TextIO.getInt();
				if (input == 0) {
					TextIO.put("value 0 stored.\n");
				} else
					break;
			}
			
			if (input % 2 == 0) {
				countEven++;
				sum = sum + input;
			}
			numbArray[count] = input;
			count++;			
		}
		
		mean = (float) sum / countEven;
		
		if (count > 0) {
			TextIO.putln("Input finished.");
			TextIO.putf("          count: %s\n", countEven);
			if (countEven > 0) {
				TextIO.putf("index positions: [%s]\n", positionEvenNumbers (numbArray, count));
				TextIO.putf("            sum: %s\n", sum);
				TextIO.putf("           mean: %.2f\n", mean);
				TextIO.putf("            max: %s [%s]\n", maxEvenNumber (numbArray), positionMaxEvenNumber(numbArray, count, maxEvenNumber (numbArray)));
			}
		} else {
			TextIO.putln("no values entered.");	
		}
	}

	public static String positionEvenNumbers(int[] numbArray, int count) {
		String output = "";
		
		for (int i = 0; i < count; i++) {
			if (numbArray[i] % 2 == 0) {
				output = output + " " + i;
			}
		}
		return output;
	}
	public static int maxEvenNumber (int[] numbArray) {
		int max = Integer.MIN_VALUE;
		for (int element: numbArray) {
			if (element % 2 == 0) {
				if (element > max) {
					max = element;
				}
			}
		}
		return max;
	}
	public static String positionMaxEvenNumber(int[] numbArray, int count, int max) {
		String output = "";
		
		for (int i = 0; i < count; i++) {
			if (numbArray[i] == max) {
				output = output + " " + i;
			}
		}
		return output;
	}
}
