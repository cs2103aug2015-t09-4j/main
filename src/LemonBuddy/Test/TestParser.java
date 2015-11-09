package LemonBuddy.Test;

import LemonBuddy.Parser;
import LemonBuddy.StorageFunction;
import LemonBuddy.Task;
import LemonBuddy.CommandExecutor;

import org.junit.After;
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

	private String currentDate;
	private String currentDateAfterOneHour;
	private String tomorrowDateAtCurrentTime;
	private String tomorrowDateAfterOneHour;
	private String currentTime;
	private String currentTimeAfterOneHour;

	@Before
	public void setUp() throws Exception {
		Calendar endDate = Calendar.getInstance();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyy HHmm");
		String initialDate = dateFormatter.format(endDate.getTime());
		String[] timeInfo = dateFormatter.format(endDate.getTime()).split(" ");
		currentDate = timeInfo[0];
		currentTime = timeInfo[1];
		endDate.add(Calendar.HOUR, 1);
		timeInfo = dateFormatter.format(endDate.getTime()).split(" ");
		currentDateAfterOneHour = timeInfo[0];
		currentTimeAfterOneHour = timeInfo[1];

		endDate.add(Calendar.HOUR, 23);
		timeInfo = dateFormatter.format(endDate.getTime()).split(" ");
		tomorrowDateAtCurrentTime = timeInfo[0];

		endDate.add(Calendar.HOUR, 1);
		timeInfo = dateFormatter.format(endDate.getTime()).split(" ");
		tomorrowDateAfterOneHour = timeInfo[0];

		testTask = new Task();
		testTask.setTaskName("test one");
		testTask.setTaskType("event");
		testTask.setTaskIsNewest();
		testTask.setTaskStartDate("050516");
		testTask.setTaskEndDate("060516");
		testTask.setTaskStartTime("1400");
		testTask.setTaskEndTime("1600");
		testTask.setTaskPriority("high");
		testTask.setTaskDescription("hello world");

		storageString = "taskname:test one;tasktype:event;taskIsNewest:true;event;taskStartDate:050516;taskEndDate:060516;taskPriority:high;taskDescription:hello world;taskStartTime:1400;taskEndTime:1600;";

//		System.out.println(currentDate);
//		System.out.println(tomorrowDateAtCurrentTime);
//		System.out.println(tomorrowDateAfterOneHour);
//		System.out.println(currentTime);
//		System.out.println(currentTimeAfterOneHour);

	}

	@After
	public void tearDown() {
		parser = null;
		testTask = null;
		storageString = null;
	}

	public void testCompareTwoTasks(Task expectedTask, String storageString) {
		try {
			Task task = StorageFunction.createTaskFromString(storageString);
			assertEquals(((Task) task).getTaskName(), expectedTask.getTaskName());
			assertEquals(((Task) task).getTaskType(), expectedTask.getTaskType());
			assertEquals(((Task) task).getTaskIsNewest(), expectedTask.getTaskIsNewest());
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

	public void testCompareTwoTasks(String[] commandParts, String expectedName, String expectedStartDate,
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
	
	
	public void testInvalidTime(String[] commandParts, String expectedMessage) {
		Task task = new Task();
		String exceptionMessage = "";
		try {
			task = parser.parseTask(commandParts);
		} catch (Exception e) {
			exceptionMessage = e.getMessage();
		}
		assertEquals(exceptionMessage, expectedMessage);
	}
	
	public void testInvalidPriority(String[] commandParts, String expectedMessage) {
		Task task = new Task();
		String exceptionMessage = "";
		try {
			task = parser.parseTask(commandParts);
		} catch (Exception e) {
			exceptionMessage = e.getMessage();
		}
		System.out.println(exceptionMessage);
		assertEquals(exceptionMessage, expectedMessage);
	}
////--------------------------------------------------------------------------------------------------------///

	@Test
	public void executeTestParseEvent() {
		String[] commandParts = new String[50];

		commandParts = splitString("add spectacular spiderman on 1000, 12/12/15 *medium desc super");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "121215", "121215", "1000", "1100", "medium",
				"super");

		commandParts = splitString("add spectacular spiderman on 201225, 1200");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "201225", "201225", "1200", "1300", "low", "");

		commandParts = splitString("add spectacular spiderman on 1200, tomorrow");
		testCompareTwoTasks(commandParts, "spectacular spiderman", tomorrowDateAtCurrentTime, tomorrowDateAfterOneHour,
				"1200", "1300", "low", "");

		commandParts = splitString("add spectacular spiderman on tomorrow, 1200");
		testCompareTwoTasks(commandParts, "spectacular spiderman", tomorrowDateAtCurrentTime, tomorrowDateAtCurrentTime,
				"1200", "1300", "low", "");

		commandParts = splitString(
				"add spectacular spiderman from 1000, 12/12/15 to 1200, 13/12/15 *high desc super spec");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "121215", "131215", "1000", "1200", "high",
				"super spec");

		commandParts = splitString("add spectacular spiderman from 201225, 1200 to 221225, 1200");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "201225", "221225", "1200", "1200", "low", "");

		commandParts = splitString("add spectacular spiderman from 201225, 1200 to 221225, 1200");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "201225", "221225", "1200", "1200", "low", "");

		commandParts = splitString("add spectacular spiderman from 201225, 1200 to 221225, 1200");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "201225", "221225", "1200", "1200", "low", "");

	}

	@Test
	public void executeTestDefaultTimeEvent() {
		String[] commandParts = new String[50];

		commandParts = splitString("add spectacular spiderman on 2340 *medium desc super duper");
		testCompareTwoTasks(commandParts, "spectacular spiderman", currentDate, tomorrowDateAtCurrentTime, "2340",
				"0040", "medium", "super duper");

		commandParts = splitString("add spectacular spiderman on 201225");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "201225", "201225", currentTime,
				currentTimeAfterOneHour, "low", "");

		commandParts = splitString("add spectacular spiderman on tomorrow");
		testCompareTwoTasks(commandParts, "spectacular spiderman", tomorrowDateAtCurrentTime, tomorrowDateAtCurrentTime,
				currentTime, currentTimeAfterOneHour, "low", "");

		commandParts = splitString("add spectacular spiderman from 1000, 12/12/15 to 1200 *high desc super spec");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "121215", "121215", "1000", "1200", "high",
				"super spec");

		commandParts = splitString("add spectacular spiderman from 201225, 1200 to 221225");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "201225", "221225", "1200", "1200", "low", "");

		commandParts = splitString("add spectacular spiderman from 1200 to 221225, 1500");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "221225", "221225", "1200", "1500", "low", "");

		commandParts = splitString("add spectacular spiderman from 1200 to 221225");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "221225", "221225", "1200", "1200", "low", "");

		commandParts = splitString("add spectacular spiderman from 201225 to 221225");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "201225", "221225", "0000", "2359", "low", "");

		commandParts = splitString("add spectacular spiderman from 1000 to 1200");
		testCompareTwoTasks(commandParts, "spectacular spiderman", currentDate, currentDate, "1000", "1200", "low", "");

		commandParts = splitString("add spectacular spiderman from 251215 to 261215");
		testCompareTwoTasks(commandParts, "spectacular spiderman", "251215", "261215", "0000", "2359", "low", "");
	}

	@Test
	public void executeTestParseDeadline() {
		String[] commandParts = new String[50];

		commandParts = splitString("add bumbling bumblebee by 0010, 25/12/15");
		testCompareTwoTasks(commandParts, "bumbling bumblebee", "-1", "251215", "-1", "0010", "low", "");

		commandParts = splitString("add bumbling bumblebee by 1200, 261215 *medium desc fly high");
		testCompareTwoTasks(commandParts, "bumbling bumblebee", "-1", "261215", "-1", "1200", "medium", "fly high");

		commandParts = splitString("add bumbling bumblebee by 1010, tomorrow *medium desc fly high");
		testCompareTwoTasks(commandParts, "bumbling bumblebee", "-1", tomorrowDateAtCurrentTime, "-1", "1010", "medium",
				"fly high");

	}

	@Test
	public void executeTestDefaultTimeDeadline() {
		String[] commandParts = new String[50];

		commandParts = splitString("add bumbling bumblebee by 0010");
		testCompareTwoTasks(commandParts, "bumbling bumblebee", "-1", parser.getCurrentDate(), "-1", "0010", "low", "");

		commandParts = splitString("add bumbling bumblebee by 261215 *medium desc fly high");
		testCompareTwoTasks(commandParts, "bumbling bumblebee", "-1", "261215", "-1", parser.getCurrentTime(), "medium",
				"fly high");
	}

	@Test
	public void executeTestInvalidTime() {
		String[] commandParts = "add spectacular spiderman from 1200, 12/12/15 to 1100, 12/12/15 *high desc super spec"
				.split(" ");
		testInvalidTime(commandParts, "Start time is after end time");
		
		commandParts = "add spectacular spiderman from 2359, 11/12/15 to 0000, 10/12/15 *high desc super spec"
				.split(" ");
		testInvalidTime(commandParts, "Start date is after end date");
		
		commandParts = "add spectacular spiderman from 0000, 11/12/15 to 1100, 10/12/15 *high desc super spec"
				.split(" ");
		testInvalidTime(commandParts, "Start date is after end date");
		
		commandParts = "add spectacular spiderman from 0000, 11/12/15 to 2400, 11/12/15 *high desc super spec"
				.split(" ");
		testInvalidTime(commandParts, "Time out of range");
		
		commandParts = "add spectacular spiderman from 0000,11/12/15 to 2400, 11/12/15 *high desc super spec"
				.split(" ");
		testInvalidTime(commandParts, "Comma has no spacing");
		
		commandParts = "add spectacular spiderman from 0000, 11/1h/15 to 2400, 1a/12/15 *high desc super spec"
				.split(" ");
		testInvalidTime(commandParts, "Invalid date/time format");
		
		commandParts = "add spectacular spiderman from 0000, 11/1sh/15 to 2400, 1a/12/15 *high desc super spec"
				.split(" ");
		testInvalidTime(commandParts, "Invalid date/time format");
		commandParts = "add spectacular spiderman from -1, 11/12/15 to 2359, 11/12/15 *high desc super spec"
				.split(" ");
		testInvalidTime(commandParts, "Invalid date/time format");
	}
	
	public void executeTestInvalidPriority() {
		String[] commandParts = "add spectacular spiderman from 0000, 11/11/15 to 2350, 11/12/15 *higher desc super spec"
				.split(" ");
		testInvalidPriority(commandParts, "Invalid priority type");
	}
	
	private String[] splitString(String command) {
		String[] commandParts = command.split(" ");
		return commandParts;
	}
}
