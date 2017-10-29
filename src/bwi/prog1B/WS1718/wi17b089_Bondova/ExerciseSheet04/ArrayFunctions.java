package bwi.prog1B.WS1718.wi17b089_Bondova.ExerciseSheet04;
import bwi.prog.utils.TextIO;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
public class ArrayFunctions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static void linearFill(double[] array, double start, double inc) {
		for (int i = 0; i < array.length; i++) {
			array [i] = start + (inc + 1);
		}
	}

	public static boolean contains(int[][] array, int key) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array [i][j] == key) {
					return true;
				}
			}
		}
		return false;
	}
	public static int countBelow(double[] arr, double a) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < a) {
				count ++;
			}
		}
		return count;
	}
	
	public static int find(int[] arr, int key) {
		for (int i = 0; i < arr.length; i++) {
			if (arr [i] == key) {
				return i;
			}
		}
		return -1;
	}
	public static int find(int[] arr, int key, int startIdx) {
		if (startIdx < 0) {
			startIdx = 0;
		}

		for (int i = startIdx; i < arr.length; i++) {
			if (arr[i] == key) {
				return i;
			}
		}

		return -1;
	}
	public static String[] filter(String[] arr, String regex) {
		List<String> allMatches = new ArrayList<String> ();
		allMatches.clear();
		
		for (int i = 0; i < arr.length; i++) {
			Matcher m = Pattern.compile("regex")
				     .matcher(arr[i]);
				 while (m.find()) {
				   allMatches.add(m.group());
				 }	
		}
		return allMatches.toArray(new String[allMatches.size()]);
	}
}
	
