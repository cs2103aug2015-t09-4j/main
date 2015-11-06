package test;

import LemonBuddy.Parser;
import LemonBuddy.Task;
import LemonBuddy.CommandExecutor;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestParser extends Parser {

	private Parser parser= new Parser();

	@Before
	public void setUp() throws Exception {
		Parser parser = new Parser();
	}

	
	public void testParseEvent(String[] commandParts, String expectedName, int expectedStartDate, int expectedEndDate,
			int expectedStartTime, int expectedEndTime, String expectedPriority, String expectedDescription) {
		try {
			Task task = parser.parseTask(commandParts);
			assertEquals(((Task) task).getTaskName(), expectedName);
			assertEquals(((Task) task).getTaskStartDate(), expectedStartDate);
			assertEquals(((Task) task).getTaskEndDate(), expectedEndDate);
			assertEquals(((Task) task).getTaskStartTime(), expectedStartTime);
			assertEquals(((Task) task).getTaskEndTime(), expectedEndTime);
			assertEquals(((Task) task).getTaskPriority(), expectedPriority);
			assertEquals(((Task) task).getTaskDescription(), expectedDescription);
		} catch (Exception e) {
			fail("DIE ALREADY");
		}
	}

	@Test
	public void executeTestParseEvent() {
		String[] commandParts = new String[50];
		
		commandParts = splitString("add spectacular spiderman from 1000, 12/12/15 to 1200");
		testParseEvent(commandParts, "spectacular spiderman", 121215, 121215, 1000, 1200, "", "");
		
		commandParts = splitString("add spectacular spiderman from 201225, 1200 to 221225");
		testParseEvent(commandParts, "spectacular spiderman", 201225, 221225, 1200, 1200, "", "");
	}

	private String[] splitString(String command) {
		String[] commandParts = command.split(" ");
		return commandParts;
	}
}
