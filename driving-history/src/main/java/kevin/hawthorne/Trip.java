package kevin.hawthorne;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Trip {

	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private double milesDriven;
	
	public Trip() {
	}
	
	public Trip(String start, String end, String miles) {
		this.startTime = LocalTime.parse(start);
		this.endTime = LocalTime.parse(end);
		this.milesDriven = Double.parseDouble(miles);
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public double getMilesDriven() {
		return milesDriven;
	}

	public void setMilesDriven(double milesDriven) {
		this.milesDriven = milesDriven;
	}
	
	public long totalDriveTimeInMinutes() {
		return startTime.until(endTime, ChronoUnit.MINUTES);
	}
	
	public double averageSpeed() {
		long driveTime = totalDriveTimeInMinutes();
		return (milesDriven / driveTime * 60);
	}
	
	public boolean isHighway() {
		if (averageSpeed() > 50.0) {
			return true;
		}
		return false;
	}
	
}
