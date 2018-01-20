package bwi.WS1718.PROG1.ES04;

import bwi.prog.utils.TextIO;

public class ArrayMeanStatistics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int count = 0;
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
			
			numbArray[count] = input;
			sum = sum + input;
			count++;			
		}
		
		mean = (float) sum / count;
		
		if (count > 0) {
			TextIO.putln("Input finished.");
			TextIO.putf("total mean: %.2f\n----------\n", mean);
			TextIO.putf("          count: %d\n", greaterMean(numbArray, mean, count));
			if (greaterMean(numbArray, mean, count) > 0) {
				TextIO.putf("index positions: [%s]\n", positionsGreaterMean(numbArray, count, mean));
				TextIO.putf("            sum: %s\n", sumMean(numbArray, mean));
				TextIO.putf("           mean: %.2f\n", (float)sumMean(numbArray, mean)/(float)greaterMean(numbArray, mean, count));
				TextIO.putf("            min: %s [%s]\n", minMean (numbArray, mean, count), positionMin(numbArray, count, minMean (numbArray, mean, count)));
			}
		} else {
			TextIO.putln("no values entered.");
		}
	}

	public static int greaterMean (int[] numbArray, float mean, int count) {
		int countMean = 0;
		for (int i = 0; i < count; i++) {
			if (numbArray[i] > mean) {
				countMean++;
			}
		}
		return countMean;
	}
	public static int sumMean (int[] numbArray, float mean) {
		int sumMean = 0;
		for (int element: numbArray) {
			if (element > mean) {
				sumMean += element;
			}
		}
		return sumMean;
	}
	public static String positionsGreaterMean(int[] numbArray, int count, float mean) {
		String output = "";
		
		for (int i = 0; i < count; i++) {
			if (numbArray[i] > mean) {
				output = output + " " + i;
			}
		}
		return output;
	}
	public static int minMean (int[] numbArray, float mean, int count) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < count; i++) {
			if (numbArray[i] > mean) {
				if (numbArray[i] < min) {
					min = numbArray[i];
				}
			}
			
		}
		return min;
	}
	public static String positionMin(int[] numbArray, int count, int min) {
		String output = "";
		
		for (int i = 0; i < count; i++) {
			if (numbArray[i] == min) {
				output = output + " " + i;
			}
		}
		return output;
	}

}
