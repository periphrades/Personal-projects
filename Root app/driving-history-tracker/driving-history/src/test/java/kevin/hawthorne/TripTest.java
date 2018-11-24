package kevin.hawthorne;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TripTest {

	private Trip trip;
	
	@Before
	public void setup() {
		trip = new Trip();
		trip.setStartTime( LocalTime.parse("13:00") );
		trip.setEndTime( LocalTime.parse("14:30") );
		trip.setMilesDriven(78.6);
	}
	
	@Test
	public void verifyTotalDriveTime() {
		
		double totalMinutes = trip.totalDriveTimeInMinutes();
		Assert.assertEquals("Incorrect total miles", 90.0, totalMinutes, 0.01);
		
	}
	
	@Test
	public void verifyAverageSpeed() {
		
		double averageSpeed = trip.averageSpeed();
		Assert.assertEquals("Incorrect average speed", 52.4, averageSpeed, 0.01);
	}
	
}
