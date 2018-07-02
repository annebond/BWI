package SS18.PROG2.MusicLandscape.container;

import java.util.*;

import SS18.PROG2.MusicLandscape.entities.Track;
import SS18.PROG2.MusicLandscape.util.MyMatcher;

public class MyTrackContainer extends Object {
	
	private java.util.Set<Track> tracks;
	private java.util.List<Track> selection;
	
	public MyTrackContainer() {
		this.selection = new ArrayList <Track>();
		this.tracks = new HashSet <Track>();
	}
	
	public MyTrackContainer(Iterable<Track> t) {
		
		this();
		Iterator<Track> li = t.iterator();
		while (li.hasNext()) { 
			this.selection.add(li.next());
		}
		tracks.addAll(this.selection);
	}
	
	public MyTrackContainer(Track[] t) {
		this();
		for (int i=0; i<t.length; i++) {
			this.selection.add(t[i]);
		}
		tracks.addAll(this.selection);
	}
	
	public void sort(java.util.Comparator<Track> theComp, boolean asc) {
		this.selection.sort(theComp);
		if (asc == false) {
			Collections.reverse(this.selection);
		}
	}
	
	public int filter(MyMatcher<Track> matcher) {
		int counter = 0;
		Iterator<Track> li = this.selection.iterator();
		while (li.hasNext()) { 
			if (matcher.matches(li.next()) == false) {
				li.remove();
				counter ++;
			}
		}
		return counter;
	}
	
	public void reset() {
		selection.clear();
		selection.addAll(tracks);
	}
	
	public int remove() {
		int count = selection.size();
		this.tracks.removeAll(selection);
		reset();
		return count;
	}
	
	public int addAll(Track[] t) {
		Set<Track> mySet = new HashSet<Track>(Arrays.asList(t));
		this.tracks.addAll(mySet);
		return mySet.size(); 
		
		/* variant B:
		int counter=0;
		for(int i=0; i<t.length; i++) {
			if(add(t[i])==true) {
				counter++;
			}
		}
		return counter;
		 */
	}
	
	public int size() {
		return this.tracks.size();
	}
	
	public Track[] selection() {
		//selection cannot be null, due to constructor.it can be of size 0
		Track[] temp = new Track[selection.size()];
		return selection.toArray(temp);
	}
	
	public boolean add(Track t) {
		if(t == null || this.tracks.contains(t)) {
			return false;
		} else {
			this.tracks.add(t);
			return true;
		}
	}
	
	
	
	
	
}
