package bwi.prog1B.WS1718.wi17b019_Sevcik.ExerciseSheet04;

import java.util.ArrayList;

public class ArrayFunctions {

	public static void linearFill(double[] array, double start, double inc) {
		for (int i = 0; i < array.length; i++) {
			array[i] = start + (inc * i);
		}
	}

	public static boolean contains(int[][] array, int key) {
		for (int[] pos : array) {
			for (int i = 0; i < pos.length; i++) {
				if (pos[i] == key) {
					return true;
				}
			}
		}
		return false;
	}

	public static int countBelow(double[] arr, double a) {
		int cnt = 0;
		for (double element : arr)
			if (element < a)
				cnt++;
		return cnt;
	}

	public static int find(int[] arr, int key) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == key) {
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
		ArrayList<String> listFound = new ArrayList<>();
		listFound.clear();

		for (String str : arr) {
			if (str.matches(regex)) {
				listFound.add(str);
			}
		}

		return listFound.toArray(new String[listFound.size()]);
	}
}
