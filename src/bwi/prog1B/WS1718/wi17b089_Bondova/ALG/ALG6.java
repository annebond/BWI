package bwi.prog1B.WS1718.wi17b089_Bondova.ALG;
import bwi.prog.utils.TextIO;

public class ALG6 {

	public static void main(String[] args) {
		
		int a = 0;
		int b = 0;
		TextIO.putln ("Erste Zahl:");
		a = TextIO.getInt();
		while (a<0) {
		TextIO.putln ("Bitte positive Zahlen eingeben");
		  TextIO.putln ("Erste Zahl:");
		  a = TextIO.getInt();
		  
		}
		TextIO.putln ("Zweite Zahl:");
		b = TextIO.getInt();
		while (b<0) {
		  TextIO.putln ("Bitte positive Zahlen eingeben");
		  TextIO.putln ("Zweite Zahl:");
		  b = TextIO.getInt();
		  
		}
		 TextIO.putln (ggT_rek(a,b) + " ist der größte gemeinsame Teiler (rekursiv berechnet)");
		 TextIO.putln (ggT_ite(a,b) + " ist der größte gemeinsame Teiler (iterativ berechnet)");
	}
	public static int ggT_rek (int a, int b) {
		if (a == b | b == 0)
		  return a;
		else if (a == 0) 
		  return b;
		else
		  return ggT_rek(b, a%b);
	}
	public static int ggT_ite (int a, int b) {
		int rest;
		do {
			rest = a % b;
			a = b;
			b = rest;
		} while (b != 0);
		return a;
	}
}

