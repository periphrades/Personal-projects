package kevin.hawthorne;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandProcessorTest {

	private CommandProcessor processor;
	private List<String> commands;
	
	@Before
	public void setup() {
		
		processor = new CommandProcessor();
		commands = new ArrayList<String>();
		commands.add("Driver Alpha");
		commands.add("Driver Beta");
		
	}
	
	@Test
	public void verifyNewDrivers() {
		
		Map<String, Driver> allDrivers = processor.processCommand(commands);
		Assert.assertEquals("Incorrect number of drivers", 2, allDrivers.size());
		
	}
	
	@Test
	public void verifyNewTrips() {
		
		commands.add("Trip Alpha 06:25 07:15 41.7");
		commands.add("Trip Alpha 13:00 13:17 6.5");
		
		Map<String, Driver> allDrivers = processor.processCommand(commands);
		Driver alpha = allDrivers.get("Alpha");
		
		Assert.assertEquals("Incorrect number of trips", 2, alpha.getTrips().size());
		
	}
	
	@Test
	public void verifyIgnoresInvalidCommands() {
		
		commands.add("");
		commands.add("Invalid command");
		
		Map<String, Driver> allDrivers = processor.processCommand(commands);
		
		Assert.assertEquals("Incorrect number of drivers with invalid commands", 2, allDrivers.size());

		Driver alpha = allDrivers.get("Alpha");
		Driver beta = allDrivers.get("Beta");
		Assert.assertEquals("Incorrect number of trips with invalid commands", 0, alpha.getTrips().size());
		Assert.assertEquals("Incorrect number of trips with invalid commands", 0, beta.getTrips().size());
			
	}
}
