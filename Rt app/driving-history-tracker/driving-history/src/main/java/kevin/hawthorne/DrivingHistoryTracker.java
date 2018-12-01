package kevin.hawthorne;

public class DrivingHistoryTracker {

	public static void main(String[] args) {
		
		if (args.length != 1) {
			
			System.out.println("Usage: java -jar driving-history.jar input.txt");
			
		} else {
			
			String filepath = args[0];
			CommandReaderDAO reader = new FileCommandReaderDAO(filepath);
						
			Controller controller = new Controller(reader);
						
			controller.run();
	
		}
	}
}
