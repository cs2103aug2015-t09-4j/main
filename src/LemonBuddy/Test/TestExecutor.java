package LemonBuddy.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

import LemonBuddy.CommandExecutor;
import LemonBuddy.Task;

public class TestExecutor{

	public TestExecutor() throws Exception {
		super();
	}

	private CommandExecutor tester = new CommandExecutor();
	
	private static final String TASKTYPE_event = "event";
	private static final String TASKTYPE_deadline = "deadline";
	private static final String TASKTYPE_overdue = "overdue";
	private static final String TASKTYPE_done = "done";
	private static final String TASKTYPE_floating = "floating";
	
	String lastListType;
	String listType;
	
	Task floatingTask1 = new Task();
	Task floatingTask2 = new Task();
	Task floatingTask3 = new Task();
	
	Task deadlineTask1 = new Task();
	Task deadlineTask2 = new Task();
	Task deadlineTask3 = new Task();
	Task deadlineTask4 = new Task();
	
	Task eventTask1 = new Task();
	Task eventTask2 = new Task();
	Task eventTask3 = new Task();
	Task eventTask4 = new Task();
	
	ArrayList<Task> floatingTasks = new ArrayList<Task>();
	ArrayList<Task> deadlineTasks = new ArrayList<Task>();
	ArrayList<Task> eventTasks = new ArrayList<Task>();
	ArrayList<Task> allTasks = new ArrayList<Task>();
	
	
	 @Before
	    public void setUp() throws Exception {
		 
		 floatingTask1.setTaskName("paint a cat");
		 floatingTask1.setTaskType("floating");
		 floatingTask2.setTaskName("paint a dog");
		 floatingTask2.setTaskType("floating");
		 floatingTask3.setTaskName("paint a giraffe");
		 floatingTask3.setTaskType("floating");
		 floatingTask3.setTaskPriority("low");
		 
		 deadlineTask1.setTaskName("do PC1432");
		 deadlineTask1.setTaskType("deadline");
		 deadlineTask1.setTaskEndDate("221215");
		 deadlineTask1.setTaskEndTime("1310");
		 deadlineTask1.setTaskPriority("medium");
		 
		 deadlineTask2.setTaskName("finish knitting project");
		 deadlineTask2.setTaskType("deadline");
		 deadlineTask2.setTaskEndDate("291115");
		 deadlineTask2.setTaskEndTime("1200");
		 
		 deadlineTask3.setTaskName("finish strawberries");
		 deadlineTask3.setTaskType("deadline");
		 deadlineTask3.setTaskEndDate("151115");
		 deadlineTask3.setTaskEndTime("1900");
		 deadlineTask3.setTaskDescription("dont let them turn mouldy");
		 
		 deadlineTask4.setTaskName("do PC1431");
		 deadlineTask4.setTaskType("deadline");
		 deadlineTask4.setTaskEndDate("221215");
		 deadlineTask4.setTaskEndTime("1300");
		 deadlineTask4.setTaskPriority("high");
		 
		 eventTask1.setTaskName("chalet with cats");
		 eventTask1.setTaskType("event");
		 eventTask1.setTaskStartDate("101215");
		 eventTask1.setTaskEndDate("221215");
		 eventTask1.setTaskStartTime("0800");
		 eventTask1.setTaskEndTime("2200");
		 eventTask1.setTaskDescription("bring party biscuits!");
		 eventTask1.setTaskPriority("high");
		 
		 eventTask2.setTaskName("water plants when granny is overseas");
		 eventTask2.setTaskType("event");
		 eventTask2.setTaskStartDate("101215");
		 eventTask2.setTaskEndDate("151215");
		 eventTask2.setTaskStartTime("0000");
		 eventTask2.setTaskEndTime("2359");
		 eventTask2.setTaskPriority("medium");
		 
		 eventTask3.setTaskName("a levels");
		 eventTask3.setTaskType("event");
		 eventTask3.setTaskStartDate("011115");
		 eventTask3.setTaskEndDate("251115");
		 eventTask3.setTaskStartTime("0000");
		 eventTask3.setTaskEndTime("2359");
		 eventTask3.setTaskDescription("last lap!");
		 eventTask3.setTaskPriority("high");
		 
		 eventTask4.setTaskName("sister's o levels");
		 eventTask4.setTaskType("event");
		 eventTask4.setTaskStartDate("011115");
		 eventTask4.setTaskEndDate("101115");
		 eventTask4.setTaskStartTime("0000");
		 eventTask4.setTaskEndTime("2359");
		 eventTask4.setTaskDescription("last lap!");
		 eventTask4.setTaskPriority("high");
		 
		 floatingTasks.add(floatingTask1);
		 floatingTasks.add(floatingTask2);
		 floatingTasks.add(floatingTask3);
		 
		 deadlineTasks.add(deadlineTask1);
		 deadlineTasks.add(deadlineTask2);
		 deadlineTasks.add(deadlineTask3);
		 deadlineTasks.add(deadlineTask4);
		 
		 eventTasks.add(eventTask1);
		 eventTasks.add(eventTask2);
		 eventTasks.add(eventTask3);
		 eventTasks.add(eventTask4);
		 
		 allTasks.add(deadlineTask1);
		 allTasks.add(deadlineTask2);
		 allTasks.add(deadlineTask3);
		 allTasks.add(deadlineTask4);
		 allTasks.add(eventTask1);
		 allTasks.add(eventTask2);
		 allTasks.add(eventTask3);
		 allTasks.add(eventTask4);
		 allTasks.add(floatingTask1);
		 allTasks.add(floatingTask2);
		 allTasks.add(floatingTask3);
	 }  
	
