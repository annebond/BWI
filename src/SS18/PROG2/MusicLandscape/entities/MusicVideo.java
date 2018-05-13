package SS18.PROG2.MusicLandscape.entities;

public class MusicVideo extends Release {

	private Track track;
	
	@Override
	public int totalTime() {
		// TODO Auto-generated method stub
		if(track==null) {
			return 0;
		}
		return track.getDuration();
	}

	public Track getTrack() {
		return track;
	}
	
	
	public void setTrack(Track track) {
		this.track = track;
	}
	
	public String toString(){
		return super.toString()+"-(Video)";
	}
	
	
}
