package bwi.WS1718.PROG1.ES04;
import bwi.prog.utils.TextIO;
public class ReadArray {

	public static void main(String[] args) {
		
		int[] numb = new int[10];
		for (int i = 0;i<numb.length;i++) {
			TextIO.put(i+1 + ". number: ");
			numb[i] = TextIO.getInt(); 
		}
		
		TextIO.putln("Input finished.");
		
		for (int i=numb.length-1;i>=0;i--) {
			TextIO.putf("[" + i + "]: %s\n", numb[i] );
		}
		
		TextIO.put("Output finished.");
	}

}
