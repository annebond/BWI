package SS18.PROG2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import SS18.PROG2.MusicLandscape.container.MyTrackContainer;
import SS18.PROG2.MusicLandscape.entities.*;
import SS18.PROG2.MusicLandscape.util.MyFormatter;
import SS18.PROG2.MusicLandscape.util.formatters.*;

public class DemoApp {
	
	public static void main(String[] args) throws IOException {
		Concert testConcert = new Concert();
		testConcert.addTrack(new Track("track1"));
		testConcert.addTrack(new Track("track2"));
		testConcert.addTrack(new Track("track3"));
		Artist testArtist = new Artist("MaxArtist");
		TVShow testTVShow = new TVShow();
		testTVShow.setName("FriendsShow");
		
		Event[] events = {new Event(), testConcert, testTVShow};
		
		for (Event e : events) {
			e.setArtist(testArtist);
			e.setDescription("same description for all events");
			System.out.println(e);
			System.out.println();
		}
		
		
		MyTrackContainer obj=new MyTrackContainer();
		
		CSVTrackFormatter formatCsv = new CSVTrackFormatter();
		ShortTrackFormatter formatShort = new ShortTrackFormatter();
		
		Track t1= new Track();
		Track t2= new Track();
		Track t3= new Track();
		
		t1.setTitle("Titel-Track1");
		t1.setPerformer(new Artist("Performer Name1"));
		t1.setWriter(new Artist("Writer Name1"));
		t1.setYear(2001);
		
		t2.setTitle("Titel-Track2");
		t2.setPerformer(new Artist("Performer Name2"));
		t2.setWriter(new Artist("Writer Name2"));
		t2.setYear(2002);
		
		
		
		obj.add(t1);
		printSelect(obj, formatCsv);
		
		obj.remove();
		printSelect(obj, formatCsv);
		
/*		obj.sort(theComp, asc);
		printSelect(obj, formatCsv);
		
		obj.filter(matcher);
		printSelect(obj, formatCsv);
*/		
		obj.size();
		printSelect(obj, formatCsv);
	
		obj.selection();
		printSelect(obj, formatCsv);

		
		
		obj.add(t2);
		printSelect(obj, formatShort);
		
		obj.remove();
		printSelect(obj, formatShort);
		
/*		obj.sort(theComp, asc);
		printSelect(obj, formatShort);
		
		obj.filter(matcher);
		printSelect(obj, formatShort);
*/		
		obj.size();
		printSelect(obj, formatShort);
	
		obj.selection();
		printSelect(obj, formatShort);
		
		try {
			FileWriter fw = new FileWriter("testfiles.txt",true);
			Writer out = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(out);
			
			String t = "Text which will be added to file. ";
			out.write(t);
			pw.println();
			pw.println("First line");
			pw.println("Second line");
			out.close();
			pw.close();
			
		} catch (IOException e) {
			
		}

	}
	
	private static void printSelect(MyTrackContainer obj, MyFormatter<Track> formater){
		Track[] temp = obj.selection();
		for(int i = 0; i < temp.length; i++){
			System.out.println(formater.format(temp[i]));
		}
	}	
}
