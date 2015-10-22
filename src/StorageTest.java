import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class StorageTest extends FileStorage{
	
	@Test
	public void testRetrieve() throws IOException {
		String path = "C:\\Users\\lemonaoko\\Desktop\\a.txt";
		RetrieveFile(path);
		assertEquals("default.txt", path);		
	}

}
