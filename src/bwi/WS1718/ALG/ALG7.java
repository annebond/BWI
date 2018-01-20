package bwi.WS1718.ALG;
import bwi.prog.utils.TextIO;
/*
Auf drei Stäben werden Scheiben unterschiedlicher Größe gestapelt. Zu Beginn befindet sich der
gesamte Stapel auf dem rechten Stapel. Ziel ist, den gesamten Stapel auf den linken Stab zu
bringen, sodass wieder die Scheibengröße von unten nach oben abnimmt. Dabei gelten folgende
Regeln:
· Es darf immer nur eine Scheibe von einem Stab genommen und auf einen anderen Stab
gelegt werden.
· Eine Scheibe darf nur auf eine andere Scheibe gelegt werden, wenn die untere Scheibe
größer als die obere ist.
 */

public class ALG7 {
	static int count = 1;
	public static void main (String[] arg) {
		int n;
		TextIO.put ("Pls enter a number of disks: ");
		n = TextIO.getInt();
		while (n <= 0) {
			TextIO.putln("Pls enter a number > 0.");
			TextIO.put ("Pls enter a number of disks: ");
			n = TextIO.getInt();
		}
		move(n, "A", "B", "C");
		
	}
	public static void move (int n, String start, String temp, String end ) {
		if (n == 1) {
			TextIO.putln(count + ".  Move top disk from " + start + " to " + end);
			count++;
		} else {
			move(n-1, start, end, temp); //step 1; temp; n=2 temp = c, end = b; n=1 temp=b, end=c;
			TextIO.putln(count + ".  Move top disk from " + start + " to " + end); //step 2
			count++;
			move(n-1, temp, start, end); //step 3
		}
		
	}
}
