package WS1718.PROG1.ES01;
import WS1718.PROG1.prog.utils.TextIO;
public class TimeCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long answer, miliseconds, seconds, minutes, hours, days, leftOverDays, leftOverHours, leftOverMinutes;
		
		TextIO.put("Welcome to the TimeCalculator!\nplease enter a duration in milliseconds:");
		answer = TextIO.getlnLong();
		
		days = answer / (1000 * 60 * 60 * 24);
			leftOverDays = answer % (1000 * 60 * 60 * 24);
		hours = leftOverDays / (1000 * 60 * 60); 
			leftOverHours = (leftOverDays) % (1000 * 60 * 60);
		minutes = leftOverHours / (1000 * 60);
			leftOverMinutes = leftOverHours % (1000 * 60);
		seconds = leftOverMinutes / 1000;
			miliseconds = leftOverMinutes % 1000;
		
//		TextIO.putln(answer+ " ms are exactly:\n"
//				+ "   days " + days + "\n"
//				+ "  hours " + hours + "\n"
//				+ "minutes " + minutes + "\n"
//				+ "seconds " + seconds + "\n"
//				+ "     ms " + miliseconds);
		TextIO.putf("%s ms are exactly:\n   days %s\n  hours %s\nminutes %s\nseconds %s\n     ms %s\n", answer, days, hours, minutes, seconds, miliseconds);
	}

}
