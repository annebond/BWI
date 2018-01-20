package WS1718.PROG1.ES03;
import WS1718.PROG1.prog.utils.TextIO;
public class BMI {

	public static void main(String[] args) {
		double weight, height, bmi;
		
		TextIO.putf("weight [kg]: ");
		weight = TextIO.getlnDouble();
		
		TextIO.putf("height [m]: ");
		height = TextIO.getlnDouble();
		
		TextIO.putf("m=%.2fkg h=%.2fm -> BMI=%.2f (", 
				weight, height, bmi=bmiCalc(height, weight));
		bmiMessage(bmiCategory(bmi));
		TextIO.putf(")\n");
		}
	public static double bmiCalc (double height, double weight) {
		if (weight < 40 || height < 1.6 ) {
			double bmi = -1;
			return bmi;
		} else {
		double bmi = weight / (height * height);
		return bmi;
		}
	}
	public static int bmiCategory (double bmi) {
		if (bmi < 0) {
			int bmiCategory = -1;
			return bmiCategory; }
		else if (bmi >= 0 && bmi < 15.00) {
			int bmiCategory = 10;
			return bmiCategory; }
		else if (bmi >= 15.00 && bmi < 16.00) {
			int bmiCategory = 11;
			return bmiCategory; }
		else if (bmi >= 16.00 && bmi < 18.50) {
			int bmiCategory = 12;
			return bmiCategory; }
		else if (bmi >= 18.50 && bmi < 25.00) {
			int bmiCategory = 20;
			return bmiCategory; }
		else if (bmi >= 25.00 && bmi < 30.00) {
			int bmiCategory = 30;
			return bmiCategory; }
		else if (bmi >= 30.00 && bmi < 35.00) {
			int bmiCategory = 40;
			return bmiCategory; }
		else if (bmi >= 35.00 && bmi < 40.00) {
			int bmiCategory = 41;
			return bmiCategory; }
		else {
			int bmiCategory = 42;
			return bmiCategory; }
		}
	public static void bmiMessage (int bmiCategory) {
		if (bmiCategory == 10)
			TextIO.put("Very severely underweight");
		if (bmiCategory == 11)
			TextIO.put("Severely underweight");
		if (bmiCategory == 12)
			TextIO.put("Underweight");
		if (bmiCategory == 20)
			TextIO.put("Normal");
		if (bmiCategory == 30)
			TextIO.put("Overweight");
		if (bmiCategory == 40)
			TextIO.put("Moderately obese");
		if (bmiCategory == 41)
			TextIO.put("Severely obese");
		if (bmiCategory == 42)
			TextIO.put("Very severely obese");
		if (bmiCategory < 0)
			TextIO.put("invalid");
	}
}