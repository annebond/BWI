package SS18.PROG2.MusicLandscape.util.io;

import java.io.BufferedReader;
import java.io.IOException;

import SS18.PROG2.MusicLandscape.entities.*;

public class MyTrackCSVReader extends MyReader<Track> {
	
	private static final int TITLE = 0;
	private static final int WRITER = 1;
	private static final int PERFORMER = 2;
	private static final int DURATION = 3;
	private static final int YEAR = 4;
	
	
	public MyTrackCSVReader(BufferedReader in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Track get() {
		Track out = new Track();
		String line = "";
		try {
			
			line = in.readLine();
			if (line == null) {
				throw new IOException("line == null");
			}
			
			String[] lineSplitArray = line.split(",");
			if (lineSplitArray.length < 5) {
				throw new Exception ("not all fields provided");
				
			}
			
			
			out.setWriter (new Artist(lineSplitArray[WRITER].trim()));
			out.setPerformer(new Artist(lineSplitArray[PERFORMER].trim()));
			out.setDuration(Integer.parseInt(lineSplitArray[DURATION].trim()));
			out.setYear(Integer.parseInt(lineSplitArray[YEAR].trim()));
			out.setTitle((lineSplitArray[TITLE].trim()));
			System.out.println(out);
			return out;
			
			
		}
		catch (IOException e){
			System.out.println("Error reading.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(line + "Error parsing.");
			e.printStackTrace();
		}
		return null;
	}

}
