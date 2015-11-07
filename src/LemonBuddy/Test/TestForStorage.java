package LemonBuddy.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import LemonBuddy.FileStorage;
import LemonBuddy.Task;

public class TestForStorage extends Task {
	
	ArrayList<Task> testList = new ArrayList<Task>();
	
	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteObjectAsString() throws IOException {
		
		//add a test
		Task t = new Task();
		t.setTaskName("test1");
		t.setTaskDescription("the task object is used for test write object as string function");
		t.setTaskStartDate("071115");
		t.setTaskStartTime("0000");
		t.setTaskEndDate("081115");
		t.setTaskEndTime("2359");
		t.setTaskPriority("high");
		t.setTaskType("deadline");
		
		FileStorage.writeObjectAsString(t);
		
		
		
		
		fail("Not yet implemented");
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

	@Test
	public void testRetrieveFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateTaskFromInformation() {
		fail("Not yet implemented");
	}

}
