# LI YUMENGA0133911N
###### .metadata\.plugins\org.eclipse.core.resources\.history\76\4000921bf986001517b1b65a1f8245b9
``` 
//package LemonBuddy.Test;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import org.junit.Test;
//
//import LemonBuddy.FileStorage;
//import LemonBuddy.StorageFunction;
//import LemonBuddy.Task;
//
//public class TestFileStorage extends FileStorage {
//	
//	/***************************************************** Private Attributes ******************************************************************/
//	private String outputFile;
//	private String stringUnderTest  = "";
//	private String fileForTestEmpty = "testClear.txt";
//	private ArrayList<Task> arrayListUnderTest = new ArrayList<Task>();	
//	private ArrayList<ArrayList<Task>> expected = new ArrayList<ArrayList<Task>>();	
//	ArrayList<ArrayList<Task>> test = new ArrayList<ArrayList<Task>>();
//	
//	/***************************************************** Private Function ******************************************************************/	
//	
//	@SuppressWarnings("resource")
//	private boolean compareTwoFiles(File f1, File f2) {
//		
//		boolean isTrue = true;
//		
//        try {   
//    		FileInputStream fis1 = new FileInputStream(f1);
//            FileInputStream fis2 = new FileInputStream(f1);
//            
//        	if(fis1.available() != fis2.available()){  
//               return false;  
//            } else {  
//                while(fis1.read() != -1 && fis2.read() != -1)  
//                {  
//                    if(fis1.read() != fis2.read())  
//                    {  
//                        isTrue = false;  
//                        break;  
//                    }  
//                }  
//                return isTrue;  
//            }  
//        } catch(IOException e) {  
//        	StorageFunction.printExceptionMessage(e);  
//        }
//		return isTrue; 
//	}
//	
//	private boolean compareTwoList(ArrayList<ArrayList<Task>> a1, ArrayList<ArrayList<Task>> a2) {
//		
//		boolean isTrue = true;
//		
//        int size1 = a1.size();
//        int size2 = a2.size();
//        
//        if(size1 != size2){
//        	return isTrue;
//        }
//        
//		for(int i=0; i< size1; i++) {
//			int size3 = a1.get(i).size();
//			int size4 = a2.get(i).size();
//			
//			if(size3 != size4){
//				isTrue = false;
//				return isTrue;
//			} else {
//				for(int j = 0; j < size3; j++) {
//					String task1 = a1.get(i).get(j).toString();
//					String task2 = a2.get(i).get(j).toString();
//					boolean tempCompare = task1.equals(task2);
//					if(!tempCompare){
//						isTrue = false;
//						return isTrue;
//					}
//				}
//			}
//		}
//		return isTrue; 
//	}
//
//	
//	private ArrayList<Task> initialTaskListForTest() throws Exception {
//		
//		ArrayList<Task> testList = new ArrayList<Task>();
//		
//		Task t1 = new Task();
//		t1.setTaskName("t1");
//		t1.setTaskType("deadline");
//		t1.setTaskEndDate("091115");
//		t1.setTaskEndTime("2359");
//		t1.setTaskIsNewest();
//		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
//		t1.setTaskPriority("medium");
//		
//		testList.add(t1);
//		
//		Task t2 = new Task();
//		t2.setTaskName("t2");
//		t2.setTaskType("floating");
//		t2.setTaskDescription("Floating Task without Any restrict");
//		t2.setTaskPriority("low");
//		
//		testList.add(t2);
//		
//		Task t3 = new Task();
//		t3.setTaskName("t3");
//		t3.setTaskType("event");
//		t3.setTaskStartTime("0000");
//		t3.setTaskStartDate("091115");
//		t3.setTaskEndDate("101115");
//		t3.setTaskEndTime("2359");
//		t3.setTaskDescription("Floating Task without Any restrict");
//		t3.setTaskPriority("high");
//		
//		testList.add(t3);
//
//		Task t4 = new Task();
//		t4.setTaskName("t4");
//		t4.setTaskType("done");
//		t4.setTaskStartTime("0000");
//		t4.setTaskStartDate("091115");
//		t4.setTaskEndDate("101115");
//		t4.setTaskEndTime("2359");
//		t4.setTaskDescription("Floating Task without Any restrict");
//		t4.setTaskPriority("high");
//		
//		testList.add(t4);	
//		
//		Task t5 = new Task();
//		t5.setTaskName("t5");
//		t5.setTaskType("overdue");
//		t5.setTaskStartTime("0000");
//		t5.setTaskStartDate("091115");
//		t5.setTaskEndDate("101115");
//		t5.setTaskEndTime("2359");
//		t5.setTaskDescription("Floating Task without Any restrict");
//		t5.setTaskPriority("high");
//		
//		testList.add(t5);	
//
//		return testList;
//	}
//	
//	private void initialTaskListForTestRead() throws Exception {
//		
//		ArrayList<Task> testFloatingList = new ArrayList<Task>();
//		ArrayList<Task> testDeadlineList = new ArrayList<Task>();
//		ArrayList<Task> testEventList = new ArrayList<Task>();
//		ArrayList<Task> testAllList = new ArrayList<Task>();
//		ArrayList<Task> testDoneList = new ArrayList<Task>();
//		ArrayList<Task> testOverdueList = new ArrayList<Task>();
//		
//		Task t1 = new Task();
//		t1.setTaskName("t1");
//		t1.setTaskType("deadline");
//		t1.setTaskEndDate("091115");
//		t1.setTaskEndTime("2359");
//		t1.setTaskIsNewest();
//		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
//		t1.setTaskPriority("medium");
//		
//		testDeadlineList.add(t1);
//		
//		
//		Task t2 = new Task();
//		t2.setTaskName("t2");
//		t2.setTaskType("floating");
//		t2.setTaskDescription("Floating Task without Any restrict");
//		t2.setTaskPriority("low");
//		
//		testFloatingList.add(t2);
//		
//		Task t3 = new Task();
//		t3.setTaskName("t3");
//		t3.setTaskType("event");
//		t3.setTaskStartTime("0000");
//		t3.setTaskStartDate("091115");
//		t3.setTaskEndDate("101115");
//		t3.setTaskEndTime("2359");
//		t3.setTaskDescription("Floating Task without Any restrict");
//		t3.setTaskPriority("high");
//		
//		testEventList.add(t3);
//
//		Task t4 = new Task();
//		t4.setTaskName("t4");
//		t4.setTaskType("done");
//		t4.setTaskStartTime("0000");
//		t4.setTaskStartDate("091115");
//		t4.setTaskEndDate("101115");
//		t4.setTaskEndTime("2359");
//		t4.setTaskDescription("Floating Task without Any restrict");
//		t4.setTaskPriority("high");
//		
//		testDoneList.add(t4);	
//		
//		Task t5 = new Task();
//		t5.setTaskName("t5");
//		t5.setTaskType("overdue");
//		t5.setTaskStartTime("0000");
//		t5.setTaskStartDate("091115");
//		t5.setTaskEndDate("101115");
//		t5.setTaskEndTime("2359");
//		t5.setTaskDescription("Floating Task without Any restrict");
//		t5.setTaskPriority("high");
//		
//		testOverdueList.add(t5);
//	
//		testAllList.add(t1);
//		testAllList.add(t2);
//		testAllList.add(t3);
//		testAllList.add(t4);
//		testAllList.add(t5);
//	
//		expected.add(testFloatingList);
//		expected.add(testDeadlineList);
//		expected.add(testEventList);
//		expected.add(testAllList);
//		expected.add(testDoneList);
//		expected.add(testOverdueList);	
//	}
//
//	
//	private void initialTaskStringTest() {
//		
//		String content = "";
//		
//		String t1 = "test whether the file can store string in the default file";
//		String t2 = "This string is used to test whether can write in a new line and the next is for whether can accept empty string";
//		
//		content = t1 + t2 + content;
//	
//		stringUnderTest =  content;
//	}
//	
//	/*********************************************************** Test*************************************************************************/	
//	
//	@Test
//	public void testWriteObjectAsString() throws Exception {
//		try {
//			filename = "testWriteObjectOutput.txt";			
//			arrayListUnderTest = initialTaskListForTest();
//			FileStorage.writeObjectAsString(arrayListUnderTest);
//			File f1 = new File(filename);
//			outputFile = "testWriteObjectExpected.txt";
//			File f2 = new File(outputFile);			
//			boolean compareResult = compareTwoFiles(f1,f2);
//			assertTrue(compareResult);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 
//	}
//
//	@Test
//	public void testReadStringAsObject() throws Exception {
//		try {
//			initialTaskListForTestRead();
//			filename = "testWriteObjectExpected.txt";	
//			test = readStringAsObject(filename);
//			boolean compare = compareTwoList(test, expected);
//			assertTrue(compare);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 
//	}
//
//	@Test
//	public void testprintExceptionMessage() throws Exception {
//		try {
//			initialTaskListForTestRead();
//			filename = "happy.txt";	
//			test = readStringAsObject(filename);
//		} catch(IOException e) {
//			assertThat(e.getMessage(), null);
//		} 
//	}
//	
//	@Test
//	public void testprintInvalidMessage() throws Exception {
//		try {
//			initialTaskListForTestRead();
//			filename = "justforfun";	
//			test = readStringAsObject(filename);
//		} catch(IOException e) {
//			assertThat(e.getMessage(), null);
//		} 
//	}
//	
//	@Test
//	public void testWriteStringAsString() throws IOException {
//		try {
//			initialTaskStringTest();	
//			filename = "testWriteStringExpected.txt";
//			FileStorage.writeStringAsString(stringUnderTest);
//			File f1 = new File(filename);
//			outputFile = "testWriteStringOutput";
//			File f2 = new File(outputFile);
//			boolean compareResult = compareTwoFiles(f1,f2);
//			assertTrue(compareResult);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 
//		
//	}
//
//	@Test
//	public void testReadStringAsString() throws IOException, ClassNotFoundException{
//		String expected;
//		String actual;
//		try {	
//			filename = "testReadStringAsString.txt";
//			expected = readStringAsString(filename);
//			actual = stringUnderTest;
//			assertEquals(expected, actual);		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			StorageFunction.printExceptionMessage(e);
//		}
//	}
//	
//	@Test
//	public void testClear() throws IOException {
//		try {
//			FileStorage.clear();
//			File f1 = new File("default.txt");
//			File f2 = new File(fileForTestEmpty);
//			boolean compareResult = compareTwoFiles(f1,f2);
//			assertTrue(compareResult);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 	
//	}
//
//}
```
###### main\src\LemonBuddy\FileStorage.java
``` java
package LemonBuddy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class FileStorage {
	
    private static String EMPTY_CONTENT = "";
	public static String filename = "default.txt";	
	private static Logger Logger = java.util.logging.Logger.getLogger("FileStorage");

	/************************************************** Write To Storage ******************************************************************/	
	public static void writeObjectAsString(ArrayList<Task> taskList) throws IOException{
		File f = new File(filename);
		assert(StorageFunction.checkFileStatus(f));
		FileWriter fw = new FileWriter(f, false);
    	String content = StorageFunction.convertArrayListToString(taskList);
    	try {
    		fw.write(content);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when write object", e);
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
    }	
   
	public static void writeStringAsString(String s) throws IOException{
		File f = new File(filename);
		assert(StorageFunction.checkFileStatus(f));
		FileWriter fw = new FileWriter(f, false);
    	try {
    		fw.write(s);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when write string", e);
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
    }
	
	/**************************************************** Clear Storage ********************************************************************/
	
	public static void clear() throws IOException {
		File f = new File(filename);	
		assert(StorageFunction.checkFileStatus(f));
		FileWriter fw = new FileWriter(f);
		assert(StorageFunction.checkFileStatus(f));
		try {
    		fw.write(EMPTY_CONTENT);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when clear", e);
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
	}
	

	/************************************************** Read From Storage ******************************************************************/
	public static ArrayList<ArrayList<Task>> readStringAsObject(String path) throws Exception {
		//floating deadline event all done overdue
		ArrayList<ArrayList<Task>> newList = new ArrayList<ArrayList<Task>>();
		try {
			ArrayList<Task> tempObjectList = new ArrayList<Task>();
			File f = new File(filename);
			assert(StorageFunction.checkFileStatus(f));
			tempObjectList = StorageFunction.createArrayList(f);	
			newList = StorageFunction.separateTaskList(tempObjectList);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when read as object", e);
			StorageFunction.printFileInvalidMessage();
			StorageFunction.printExceptionMessage(e);
    	} finally {
    		
    	}		
		return newList;
	}

	public static String readStringAsString(String path) throws IOException, ClassNotFoundException {
    	 String filecontent = "";
         File f = new File(filename);
         assert(StorageFunction.checkFileStatus(f));
         if(StorageFunction.checkFileStatus(f)){        	 
        	 filecontent = StorageFunction.createString(f,filecontent);
         } else {
			 Logger.log(Level.WARNING, "Cannot write into the file when read as string");
        	 StorageFunction.printFileInvalidMessage();
         }
		 return filecontent;       
     }

}
    	

```
###### main\src\LemonBuddy\Sort.java
``` java
package LemonBuddy;

import java.util.ArrayList;

public class Sort {
	
		public static ArrayList<Task> sortByTime(ArrayList<Task> list) {
			return SortImplementation.SortTime(list);
		}
	
		public static ArrayList<Task> normal_sort(ArrayList<Task> list) {
			return SortImplementation.SortEndDate(list);
		}
		
		public static ArrayList<Task> sortByName(ArrayList<Task> list) {
			return SortImplementation.SortName(list);
		}
		
		public static ArrayList<Task> sortByType(ArrayList<Task> list) {
			return SortImplementation.SortType(list);
		}

		public static ArrayList<Task> sortByStartDate(ArrayList<Task> list) {
			return SortImplementation.SortStartDate(list);
		}

		public static ArrayList<Task> sortByEndDate(ArrayList<Task> list) {
			return SortImplementation.SortEndDate(list);
		}

}
```
###### main\src\LemonBuddy\SortImplementation.java
``` java
package LemonBuddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortImplementation{
	
/************************************************** Convert Function for Sorting ******************************************************************/	
	private static int convertPriority(Task t){
		String tempPriority = t.getTaskPriority();
		switch(tempPriority) {
			case "high":
				return 1;
			case "medium":
				return 0;
			case "low":
				return -1;
			default:
				return -1;
		}
	}	
	
	private static Integer convertType(Task t){
		String tempType = t.getTaskType().toLowerCase();
		switch(tempType) {
			case "overdue":
				return -2;
			case "deadline":
				return -1;
			case "event":
				return 0;
			case "done":
				return 2;
			default:
				return 1;
		}
	}
	
	private static int convertDate(int d) {
		int year = d % 100;
		d = d / 100;
		int month = d % 100;
		d = d / 100;
		int day = d;
		
		int newdate = (year * 10000) + (month * 100) + day;
		return newdate;
	}
	
/***************************************************** Sort By Condition ******************************************************************/	
	
	protected static ArrayList<Task> SortEndDate(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				 String type1, type2;
				 int end1, end2;
				 int p1, p2;
				 p1 = convertPriority(t1);
				 p2 = convertPriority(t2);
				 
				String deadline = "deadline";
				String event = "event";
				//String floating = "floating";

				type1 = t1.getTaskType().toLowerCase();
				type2 = t2.getTaskType().toLowerCase();	
					
				if(type1.equals(deadline)) {
					end1 = Integer.valueOf(t1.getTaskEndDate()).intValue();
				} else if(type1.equals(event)) {
					end1 = Integer.valueOf(t1.getTaskStartDate()).intValue();
				//} else if(type1.equals(floating)) {
				//	end1 = 999999;
				} else {
					end1 = 999999;
				}
			
				if(type2.equals(deadline)) {
					end2 = Integer.valueOf(t2.getTaskEndDate()).intValue();
				} else if(type2.equals(event)) {
					end2 = Integer.valueOf(t2.getTaskStartDate()).intValue();
				//} else if(type1.equals(floating)) {
				//	end2 = 999999;
				} else {
					end2 = 999999;
				}
				 
				end1 = convertDate(end1);
				end2 = convertDate(end2);

				if(end1 > end2){
					return 1;
				} else if (end1 == end2) {
					if(p1 < p2){
						return 1;
					} else if (p1 == p2) {
						return 0;
					} else {
						return -1;
					}
				} else {
					return -1;
			    }					
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

	public static ArrayList<Task> SortTime(ArrayList<Task> sortList) {
		  Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				
				String deadline = "deadline";
				String event = "event";
				
				String type1, type2;
				int time1, time2;
				type1 = t1.getTaskType().toLowerCase();
				type2 = t2.getTaskType().toLowerCase();	
					
				if(type1.equals(deadline)) {
					time1 = Integer.valueOf(t1.getTaskEndTime()).intValue();
				} else if(type1.equals(event)) {
					time1 = Integer.valueOf(t1.getTaskStartTime()).intValue();
				} else {
					time1 = -1;
				}
		 
				if(type2.equals(deadline)) {
					time2 = Integer.valueOf(t2.getTaskEndTime()).intValue();
				} else if(type2.equals(event)) {
					time2 = Integer.valueOf(t2.getTaskStartTime()).intValue();
				} else {
					time2 = -1;
				}

				if(time1 > time2){
					return 1;
				} else if (time1 == time2) {
					return 0;
				} else {
					return -1;
				}
			 
			}
		};	
		
		Collections.sort(sortList, comparator);
		return sortList;
	}
  
	protected static ArrayList<Task> SortName(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				String n1, n2;
				n1 = t1.getTaskName();
				n2 = t2.getTaskName();
				return n1.compareTo(n2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}
	
	protected static ArrayList<Task> SortType(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				Integer p1, p2;
				p1 = convertType(t1);
				p2 = convertType(t2);
				
				return p1.compareTo(p2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

	protected static ArrayList<Task> SortStartDate(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				Integer sd1, sd2;
				sd1 = Integer.valueOf(t1.getTaskStartDate()).intValue(); 
				sd2 = Integer.valueOf(t2.getTaskStartDate()).intValue(); 
				return sd1.compareTo(sd2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

}
```
###### main\src\LemonBuddy\StorageFunction.java
``` java
package LemonBuddy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StorageFunction {

	private static final String MSG_WHEN_INVALID_FILENAME = "cannot find targeted file"; 
	private static final String MSG_WHEN_IOEXCEPTION = "cannot store information"; 

	/************************************************** Modify String 
	 * @throws Exception ******************************************************************/	
	public static Task createTaskFromString(String s) throws Exception {
		String[] array = s.split(";");
		Task t = new Task();
		for (int i = 0; i < array.length; i++) {
			String[] temp = array[i].split(":", 2);
			switch (temp[0]) {
			case "taskname":
				t.setTaskName(temp[1]);
				break;
			case "tasktype":
				t.setTaskType(temp[1]);
				break;
			case "taskIsNewest":
				if (temp[1].equals("true"))
					t.setTaskIsNewest();
				break;
			case "taskStartDate":
				t.setTaskStartDate(temp[1]);
				break;
			case "taskEndDate":
				t.setTaskEndDate(temp[1]);
				break;
			case "taskPriority":
				t.setTaskPriority(temp[1]);
				break;
			case "taskDescription":
				t.setTaskDescription(temp[1]);
				break;
			case "taskStartTime":
				t.setTaskStartTime(temp[1]);
				break;
			case "taskEndTime":
				t.setTaskEndTime(temp[1]);
				break;
			default:
				break;
			}
		}
		return t;
	}
	

/************************************************** Modify TaskList ******************************************************************/		

	
	protected static ArrayList<Task> createArrayList(File f) throws Exception {
		ArrayList<Task> result= new ArrayList<Task>();
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(read); 
			String lineText = null;
			while((lineText = br.readLine()) != null) {
        	 result.add(createTaskFromString(lineText));
        	}
			read.close();		
		} catch (FileNotFoundException e) {			
			printExceptionMessage(e);
		} finally {			
		}
		return result;
	}
	
	protected static String convertArrayListToString(ArrayList<Task> p) {
		int size = p.size();
		Task temptask = new Task();
		String content = "";
		String tempContent;
		
		for(int i = 0; i < size; i++) {
			temptask = p.get(i);
			tempContent = TaskToString(temptask);
			content = content + tempContent;
		}		
		return content;
	}

	
	protected static String createString(File f,String s) throws IOException {
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(read); 
			String lineText = null;
			while((lineText = br.readLine()) != null) {
				s = s + lineText + System.getProperty("line.separator");
			}
			read.close();		
		} catch (FileNotFoundException e) {
			printExceptionMessage(e);
		} finally {			
		}
		return s;
	}

	
	protected static ArrayList<ArrayList<Task>> separateTaskList(ArrayList<Task> taskList) {
		
		ArrayList<ArrayList<Task>> separateList = new  ArrayList<ArrayList<Task>>();
		ArrayList<Task> floatingList = new ArrayList<Task>();
		ArrayList<Task> deadlineList = new ArrayList<Task>();
		ArrayList<Task> eventList = new ArrayList<Task>();
		ArrayList<Task> allList = taskList;
		ArrayList<Task> doneList = new ArrayList<Task>();
		ArrayList<Task> overdueList = new ArrayList<Task>();
		
		int size = allList.size();
		String type;
		Task temptask = new Task();
		
		//separate allList
		for(int i = 0; i < size; i++) {
			
			temptask = taskList.get(i);
			
			type = temptask.getTaskType().toLowerCase();
			
			switch(type){
				case "floating":
					floatingList.add(temptask);
					break;
				case "deadline":
					deadlineList.add(temptask);
					break;
				case "event":
					eventList.add(temptask);
					break;
				case "done":
					doneList.add(temptask);
					break;
				case "overdue":
					overdueList.add(temptask);
					break;
				default:
					break;									
			}

		}	
		
		separateList.add(floatingList);
		separateList.add(deadlineList);
		separateList.add(eventList);
		separateList.add(allList);
		separateList.add(doneList);
		separateList.add(overdueList);
		
		return separateList;
	}
	
	protected static String TaskToString(Task t){
		return t.toString();
	}

	/************************************************** Print Function ******************************************************************/
	
	public static void printExceptionMessage(IOException e) {
		System.out.println(MSG_WHEN_IOEXCEPTION);
		e.printStackTrace();
	}
	
	
	protected static void printFileInvalidMessage() {
		System.out.println(MSG_WHEN_INVALID_FILENAME);
	}
	
	/************************************************** File Funciton ******************************************************************/

	protected static boolean checkFileStatus(File f) throws IOException{
		boolean status = false;
		if(f.exists() && f.isFile()) {
			status = true;
		} else {
			printFileInvalidMessage();
		}
		return status;
	}	

	
}
```
###### main\src\LemonBuddy\Test\TestFileStorage.java
``` java
package LemonBuddy.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

import LemonBuddy.FileStorage;
import LemonBuddy.StorageFunction;
import LemonBuddy.Task;

public class TestFileStorage extends FileStorage {
	
	/***************************************************** Private Attributes ******************************************************************/
	private String outputFile;
	private String stringUnderTest  = "";
	private String fileForTestEmpty = "testClear.txt";
	private ArrayList<Task> arrayListUnderTest = new ArrayList<Task>();	
	private ArrayList<ArrayList<Task>> expected = new ArrayList<ArrayList<Task>>();	
	ArrayList<ArrayList<Task>> test = new ArrayList<ArrayList<Task>>();
	
	/***************************************************** Private Function ******************************************************************/	
	
	@SuppressWarnings("resource")
	private boolean compareTwoFiles(File f1, File f2) {
		
		boolean isTrue = true;
		
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
                        isTrue = false;  
                        break;  
                    }  
                }  
                return isTrue;  
            }  
        } catch(IOException e) {  
        	StorageFunction.printExceptionMessage(e);  
        }
		return isTrue; 
	}
	
	private boolean compareTwoList(ArrayList<ArrayList<Task>> a1, ArrayList<ArrayList<Task>> a2) {
		
		boolean isTrue = true;
		
        int size1 = a1.size();
        int size2 = a2.size();
        
        if(size1 != size2){
        	return isTrue;
        }
        
		for(int i=0; i< size1; i++) {
			int size3 = a1.get(i).size();
			int size4 = a2.get(i).size();
			
			if(size3 != size4){
				isTrue = false;
				return isTrue;
			} else {
				for(int j = 0; j < size3; j++) {
					String task1 = a1.get(i).get(j).toString();
					String task2 = a2.get(i).get(j).toString();
					boolean tempCompare = task1.equals(task2);
					if(!tempCompare){
						isTrue = false;
						return isTrue;
					}
				}
			}
		}
		return isTrue; 
	}

	
	private ArrayList<Task> initialTaskListForTest() throws Exception {
		
		ArrayList<Task> testList = new ArrayList<Task>();
		
		Task t1 = new Task();
		t1.setTaskName("t1");
		t1.setTaskType("deadline");
		t1.setTaskEndDate("091115");
		t1.setTaskEndTime("2359");
		t1.setTaskIsNewest();
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
	
	private void initialTaskListForTestRead() throws Exception {
		
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
		t1.setTaskIsNewest();
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
		
		content = t1 + t2 + content;
	
		stringUnderTest =  content;
	}
	
	/*********************************************************** Test*************************************************************************/	
	
	@Test
	public void testWriteObjectAsString() throws Exception {
		try {
			filename = "testWriteObjectOutput.txt";			
			arrayListUnderTest = initialTaskListForTest();
			FileStorage.writeObjectAsString(arrayListUnderTest);
			File f1 = new File(filename);
			outputFile = "testWriteObjectExpected.txt";
			File f2 = new File(outputFile);			
			boolean compareResult = compareTwoFiles(f1,f2);
			assertTrue(compareResult);
		} catch(IOException e) {
			StorageFunction.printExceptionMessage(e);
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
			StorageFunction.printExceptionMessage(e);
		} 
	}

	@Test
	public void testprintExceptionMessage() throws Exception {
		try {
			initialTaskListForTestRead();
			filename = "happy.txt";	
			test = readStringAsObject(filename);
		} catch(IOException e) {
			assertThat(e.getMessage(), null);
		} 
	}
	
	@Test
	public void testprintInvalidMessage() throws Exception {
		try {
			initialTaskListForTestRead();
			filename = "justforfun";	
			test = readStringAsObject(filename);
		} catch(IOException e) {
			assertThat(e.getMessage(), null);
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
			StorageFunction.printExceptionMessage(e);
		} 
		
	}

	@Test
	public void testReadStringAsString() throws IOException, ClassNotFoundException{
		String expected;
		String actual;
		try {	
			filename = "testReadStringAsString.txt";
			expected = readStringAsString(filename);
			actual = stringUnderTest;
			assertEquals(expected, actual);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			StorageFunction.printExceptionMessage(e);
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
			StorageFunction.printExceptionMessage(e);
		} 	
	}

}
```
###### main\src\LemonBuddy\Test\TestSort.java
``` java
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
	
	private void initialTaskListForTest() throws Exception {
		
		Task t1 = new Task();
		t1.setTaskName("t1");
		t1.setTaskType("deadline");
		t1.setTaskEndDate("091115");
		t1.setTaskEndTime("2359");
		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
		t1.setTaskPriority("low");
		
		Task t8 = new Task();
		t8.setTaskName("t8");
		t8.setTaskType("event");
		t8.setTaskStartDate("161724");
		t8.setTaskEndDate("091115");
		t8.setTaskEndTime("1111");
		t8.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
		t8.setTaskPriority("medium");

		Task t7 = new Task();
		t7.setTaskName("t7");
		t7.setTaskType("deadline");
		t7.setTaskEndDate("111114");
		t7.setTaskEndTime("1111");
		t7.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");

		Task t2 = new Task();
		t2.setTaskName("t2");
		t2.setTaskType("deadline");
		t2.setTaskEndTime("1759");
		t2.setTaskDescription("Floating Task without Any restrict");
		t2.setTaskPriority("low");
				
		Task t3 = new Task();
		t3.setTaskName("t3");
		t3.setTaskStartTime("0000");
		t3.setTaskStartDate("091115");
		t3.setTaskEndDate("101115");
		t3.setTaskEndTime("2359");
		t3.setTaskDescription("Floating Task without Any restrict");
		t3.setTaskPriority("high");

		Task t4 = new Task();
		t4.setTaskName("t4");
		t4.setTaskType("done");
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
		TestSortByTime.add(t1);
		TestSortByTime.add(t8);
		TestSortByTime.add(t2);
		TestSortByTime.add(t8);
		TestSortByTime.add(t4);
		TestSortByTime.add(t1);
		
		ExpectedTestSortByTime.add(t4);
		ExpectedTestSortByTime.add(t8);
		ExpectedTestSortByTime.add(t8);
		ExpectedTestSortByTime.add(t4);
		ExpectedTestSortByTime.add(t7);
		ExpectedTestSortByTime.add(t2);
		ExpectedTestSortByTime.add(t1);
		ExpectedTestSortByTime.add(t1);
		
		/**************************************************** Normal_Sort ********************************************************************/
		
		TestNormal_sort.add(t1);
		TestNormal_sort.add(t2);
		TestNormal_sort.add(t3);
		TestNormal_sort.add(t4);
		TestNormal_sort.add(t5);
		TestNormal_sort.add(t6);

		ExpectedTestNormal_sort.add(t3);
		ExpectedTestNormal_sort.add(t4);
		ExpectedTestNormal_sort.add(t5);
		ExpectedTestNormal_sort.add(t6);
		ExpectedTestNormal_sort.add(t2);
		ExpectedTestNormal_sort.add(t1);
		
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
		ExpectedTestSortByType.add(t8);
		ExpectedTestSortByType.add(t3);
		ExpectedTestSortByType.add(t6);
		ExpectedTestSortByType.add(t4);

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
		
		ExpectedTestSortByEndDate.add(t3);
		ExpectedTestSortByEndDate.add(t5);
		ExpectedTestSortByEndDate.add(t4);
		ExpectedTestSortByEndDate.add(t6);
		ExpectedTestSortByEndDate.add(t2);
		ExpectedTestSortByEndDate.add(t7);
		ExpectedTestSortByEndDate.add(t1);
		ExpectedTestSortByEndDate.add(t8);

	}

	@Test
	public void testSortByTime() throws Exception {
		initialTaskListForTest();
		sortByTime(TestSortByTime);
		assertEquals(ExpectedTestSortByTime, TestSortByTime);
	}

	@Test
	public void testNormal_sort() throws Exception {
		initialTaskListForTest();
		normal_sort(TestNormal_sort);
		assertEquals(ExpectedTestNormal_sort, TestNormal_sort);
	}

	@Test
	public void testSortByName() throws Exception {
		initialTaskListForTest();
		sortByName(TestSortByName);
		assertEquals(ExpectedTestSortByName, TestSortByName);
	}

	@Test
	public void testSortByType() throws Exception {
		initialTaskListForTest();
		sortByType(TestSortByType);
		assertEquals(ExpectedTestSortByType, TestSortByType);
	}

	@Test
	public void testSortByStartDate() throws Exception {
		initialTaskListForTest();
		sortByStartDate(TestSortByStartDate);
		assertEquals(ExpectedTestSortByStartDate, TestSortByStartDate);
	}
	
	@Test
	public void testSortByEndDate() throws Exception {
		initialTaskListForTest();
		sortByEndDate(TestSortByEndDate);
		assertEquals(ExpectedTestSortByEndDate, TestSortByEndDate);
	}
}
```
###### New folder\src\LemonBuddy\FileStorage.java
``` java
package LemonBuddy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class FileStorage {
	
    private static String EMPTY_CONTENT = "";
	public static String filename = "default.txt";	
	private static Logger Logger = java.util.logging.Logger.getLogger("FileStorage");

	/************************************************** Write To Storage ******************************************************************/	
	public static void writeObjectAsString(ArrayList<Task> taskList) throws IOException{
		File f = new File(filename);
		assert(StorageFunction.checkFileStatus(f));
		FileWriter fw = new FileWriter(f, false);
    	String content = StorageFunction.convertArrayListToString(taskList);
    	try {
    		fw.write(content);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when write object", e);
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
    }	
   
	public static void writeStringAsString(String s) throws IOException{
		File f = new File(filename);
		assert(StorageFunction.checkFileStatus(f));
		FileWriter fw = new FileWriter(f, false);
    	try {
    		fw.write(s);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when write string", e);
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
    }
	
	/**************************************************** Clear Storage ********************************************************************/
	
	public static void clear() throws IOException {
		File f = new File(filename);	
		assert(StorageFunction.checkFileStatus(f));
		FileWriter fw = new FileWriter(f);
		assert(StorageFunction.checkFileStatus(f));
		try {
    		fw.write(EMPTY_CONTENT);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when clear", e);
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
	}
	

	/************************************************** Read From Storage ******************************************************************/
	public static ArrayList<ArrayList<Task>> readStringAsObject(String path) throws Exception {
		//floating deadline event all done overdue
		ArrayList<ArrayList<Task>> newList = new ArrayList<ArrayList<Task>>();
		try {
			ArrayList<Task> tempObjectList = new ArrayList<Task>();
			File f = new File(filename);
			assert(StorageFunction.checkFileStatus(f));
			tempObjectList = StorageFunction.createArrayList(f);	
			newList = StorageFunction.separateTaskList(tempObjectList);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when read as object", e);
			StorageFunction.printFileInvalidMessage();
			StorageFunction.printExceptionMessage(e);
    	} finally {
    		
    	}		
		return newList;
	}

	public static String readStringAsString(String path) throws IOException, ClassNotFoundException {
    	 String filecontent = "";
         File f = new File(filename);
         assert(StorageFunction.checkFileStatus(f));
         if(StorageFunction.checkFileStatus(f)){        	 
        	 filecontent = StorageFunction.createString(f,filecontent);
         } else {
			 Logger.log(Level.WARNING, "Cannot write into the file when read as string");
        	 StorageFunction.printFileInvalidMessage();
         }
		 return filecontent;       
     }

}
    	

```
###### New folder\src\LemonBuddy\Sort.java
``` java
package LemonBuddy;

import java.util.ArrayList;

public class Sort {
	
		public static ArrayList<Task> sortByTime(ArrayList<Task> list) {
			return SortImplementation.SortTime(list);
		}
	
		public static ArrayList<Task> normal_sort(ArrayList<Task> list) {
			return SortImplementation.SortEndDate(list);
		}
		
		public static ArrayList<Task> sortByName(ArrayList<Task> list) {
			return SortImplementation.SortName(list);
		}
		
		public static ArrayList<Task> sortByType(ArrayList<Task> list) {
			return SortImplementation.SortType(list);
		}

		public static ArrayList<Task> sortByStartDate(ArrayList<Task> list) {
			return SortImplementation.SortStartDate(list);
		}

		public static ArrayList<Task> sortByEndDate(ArrayList<Task> list) {
			return SortImplementation.SortEndDate(list);
		}

}
```
###### New folder\src\LemonBuddy\SortImplementation.java
``` java
package LemonBuddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortImplementation{
	
/************************************************** Convert Function for Sorting ******************************************************************/	
	private static int convertPriority(Task t){
		String tempPriority = t.getTaskPriority();
		switch(tempPriority) {
			case "high":
				return 1;
			case "medium":
				return 0;
			case "low":
				return -1;
			default:
				return -1;
		}
	}	
	
	private static Integer convertType(Task t){
		String tempType = t.getTaskType().toLowerCase();
		switch(tempType) {
			case "overdue":
				return -2;
			case "deadline":
				return -1;
			case "event":
				return 0;
			case "done":
				return 2;
			default:
				return 1;
		}
	}
	
	private static int convertDate(int d) {
		int year = d % 100;
		d = d / 100;
		int month = d % 100;
		d = d / 100;
		int day = d;
		
		int newdate = (year * 10000) + (month * 100) + day;
		return newdate;
	}
	
/***************************************************** Sort By Condition ******************************************************************/	
	
	protected static ArrayList<Task> SortEndDate(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				 String type1, type2;
				 int end1, end2;
				 int p1, p2;
				 p1 = convertPriority(t1);
				 p2 = convertPriority(t2);
				 
				String deadline = "deadline";
				String event = "event";
				//String floating = "floating";

				type1 = t1.getTaskType().toLowerCase();
				type2 = t2.getTaskType().toLowerCase();	
					
				if(type1.equals(deadline)) {
					end1 = Integer.valueOf(t1.getTaskEndDate()).intValue();
				} else if(type1.equals(event)) {
					end1 = Integer.valueOf(t1.getTaskStartDate()).intValue();
				//} else if(type1.equals(floating)) {
				//	end1 = 999999;
				} else {
					end1 = 999999;
				}
			
				if(type2.equals(deadline)) {
					end2 = Integer.valueOf(t2.getTaskEndDate()).intValue();
				} else if(type2.equals(event)) {
					end2 = Integer.valueOf(t2.getTaskStartDate()).intValue();
				//} else if(type1.equals(floating)) {
				//	end2 = 999999;
				} else {
					end2 = 999999;
				}
				 
				end1 = convertDate(end1);
				end2 = convertDate(end2);

				if(end1 > end2){
					return 1;
				} else if (end1 == end2) {
					if(p1 < p2){
						return 1;
					} else if (p1 == p2) {
						return 0;
					} else {
						return -1;
					}
				} else {
					return -1;
			    }					
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

	public static ArrayList<Task> SortTime(ArrayList<Task> sortList) {
		  Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				
				String deadline = "deadline";
				String event = "event";
				
				String type1, type2;
				int time1, time2;
				type1 = t1.getTaskType().toLowerCase();
				type2 = t2.getTaskType().toLowerCase();	
					
				if(type1.equals(deadline)) {
					time1 = Integer.valueOf(t1.getTaskEndTime()).intValue();
				} else if(type1.equals(event)) {
					time1 = Integer.valueOf(t1.getTaskStartTime()).intValue();
				} else {
					time1 = -1;
				}
		 
				if(type2.equals(deadline)) {
					time2 = Integer.valueOf(t2.getTaskEndTime()).intValue();
				} else if(type2.equals(event)) {
					time2 = Integer.valueOf(t2.getTaskStartTime()).intValue();
				} else {
					time2 = -1;
				}

				if(time1 > time2){
					return 1;
				} else if (time1 == time2) {
					return 0;
				} else {
					return -1;
				}
			 
			}
		};	
		
		Collections.sort(sortList, comparator);
		return sortList;
	}
  
	protected static ArrayList<Task> SortName(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				String n1, n2;
				n1 = t1.getTaskName();
				n2 = t2.getTaskName();
				return n1.compareTo(n2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}
	
	protected static ArrayList<Task> SortType(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				Integer p1, p2;
				p1 = convertType(t1);
				p2 = convertType(t2);
				
				return p1.compareTo(p2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

	protected static ArrayList<Task> SortStartDate(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				Integer sd1, sd2;
				sd1 = Integer.valueOf(t1.getTaskStartDate()).intValue(); 
				sd2 = Integer.valueOf(t2.getTaskStartDate()).intValue(); 
				return sd1.compareTo(sd2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

}
```
###### New folder\src\LemonBuddy\StorageFunction.java
``` java
package LemonBuddy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StorageFunction {

	private static final String MSG_WHEN_INVALID_FILENAME = "cannot find targeted file"; 
	private static final String MSG_WHEN_IOEXCEPTION = "cannot store information"; 

	/************************************************** Modify String 
	 * @throws Exception ******************************************************************/	
	public static Task createTaskFromString(String s) throws Exception {
		String[] array = s.split(";");
		Task t = new Task();
		for (int i = 0; i < array.length; i++) {
			String[] temp = array[i].split(":", 2);
			switch (temp[0]) {
			case "taskname":
				t.setTaskName(temp[1]);
				break;
			case "tasktype":
				t.setTaskType(temp[1]);
				break;
			case "taskIsNewest":
				if (temp[1].equals("true"))
					t.setTaskIsNewest();
				break;
			case "taskStartDate":
				t.setTaskStartDate(temp[1]);
				break;
			case "taskEndDate":
				t.setTaskEndDate(temp[1]);
				break;
			case "taskPriority":
				t.setTaskPriority(temp[1]);
				break;
			case "taskDescription":
				t.setTaskDescription(temp[1]);
				break;
			case "taskStartTime":
				t.setTaskStartTime(temp[1]);
				break;
			case "taskEndTime":
				t.setTaskEndTime(temp[1]);
				break;
			default:
				break;
			}
		}
		return t;
	}
	

/************************************************** Modify TaskList ******************************************************************/		

	
	protected static ArrayList<Task> createArrayList(File f) throws Exception {
		ArrayList<Task> result= new ArrayList<Task>();
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(read); 
			String lineText = null;
			while((lineText = br.readLine()) != null) {
        	 result.add(createTaskFromString(lineText));
        	}
			read.close();		
		} catch (FileNotFoundException e) {			
			printExceptionMessage(e);
		} finally {			
		}
		return result;
	}
	
	protected static String convertArrayListToString(ArrayList<Task> p) {
		int size = p.size();
		Task temptask = new Task();
		String content = "";
		String tempContent;
		
		for(int i = 0; i < size; i++) {
			temptask = p.get(i);
			tempContent = TaskToString(temptask);
			content = content + tempContent;
		}		
		return content;
	}

	
	protected static String createString(File f,String s) throws IOException {
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(read); 
			String lineText = null;
			while((lineText = br.readLine()) != null) {
				s = s + lineText + System.getProperty("line.separator");
			}
			read.close();		
		} catch (FileNotFoundException e) {
			printExceptionMessage(e);
		} finally {			
		}
		return s;
	}

	
	protected static ArrayList<ArrayList<Task>> separateTaskList(ArrayList<Task> taskList) {
		
		ArrayList<ArrayList<Task>> separateList = new  ArrayList<ArrayList<Task>>();
		ArrayList<Task> floatingList = new ArrayList<Task>();
		ArrayList<Task> deadlineList = new ArrayList<Task>();
		ArrayList<Task> eventList = new ArrayList<Task>();
		ArrayList<Task> allList = taskList;
		ArrayList<Task> doneList = new ArrayList<Task>();
		ArrayList<Task> overdueList = new ArrayList<Task>();
		
		int size = allList.size();
		String type;
		Task temptask = new Task();
		
		//separate allList
		for(int i = 0; i < size; i++) {
			
			temptask = taskList.get(i);
			
			type = temptask.getTaskType().toLowerCase();
			
			switch(type){
				case "floating":
					floatingList.add(temptask);
					break;
				case "deadline":
					deadlineList.add(temptask);
					break;
				case "event":
					eventList.add(temptask);
					break;
				case "done":
					doneList.add(temptask);
					break;
				case "overdue":
					overdueList.add(temptask);
					break;
				default:
					break;									
			}

		}	
		
		separateList.add(floatingList);
		separateList.add(deadlineList);
		separateList.add(eventList);
		separateList.add(allList);
		separateList.add(doneList);
		separateList.add(overdueList);
		
		return separateList;
	}
	
	protected static String TaskToString(Task t){
		return t.toString();
	}

	/************************************************** Print Function ******************************************************************/
	
	public static void printExceptionMessage(IOException e) {
		System.out.println(MSG_WHEN_IOEXCEPTION);
		e.printStackTrace();
	}
	
	
	protected static void printFileInvalidMessage() {
		System.out.println(MSG_WHEN_INVALID_FILENAME);
	}
	
	/************************************************** File Funciton ******************************************************************/

	protected static boolean checkFileStatus(File f) throws IOException{
		boolean status = false;
		if(f.exists() && f.isFile()) {
			status = true;
		} else {
			printFileInvalidMessage();
		}
		return status;
	}	

	
}
```
###### New folder\src\LemonBuddy\Test\TestFileStorage.java
``` java
//package LemonBuddy.Test;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import org.junit.Test;
//
//import LemonBuddy.FileStorage;
//import LemonBuddy.StorageFunction;
//import LemonBuddy.Task;
//
//public class TestFileStorage extends FileStorage {
//	
//	/***************************************************** Private Attributes ******************************************************************/
//	private String outputFile;
//	private String stringUnderTest  = "";
//	private String fileForTestEmpty = "testClear.txt";
//	private ArrayList<Task> arrayListUnderTest = new ArrayList<Task>();	
//	private ArrayList<ArrayList<Task>> expected = new ArrayList<ArrayList<Task>>();	
//	ArrayList<ArrayList<Task>> test = new ArrayList<ArrayList<Task>>();
//	
//	/***************************************************** Private Function ******************************************************************/	
//	
//	@SuppressWarnings("resource")
//	private boolean compareTwoFiles(File f1, File f2) {
//		
//		boolean isTrue = true;
//		
//        try {   
//    		FileInputStream fis1 = new FileInputStream(f1);
//            FileInputStream fis2 = new FileInputStream(f1);
//            
//        	if(fis1.available() != fis2.available()){  
//               return false;  
//            } else {  
//                while(fis1.read() != -1 && fis2.read() != -1)  
//                {  
//                    if(fis1.read() != fis2.read())  
//                    {  
//                        isTrue = false;  
//                        break;  
//                    }  
//                }  
//                return isTrue;  
//            }  
//        } catch(IOException e) {  
//        	StorageFunction.printExceptionMessage(e);  
//        }
//		return isTrue; 
//	}
//	
//	private boolean compareTwoList(ArrayList<ArrayList<Task>> a1, ArrayList<ArrayList<Task>> a2) {
//		
//		boolean isTrue = true;
//		
//        int size1 = a1.size();
//        int size2 = a2.size();
//        
//        if(size1 != size2){
//        	return isTrue;
//        }
//        
//		for(int i=0; i< size1; i++) {
//			int size3 = a1.get(i).size();
//			int size4 = a2.get(i).size();
//			
//			if(size3 != size4){
//				isTrue = false;
//				return isTrue;
//			} else {
//				for(int j = 0; j < size3; j++) {
//					String task1 = a1.get(i).get(j).toString();
//					String task2 = a2.get(i).get(j).toString();
//					boolean tempCompare = task1.equals(task2);
//					if(!tempCompare){
//						isTrue = false;
//						return isTrue;
//					}
//				}
//			}
//		}
//		return isTrue; 
//	}
//
//	
//	private ArrayList<Task> initialTaskListForTest() throws Exception {
//		
//		ArrayList<Task> testList = new ArrayList<Task>();
//		
//		Task t1 = new Task();
//		t1.setTaskName("t1");
//		t1.setTaskType("deadline");
//		t1.setTaskEndDate("091115");
//		t1.setTaskEndTime("2359");
//		t1.setTaskIsNewest();
//		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
//		t1.setTaskPriority("medium");
//		
//		testList.add(t1);
//		
//		Task t2 = new Task();
//		t2.setTaskName("t2");
//		t2.setTaskType("floating");
//		t2.setTaskDescription("Floating Task without Any restrict");
//		t2.setTaskPriority("low");
//		
//		testList.add(t2);
//		
//		Task t3 = new Task();
//		t3.setTaskName("t3");
//		t3.setTaskType("event");
//		t3.setTaskStartTime("0000");
//		t3.setTaskStartDate("091115");
//		t3.setTaskEndDate("101115");
//		t3.setTaskEndTime("2359");
//		t3.setTaskDescription("Floating Task without Any restrict");
//		t3.setTaskPriority("high");
//		
//		testList.add(t3);
//
//		Task t4 = new Task();
//		t4.setTaskName("t4");
//		t4.setTaskType("done");
//		t4.setTaskStartTime("0000");
//		t4.setTaskStartDate("091115");
//		t4.setTaskEndDate("101115");
//		t4.setTaskEndTime("2359");
//		t4.setTaskDescription("Floating Task without Any restrict");
//		t4.setTaskPriority("high");
//		
//		testList.add(t4);	
//		
//		Task t5 = new Task();
//		t5.setTaskName("t5");
//		t5.setTaskType("overdue");
//		t5.setTaskStartTime("0000");
//		t5.setTaskStartDate("091115");
//		t5.setTaskEndDate("101115");
//		t5.setTaskEndTime("2359");
//		t5.setTaskDescription("Floating Task without Any restrict");
//		t5.setTaskPriority("high");
//		
//		testList.add(t5);	
//
//		return testList;
//	}
//	
//	private void initialTaskListForTestRead() throws Exception {
//		
//		ArrayList<Task> testFloatingList = new ArrayList<Task>();
//		ArrayList<Task> testDeadlineList = new ArrayList<Task>();
//		ArrayList<Task> testEventList = new ArrayList<Task>();
//		ArrayList<Task> testAllList = new ArrayList<Task>();
//		ArrayList<Task> testDoneList = new ArrayList<Task>();
//		ArrayList<Task> testOverdueList = new ArrayList<Task>();
//		
//		Task t1 = new Task();
//		t1.setTaskName("t1");
//		t1.setTaskType("deadline");
//		t1.setTaskEndDate("091115");
//		t1.setTaskEndTime("2359");
//		t1.setTaskIsNewest();
//		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
//		t1.setTaskPriority("medium");
//		
//		testDeadlineList.add(t1);
//		
//		
//		Task t2 = new Task();
//		t2.setTaskName("t2");
//		t2.setTaskType("floating");
//		t2.setTaskDescription("Floating Task without Any restrict");
//		t2.setTaskPriority("low");
//		
//		testFloatingList.add(t2);
//		
//		Task t3 = new Task();
//		t3.setTaskName("t3");
//		t3.setTaskType("event");
//		t3.setTaskStartTime("0000");
//		t3.setTaskStartDate("091115");
//		t3.setTaskEndDate("101115");
//		t3.setTaskEndTime("2359");
//		t3.setTaskDescription("Floating Task without Any restrict");
//		t3.setTaskPriority("high");
//		
//		testEventList.add(t3);
//
//		Task t4 = new Task();
//		t4.setTaskName("t4");
//		t4.setTaskType("done");
//		t4.setTaskStartTime("0000");
//		t4.setTaskStartDate("091115");
//		t4.setTaskEndDate("101115");
//		t4.setTaskEndTime("2359");
//		t4.setTaskDescription("Floating Task without Any restrict");
//		t4.setTaskPriority("high");
//		
//		testDoneList.add(t4);	
//		
//		Task t5 = new Task();
//		t5.setTaskName("t5");
//		t5.setTaskType("overdue");
//		t5.setTaskStartTime("0000");
//		t5.setTaskStartDate("091115");
//		t5.setTaskEndDate("101115");
//		t5.setTaskEndTime("2359");
//		t5.setTaskDescription("Floating Task without Any restrict");
//		t5.setTaskPriority("high");
//		
//		testOverdueList.add(t5);
//	
//		testAllList.add(t1);
//		testAllList.add(t2);
//		testAllList.add(t3);
//		testAllList.add(t4);
//		testAllList.add(t5);
//	
//		expected.add(testFloatingList);
//		expected.add(testDeadlineList);
//		expected.add(testEventList);
//		expected.add(testAllList);
//		expected.add(testDoneList);
//		expected.add(testOverdueList);	
//	}
//
//	
//	private void initialTaskStringTest() {
//		
//		String content = "";
//		
//		String t1 = "test whether the file can store string in the default file";
//		String t2 = "This string is used to test whether can write in a new line and the next is for whether can accept empty string";
//		
//		content = t1 + t2 + content;
//	
//		stringUnderTest =  content;
//	}
//	
//	/*********************************************************** Test*************************************************************************/	
//	
//	@Test
//	public void testWriteObjectAsString() throws Exception {
//		try {
//			filename = "testWriteObjectOutput.txt";			
//			arrayListUnderTest = initialTaskListForTest();
//			FileStorage.writeObjectAsString(arrayListUnderTest);
//			File f1 = new File(filename);
//			outputFile = "testWriteObjectExpected.txt";
//			File f2 = new File(outputFile);			
//			boolean compareResult = compareTwoFiles(f1,f2);
//			assertTrue(compareResult);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 
//	}
//
//	@Test
//	public void testReadStringAsObject() throws Exception {
//		try {
//			initialTaskListForTestRead();
//			filename = "testWriteObjectExpected.txt";	
//			test = readStringAsObject(filename);
//			boolean compare = compareTwoList(test, expected);
//			assertTrue(compare);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 
//	}
//
//	@Test
//	public void testprintExceptionMessage() throws Exception {
//		try {
//			initialTaskListForTestRead();
//			filename = "happy.txt";	
//			test = readStringAsObject(filename);
//		} catch(IOException e) {
//			assertThat(e.getMessage(), null);
//		} 
//	}
//	
//	@Test
//	public void testprintInvalidMessage() throws Exception {
//		try {
//			initialTaskListForTestRead();
//			filename = "justforfun";	
//			test = readStringAsObject(filename);
//		} catch(IOException e) {
//			assertThat(e.getMessage(), null);
//		} 
//	}
//	
//	@Test
//	public void testWriteStringAsString() throws IOException {
//		try {
//			initialTaskStringTest();	
//			filename = "testWriteStringExpected.txt";
//			FileStorage.writeStringAsString(stringUnderTest);
//			File f1 = new File(filename);
//			outputFile = "testWriteStringOutput";
//			File f2 = new File(outputFile);
//			boolean compareResult = compareTwoFiles(f1,f2);
//			assertTrue(compareResult);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 
//		
//	}
//
//	@Test
//	public void testReadStringAsString() throws IOException, ClassNotFoundException{
//		String expected;
//		String actual;
//		try {	
//			filename = "testReadStringAsString.txt";
//			expected = readStringAsString(filename);
//			actual = stringUnderTest;
//			assertEquals(expected, actual);		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			StorageFunction.printExceptionMessage(e);
//		}
//	}
//	
//	@Test
//	public void testClear() throws IOException {
//		try {
//			FileStorage.clear();
//			File f1 = new File("default.txt");
//			File f2 = new File(fileForTestEmpty);
//			boolean compareResult = compareTwoFiles(f1,f2);
//			assertTrue(compareResult);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 	
//	}
//
//}
```
###### New folder\src\LemonBuddy\Test\TestSort.java
``` java
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
	
	private void initialTaskListForTest() throws Exception {
		
		Task t1 = new Task();
		t1.setTaskName("t1");
		t1.setTaskType("deadline");
		t1.setTaskEndDate("091115");
		t1.setTaskEndTime("2359");
		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
		t1.setTaskPriority("low");
		
		Task t8 = new Task();
		t8.setTaskName("t8");
		t8.setTaskType("event");
		t8.setTaskStartDate("161724");
		t8.setTaskEndDate("091115");
		t8.setTaskEndTime("1111");
		t8.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
		t8.setTaskPriority("medium");

		Task t7 = new Task();
		t7.setTaskName("t7");
		t7.setTaskType("deadline");
		t7.setTaskEndDate("111114");
		t7.setTaskEndTime("1111");
		t7.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");

		Task t2 = new Task();
		t2.setTaskName("t2");
		t2.setTaskType("deadline");
		t2.setTaskEndTime("1759");
		t2.setTaskDescription("Floating Task without Any restrict");
		t2.setTaskPriority("low");
				
		Task t3 = new Task();
		t3.setTaskName("t3");
		t3.setTaskStartTime("0000");
		t3.setTaskStartDate("091115");
		t3.setTaskEndDate("101115");
		t3.setTaskEndTime("2359");
		t3.setTaskDescription("Floating Task without Any restrict");
		t3.setTaskPriority("high");

		Task t4 = new Task();
		t4.setTaskName("t4");
		t4.setTaskType("done");
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
		TestSortByTime.add(t1);
		TestSortByTime.add(t8);
		TestSortByTime.add(t2);
		TestSortByTime.add(t8);
		TestSortByTime.add(t4);
		TestSortByTime.add(t1);
		
		ExpectedTestSortByTime.add(t4);
		ExpectedTestSortByTime.add(t8);
		ExpectedTestSortByTime.add(t8);
		ExpectedTestSortByTime.add(t4);
		ExpectedTestSortByTime.add(t7);
		ExpectedTestSortByTime.add(t2);
		ExpectedTestSortByTime.add(t1);
		ExpectedTestSortByTime.add(t1);
		
		/**************************************************** Normal_Sort ********************************************************************/
		
		TestNormal_sort.add(t1);
		TestNormal_sort.add(t2);
		TestNormal_sort.add(t3);
		TestNormal_sort.add(t4);
		TestNormal_sort.add(t5);
		TestNormal_sort.add(t6);

		ExpectedTestNormal_sort.add(t3);
		ExpectedTestNormal_sort.add(t4);
		ExpectedTestNormal_sort.add(t5);
		ExpectedTestNormal_sort.add(t6);
		ExpectedTestNormal_sort.add(t2);
		ExpectedTestNormal_sort.add(t1);
		
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
		ExpectedTestSortByType.add(t8);
		ExpectedTestSortByType.add(t3);
		ExpectedTestSortByType.add(t6);
		ExpectedTestSortByType.add(t4);

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
		
		ExpectedTestSortByEndDate.add(t3);
		ExpectedTestSortByEndDate.add(t5);
		ExpectedTestSortByEndDate.add(t4);
		ExpectedTestSortByEndDate.add(t6);
		ExpectedTestSortByEndDate.add(t2);
		ExpectedTestSortByEndDate.add(t7);
		ExpectedTestSortByEndDate.add(t1);
		ExpectedTestSortByEndDate.add(t8);

	}

	@Test
	public void testSortByTime() throws Exception {
		initialTaskListForTest();
		sortByTime(TestSortByTime);
		assertEquals(ExpectedTestSortByTime, TestSortByTime);
	}

	@Test
	public void testNormal_sort() throws Exception {
		initialTaskListForTest();
		normal_sort(TestNormal_sort);
		assertEquals(ExpectedTestNormal_sort, TestNormal_sort);
	}

	@Test
	public void testSortByName() throws Exception {
		initialTaskListForTest();
		sortByName(TestSortByName);
		assertEquals(ExpectedTestSortByName, TestSortByName);
	}

	@Test
	public void testSortByType() throws Exception {
		initialTaskListForTest();
		sortByType(TestSortByType);
		assertEquals(ExpectedTestSortByType, TestSortByType);
	}

	@Test
	public void testSortByStartDate() throws Exception {
		initialTaskListForTest();
		sortByStartDate(TestSortByStartDate);
		assertEquals(ExpectedTestSortByStartDate, TestSortByStartDate);
	}
	
	@Test
	public void testSortByEndDate() throws Exception {
		initialTaskListForTest();
		sortByEndDate(TestSortByEndDate);
		assertEquals(ExpectedTestSortByEndDate, TestSortByEndDate);
	}
}
```
###### SKOOL NOTES\main\src\LemonBuddy\FileStorage.java
``` java
package LemonBuddy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class FileStorage {
	
    private static String EMPTY_CONTENT = "";
	public static String filename = "default.txt";	
	private static Logger Logger = java.util.logging.Logger.getLogger("FileStorage");

	/************************************************** Write To Storage ******************************************************************/	
	public static void writeObjectAsString(ArrayList<Task> taskList) throws IOException{
		File f = new File(filename);
		assert(StorageFunction.checkFileStatus(f));
		FileWriter fw = new FileWriter(f, false);
    	String content = StorageFunction.convertArrayListToString(taskList);
    	try {
    		fw.write(content);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when write object", e);
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
    }	
   
	public static void writeStringAsString(String s) throws IOException{
		File f = new File(filename);
		assert(StorageFunction.checkFileStatus(f));
		FileWriter fw = new FileWriter(f, false);
    	try {
    		fw.write(s);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when write string", e);
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
    }
	
	/**************************************************** Clear Storage ********************************************************************/
	
	public static void clear() throws IOException {
		File f = new File(filename);	
		assert(StorageFunction.checkFileStatus(f));
		FileWriter fw = new FileWriter(f);
		assert(StorageFunction.checkFileStatus(f));
		try {
    		fw.write(EMPTY_CONTENT);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when clear", e);
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
	}
	

	/************************************************** Read From Storage ******************************************************************/
	public static ArrayList<ArrayList<Task>> readStringAsObject(String path) throws Exception {
		//floating deadline event all done overdue
		ArrayList<ArrayList<Task>> newList = new ArrayList<ArrayList<Task>>();
		try {
			ArrayList<Task> tempObjectList = new ArrayList<Task>();
			File f = new File(filename);
			assert(StorageFunction.checkFileStatus(f));
			tempObjectList = StorageFunction.createArrayList(f);	
			newList = StorageFunction.separateTaskList(tempObjectList);
		} catch (IOException e) {
			Logger.log(Level.WARNING, "Cannot write into the file when read as object", e);
			StorageFunction.printFileInvalidMessage();
			StorageFunction.printExceptionMessage(e);
    	} finally {
    		
    	}		
		return newList;
	}

	public static String readStringAsString(String path) throws IOException, ClassNotFoundException {
    	 String filecontent = "";
         File f = new File(filename);
         assert(StorageFunction.checkFileStatus(f));
         if(StorageFunction.checkFileStatus(f)){        	 
        	 filecontent = StorageFunction.createString(f,filecontent);
         } else {
			 Logger.log(Level.WARNING, "Cannot write into the file when read as string");
        	 StorageFunction.printFileInvalidMessage();
         }
		 return filecontent;       
     }

}
    	

```
###### SKOOL NOTES\main\src\LemonBuddy\Sort.java
``` java
package LemonBuddy;

import java.util.ArrayList;

public class Sort {
	
		public static ArrayList<Task> sortByTime(ArrayList<Task> list) {
			return SortImplementation.SortTime(list);
		}
	
		public static ArrayList<Task> normal_sort(ArrayList<Task> list) {
			return SortImplementation.SortEndDate(list);
		}
		
		public static ArrayList<Task> sortByName(ArrayList<Task> list) {
			return SortImplementation.SortName(list);
		}
		
		public static ArrayList<Task> sortByType(ArrayList<Task> list) {
			return SortImplementation.SortType(list);
		}

		public static ArrayList<Task> sortByStartDate(ArrayList<Task> list) {
			return SortImplementation.SortStartDate(list);
		}

		public static ArrayList<Task> sortByEndDate(ArrayList<Task> list) {
			return SortImplementation.SortEndDate(list);
		}

}
```
###### SKOOL NOTES\main\src\LemonBuddy\SortImplementation.java
``` java
package LemonBuddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortImplementation{
	
/************************************************** Convert Function for Sorting ******************************************************************/	
	private static int convertPriority(Task t){
		String tempPriority = t.getTaskPriority();
		switch(tempPriority) {
			case "high":
				return 1;
			case "medium":
				return 0;
			case "low":
				return -1;
			default:
				return -1;
		}
	}	
	
	private static Integer convertType(Task t){
		String tempType = t.getTaskType().toLowerCase();
		switch(tempType) {
			case "overdue":
				return -2;
			case "deadline":
				return -1;
			case "event":
				return 0;
			case "done":
				return 2;
			default:
				return 1;
		}
	}
	
	private static int convertDate(int d) {
		int year = d % 100;
		d = d / 100;
		int month = d % 100;
		d = d / 100;
		int day = d;
		
		int newdate = (year * 10000) + (month * 100) + day;
		return newdate;
	}
	
/***************************************************** Sort By Condition ******************************************************************/	
	
	protected static ArrayList<Task> SortEndDate(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				 String type1, type2;
				 int end1, end2;
				 int p1, p2;
				 p1 = convertPriority(t1);
				 p2 = convertPriority(t2);
				 
				String deadline = "deadline";
				String event = "event";
				//String floating = "floating";

				type1 = t1.getTaskType().toLowerCase();
				type2 = t2.getTaskType().toLowerCase();	
					
				if(type1.equals(deadline)) {
					end1 = Integer.valueOf(t1.getTaskEndDate()).intValue();
				} else if(type1.equals(event)) {
					end1 = Integer.valueOf(t1.getTaskStartDate()).intValue();
				//} else if(type1.equals(floating)) {
				//	end1 = 999999;
				} else {
					end1 = 999999;
				}
			
				if(type2.equals(deadline)) {
					end2 = Integer.valueOf(t2.getTaskEndDate()).intValue();
				} else if(type2.equals(event)) {
					end2 = Integer.valueOf(t2.getTaskStartDate()).intValue();
				//} else if(type1.equals(floating)) {
				//	end2 = 999999;
				} else {
					end2 = 999999;
				}
				 
				end1 = convertDate(end1);
				end2 = convertDate(end2);

				if(end1 > end2){
					return 1;
				} else if (end1 == end2) {
					if(p1 < p2){
						return 1;
					} else if (p1 == p2) {
						return 0;
					} else {
						return -1;
					}
				} else {
					return -1;
			    }					
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

	public static ArrayList<Task> SortTime(ArrayList<Task> sortList) {
		  Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				
				String deadline = "deadline";
				String event = "event";
				
				String type1, type2;
				int time1, time2;
				type1 = t1.getTaskType().toLowerCase();
				type2 = t2.getTaskType().toLowerCase();	
					
				if(type1.equals(deadline)) {
					time1 = Integer.valueOf(t1.getTaskEndTime()).intValue();
				} else if(type1.equals(event)) {
					time1 = Integer.valueOf(t1.getTaskStartTime()).intValue();
				} else {
					time1 = -1;
				}
		 
				if(type2.equals(deadline)) {
					time2 = Integer.valueOf(t2.getTaskEndTime()).intValue();
				} else if(type2.equals(event)) {
					time2 = Integer.valueOf(t2.getTaskStartTime()).intValue();
				} else {
					time2 = -1;
				}

				if(time1 > time2){
					return 1;
				} else if (time1 == time2) {
					return 0;
				} else {
					return -1;
				}
			 
			}
		};	
		
		Collections.sort(sortList, comparator);
		return sortList;
	}
  
	protected static ArrayList<Task> SortName(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				String n1, n2;
				n1 = t1.getTaskName();
				n2 = t2.getTaskName();
				return n1.compareTo(n2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}
	
	protected static ArrayList<Task> SortType(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				Integer p1, p2;
				p1 = convertType(t1);
				p2 = convertType(t2);
				
				return p1.compareTo(p2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

	protected static ArrayList<Task> SortStartDate(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				Integer sd1, sd2;
				sd1 = Integer.valueOf(t1.getTaskStartDate()).intValue(); 
				sd2 = Integer.valueOf(t2.getTaskStartDate()).intValue(); 
				return sd1.compareTo(sd2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

}
```
###### SKOOL NOTES\main\src\LemonBuddy\StorageFunction.java
``` java
package LemonBuddy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StorageFunction {

	private static final String MSG_WHEN_INVALID_FILENAME = "cannot find targeted file"; 
	private static final String MSG_WHEN_IOEXCEPTION = "cannot store information"; 

	/************************************************** Modify String 
	 * @throws Exception ******************************************************************/	
	public static Task createTaskFromString(String s) throws Exception {
		String[] array = s.split(";");
		Task t = new Task();
		for (int i = 0; i < array.length; i++) {
			String[] temp = array[i].split(":", 2);
			switch (temp[0]) {
			case "taskname":
				t.setTaskName(temp[1]);
				break;
			case "tasktype":
				t.setTaskType(temp[1]);
				break;
			case "taskIsNewest":
				if (temp[1].equals("true"))
					t.setTaskIsNewest();
				break;
			case "taskStartDate":
				t.setTaskStartDate(temp[1]);
				break;
			case "taskEndDate":
				t.setTaskEndDate(temp[1]);
				break;
			case "taskPriority":
				t.setTaskPriority(temp[1]);
				break;
			case "taskDescription":
				t.setTaskDescription(temp[1]);
				break;
			case "taskStartTime":
				t.setTaskStartTime(temp[1]);
				break;
			case "taskEndTime":
				t.setTaskEndTime(temp[1]);
				break;
			default:
				break;
			}
		}
		return t;
	}
	

/************************************************** Modify TaskList ******************************************************************/		

	
	protected static ArrayList<Task> createArrayList(File f) throws Exception {
		ArrayList<Task> result= new ArrayList<Task>();
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(read); 
			String lineText = null;
			while((lineText = br.readLine()) != null) {
        	 result.add(createTaskFromString(lineText));
        	}
			read.close();		
		} catch (FileNotFoundException e) {			
			printExceptionMessage(e);
		} finally {			
		}
		return result;
	}
	
	protected static String convertArrayListToString(ArrayList<Task> p) {
		int size = p.size();
		Task temptask = new Task();
		String content = "";
		String tempContent;
		
		for(int i = 0; i < size; i++) {
			temptask = p.get(i);
			tempContent = TaskToString(temptask);
			content = content + tempContent;
		}		
		return content;
	}

	
	protected static String createString(File f,String s) throws IOException {
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(read); 
			String lineText = null;
			while((lineText = br.readLine()) != null) {
				s = s + lineText + System.getProperty("line.separator");
			}
			read.close();		
		} catch (FileNotFoundException e) {
			printExceptionMessage(e);
		} finally {			
		}
		return s;
	}

	
	protected static ArrayList<ArrayList<Task>> separateTaskList(ArrayList<Task> taskList) {
		
		ArrayList<ArrayList<Task>> separateList = new  ArrayList<ArrayList<Task>>();
		ArrayList<Task> floatingList = new ArrayList<Task>();
		ArrayList<Task> deadlineList = new ArrayList<Task>();
		ArrayList<Task> eventList = new ArrayList<Task>();
		ArrayList<Task> allList = taskList;
		ArrayList<Task> doneList = new ArrayList<Task>();
		ArrayList<Task> overdueList = new ArrayList<Task>();
		
		int size = allList.size();
		String type;
		Task temptask = new Task();
		
		//separate allList
		for(int i = 0; i < size; i++) {
			
			temptask = taskList.get(i);
			
			type = temptask.getTaskType().toLowerCase();
			
			switch(type){
				case "floating":
					floatingList.add(temptask);
					break;
				case "deadline":
					deadlineList.add(temptask);
					break;
				case "event":
					eventList.add(temptask);
					break;
				case "done":
					doneList.add(temptask);
					break;
				case "overdue":
					overdueList.add(temptask);
					break;
				default:
					break;									
			}

		}	
		
		separateList.add(floatingList);
		separateList.add(deadlineList);
		separateList.add(eventList);
		separateList.add(allList);
		separateList.add(doneList);
		separateList.add(overdueList);
		
		return separateList;
	}
	
	protected static String TaskToString(Task t){
		return t.toString();
	}

	/************************************************** Print Function ******************************************************************/
	
	public static void printExceptionMessage(IOException e) {
		System.out.println(MSG_WHEN_IOEXCEPTION);
		e.printStackTrace();
	}
	
	
	protected static void printFileInvalidMessage() {
		System.out.println(MSG_WHEN_INVALID_FILENAME);
	}
	
	/************************************************** File Funciton ******************************************************************/

	protected static boolean checkFileStatus(File f) throws IOException{
		boolean status = false;
		if(f.exists() && f.isFile()) {
			status = true;
		} else {
			printFileInvalidMessage();
		}
		return status;
	}	

	
}
```
###### SKOOL NOTES\main\src\LemonBuddy\Test\TestFileStorage.java
``` java
//package LemonBuddy.Test;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import org.junit.Test;
//
//import LemonBuddy.FileStorage;
//import LemonBuddy.StorageFunction;
//import LemonBuddy.Task;
//
//public class TestFileStorage extends FileStorage {
//	
//	/***************************************************** Private Attributes ******************************************************************/
//	private String outputFile;
//	private String stringUnderTest  = "";
//	private String fileForTestEmpty = "testClear.txt";
//	private ArrayList<Task> arrayListUnderTest = new ArrayList<Task>();	
//	private ArrayList<ArrayList<Task>> expected = new ArrayList<ArrayList<Task>>();	
//	ArrayList<ArrayList<Task>> test = new ArrayList<ArrayList<Task>>();
//	
//	/***************************************************** Private Function ******************************************************************/	
//	
//	@SuppressWarnings("resource")
//	private boolean compareTwoFiles(File f1, File f2) {
//		
//		boolean isTrue = true;
//		
//        try {   
//    		FileInputStream fis1 = new FileInputStream(f1);
//            FileInputStream fis2 = new FileInputStream(f1);
//            
//        	if(fis1.available() != fis2.available()){  
//               return false;  
//            } else {  
//                while(fis1.read() != -1 && fis2.read() != -1)  
//                {  
//                    if(fis1.read() != fis2.read())  
//                    {  
//                        isTrue = false;  
//                        break;  
//                    }  
//                }  
//                return isTrue;  
//            }  
//        } catch(IOException e) {  
//        	StorageFunction.printExceptionMessage(e);  
//        }
//		return isTrue; 
//	}
//	
//	private boolean compareTwoList(ArrayList<ArrayList<Task>> a1, ArrayList<ArrayList<Task>> a2) {
//		
//		boolean isTrue = true;
//		
//        int size1 = a1.size();
//        int size2 = a2.size();
//        
//        if(size1 != size2){
//        	return isTrue;
//        }
//        
//		for(int i=0; i< size1; i++) {
//			int size3 = a1.get(i).size();
//			int size4 = a2.get(i).size();
//			
//			if(size3 != size4){
//				isTrue = false;
//				return isTrue;
//			} else {
//				for(int j = 0; j < size3; j++) {
//					String task1 = a1.get(i).get(j).toString();
//					String task2 = a2.get(i).get(j).toString();
//					boolean tempCompare = task1.equals(task2);
//					if(!tempCompare){
//						isTrue = false;
//						return isTrue;
//					}
//				}
//			}
//		}
//		return isTrue; 
//	}
//
//	
//	private ArrayList<Task> initialTaskListForTest() throws Exception {
//		
//		ArrayList<Task> testList = new ArrayList<Task>();
//		
//		Task t1 = new Task();
//		t1.setTaskName("t1");
//		t1.setTaskType("deadline");
//		t1.setTaskEndDate("091115");
//		t1.setTaskEndTime("2359");
//		t1.setTaskIsNewest();
//		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
//		t1.setTaskPriority("medium");
//		
//		testList.add(t1);
//		
//		Task t2 = new Task();
//		t2.setTaskName("t2");
//		t2.setTaskType("floating");
//		t2.setTaskDescription("Floating Task without Any restrict");
//		t2.setTaskPriority("low");
//		
//		testList.add(t2);
//		
//		Task t3 = new Task();
//		t3.setTaskName("t3");
//		t3.setTaskType("event");
//		t3.setTaskStartTime("0000");
//		t3.setTaskStartDate("091115");
//		t3.setTaskEndDate("101115");
//		t3.setTaskEndTime("2359");
//		t3.setTaskDescription("Floating Task without Any restrict");
//		t3.setTaskPriority("high");
//		
//		testList.add(t3);
//
//		Task t4 = new Task();
//		t4.setTaskName("t4");
//		t4.setTaskType("done");
//		t4.setTaskStartTime("0000");
//		t4.setTaskStartDate("091115");
//		t4.setTaskEndDate("101115");
//		t4.setTaskEndTime("2359");
//		t4.setTaskDescription("Floating Task without Any restrict");
//		t4.setTaskPriority("high");
//		
//		testList.add(t4);	
//		
//		Task t5 = new Task();
//		t5.setTaskName("t5");
//		t5.setTaskType("overdue");
//		t5.setTaskStartTime("0000");
//		t5.setTaskStartDate("091115");
//		t5.setTaskEndDate("101115");
//		t5.setTaskEndTime("2359");
//		t5.setTaskDescription("Floating Task without Any restrict");
//		t5.setTaskPriority("high");
//		
//		testList.add(t5);	
//
//		return testList;
//	}
//	
//	private void initialTaskListForTestRead() throws Exception {
//		
//		ArrayList<Task> testFloatingList = new ArrayList<Task>();
//		ArrayList<Task> testDeadlineList = new ArrayList<Task>();
//		ArrayList<Task> testEventList = new ArrayList<Task>();
//		ArrayList<Task> testAllList = new ArrayList<Task>();
//		ArrayList<Task> testDoneList = new ArrayList<Task>();
//		ArrayList<Task> testOverdueList = new ArrayList<Task>();
//		
//		Task t1 = new Task();
//		t1.setTaskName("t1");
//		t1.setTaskType("deadline");
//		t1.setTaskEndDate("091115");
//		t1.setTaskEndTime("2359");
//		t1.setTaskIsNewest();
//		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
//		t1.setTaskPriority("medium");
//		
//		testDeadlineList.add(t1);
//		
//		
//		Task t2 = new Task();
//		t2.setTaskName("t2");
//		t2.setTaskType("floating");
//		t2.setTaskDescription("Floating Task without Any restrict");
//		t2.setTaskPriority("low");
//		
//		testFloatingList.add(t2);
//		
//		Task t3 = new Task();
//		t3.setTaskName("t3");
//		t3.setTaskType("event");
//		t3.setTaskStartTime("0000");
//		t3.setTaskStartDate("091115");
//		t3.setTaskEndDate("101115");
//		t3.setTaskEndTime("2359");
//		t3.setTaskDescription("Floating Task without Any restrict");
//		t3.setTaskPriority("high");
//		
//		testEventList.add(t3);
//
//		Task t4 = new Task();
//		t4.setTaskName("t4");
//		t4.setTaskType("done");
//		t4.setTaskStartTime("0000");
//		t4.setTaskStartDate("091115");
//		t4.setTaskEndDate("101115");
//		t4.setTaskEndTime("2359");
//		t4.setTaskDescription("Floating Task without Any restrict");
//		t4.setTaskPriority("high");
//		
//		testDoneList.add(t4);	
//		
//		Task t5 = new Task();
//		t5.setTaskName("t5");
//		t5.setTaskType("overdue");
//		t5.setTaskStartTime("0000");
//		t5.setTaskStartDate("091115");
//		t5.setTaskEndDate("101115");
//		t5.setTaskEndTime("2359");
//		t5.setTaskDescription("Floating Task without Any restrict");
//		t5.setTaskPriority("high");
//		
//		testOverdueList.add(t5);
//	
//		testAllList.add(t1);
//		testAllList.add(t2);
//		testAllList.add(t3);
//		testAllList.add(t4);
//		testAllList.add(t5);
//	
//		expected.add(testFloatingList);
//		expected.add(testDeadlineList);
//		expected.add(testEventList);
//		expected.add(testAllList);
//		expected.add(testDoneList);
//		expected.add(testOverdueList);	
//	}
//
//	
//	private void initialTaskStringTest() {
//		
//		String content = "";
//		
//		String t1 = "test whether the file can store string in the default file";
//		String t2 = "This string is used to test whether can write in a new line and the next is for whether can accept empty string";
//		
//		content = t1 + t2 + content;
//	
//		stringUnderTest =  content;
//	}
//	
//	/*********************************************************** Test*************************************************************************/	
//	
//	@Test
//	public void testWriteObjectAsString() throws Exception {
//		try {
//			filename = "testWriteObjectOutput.txt";			
//			arrayListUnderTest = initialTaskListForTest();
//			FileStorage.writeObjectAsString(arrayListUnderTest);
//			File f1 = new File(filename);
//			outputFile = "testWriteObjectExpected.txt";
//			File f2 = new File(outputFile);			
//			boolean compareResult = compareTwoFiles(f1,f2);
//			assertTrue(compareResult);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 
//	}
//
//	@Test
//	public void testReadStringAsObject() throws Exception {
//		try {
//			initialTaskListForTestRead();
//			filename = "testWriteObjectExpected.txt";	
//			test = readStringAsObject(filename);
//			boolean compare = compareTwoList(test, expected);
//			assertTrue(compare);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 
//	}
//
//	@Test
//	public void testprintExceptionMessage() throws Exception {
//		try {
//			initialTaskListForTestRead();
//			filename = "happy.txt";	
//			test = readStringAsObject(filename);
//		} catch(IOException e) {
//			assertThat(e.getMessage(), null);
//		} 
//	}
//	
//	@Test
//	public void testprintInvalidMessage() throws Exception {
//		try {
//			initialTaskListForTestRead();
//			filename = "justforfun";	
//			test = readStringAsObject(filename);
//		} catch(IOException e) {
//			assertThat(e.getMessage(), null);
//		} 
//	}
//	
//	@Test
//	public void testWriteStringAsString() throws IOException {
//		try {
//			initialTaskStringTest();	
//			filename = "testWriteStringExpected.txt";
//			FileStorage.writeStringAsString(stringUnderTest);
//			File f1 = new File(filename);
//			outputFile = "testWriteStringOutput";
//			File f2 = new File(outputFile);
//			boolean compareResult = compareTwoFiles(f1,f2);
//			assertTrue(compareResult);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 
//		
//	}
//
//	@Test
//	public void testReadStringAsString() throws IOException, ClassNotFoundException{
//		String expected;
//		String actual;
//		try {	
//			filename = "testReadStringAsString.txt";
//			expected = readStringAsString(filename);
//			actual = stringUnderTest;
//			assertEquals(expected, actual);		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			StorageFunction.printExceptionMessage(e);
//		}
//	}
//	
//	@Test
//	public void testClear() throws IOException {
//		try {
//			FileStorage.clear();
//			File f1 = new File("default.txt");
//			File f2 = new File(fileForTestEmpty);
//			boolean compareResult = compareTwoFiles(f1,f2);
//			assertTrue(compareResult);
//		} catch(IOException e) {
//			StorageFunction.printExceptionMessage(e);
//		} 	
//	}
//
//}
```
###### SKOOL NOTES\main\src\LemonBuddy\Test\TestSort.java
``` java
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
	
	private void initialTaskListForTest() throws Exception {
		
		Task t1 = new Task();
		t1.setTaskName("t1");
		t1.setTaskType("deadline");
		t1.setTaskEndDate("091115");
		t1.setTaskEndTime("2359");
		t1.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
		t1.setTaskPriority("low");
		
		Task t8 = new Task();
		t8.setTaskName("t8");
		t8.setTaskType("event");
		t8.setTaskStartDate("161724");
		t8.setTaskEndDate("091115");
		t8.setTaskEndTime("1111");
		t8.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");
		t8.setTaskPriority("medium");

		Task t7 = new Task();
		t7.setTaskName("t7");
		t7.setTaskType("deadline");
		t7.setTaskEndDate("111114");
		t7.setTaskEndTime("1111");
		t7.setTaskDescription("Need to finish devGuide and JUNIT test by Monday 2359");

		Task t2 = new Task();
		t2.setTaskName("t2");
		t2.setTaskType("deadline");
		t2.setTaskEndTime("1759");
		t2.setTaskDescription("Floating Task without Any restrict");
		t2.setTaskPriority("low");
				
		Task t3 = new Task();
		t3.setTaskName("t3");
		t3.setTaskStartTime("0000");
		t3.setTaskStartDate("091115");
		t3.setTaskEndDate("101115");
		t3.setTaskEndTime("2359");
		t3.setTaskDescription("Floating Task without Any restrict");
		t3.setTaskPriority("high");

		Task t4 = new Task();
		t4.setTaskName("t4");
		t4.setTaskType("done");
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
		TestSortByTime.add(t1);
		TestSortByTime.add(t8);
		TestSortByTime.add(t2);
		TestSortByTime.add(t8);
		TestSortByTime.add(t4);
		TestSortByTime.add(t1);
		
		ExpectedTestSortByTime.add(t4);
		ExpectedTestSortByTime.add(t8);
		ExpectedTestSortByTime.add(t8);
		ExpectedTestSortByTime.add(t4);
		ExpectedTestSortByTime.add(t7);
		ExpectedTestSortByTime.add(t2);
		ExpectedTestSortByTime.add(t1);
		ExpectedTestSortByTime.add(t1);
		
		/**************************************************** Normal_Sort ********************************************************************/
		
		TestNormal_sort.add(t1);
		TestNormal_sort.add(t2);
		TestNormal_sort.add(t3);
		TestNormal_sort.add(t4);
		TestNormal_sort.add(t5);
		TestNormal_sort.add(t6);

		ExpectedTestNormal_sort.add(t3);
		ExpectedTestNormal_sort.add(t4);
		ExpectedTestNormal_sort.add(t5);
		ExpectedTestNormal_sort.add(t6);
		ExpectedTestNormal_sort.add(t2);
		ExpectedTestNormal_sort.add(t1);
		
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
		ExpectedTestSortByType.add(t8);
		ExpectedTestSortByType.add(t3);
		ExpectedTestSortByType.add(t6);
		ExpectedTestSortByType.add(t4);

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
		
		ExpectedTestSortByEndDate.add(t3);
		ExpectedTestSortByEndDate.add(t5);
		ExpectedTestSortByEndDate.add(t4);
		ExpectedTestSortByEndDate.add(t6);
		ExpectedTestSortByEndDate.add(t2);
		ExpectedTestSortByEndDate.add(t7);
		ExpectedTestSortByEndDate.add(t1);
		ExpectedTestSortByEndDate.add(t8);

	}

	@Test
	public void testSortByTime() throws Exception {
		initialTaskListForTest();
		sortByTime(TestSortByTime);
		assertEquals(ExpectedTestSortByTime, TestSortByTime);
	}

	@Test
	public void testNormal_sort() throws Exception {
		initialTaskListForTest();
		normal_sort(TestNormal_sort);
		assertEquals(ExpectedTestNormal_sort, TestNormal_sort);
	}

	@Test
	public void testSortByName() throws Exception {
		initialTaskListForTest();
		sortByName(TestSortByName);
		assertEquals(ExpectedTestSortByName, TestSortByName);
	}

	@Test
	public void testSortByType() throws Exception {
		initialTaskListForTest();
		sortByType(TestSortByType);
		assertEquals(ExpectedTestSortByType, TestSortByType);
	}

	@Test
	public void testSortByStartDate() throws Exception {
		initialTaskListForTest();
		sortByStartDate(TestSortByStartDate);
		assertEquals(ExpectedTestSortByStartDate, TestSortByStartDate);
	}
	
	@Test
	public void testSortByEndDate() throws Exception {
		initialTaskListForTest();
		sortByEndDate(TestSortByEndDate);
		assertEquals(ExpectedTestSortByEndDate, TestSortByEndDate);
	}
}
```
