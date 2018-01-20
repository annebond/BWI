package WS1718.PROG1.ES02;
import WS1718.PROG1.prog.utils.TextIO;
public class NeatMultTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextIO.put("enter rows: ");
		byte answerRows = TextIO.getByte();
		
		TextIO.put("enter columns: ");
		byte answerColumns = TextIO.getlnByte();
		TextIO.putln();
		
		// loop for the columns numbers-header
		int startHeader = 1;
		int stopHeader = answerColumns;
		int stepHeader = 1;
		TextIO.put("    ");
		
		while (startHeader <= stopHeader){
			TextIO.putf("%3d", startHeader);
			startHeader += stepHeader;
		}
		// loop for the columns char-header
		TextIO.put("\n----");
		startHeader = 1;
		stopHeader = answerColumns;
		while (startHeader <= stopHeader){
			TextIO.put("---");
			startHeader++;
		}
		int rowNumber = 1;
		int columnNumber = 1;
		while(rowNumber <= answerRows) { //row-loop
			TextIO.putln(); //start new row
			TextIO.put(" " + rowNumber + " |");
			columnNumber = 1; //(re)set row number
			
			while(columnNumber <= answerColumns) { //column loop
				TextIO.putf("%3d",rowNumber*columnNumber);
				columnNumber++; //increase column number
		}
			rowNumber++; //increase row number
		}
	}

}
