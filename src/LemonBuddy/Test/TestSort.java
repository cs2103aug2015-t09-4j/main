package LemonBuddy.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import LemonBuddy.Task;

import LemonBuddy.Sort;

public class TestSort extends Sort {
	
	ArrayList<Task> TestSortByTime = new ArrayList<Task>();
	ArrayList<Task> TestNormal_sort = new ArrayList<Task>();
	ArrayList<Task> TestSortByName = new ArrayList<Task>();
	ArrayList<Task> TestSortByType = new ArrayList<Task>();
	ArrayList<Task> TestSortByStartDate = new ArrayList<Task>();
	ArrayList<Task> TestSortByEndDate = new ArrayList<Task>();
	ArrayList<Task> TestSortByStatus = new ArrayList<Task>();
		
	ArrayList<Task> ExpectedTestSortByTime = new ArrayList<Task>();
	ArrayList<Task> ExpectedTestNormal_sort = new ArrayList<Task>();
	ArrayList<Task> ExpectedTestSortByName = new ArrayList<Task>();
	ArrayList<Task> ExpectedTestSortByType = new ArrayList<Task>();
	ArrayList<Task> ExpectedTestSortByStartDate = new ArrayList<Task>();
	ArrayList<Task> ExpectedTestSortByEndDate = new ArrayList<Task>();
	ArrayList<Task> ExpectedTestSortByStatus = new ArrayList<Task>();
	
