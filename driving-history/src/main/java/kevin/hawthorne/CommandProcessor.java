package kevin.hawthorne;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandProcessor {
	
	public Map<String, Driver> processCommand(List<String> allCommands) {
		
		Map<String, Driver> allDrivers = new HashMap<String, Driver>();
		
		for ( String command : allCommands ) {
		
			String[] commandArr = command.split(" ");
			
			if ( commandArr[0].equals("Driver") ) {
				
				Driver driver = new Driver(commandArr[1]);
				allDrivers.put(commandArr[1], driver);
				
			} else if (commandArr[0].equals("Trip") ) {
				
				Trip trip = new Trip(commandArr[2], commandArr[3], commandArr[4]);
				
				// record trip if average speed is greater than 5mph and less than 100mph
				if (trip.averageSpeed() >= 5.0 && trip.averageSpeed() <= 100.0 && 
						allDrivers.containsKey(commandArr[1]) ) {
					
					allDrivers.get(commandArr[1]).addTrip(trip);
					
				} else {
					
					allDrivers.get(commandArr[1]).incrementInvalidTrips();;
				}
			}
		}
		
		return allDrivers;
		
	}
}
