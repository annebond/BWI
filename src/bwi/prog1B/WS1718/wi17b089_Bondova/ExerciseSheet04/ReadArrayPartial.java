package bwi.prog1B.WS1718.wi17b089_Bondova.ExerciseSheet04;
import bwi.prog.utils.TextIO;
public class ReadArrayPartial {

	public static void main(String[] args) {
		
		int[] numbArray = new int[10];
		int count = 1;
		int input;
		
		while (count <= 10) {
			TextIO.put(count + ". number: ");
			input = TextIO.getInt();
			
			if (input == 0) {
				break;
			} 
			
			if (input < 0){
				TextIO.putln("invalid input!");
			} else {
					numbArray[count - 1] = input;
					count++;
				}
			}
		
		TextIO.putln(count-1 + " number(s) entered.");
		
		if ((count-1) > 0) {
			for (int i=0;i<(count-1);i++) {
				TextIO.putf("[" + i + "]: %s\n", numbArray[i] );
			}
			TextIO.put("Output finished.");
		}
			
		}
	}
