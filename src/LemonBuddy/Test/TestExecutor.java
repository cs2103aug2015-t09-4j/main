//package LemonBuddy.Test;
//
//import static org.junit.Assert.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.junit.Test;
//import org.junit.Before;
//
//import LemonBuddy.CommandExecutor;
//import LemonBuddy.Task;
//
//public class TestExecutor extends CommandExecutor {
//	
//	CommandExecutor tester = new CommandExecutor();
//	
//	private static final String TASKTYPE_EVENT = "event";
//	private static final String TASKTYPE_DEADLINE = "deadline";
//	private static final String TASKTYPE_OVERDUE = "overdue";
//	private static final String TASKTYPE_DONE = "done";
//	private static final String TASKTYPE_FLOATING = "floating";
//	
//	String lastListType = TASKTYPE_FLOATING;
//	
//	Task floatingTask1 = new Task();
//	Task floatingTask2 = new Task();
//	Task floatingTask3 = new Task();
//	
//	Task deadlineTask1 = new Task();
//	Task deadlineTask2 = new Task();
//	Task deadlineTask3 = new Task();
//	
//	Task eventTask1 = new Task();
//	Task eventTask2 = new Task();
//	Task eventTask3 = new Task();
//	
//	ArrayList<Task> floatingTasks = new ArrayList<Task>();
//	ArrayList<Task> deadlineTasks = new ArrayList<Task>();
//	ArrayList<Task> eventTasks = new ArrayList<Task>();
//	ArrayList<Task> allTasks = new ArrayList<Task>();
//	
//	
//	 @Before
//	    public void setUp() throws Exception {
//		 
//		 floatingTask1.setTaskName("paint a cat");
//		 floatingTask2.setTaskName("paint a dog");
//		 floatingTask3.setTaskName("paint a giraffe");
//		 
//		 deadlineTask1.setTaskName("do PC1432");
//		 deadlineTask1.setTaskEndDate("221215");
//		 deadlineTask1.setTaskEndTime("1310");
//		 deadlineTask1.setTaskPriority("high");
//		 
//		 deadlineTask2.setTaskName("finish knitting project");
//		 deadlineTask2.setTaskEndDate("291115");
//		 deadlineTask2.setTaskEndTime("1200");
//		 
//		 deadlineTask3.setTaskName("finish strawberries");
//		 deadlineTask3.setTaskEndDate("151115");
//		 deadlineTask3.setTaskEndTime("1900");
//		 deadlineTask3.setTaskDescription("dont let them turn mouldy");
//		 
//		 eventTask1.setTaskName("chalet with cats");
//		 eventTask1.setTaskStartDate("201215");
//		 eventTask1.setTaskEndDate("221215");
//		 eventTask1.setTaskStartTime("0800");
//		 eventTask1.setTaskEndTime("2200");
//		 eventTask1.setTaskDescription("bring party biscuits!");
//		 eventTask1.setTaskPriority("high");
//		 
//		 eventTask2.setTaskName("water plants when granny is overseas");
//		 eventTask2.setTaskStartDate("101215");
//		 eventTask2.setTaskEndDate("151215");
//		 eventTask2.setTaskStartTime("0000");
//		 eventTask2.setTaskEndTime("2359");
//		 eventTask2.setTaskPriority("medium");
//		 
//		 eventTask2.setTaskName("a levels");
//		 eventTask2.setTaskStartDate("011115");
//		 eventTask2.setTaskEndDate("251115");
//		 eventTask2.setTaskStartTime("0000");
//		 eventTask2.setTaskEndTime("2359");
//		 eventTask2.setTaskDescription("last lap!");
//		 eventTask2.setTaskPriority("high");
//		 
//		 floatingTasks.add(floatingTask1);
//		 floatingTasks.add(floatingTask2);
//		 floatingTasks.add(floatingTask3);
//		 
//		 deadlineTasks.add(deadlineTask1);
//		 deadlineTasks.add(deadlineTask2);
//		 deadlineTasks.add(deadlineTask3);
//		 
//		 eventTasks.add(eventTask1);
//		 eventTasks.add(eventTask2);
//		 eventTasks.add(eventTask3);
//		 
//		 allTasks.add(floatingTask1);
//		 allTasks.add(floatingTask2);
//		 allTasks.add(floatingTask3);
//		 allTasks.add(deadlineTask1);
//		 allTasks.add(deadlineTask2);
//		 allTasks.add(deadlineTask3);
//		 allTasks.add(eventTask1);
//		 allTasks.add(eventTask2);
//		 allTasks.add(eventTask3);
//	 }  
//	
//	@Test
//	public void testStringForParsingForEdit() throws ClassNotFoundException, IOException {
//		
//		String input = "edit 1 buy bed by 291215";
//		String[] commandParts = input.split(" ");
//		String[] expectedParts = {"add", "buy", "bed", "by", "291215"};
//		
//		assertArrayEquals("edits the input to help add a new task to merge with the old one", expectedParts, tester.getStringForParsing(commandParts));
//	}
//	
//	@Test
//	public void testRemoveTaskFromFloatingList() throws ClassNotFoundException, IOException { 
//		int deleteID1 = 1;
//		
//		assertEquals("test correct task is deleted", floatingTask1, tester.removeTaskFromFloatingList(deleteID1));
//		
//	}
//	
//	@Test
//	public void testDeleteTaskFromList() throws ClassNotFoundException, IOException { 
//		int deleteID1 = 1;
//		
//		
//		assertEquals("test correct task is deleted", floatingTask1, tester.deleteTaskFromList(deleteID1));
//		
//	}
//	
//	@Test
//	public void testSort() {
//		assertEquals("test correct task is deleted", floatingTask1, tester.executeSort(allTasks));
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//}
