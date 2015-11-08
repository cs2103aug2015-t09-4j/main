package LemonBuddy.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import LemonBuddy.FileStorage;
import LemonBuddy.Task;

public class FileStorageTest extends FileStorage {

	/***************************************************** Private Attributes ******************************************************************/	

	String filename = "test.txt";
	private String fileForTestEmpty = "emptyFile";
	private Logger TestLogger;
	private ArrayList<Task> arrayListUnderTest = new ArrayList<Task>();	
	private static final String MSG_WHEN_IOEXCEPTION = "cannot store information"; 
	
	/***************************************************** Private Function ******************************************************************/	

	private void printExceptionMessage(IOException e) {
		TestLogger.log(Level.INFO, "throw IOException");
		System.out.println(MSG_WHEN_IOEXCEPTION);
		e.printStackTrace();
	}
	
	private ArrayList<Task> initialTaskListForTest() {
		
		ArrayList<Task> testList = new ArrayList<Task>();
		
		Task t1 = new Task();
		t1.setTaskName("t1");
		t1.setTaskType("deadline");
		t1.setTaskEndDate("091115");
		t1.setTaskEndTime("2359");
		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
		
		testList.add(t1);
		
		return testList;
	}
	
	/*********************************************************** Test *************************************************************************/	
	
	@Test
	public void testClear() throws IOException {
		try {
			FileStorage.clear();
			File f1 = new File(filename);
			File f2 = new File(fileForTestEmpty);
			assertEquals(f1, f2);
		} catch(IOException e) {
			printExceptionMessage(e);
		} 	
	}

	@Test
	public void testWriteObjectAsString() throws IOException {
		try {
			arrayListUnderTest = initialTaskListForTest();
			FileStorage.writeObjectAsString(arrayListUnderTest);
			File f1 = new File("default.txt");
			File f2 = new File(filename);
			assertEquals(f1, f2);
		} catch(IOException e) {
			printExceptionMessage(e);
		} 
	}

	@Test
	public void testWriteStringAsString() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadStringAsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadStringAsString() {
		fail("Not yet implemented");
	}

}
