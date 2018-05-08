package SS18.PROG2_CUE01.Workout;

public class Running extends BasicWorkout {
	private int	distance;
	
	//String.format("%.100s", description

	public Running(Date date, int duration, int distance, String description) {
		super(date, duration, 0, 0, description);
	}
	
	public int getEnergy() {
		int ener;
		ener = super.getEnergy()*100/60;
		
		return ener;
	}
	
	@Override
	public int getIntensity() {
		double distanceKm = distance/1000;
		double durationMinutes = super.duration/60;
		int intensity = (int)(13-(durationMinutes/distanceKm));
		
		if (intensity > 10) {
			intensity = 10;
		}
		if (intensity < 1) {
			intensity = 1;
		}
		
		return intensity;
		
	}
	
	public String toString() {
		return super.toString() + String.format("%sm", distance);
	}
}
