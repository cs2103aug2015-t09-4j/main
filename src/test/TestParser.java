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

	
//	public void testParseEvent(String[] commandParts, String expectedName, int expectedStartDate, int expectedEndDate,
//			int expectedStartTime, int expectedEndTime, String expectedPriority, String expectedDescription) {
//		try {
//			Task task = parser.parseTask(commandParts);
//			assertEquals(((Task) task).getTaskName(), expectedName);
//			assertEquals(((Task) task).getTaskStartDate(), expectedStartDate);
//			assertEquals(((Task) task).getTaskEndDate(), expectedEndDate);
//			assertEquals(((Task) task).getTaskStartTime(), expectedStartTime);
//			assertEquals(((Task) task).getTaskEndTime(), expectedEndTime);
//			assertEquals(((Task) task).getTaskPriority(), expectedPriority);
//			assertEquals(((Task) task).getTaskDescription(), expectedDescription);
//		} catch (Exception e) {
//			fail("Unknown exception");
//		}
//	}
	
	public void testParseDeadline(String[] commandParts, String expectedName, String expectedStartDate, String expectedEndDate,
			String expectedStartTime, String expectedEndTime, String expectedPriority, String expectedDescription) {
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
			fail("Unknown exception");
		}
	}
//
//	@Test
//	public void executeTestParseEvent() {
//		String[] commandParts = new String[50];
//		
//		commandParts = splitString("add spectacular spiderman on 1000, 12/12/15 *medium desc super");
//		testParseEvent(commandParts, "spectacular spiderman", 121215, 121215, 1000, 1100, "medium", "super");
//		
//		commandParts = splitString("add spectacular spiderman on 201225, 1200");
//		testParseEvent(commandParts, "spectacular spiderman", 201225, 201225, 1200, 1300, "", "");
//		
//		commandParts = splitString("add spectacular spiderman from 1000, 12/12/15 to 1200 *high desc super spec");
//		testParseEvent(commandParts, "spectacular spiderman", 121215, 121215, 1000, 1200, "high", "super spec");
//		
//		commandParts = splitString("add spectacular spiderman from 201225, 1200 to 221225");
//		testParseEvent(commandParts, "spectacular spiderman", 201225, 221225, 1200, 1200, "", "");
//		
//	}
//	

	@Test
	public void executeTestParseDeadline() {
		String[] commandParts = new String[50];
		
		commandParts = splitString("add bumbling bumblebee by 0010, 25/12/15");
		testParseDeadline(commandParts, "bumbling bumblebee", "-1", "251215", "-1", "0010", "", "");
		
		commandParts = splitString("add bumbling bumblebee by 1200, 261215 *medium desc fly high");
		testParseDeadline(commandParts, "bumbling bumblebee", "-1", "261215", "-1", "1200", "medium", "fly high");
		
	}
	
	private String[] splitString(String command) {
		String[] commandParts = command.split(" ");
		return commandParts;
	}
}
