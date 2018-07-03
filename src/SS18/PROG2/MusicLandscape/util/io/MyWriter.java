package SS18.PROG2.MusicLandscape.util.io;

import java.io.IOException;
import SS18.PROG2.MusicLandscape.util.MyFormatter;

public class MyWriter<T> {

	protected java.io.FileWriter out;
	private MyFormatter<T> theFormat;
	
	public MyWriter(java.io.FileWriter file,MyFormatter<T> theFormat) {
		if (file == null) {
			throw new IllegalArgumentException("expected non-null FileWriter");
		}
		
		if (theFormat == null) {
			throw new IllegalArgumentException("expected non-null MyFormatter");
		}
		
		this.out = file;
		this.theFormat = theFormat;
	}
	
	public final boolean put(T t) {
		try {
			String str = theFormat.format(t);
			out.write(str + "\n");
			return true;
		}
		catch (IOException e) {
			return false;
		}	
	}
	
	public void close() {
		try {
			out.close();
		} catch (IOException e) {
		}
	}
	
}
