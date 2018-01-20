package WS1718.ALG;
import WS1718.PROG1.prog.utils.TextIO;

/* 
U6: Entwickeln Sie ein Programm, das zwei natürliche Zahlen einliest und den größten gemeinsamen
Teiler der zwei Zahlen auf der Konsole ausgibt. Die Berechnung sollte nach dem Euklidischen
Algorithmus erfolgen. Dieser Algorithmus ist rekursiv.

Definition of recursive:
Unter Rekursion versteht man die Definition eines Problems, einer Funktion oder eines
Verfahrens durch sich selbst.
 */

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
/*
 * Examples:
 * ggT(24,36) =* ggT(36,24 mod 36) = ggT(36,24) =* ggT(24,36 mod 24) = ggT(24,12) =*ggT(12,24 mod 12) = ggT(12,0) =* 12
 * ggT(17,19) =* ggT(19, 17 mod 19) = ggT(19,17) =* ggT(17,19 mod 17) = ggT(17,2) =* ggT(2, 17 mod 2) = ggT(2,1) =* ggT(1, 2 mod 1) 0 ggT(1,0) =* 1 
 */
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