	@Test
	public void testStringForParsingForEdit() throws ClassNotFoundException, IOException {
		
		String input = "edit 1 buy bed by 291215";
		String[] commandParts = input.split(" ");
		String[] expectedParts = {"add", "buy", "bed", "by", "291215"};
		
		assertArrayEquals("edits the input to help add a new task to merge with the old one", expectedParts, tester.getStringForParsing(commandParts));
	}
	
	@Test
	public void testAddTaskToList() throws ClassNotFoundException, IOException { 
		
		Task newTask = new Task();
		
		newTask.setTaskType("floating");
		tester.addTaskToList(newTask);
		
		newTask.setTaskType("deadline");
		tester.addTaskToList(newTask);
		
		newTask.setTaskType("event");
		tester.addTaskToList(newTask);
		
	}
	
	@Test
	public void testDeleteTaskFromList() throws ClassNotFoundException, IOException { 
		
		int deleteId = 1;
		Task deletedTask = new Task();
		
		listType = TASKTYPE_floating;
		deletedTask = tester.deleteTaskFromList(deleteId);
		
		assertEquals("test correct task is deleted", floatingTask1, tester.deleteTaskFromList(deleteId));
		
	}
	
	@Test
	public void testRemoveTaskFromFloatingList() throws ClassNotFoundException, IOException { 
		int deleteID = 1;
		Task newTask = new Task();
		newTask = tester.removeTaskFromFloatingList(deleteID);
		
		assertEquals("check that floating list size is decreased", 2, floatingTasks.size());
		
	}
	
	@Test
	public void testSort() {
		
		ArrayList<Task> expectedDeadlineSort = new ArrayList<Task>();
		expectedDeadlineSort.add(deadlineTask3);
		expectedDeadlineSort.add(deadlineTask2);
		expectedDeadlineSort.add(deadlineTask4);
		expectedDeadlineSort.add(deadlineTask1);
		
		assertEquals("test correct task is deleted", expectedDeadlineSort, tester.executeSort(deadlineTasks));
		
		ArrayList<Task> expectedEventSort = new ArrayList<Task>();
		expectedEventSort.add(eventTask3);
		expectedEventSort.add(eventTask4);
		expectedEventSort.add(eventTask1);
		expectedEventSort.add(eventTask2);
		
		assertEquals("test correct task is deleted", expectedEventSort, tester.executeSort(eventTasks));
		
		ArrayList<Task> expectedFloatingSort = new ArrayList<Task>();
		expectedFloatingSort.add(floatingTask1);
		expectedFloatingSort.add(floatingTask2);
		expectedFloatingSort.add(floatingTask3);
		
		assertEquals("test correct task is deleted", expectedFloatingSort, tester.executeSort(floatingTasks));
		
		ArrayList<Task> expectedSort = new ArrayList<Task>();
		expectedSort.add(eventTask3);
		expectedSort.add(eventTask4);
		expectedSort.add(deadlineTask3);
		expectedSort.add(deadlineTask2);
		expectedSort.add(eventTask1);
		expectedSort.add(eventTask2);
		expectedSort.add(deadlineTask4);
		expectedSort.add(deadlineTask1);
		expectedSort.add(floatingTask1);
		expectedSort.add(floatingTask2);
		expectedSort.add(floatingTask3);
		
		
		assertEquals("test correct task is deleted", expectedSort, tester.executeSort(allTasks));
	}
}
