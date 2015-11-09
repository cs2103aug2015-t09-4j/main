package LemonBuddy.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import LemonBuddy.FileStorage;
import LemonBuddy.Task;

public class TestFileStorage extends FileStorage {
	
	/***************************************************** Private Attributes ******************************************************************/
	private String outputFile;
	private String fileForTestEmpty = "testClear.txt";
	private Logger TestLogger;
	private ArrayList<Task> arrayListUnderTest = new ArrayList<Task>();	
	private ArrayList<ArrayList<Task>> expected = new ArrayList<ArrayList<Task>>();	
	ArrayList<ArrayList<Task>> test = new ArrayList<ArrayList<Task>>();
	private String stringUnderTest  = "";
	private static final String MSG_WHEN_IOEXCEPTION = "cannjot store information"; 
	
	/***************************************************** Private Function ******************************************************************/	

	private boolean compareTwoFiles(File f1, File f2) {
		
		boolean tag = true;
		
        try {   
    		FileInputStream fis1 = new FileInputStream(f1);
            FileInputStream fis2 = new FileInputStream(f1);
            
        	if(fis1.available() != fis2.available()){  
               return false;  
            } else {  
                while(fis1.read() != -1 && fis2.read() != -1)  
                {  
                    if(fis1.read() != fis2.read())  
                    {  
                        tag = false;  
                        break;  
                    }  
                }  
                return tag;  
            }  
        } catch(IOException e) {  
        	printExceptionMessage(e);  
        }
		return tag; 
	}
	
	private boolean compareTwoList(ArrayList<ArrayList<Task>> a1, ArrayList<ArrayList<Task>> a2) {
		
		boolean tag = true;
		
        int size1 = a1.size();
        int size2 = a2.size();
        
        if(size1 != size2){
        	return tag;
        }
        
		for(int i=0; i< size1; i++) {
			int size3 = a1.get(i).size();
			int size4 = a2.get(i).size();
			
			if(size3 != size4){
				tag = false;
				return tag;
			} else {
				for(int j = 0; j < size3; j++) {
					String task1 = a1.get(i).get(j).toString();
					String task2 = a2.get(i).get(j).toString();
					boolean tempCompare = task1.equals(task2);
					if(!tempCompare){
						tag = false;
						return tag;
					}
				}
			}
		}
		return tag; 
	}
	
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
		t1.setTaskPriority("medium");
		
		testList.add(t1);
		
		Task t2 = new Task();
		t2.setTaskName("t2");
		t2.setTaskType("floating");
		t2.setTaskDescription("Floating Task without Any restrict");
		t2.setTaskPriority("low");
		
		testList.add(t2);
		
		Task t3 = new Task();
		t3.setTaskName("t3");
		t3.setTaskType("event");
		t3.setTaskStartTime("0000");
		t3.setTaskStartDate("091115");
		t3.setTaskEndDate("101115");
		t3.setTaskEndTime("2359");
		t3.setTaskDescription("Floating Task without Any restrict");
		t3.setTaskPriority("high");
		
		testList.add(t3);

		Task t4 = new Task();
		t4.setTaskName("t4");
		t4.setTaskType("done");
		t4.setTaskStartTime("0000");
		t4.setTaskStartDate("091115");
		t4.setTaskEndDate("101115");
		t4.setTaskEndTime("2359");
		t4.setTaskDescription("Floating Task without Any restrict");
		t4.setTaskPriority("high");
		
		testList.add(t4);	
		
		Task t5 = new Task();
		t5.setTaskName("t5");
		t5.setTaskType("overdue");
		t5.setTaskStartTime("0000");
		t5.setTaskStartDate("091115");
		t5.setTaskEndDate("101115");
		t5.setTaskEndTime("2359");
		t5.setTaskDescription("Floating Task without Any restrict");
		t5.setTaskPriority("high");
		
		testList.add(t5);	
	
