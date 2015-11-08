package LemonBuddy.Test;

import LemonBuddy.Parser;
import LemonBuddy.Task;
import LemonBuddy.CommandExecutor;

import org.junit.Before;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestParser {

	private Parser parser = new Parser();
	private Task testTask;
	private String storageString;
	private final String currentDate = "081115";
	private final String tomorrowDate = "091115";
	private final String currentTime = "2001";
	private final String currentTimePlusHour = "2101";
	
	@Before
	public void setUp() throws Exception { 
		testTask = new Task();
		testTask.setTaskName("test one");
		testTask.setTaskType("event");
		testTask.setTaskStartDate("050516");
		testTask.setTaskEndDate("060516");
		testTask.setTaskStartTime("1400");
		testTask.setTaskEndTime("1600");
		testTask.setTaskPriority("high");
		testTask.setTaskDescription("hello world");
		
		storageString = "taskname:test one;event;taskStartDate:050516;taskEndDate:060516;taskPriority:high;taskDescription:hello world;taskStartTime:1400;taskEndTime:1600;";
	}

	public void testCreateTaskFromString(Task expectedTask, String storageString){
		try {
			Task task = parser.createTaskFromString(storageString);
			assertEquals(((Task) task).getTaskName(), expectedTask.getTaskName());
			assertEquals(((Task) task).getTaskStartDate(), expectedTask.getTaskStartDate());
			assertEquals(((Task) task).getTaskEndDate(), expectedTask.getTaskEndDate());
			assertEquals(((Task) task).getTaskStartTime(), expectedTask.getTaskStartTime());
			assertEquals(((Task) task).getTaskEndTime(), expectedTask.getTaskEndTime());
			assertEquals(((Task) task).getTaskPriority(), expectedTask.getTaskPriority());
			assertEquals(((Task) task).getTaskDescription(), expectedTask.getTaskDescription());
		} catch (Exception e) {
			fail("Unknown exception");
		}
	}

	public void testParseEvent(String[] commandParts, String expectedName, String expectedStartDate,
			String expectedEndDate, String expectedStartTime, String expectedEndTime, String expectedPriority,
			String expectedDescription) {
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

	public void testDefaultTimeEvent(String[] commandParts, String expectedName, String expectedStartDate,
			String expectedEndDate, String expectedStartTime, String expectedEndTime, String expectedPriority,
			String expectedDescription) {
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

	public void testParseDeadline(String[] commandParts, String expectedName, String expectedStartDate,
			String expectedEndDate, String expectedStartTime, String expectedEndTime, String expectedPriority,
			String expectedDescription) {
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

	public void testDefaultTimeDeadline(String[] commandParts, String expectedName, String expectedStartDate,
			String expectedEndDate, String expectedStartTime, String expectedEndTime, String expectedPriority,
			String expectedDescription) {
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

	
////--------------------------------------------------------------------------------------------------------///
	@Test
	public void executeTestCreateTaskFromString(){
		testCreateTaskFromString(testTask, storageString);
	}
	
	@Test
 	public void executeTestParseEvent() {
		String[] commandParts = new String[50];

		commandParts = splitString("add spectacular spiderman on 1000, 12/12/15 *medium desc super");
		testParseEvent(commandParts, "spectacular spiderman", "121215", "121215", "1000", "1100", "medium", "super");

		commandParts = splitString("add spectacular spiderman on 201225, 1200");
		testParseEvent(commandParts, "spectacular spiderman", "201225", "201225", "1200", "1300", "", "");

		commandParts = splitString("add spectacular spiderman on 1200, tomorrow");
		testParseEvent(commandParts, "spectacular spiderman", tomorrowDate, tomorrowDate, "1200", "1300", "", "");
		
		commandParts = splitString("add spectacular spiderman on tomorrow, 1200");
		testParseEvent(commandParts, "spectacular spiderman", tomorrowDate, tomorrowDate, "1200", "1300", "", "");

		commandParts = splitString("add spectacular spiderman from 1000, 12/12/15 to 1200, 12/12/15 *high desc super spec");
		testParseEvent(commandParts, "spectacular spiderman", "121215", "121215", "1000", "1200", "high", "super spec");

		commandParts = splitString("add spectacular spiderman from 201225, 1200 to 221225, 1200");
		testParseEvent(commandParts, "spectacular spiderman", "201225", "221225", "1200", "1200", "", "");
		
		commandParts = splitString("add spectacular spiderman from 201225, 1200 to 221225, 1200");
		testParseEvent(commandParts, "spectacular spiderman", "201225", "221225", "1200", "1200", "", "");
		
		commandParts = splitString("add spectacular spiderman from 201225, 1200 to 221225, 1200");
		testParseEvent(commandParts, "spectacular spiderman", "201225", "221225", "1200", "1200", "", "");
		
	}
	
	@Test
	public void executeTestDefaultTimeEvent(){
		String[] commandParts = new String[50];

		commandParts = splitString("add spectacular spiderman on 2340 *medium desc super duper");
		testParseEvent(commandParts, "spectacular spiderman", currentDate, tomorrowDate, "2340", "0040", "medium", "super duper");

		commandParts = splitString("add spectacular spiderman on 201225");
		testParseEvent(commandParts, "spectacular spiderman", "201225", "201225", currentTime, currentTimePlusHour, "", "");

		commandParts = splitString("add spectacular spiderman on tomorrow");
		testParseEvent(commandParts, "spectacular spiderman", tomorrowDate, tomorrowDate, currentTime, currentTimePlusHour, "", "");
		
		commandParts = splitString("add spectacular spiderman from 1000, 12/12/15 to 1200 *high desc super spec");
		testParseEvent(commandParts, "spectacular spiderman", "121215", "121215", "1000", "1200", "high", "super spec");

		commandParts = splitString("add spectacular spiderman from 201225, 1200 to 221225");
		testParseEvent(commandParts, "spectacular spiderman", "201225", "221225", "1200", "1200", "", "");
		
		commandParts = splitString("add spectacular spiderman from 1200 to 221225, 1500");
		testParseEvent(commandParts, "spectacular spiderman", "221225", "221225", "1200", "1500", "", "");
		
		commandParts = splitString("add spectacular spiderman from 1200 to 221225");
		testParseEvent(commandParts, "spectacular spiderman", "221225", "221225", "1200", "1200", "", "");
		
		commandParts = splitString("add spectacular spiderman from 201225 to 221225");
		testParseEvent(commandParts, "spectacular spiderman", "201225", "221225", "0000", "2359", "", "");
		
		commandParts = splitString("add spectacular spiderman from 1000 to 1200");
		testParseEvent(commandParts, "spectacular spiderman", currentDate, currentDate, "1000", "1200", "", "");
		
		commandParts = splitString("add spectacular spiderman from 251215 to 261215");
		testParseEvent(commandParts, "spectacular spiderman", "251215", "261215", "0000", "2359", "", "");
	}

	@Test
	public void executeTestParseDeadline() {
		String[] commandParts = new String[50];

		commandParts = splitString("add bumbling bumblebee by 0010, 25/12/15");
		testParseDeadline(commandParts, "bumbling bumblebee", "-1", "251215", "-1", "0010", "", "");

		commandParts = splitString("add bumbling bumblebee by 1200, 261215 *medium desc fly high");
		testParseDeadline(commandParts, "bumbling bumblebee", "-1", "261215", "-1", "1200", "medium", "fly high");
		
		commandParts = splitString("add bumbling bumblebee by 1010, tomorrow *medium desc fly high");
		testParseDeadline(commandParts, "bumbling bumblebee", "-1", tomorrowDate, "-1", "1010", "medium", "fly high");
		
	}
	
	@Test
	public void executeTestDefaultTimeDeadline() {
		String[] commandParts = new String[50];

		commandParts = splitString("add bumbling bumblebee by 0010");
		testParseDeadline(commandParts, "bumbling bumblebee", "-1", parser.getCurrentDate(), "-1", "0010", "", "");

		commandParts = splitString("add bumbling bumblebee by 261215 *medium desc fly high");
		testParseDeadline(commandParts, "bumbling bumblebee", "-1", "261215", "-1", parser.getCurrentTime(), "medium",
				"fly high");
	}
	private String[] splitString(String command) {
		String[] commandParts = command.split(" ");
		return commandParts;
	}
}
