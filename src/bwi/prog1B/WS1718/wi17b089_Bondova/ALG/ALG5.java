package bwi.prog1B.WS1718.wi17b089_Bondova.ALG;
import bwi.prog.utils.TextIO;
/*
 U5: Faktorielle
 Schreiben Sie ein Programm zur rekursiven Berechnung der Faktorielle einer eingegebenen Zahl.

Hilfe:
Die Fakultätsfunktion ist mathematisch wie folgt definiert:
n! = 1*2*3*…*(n-1)*n
 */
public class ALG5 {

	public static void main (String[] arg) {
		int n;
		TextIO.put("Bitte eine Zahl eingeben: ");
		n = TextIO.getInt();
		TextIO.putln("REKURSIV: Fakultät von " + n + " aka " + n + "! = " +  fact_rec(n));
		TextIO.put("ITERATIV: Fakultät von " + n + " aka " + n + "! = " +  fact_ite(n));
		
	}
	public static int fact_rec (int n) {
		if (n == 1) {
			return 1;	// nicht rekursiver Teil
		} else {
			return fact_rec(n-1)*n; 	// rekursiver Teil
		}
	}
	public static int fact_ite (int n) {
		int result = 1;
		for (int i = 2; i <= n; ++i)
			result *= i;
		return result;
/*
Example iterativ: n == 4
result = result x i vom aktuellen Schleifendurchlauf
result 1 x i 2 -> result neu 2
result 2 x i 3 -> result neu 6
result 6 x i 4 -> result neu 24
i 5 erfüllt for Schleife nicht mehr => result von n! = 24
 */
	}
}
