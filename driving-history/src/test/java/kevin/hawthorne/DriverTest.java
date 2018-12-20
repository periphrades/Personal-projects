package kevin.hawthorne;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DriverTest {

	private Driver driver1;
	private Driver driver2;
	
	@Before
	public void setup() {
		
		driver1 = new Driver("Alice");
		driver1.addTrip( new Trip("08:00", "08:20", "12.0") );
		driver1.addTrip( new Trip("10:00", "11:35", "72.8") );
		driver1.addTrip( new Trip("16:22", "17:10", "41.2") );
		
	}
	
	@Test
	public void verifyTotalMilesDiven() {
		
		double totalMiles = driver1.getTotalMilesDriven();
		Assert.assertEquals("Incorrect total miles", 126.0, totalMiles, 0.01);

	}
	
	@Test
	public void verifyTotalMinutesDriven() {
		
		int totalMinutes = driver1.getTotalMinutesDriven();
		Assert.assertEquals("Incorrect total minutes",  163, totalMinutes);
		
	}
	
	@Test
	public void verifyAverageSpeed() {
		
		double averageSpeed = driver1.getAverageSpeed();
		Assert.assertEquals("Incorrect average speed",  46.38, averageSpeed, 0.01);
		
	}
	
	@Test
	public void verifySummary() {
		
		Driver driver3 = new Driver("Gamma");
		
		driver3.addTrip( new Trip("09:00", "10:00", "50.1") );
		driver3.addTrip( new Trip("09:00", "10:00", "49.9") );
		driver3.addTrip( new Trip("09:00", "10:00", "4.9") );
		driver3.addTrip( new Trip("09:00", "10:00", "100.1") );
		
		String str = "Gamma: 100 miles @ 50 mph, 50.0% highway miles, 2 invalid trips";
		String summary = driver3.summary();
		Assert.assertEquals(str, summary);
		
	}
	
	@Test
	public void verifyDriverMileageComparison() {
		
		driver2 = new Driver("Josh");
		driver2.addTrip( new Trip("09:30", "11:50", "110.0") );
		
		int comparison = driver1.compareTo(driver2);
		Assert.assertEquals("Wrong mileage comparison driver1 > driver2", -1, comparison);
		
		driver2.addTrip( new Trip("20:14", "20:37", "16.0") );
		comparison = driver1.compareTo(driver2);
		Assert.assertEquals("Wrong mileage comparison driver1 = driver2", 0, comparison);
		
		driver2.addTrip( new Trip("15:00", "15:38", "25.7") );
		comparison = driver1.compareTo(driver2);
		Assert.assertEquals("Wrong mileage comparison driver1 < driver2", 1, comparison);
	}
	
	
	@Test
	public void verifyHwyMilesDriven() {
		
		Driver driver3 = new Driver("Gamma");
		
		driver3.addTrip( new Trip("09:00", "10:00", "50.1") );
		driver3.addTrip( new Trip("09:00", "10:00", "49.9") );
		
		double hwyMiles = driver3.hwyMileDriver();
		
		Assert.assertEquals(50.1, hwyMiles, 0.01);
		
	}
	
	
	
}
