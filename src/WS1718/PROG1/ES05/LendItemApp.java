package WS1718.PROG1.ES05;
import WS1718.PROG1.prog.utils.TextIO;

public class LendItemApp {
		public static void main(String[] args) {
		
		LendItem it1 = new LendItem(), it2 = new LendItem();
		TextIO.putf("Welcome to LendApp\n");
		
		int mode=0, format = 1;
		int input = 0;
		do {
			TextIO.putf("enter opcode (9 for usage): ");
			input = TextIO.getlnInt();
			switch (input) {
			case 1:
				it1 = scanLendItem();
				break;
			case 2:
				it2 = scanLendItem();
				break;
			case 3:
				TextIO.putf("%s\n", lendItemString(it1, format));
				TextIO.putf("%s\n", lendItemString(it2, format));
			break;
			case 4:
				TextIO.putf("set compare mode ("
				+ "1=by lend date, "
				+ "2=by return date, "
				+ "3=by lender, "
				+ "4=by owner, "
				+ "all other=by description: ");
				mode = TextIO.getInt();
				break;
			case 5:
				int cmp=compare(it1, it2, mode);
				if(cmp<0)
					TextIO.putf("LendItem1 is before LendItem2.\n");
				else if(cmp>0)
					TextIO.putf("LendItem1 is after LendItem2.\n");
				else
					TextIO.putf("LendItems are equal.\n");
				break;
			case 6:
				TextIO.putf("LendItem1 and LendItem2 are %s equal.\n",
						LendItemFunctions.equals(it1, it2)?"":"not");
				break;
			case 9:
				TextIO.putf( "1 enter LendItem1\n"
						+ "2 enter LendItem2\n"
						+ "3 print LendItem1 and 2\n"
						+ "4 set compare mode\n"
						+ "5 compare the LendItems\n"
						+ "6 check equality\n"
						+ "9 display this message\n"
						+ "0 exit\n");
				break;
			case 0:
				break;
			default:
				TextIO.putln("invalid op code");
			}
		} while (input != 0);
		}
	}
