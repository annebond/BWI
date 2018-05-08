
package SS18.PROG2_CUE01.Workout;

public class BasicWorkout {
	

	private Date date;
	private String description;
	protected int duration;
	protected int energy;
	protected int intensity;
	
	
	
	/**
	 * Constructs a basic workout on a specified date and of a specified duration.
	 * 
	 * @param date
	 *            the date on which this workout started
	 * @param duration
	 *            the duration of this workout in s
	 * @param intensity
	 *            the intensity of this workout
	 * @param energy
	 *            the energy burned during this workout in kcal
	 * @param description
	 *            the description of this workout
	 */
	public BasicWorkout(Date date, int duration, int intensity, int energy, String description) {
		this(date, duration, intensity, energy);
		this.description = (description == null) ? null : String.format("%.100s", description);

	}
	

	
	public BasicWorkout(Date date,int duration) {
		this.date = date;
		this.duration = duration;
	}
	
	public BasicWorkout(Date date, int duration, int intensity) {
		this(date, duration);
		this.intensity = intensity;
	}
	
	public BasicWorkout(Date date, int duration, int intensity, int energy) {
		this(date, duration, intensity);
		this.energy = energy;
		
	}
	
	public BasicWorkout(BasicWorkout bw) {
		this(bw.date, bw.duration, bw.intensity,bw.energy, bw.description);
		
	}

	/**
	 * Creates a String representation of this workout.<br>
	 * 
	 * 
	 */
	@Override
	public String toString() {
		return String.format("%s: %s \"%s\" %.1fh, %dkcal ", date, getIntensityString(), getDescription(), 
				duration/3600., getEnergy());
	}


	public String getDescription() {
		if (description == null) {
			return "no description";
		}
		return description;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public int getIntensity() {
		return intensity;
	}
	public final String getIntensityString() {
		int count = this.getIntensity();
		String formString;
		
		formString = "[";
		for (int i = 0; i < count; i++) {
			formString += "*" ;
			
		}
		formString += "]";
		
		/*
		if (count == 1) {
			formStar = String.format("*");
		}
		if (count == 2) {
			formStar = String.format("**");
		}
		if (count == 3) {
			formStar = String.format("***");
		}
		if (count == 4) {
			formStar = String.format("****");
		}
		if (count == 5) {
			formStar = String.format("*****");
		}
		if (count == 6) {
			formStar = String.format("******");
		}
		if (count == 7) {
			formStar = String.format("*******");
		}
		if (count == 8) {
			formStar = String.format("********");
		}
		if (count == 9) {
			formStar = String.format("*********");
		}
		if (count == 10) {
			formStar = String.format("**********");
		}
		
		formString = String.format("[" + "%s" + "]", formStar); 
		*/
		return formString;
	}

}
