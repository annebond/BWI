package SS18.PROG2_CUE01.Workout;


import WS1718.PROG1.prog.utils.TextIO;

public class Main {
	

	
	public static void main(String[] args) {

		BasicWorkout[] data = getDemoData();
		print(data);
		TextIO.putf("---\ntotal energy: %dkCal\n", totalEnergy(data));
		TextIO.putf("---\nmean intensity: %.1f\n", meanIntensity(data));

	}

	public static BasicWorkout[] getDemoData(){
		
		return new BasicWorkout[] {
				new BasicWorkout(new Date(2018, 04, 1), 8200, 7, 1200, "Bike trip to Krems"),
				new BasicWorkout(new Date(2018, 05, 8), 1200, 5, 500, "Digging a hole"),
				new Running(new Date(2018, 05, 7), 3600, 12000, "Evening run from work to home"),
				
		};
	}
	private static double meanIntensity(BasicWorkout[] data) {
		
		int intensitySum = 0;
		for (BasicWorkout workout : data) {
			intensitySum += workout.intensity;
		}
		return intensitySum/data.length;
	}
}