	private void initialTaskListForTest() {
		
		Task t1 = new Task();
		t1.setTaskName("t1");
		t1.setTaskType("deadline");
		t1.setTaskEndDate("091115");
		t1.setTaskEndTime("2359");
		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
		t1.setTaskPriority("medium");
		
		Task t8 = new Task();
		t8.setTaskName("t8");
		t8.setTaskType("event");
		t8.setTaskStartDate("161724");
		t8.setTaskEndDate("111114");
		t8.setTaskEndTime("1111");
		t8.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
		t8.setTaskPriority("medium");

		Task t7 = new Task();
		t7.setTaskName("t7");
		t7.setTaskType("deadline");
		t7.setTaskEndDate("111114");
		t7.setTaskEndTime("1111");
		t7.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
		t7.setTaskPriority("medium");

		Task t2 = new Task();
		t2.setTaskName("t2");
		t2.setTaskType("deadline");
		t2.setTaskEndTime("1759");
		t2.setTaskDescription("Floating Task without Any restrict");
		t2.setTaskPriority("low");
				
		Task t3 = new Task();
		t3.setTaskName("t3");
		t3.setTaskType("event");
		t3.setTaskStartTime("0000");
		t3.setTaskStartDate("091115");
		t3.setTaskEndDate("101115");
		t3.setTaskEndTime("2359");
		t3.setTaskDescription("Floating Task without Any restrict");
		t3.setTaskPriority("high");

		Task t4 = new Task();
		t4.setTaskName("t4");
		t4.setTaskType("event");
		t4.setTaskStartTime("0300");
		t4.setTaskStartDate("091115");
		t4.setTaskEndDate("101115");
		t4.setTaskEndTime("2359");
		t4.setTaskDescription("Floating Task without Any restrict");
		t4.setTaskPriority("high");
				
		Task t5 = new Task();
		t5.setTaskName("t5");
		t5.setTaskType("overdue");
		t5.setTaskStartTime("0000");
		t5.setTaskStartDate("091115");
		t5.setTaskEndDate("101115");
		t5.setTaskEndTime("2359");
		t5.setTaskDescription("Floating Task without Any restrict");
		t5.setTaskPriority("high");

		Task t6 = new Task();
		t6.setTaskName("t6");
		t6.setTaskType("floating");
		t6.setTaskDescription("Floating Task without Any restrict");
		t6.setTaskPriority("high");

		
		/**************************************************** SortByTime ********************************************************************/

		TestSortByTime.add(t7);		
		TestSortByTime.add(t4);

		
		ExpectedTestSortByTime.add(t4);
		ExpectedTestSortByTime.add(t7);
		
		/**************************************************** Normal_Sort ********************************************************************/
		
		TestNormal_sort.add(t1);
		TestNormal_sort.add(t2);
		TestNormal_sort.add(t3);
		TestNormal_sort.add(t4);
		TestNormal_sort.add(t5);
		TestNormal_sort.add(t6);

		ExpectedTestNormal_sort.add(t6);
		ExpectedTestNormal_sort.add(t2);
		ExpectedTestNormal_sort.add(t1);
		ExpectedTestNormal_sort.add(t3);
		ExpectedTestNormal_sort.add(t4);
		ExpectedTestNormal_sort.add(t5);
		
		/**************************************************** SortByName ********************************************************************/
		TestSortByName.add(t3);
		TestSortByName.add(t2);
		TestSortByName.add(t5);
		TestSortByName.add(t1);
		TestSortByName.add(t4);
		TestSortByName.add(t6);
		
		ExpectedTestSortByName.add(t1);
		ExpectedTestSortByName.add(t2);
		ExpectedTestSortByName.add(t3);
		ExpectedTestSortByName.add(t4);
		ExpectedTestSortByName.add(t5);
		ExpectedTestSortByName.add(t6);
		
		/**************************************************** SortByType ********************************************************************/
		TestSortByType.add(t3);
		TestSortByType.add(t2);
		TestSortByType.add(t5);
		TestSortByType.add(t8);
		TestSortByType.add(t7);
		TestSortByType.add(t1);
		TestSortByType.add(t4);
		TestSortByType.add(t6);
		
		ExpectedTestSortByType.add(t5);
		ExpectedTestSortByType.add(t2);
		ExpectedTestSortByType.add(t7);
		ExpectedTestSortByType.add(t1);
		ExpectedTestSortByType.add(t3);
		ExpectedTestSortByType.add(t8);
		ExpectedTestSortByType.add(t4);
		ExpectedTestSortByType.add(t6);

		/**************************************************** SortByType ********************************************************************/
		TestSortByStartDate.add(t3);
		TestSortByStartDate.add(t2);
		TestSortByStartDate.add(t5);
		TestSortByStartDate.add(t8);
		TestSortByStartDate.add(t7);
		TestSortByStartDate.add(t1);
		TestSortByStartDate.add(t4);
		TestSortByStartDate.add(t6);
		
		ExpectedTestSortByStartDate.add(t2);
		ExpectedTestSortByStartDate.add(t7);
		ExpectedTestSortByStartDate.add(t1);
		ExpectedTestSortByStartDate.add(t6);
		ExpectedTestSortByStartDate.add(t3);
		ExpectedTestSortByStartDate.add(t5);
		ExpectedTestSortByStartDate.add(t4);
		ExpectedTestSortByStartDate.add(t8);

		/**************************************************** SortByType ********************************************************************/
		TestSortByEndDate.add(t3);
		TestSortByEndDate.add(t2);
		TestSortByEndDate.add(t5);
		TestSortByEndDate.add(t8);
		TestSortByEndDate.add(t7);
		TestSortByEndDate.add(t1);
		TestSortByEndDate.add(t4);
		TestSortByEndDate.add(t6);
		
		ExpectedTestSortByEndDate.add(t2);
		ExpectedTestSortByEndDate.add(t7);
		ExpectedTestSortByEndDate.add(t1);
		ExpectedTestSortByEndDate.add(t6);
		ExpectedTestSortByEndDate.add(t3);
		ExpectedTestSortByEndDate.add(t5);
		ExpectedTestSortByEndDate.add(t4);
		ExpectedTestSortByEndDate.add(t8);

	}

	@Test
	public void testSortByTime() {
		initialTaskListForTest();
		sortByTime(TestSortByTime);
		assertEquals(ExpectedTestSortByTime, TestSortByTime);
	}

	@Test
	public void testNormal_sort() {
		initialTaskListForTest();
		normal_sort(TestNormal_sort);
		assertEquals(ExpectedTestNormal_sort, TestNormal_sort);
	}

	@Test
	public void testSortByName() {
		initialTaskListForTest();
		sortByName(TestSortByName);
		assertEquals(ExpectedTestSortByName, TestSortByName);
	}

	@Test
	public void testSortByType() {
		initialTaskListForTest();
		sortByType(TestSortByType);
		assertEquals(ExpectedTestSortByType, TestSortByType);
	}

	@Test
	public void testSortByStartDate() {
		initialTaskListForTest();
		sortByStartDate(TestSortByStartDate);
		assertEquals(ExpectedTestSortByStartDate, TestSortByStartDate);
	}
	
	@Test
	public void testSortByEndDate() {
		initialTaskListForTest();
		sortByStartDate(TestSortByEndDate);
		assertEquals(ExpectedTestSortByEndDate, TestSortByEndDate);
	}
}
