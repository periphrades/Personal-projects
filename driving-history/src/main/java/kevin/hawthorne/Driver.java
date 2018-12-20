package kevin.hawthorne;

import java.util.ArrayList;
import java.util.List;

public class Driver implements Comparable<Driver> {

	private String name;
	
	private List<Trip> trips;
	
	private int invalidTrips;
	
	public Driver(String name) {
		this.name = name;
		trips = new ArrayList<Trip>();
		setInvalidTrips(0);
	}

	public List<Trip> getTrips() {
		return trips;
	}
	
	public void addTrip(Trip trip) {
		trips.add(trip);
	}
	
	public double getTotalMilesDriven() {
		double totalMiles = 0.0;
		for (Trip trip : trips) {
			totalMiles += trip.getMilesDriven();
		}
		return totalMiles;
	}
	
	public int getTotalMinutesDriven() {
		int totalMinutes = 0;
		for (Trip trip : trips) {
			totalMinutes += trip.totalDriveTimeInMinutes();
		}
		return totalMinutes;
	}
	
	public double getAverageSpeed() {
		int totalMinutes = getTotalMinutesDriven();
		double totalMiles = getTotalMilesDriven();
		if (totalMinutes == 0) {
			return 0;
		}
		return (totalMiles / totalMinutes * 60);
		
	}
	
	public double hwyMileDriver() {
		
		double hwyMiles = 0.0;
		
		for (Trip trip : trips) {
			if (trip.isHighway()) {
				hwyMiles += trip.getMilesDriven();
			}
		}
		
		return hwyMiles;
	}

	public String summary() {
		long totalMiles = Math.round(getTotalMilesDriven());
		long averageSpeed = Math.round(getAverageSpeed());
		long hwyMiles = Math.round(hwyMileDriver());
		
		double percentHwyMiles = hwyMiles / totalMiles *100;
		
		int invalidTrips = getInvalidTrips();
		
		if (totalMiles == 0) {
			return name + ": " + totalMiles + " miles";
		}
		
		String str = name + ": " + totalMiles + " miles @ " + averageSpeed + " mph, " + percentHwyMiles + "% highway miles, " + invalidTrips + " invalid trips";
		return str;
		
	}
	
	public void incrementInvalidTrips() {
		this.setInvalidTrips(this.getInvalidTrips() + 1);
	}
	
	

	@Override
	public int compareTo(Driver otherDriver) {
		if (this.getTotalMilesDriven() > otherDriver.getTotalMilesDriven()) {
			return -1;
		} else if (this.getTotalMilesDriven() < otherDriver.getTotalMilesDriven()) {
			return 1;
		}
		return 0;
	}

	public int getInvalidTrips() {
		return invalidTrips;
	}

	public void setInvalidTrips(int invalidTrips) {
		this.invalidTrips = invalidTrips;
	}
	
}
