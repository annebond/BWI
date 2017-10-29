package bwi.prog1B.WS1718.wi17b089_Bondova.ExerciseSheet02;
import bwi.prog.utils.TextIO;
public class Classifier {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double price, quality;
		
		TextIO.put("enter price: ");
		price = TextIO.getDouble();
		
		TextIO.put("enter quality: ");
		quality = TextIO.getDouble();
		//TextIO.putln((int)price + " " + (int)quality);
		
		// check an print low/high price
		if (price < 50)
				TextIO.putln("low price");
		else
			TextIO.putln("high price");
		
		// check an print low/high quality
		if (quality < 50)
			TextIO.putln("low quality");
		else
			TextIO.putln("high quality");

		// check and print classification
		if (quality < 50){
			if (price < 50)
				TextIO.putln("junk");
			else
				TextIO.putln("rip off");
		} else {
			if (price < 50)
				TextIO.putln("bargain");
			else
				TextIO.putln("luxury");
		}
		
	}

}
