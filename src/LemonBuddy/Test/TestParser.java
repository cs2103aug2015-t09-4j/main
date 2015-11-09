package LemonBuddy.Test;

import LemonBuddy.Parser;
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
	
		System.out.println(currentDate);
		System.out.println(tomorrowDateAtCurrentTime);
		System.out.println(tomorrowDateAfterOneHour);
		System.out.println(currentTime);
		System.out.println(currentTimeAfterOneHour);
		
	}
	
	@After
    public void tearDown() {
		parser = null;
		testTask = null;
		storageString = null;
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
 	public void executeTestParseEvent() {
		String[] commandParts = new String[50];

		commandParts = splitString("add spectacular spiderman on 1000, 12/12/15 *medium desc super");
		testParseEvent(commandParts, "spectacular spiderman", "121215", "121215", "1000", "1100", "medium", "super");

		commandParts = splitString("add spectacular spiderman on 201225, 1200");
		testParseEvent(commandParts, "spectacular spiderman", "201225", "201225", "1200", "1300", "", "");

		commandParts = splitString("add spectacular spiderman on 1200, tomorrow");
		testParseEvent(commandParts, "spectacular spiderman", tomorrowDateAtCurrentTime, tomorrowDateAfterOneHour, "1200", "1300", "", "");
		
		commandParts = splitString("add spectacular spiderman on tomorrow, 1200");
		testParseEvent(commandParts, "spectacular spiderman", tomorrowDateAtCurrentTime, tomorrowDateAtCurrentTime, "1200", "1300", "", "");

		commandParts = splitString("add spectacular spiderman from 1000, 12/12/15 to 1200, 13/12/15 *high desc super spec");
		testParseEvent(commandParts, "spectacular spiderman", "121215", "131215", "1000", "1200", "high", "super spec");

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
		testDefaultTimeEvent(commandParts, "spectacular spiderman", currentDate, tomorrowDateAtCurrentTime, "2340", "0040", "medium", "super duper");

		commandParts = splitString("add spectacular spiderman on 201225");
		testDefaultTimeEvent(commandParts, "spectacular spiderman", "201225", "201225", currentTime, currentTimeAfterOneHour, "", "");

		commandParts = splitString("add spectacular spiderman on tomorrow");
		testDefaultTimeEvent(commandParts, "spectacular spiderman", tomorrowDateAtCurrentTime, tomorrowDateAtCurrentTime, currentTime, currentTimeAfterOneHour, "", "");
		
		commandParts = splitString("add spectacular spiderman from 1000, 12/12/15 to 1200 *high desc super spec");
		testDefaultTimeEvent(commandParts, "spectacular spiderman", "121215", "121215", "1000", "1200", "high", "super spec");

		commandParts = splitString("add spectacular spiderman from 201225, 1200 to 221225");
		testDefaultTimeEvent(commandParts, "spectacular spiderman", "201225", "221225", "1200", "1200", "", "");
		
		commandParts = splitString("add spectacular spiderman from 1200 to 221225, 1500");
		testDefaultTimeEvent(commandParts, "spectacular spiderman", "221225", "221225", "1200", "1500", "", "");
		
		commandParts = splitString("add spectacular spiderman from 1200 to 221225");
		testDefaultTimeEvent(commandParts, "spectacular spiderman", "221225", "221225", "1200", "1200", "", "");
		
		commandParts = splitString("add spectacular spiderman from 201225 to 221225");
		testDefaultTimeEvent(commandParts, "spectacular spiderman", "201225", "221225", "0000", "2359", "", "");
		
		commandParts = splitString("add spectacular spiderman from 1000 to 1200");
		testDefaultTimeEvent(commandParts, "spectacular spiderman", currentDate, currentDate, "1000", "1200", "", "");
		
		commandParts = splitString("add spectacular spiderman from 251215 to 261215");
		testDefaultTimeEvent(commandParts, "spectacular spiderman", "251215", "261215", "0000", "2359", "", "");
	}

	@Test
	public void executeTestParseDeadline() {
		String[] commandParts = new String[50];

		commandParts = splitString("add bumbling bumblebee by 0010, 25/12/15");
		testDefaultTimeDeadline(commandParts, "bumbling bumblebee", "-1", "251215", "-1", "0010", "", "");

		commandParts = splitString("add bumbling bumblebee by 1200, 261215 *medium desc fly high");
		testDefaultTimeDeadline(commandParts, "bumbling bumblebee", "-1", "261215", "-1", "1200", "medium", "fly high");
		
		commandParts = splitString("add bumbling bumblebee by 1010, tomorrow *medium desc fly high");
		testDefaultTimeDeadline(commandParts, "bumbling bumblebee", "-1", tomorrowDateAtCurrentTime, "-1", "1010", "medium", "fly high");
		
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
