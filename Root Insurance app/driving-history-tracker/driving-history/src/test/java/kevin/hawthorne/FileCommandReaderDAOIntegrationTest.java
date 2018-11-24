package kevin.hawthorne;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileCommandReaderDAOIntegrationTest {

	private CommandReaderDAO reader;
	
	@Before
	public void setup() {

		reader = new FileCommandReaderDAO("test.txt");
		
	}
	
	@Test
	public void verifyGetCommands() {
		
		List<String> allCommandLines = reader.getCommands();
		
		Assert.assertEquals(13, allCommandLines.size());
				
	}
	
	
}
