package SS18.PROG2.MusicLandscape.entities;
import WS1718.PROG1.prog.utils.*;
import SS18.PROG2.MusicLandscape.util.*;
import java.util.Scanner;


public class Track implements ConsoleScanable {

	private int duration;
	private Artist performer;
	private String title;
	private Artist writer;
	private int year;
	
	public Track () {
		this.title = null;
		this.duration = 0;
		this.writer = new Artist();
		this.performer = new Artist();
		this.year = 1900;
	}
	
	public Track (String title) {
		this();
		this.title = title;
	}
	public Track (Track t) {
		this.duration = t.duration;
		this.performer = new Artist(t.performer);
		this.title = t.title;
		this.writer = new Artist(t.writer);
		this.year = t.year;
	}
	public int getDuration() {
		return duration;
	}

	public Artist getPerformer() {
		return performer;
	}
	
	public String getTitle() {
		if (title == null) {
			return "unknown title";
		} else {
			return title;
		}
	}
	
	public Artist getWriter() {
		return writer;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setDuration(int duration) {
		if (duration >= 0) {
			this.duration = duration;
		}
	}
	
	public void setPerformer(Artist performer) {
		if (performer != null) {
			this.performer = performer;
		}
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setWriter(Artist writer) {
		if (writer != null) {
			this.writer = writer;
		}
	}
	
	public void setYear(int year) {
		if (year >= 1900 && year < 3000) {
			this.year = year;
		}
	}
	
	public boolean writerIsKnown() {
		return getWriter() != null && getWriter().getName() != null;
	}
	
	public String getString() {
		String formString;
		String formMin, formSec;
		
		formMin = String.format("%02d", getDuration()/60);
		formSec = String.format("%02d", getDuration()%60);
		formString = formTitle(getTitle()) + " by " + formTitle(getWriter()) + " performed by " + formTitle(getPerformer()) + " (" + formMin + ":" + formSec + ")";
		return formString;
	}
	
	public String formTitle (String strForm) {
		if (strForm == null || strForm.isEmpty() || strForm.equals("unknown title")) {
			strForm = String.format("%.10s", "unknown");
		} else {
			strForm = String.format("%.10s", strForm);
		}
		if (strForm.length() > 10) {strForm = String.format("%.10s", strForm);}	
		if (strForm.length() < 10) {strForm = String.format("%1$10s", strForm);}
		
		return strForm;
	}
	
	public String formTitle(Artist artForm) {
		
		String strForm;
		
		if (artForm == null) {
			strForm = String.format("%.10s", "unknown");
		} else {
			strForm = artForm.getName();
		}
		return formTitle(strForm);
	}
	
	@Override
	public String toString () {	
		return this.getString();		
	}
	
	public boolean scan() {
		boolean fieldChanged = false, objectChanged = false;
		String input;
		// scanning title
		do {
		TextIO.putf("current title: %s\n", this.title);
		TextIO.putf("enter new title (leave empty to keep):");
		input = TextIO.getlnString();
		if (input.length() == 0) { // keep old value?
		fieldChanged = false;
		break;
		}
	/*
	if (!validateTitle(title)) {
	TextIO.putf("not a valid title (%s).\n", title);
	continue;
	}
	*/
		fieldChanged = true;	
		break;
	} while (true);
		if (fieldChanged) {
			setTitle(input);
	}
	
	objectChanged = objectChanged || fieldChanged;
	fieldChanged = false; // set up for next field
	// scan next field(s)
	return objectChanged;
	}
	
	
}
