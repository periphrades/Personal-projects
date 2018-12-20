package kevin.hawthorne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileCommandReaderDAO implements CommandReaderDAO {

	private File file;
	
	public FileCommandReaderDAO(String filepath) {
		this.file = new File(filepath);
	}
	
	@Override
	public List<String> getCommands() {
		
		List<String> allCommands = new ArrayList<String>();
		
		try (Scanner scan = new Scanner(file)) {
			
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				allCommands.add(line);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return allCommands;
		
	}
	
	
	
	
}
