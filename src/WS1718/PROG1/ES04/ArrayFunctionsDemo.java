package WS1718.PROG1.ES04;

import WS1718.PROG1.prog.utils.TextIO;

public class ArrayFunctionsDemo {

	private static final int LINEAR_FILL = 1;
	private static final int CONTAINS = 2;
	private static final int COUNT_BELOW = 3;
	private static final int FIND_1 = 4;
	private static final int FIND_2 = 5;
	private static final int FILTER = 6;


	public static void main(String[] args) {
		go();

	}

	public static void go() {
		
		double[] dArr = {1,4,23.3,12.3,0.5,432.53,-123.34,34.12};
		int[][] iArr2 = { { 0 }, { 10, 11 }, { 20, 21, 22 },
				{ 30, 31, 32, 33, 34, 35, 36, 37, 38, 39 },
				{ 5, 8, 58, 85, 5, 8, 8, 5, 8, 5 },{900,800,700,600,500,400,300,200,100} };
		String[] strings = null;

		while (true) {

			TextIO.putln("choose method to test \n(LINEAR_FILL = 1, "
					+ "CONTAINS = 2, 	COUNT_BELOW = 3, "
					+ "FIND_1 = 4, FIND_2 = 5, FILTER = 6)");
			int choice = TextIO.getInt();
			int n;
			switch (choice) {
			case LINEAR_FILL:
				TextIO.putln("LinearFill");
				TextIO.putln("enter array length, start value and inc value:");
				ArrayFunctions.linearFill(dArr = new double[TextIO.getInt()],
						TextIO.getDouble(), TextIO.getDouble());
				display(dArr);
				continue;

			case CONTAINS:
				TextIO.putln("enter value to look for:");
				TextIO.putln("contains: "
						+ ArrayFunctions.contains(iArr2, n = TextIO.getInt())
						+ " (" + n + ")");
				continue;

			case COUNT_BELOW:
				TextIO.putln("CountBelow");
				TextIO.putln("enter boundary:");
				TextIO.putf("%d",
						ArrayFunctions.countBelow(dArr, TextIO.getDouble()));

				continue;

			case FIND_1:
				TextIO.putln("enter value to find:");
				TextIO.putln("find 1: "
						+ ArrayFunctions.find(iArr2[3], n = TextIO.getInt())
						+ " (" + n + ")");
				continue;

			case FIND_2:
				TextIO.putln("enter value to find and starting index:");
				TextIO.putln("find 2: "
						+ ArrayFunctions.find(iArr2[4], n = TextIO.getInt(),
								TextIO.getInt()) + " (" + n + ")");
				continue;

			case FILTER:
				TextIO.putln("enter size of String array, and strings for each position:");
				TextIO.putln("Filter");
				strings = new String[TextIO.getInt()];
				for (int i = 0; i < strings.length; i++) {
					String it;
					TextIO.put(": ");
					while ((it = TextIO.getln()).equals(""))
						;
					strings[i] = it;
				}
				TextIO.put("enter regex: ");
				String regex;
				while ((regex = TextIO.getln()).equals(""))
					;
				display(strings);
				display(ArrayFunctions.filter(strings, regex));

				continue;

			}
			break;
		}

	}


	public static void display(double[] d) {
		TextIO.put("[");
		for (double i : d)
			TextIO.putf("%.2f ", i);
		TextIO.putln("]");
	}

	public static void display(int[] d) {
		TextIO.put("[");
		for (int i : d)
			TextIO.putf("%3d ", i);
		TextIO.putln("]");
	}

	public static void display(String[] d) {
		TextIO.putln("[");
		for (String s : d)
			TextIO.putf("%s ", s);
		TextIO.putln("]");

	}

}
