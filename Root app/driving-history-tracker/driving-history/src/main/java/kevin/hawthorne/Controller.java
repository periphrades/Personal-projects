package kevin.hawthorne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Controller {

	private CommandReaderDAO reader;
	private CommandProcessor processor;
	
	public Controller(CommandReaderDAO reader) {
		this.reader = reader;
		this.processor = new CommandProcessor();
	}
	
	public void run() {
		
		List<String> allCommands = reader.getCommands();
		
		Map<String, Driver> allDrivers = processor.processCommand(allCommands);
		
		List<Driver> sortedAllDrivers = sortDrivers(allDrivers);
		
		for (Driver driver : sortedAllDrivers) {
			System.out.println(driver.summary());
		}
		
	}

	private List<Driver> sortDrivers(Map<String, Driver> allDrivers) {
		
		List<Driver> driversByMilage = new ArrayList<Driver>();
		
		for (String key : allDrivers.keySet()) {
			driversByMilage.add(allDrivers.get(key));
		}
		
		Collections.sort(driversByMilage);
		
		return driversByMilage;
	}
}