		return testList;
	}
	
	private void initialTaskListForTestRead() {
		
		ArrayList<Task> testFloatingList = new ArrayList<Task>();
		ArrayList<Task> testDeadlineList = new ArrayList<Task>();
		ArrayList<Task> testEventList = new ArrayList<Task>();
		ArrayList<Task> testAllList = new ArrayList<Task>();
		ArrayList<Task> testDoneList = new ArrayList<Task>();
		ArrayList<Task> testOverdueList = new ArrayList<Task>();
		
		Task t1 = new Task();
		t1.setTaskName("t1");
		t1.setTaskType("deadline");
		t1.setTaskEndDate("091115");
		t1.setTaskEndTime("2359");
		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
		t1.setTaskPriority("medium");
		
		testDeadlineList.add(t1);
		
		
		Task t2 = new Task();
		t2.setTaskName("t2");
		t2.setTaskType("floating");
		t2.setTaskDescription("Floating Task without Any restrict");
		t2.setTaskPriority("low");
		
		testFloatingList.add(t2);
		
		Task t3 = new Task();
		t3.setTaskName("t3");
		t3.setTaskType("event");
		t3.setTaskStartTime("0000");
		t3.setTaskStartDate("091115");
		t3.setTaskEndDate("101115");
		t3.setTaskEndTime("2359");
		t3.setTaskDescription("Floating Task without Any restrict");
		t3.setTaskPriority("high");
		
		testEventList.add(t3);

		Task t4 = new Task();
		t4.setTaskName("t4");
		t4.setTaskType("done");
		t4.setTaskStartTime("0000");
		t4.setTaskStartDate("091115");
		t4.setTaskEndDate("101115");
		t4.setTaskEndTime("2359");
		t4.setTaskDescription("Floating Task without Any restrict");
		t4.setTaskPriority("high");
		
		testDoneList.add(t4);	
		
		Task t5 = new Task();
		t5.setTaskName("t5");
		t5.setTaskType("overdue");
		t5.setTaskStartTime("0000");
		t5.setTaskStartDate("091115");
		t5.setTaskEndDate("101115");
		t5.setTaskEndTime("2359");
		t5.setTaskDescription("Floating Task without Any restrict");
		t5.setTaskPriority("high");
		
		testOverdueList.add(t5);	
		
		testAllList.add(t1);
		testAllList.add(t2);
		testAllList.add(t3);
		testAllList.add(t4);
		testAllList.add(t5);
		
		expected.add(testFloatingList);
		expected.add(testDeadlineList);
		expected.add(testEventList);
		expected.add(testAllList);
		expected.add(testDoneList);
		expected.add(testOverdueList);	
	}

	
	private void initialTaskStringTest() {
		
		String content = "";
		
		String t1 = "test whether the file can store string in the default file";
		String t2 = "This string is used to test whether can write in a new line and the next is for whether can accept empty string";
		String t3 = "";
		
		content = t1 + t2 + t3 + content;
	
		stringUnderTest =  content;
	}
	
	/*********************************************************** Test *************************************************************************/	
	
	@Test
	public void testWriteObjectAsString() throws IOException {
		try {
			filename = "testWriteObjectExpected.txt";			
			arrayListUnderTest = initialTaskListForTest();
			FileStorage.writeObjectAsString(arrayListUnderTest);
			File f1 = new File(filename);
			outputFile = "testWriteObjectExpected.txt";
			File f2 = new File(outputFile);
			boolean compareResult = compareTwoFiles(f1,f2);
			assertTrue(compareResult);
		} catch(IOException e) {
			printExceptionMessage(e);
		} 
	}

	@Test
	public void testReadStringAsObject() throws Exception {
		try {
			initialTaskListForTestRead();
			filename = "testWriteObjectExpected.txt";	
			test = readStringAsObject(filename);
			boolean compare = compareTwoList(test, expected);
			assertTrue(compare);
		} catch(IOException e) {
			printExceptionMessage(e);
		} 
	}

	@Test
	public void testWriteStringAsString() throws IOException {
		try {
			initialTaskStringTest();	
			filename = "testWriteStringExpected.txt";
			FileStorage.writeStringAsString(stringUnderTest);
			File f1 = new File(filename);
			outputFile = "testWriteStringOutput";
			File f2 = new File(outputFile);
			boolean compareResult = compareTwoFiles(f1,f2);
			assertTrue(compareResult);
		} catch(IOException e) {
			printExceptionMessage(e);
		} 
		
	}

	@Test
	public void testReadStringAsString() throws IOException, ClassNotFoundException{
		String expected;
		String actual;
		try {
			filename = "testReadStringInput.txt";
			expected = readStringAsString(filename);
			actual = stringUnderTest;
			assertEquals(expected, actual);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			printExceptionMessage(e);
		}
	}
	
	@Test
	public void testClear() throws IOException {
		try {
			FileStorage.clear();
			File f1 = new File("default.txt");
			File f2 = new File(fileForTestEmpty);
			boolean compareResult = compareTwoFiles(f1,f2);
			assertTrue(compareResult);
		} catch(IOException e) {
			printExceptionMessage(e);
		} 	
	}

}
