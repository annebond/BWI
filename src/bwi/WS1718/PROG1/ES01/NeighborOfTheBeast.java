package bwi.WS1718.PROG1.ES01;
import bwi.prog.utils.TextIO;
public class NeighborOfTheBeast {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int formattedNumber = 667;
		TextIO.putf("the neighbor of the beast: %d!\n", formattedNumber); //display as decimal #
		TextIO.putf("the neighbor of the beast: %X!\n", formattedNumber); //display as hex #
		TextIO.putf("the neighbor of the beast: %10d!\n", formattedNumber); //display as decimal # 10char right aligned
		TextIO.putf("the neighbor of the beast: %-10d!\n", formattedNumber); //display as decimal # 10char left aligned
		TextIO.putf("the neighbor of the beast: %08d!\n", formattedNumber); //display as decimal # 8char with leading zeros
		TextIO.putf("the neighbor of the beast: +%-11d!\n", formattedNumber); //display as decimal # 12char left aligned + sign
	}

}
