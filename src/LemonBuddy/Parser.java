package LemonBuddy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Parser {

	public Task parseTask(String[] commandParts) {
		Task newTask = new Task();

		int wordIndex = 1;
		String taskName = "";
		newTask.setTaskType("floating");
		// this while loop gets task name
		while (true) {
			taskName = taskName + commandParts[wordIndex++];
			if (wordIndex >= commandParts.length) {
				break;
			}
			if (commandParts[wordIndex].equals("on") || commandParts[wordIndex].equals("from")
					|| commandParts[wordIndex].substring(0, 1).equals("*") || commandParts[wordIndex].equals("desc")
					||commandParts[wordIndex].equals("by")) {
				break;
			}
			taskName = taskName + " ";
		}
		newTask.setTaskName(taskName);

		try {
			wordIndex = parseDescription(commandParts, newTask, wordIndex);
			wordIndex = parsePriority(commandParts, newTask, wordIndex);
			wordIndex = parseTime(commandParts, newTask, wordIndex);
		} catch (Exception e)

		{
			// System.out.println(commandParts[wordIndex]);
			System.err.println("Invalid input: " + e.getMessage());
		}


		return newTask;

	}

	/**
	 * @param commandParts
	 * @param newTask
	 * @param wordIndex
	 * @return
	 */
	private int parseDescription(String[] commandParts, Task newTask, int wordIndex) {
		int initialIndex = wordIndex;
		while (wordIndex < commandParts.length) {
			if (commandParts[wordIndex].equals("desc")) {
				String taskDesc = "";
				wordIndex++;
				while (true) {
					System.out.println(commandParts[wordIndex]);
					taskDesc = taskDesc + commandParts[wordIndex++];
					if(wordIndex==commandParts.length){
						break;
					}
					taskDesc = taskDesc + " ";
				}
				newTask.setTaskDescription(taskDesc);
			}
			wordIndex++;
		}
		return initialIndex;
	}

	/**
	 * @param commandParts
	 * @param newTask
	 * @param wordIndex
	 * @return
	 */
	private int parsePriority(String[] commandParts, Task newTask, int wordIndex) {
		int initialIndex = wordIndex;
		while (wordIndex < commandParts.length) {
			if (commandParts[wordIndex].substring(0, 1).equals("*")) {
				newTask.setTaskPriority(commandParts[wordIndex].substring(1));
			}
			wordIndex++;
		}
		return initialIndex;
	}

	public static Task createTaskFromInformation(String s) {
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
			case "recurType":
				t.setRecurType(temp[1]);
				break;
			case "recurStartDate":
				t.setRecurStartDate(temp[1]);
				break;
			case "recurEndDate":
				t.setRecurEndDate(temp[1]);
				break;
			default:
				break;
			}
		}
		return t;
	}

	private int parseTime(String[] commandParts, Task newTask, int wordIndex) throws Exception {
		//int detailIndex = wordIndex;
		Boolean from = false;
		Boolean to = false;
		for (String part : commandParts) {
			if (part.contains("from")) {
				from = true;
			}
		}
		for (String part : commandParts) {
			if (part.contains("to")) {
				to = true;
			}
		}
		if (from) {
			if (!to) {
				throw new Exception("From has no to");
			}
		}

		while (wordIndex < commandParts.length) {
			Boolean comma = false;
			switch (commandParts[wordIndex++]) {
			case "on":
				wordIndex = splitCommaStart(commandParts, newTask, wordIndex, comma);
				newTask.setTaskType("event");
				break;
			case "by":
				wordIndex = splitCommaEnd(commandParts, newTask, wordIndex, comma);
				newTask.setTaskType("deadline");
				break;
			case "from":
				wordIndex = splitCommaStart(commandParts, newTask, wordIndex, comma);
				wordIndex++;
				wordIndex++;
				wordIndex = splitCommaEnd(commandParts, newTask, wordIndex, comma);
				newTask.setTaskType("event");
				break;

			}
		}
		return wordIndex;
	}

	public String getCurrentDate() {
		DateFormat df = new SimpleDateFormat("ddMMyy");
		Date dateobj = new Date();
		String currentDate = df.format(dateobj);
		System.out.println(currentDate);
		return currentDate;
	}

	public String getCurrentTime() {
		DateFormat df = new SimpleDateFormat("HHmm");
		Calendar calobj = Calendar.getInstance();
		String time = df.format(calobj.getTime());
		return time;
	}

	private int splitCommaStart(String[] commandParts, Task newTask, int wordIndex, Boolean comma) throws Exception {
		String taskOn = commandParts[wordIndex];
		// trim and comma is present
		if (commandParts[wordIndex].contains(",")) {
			if (!(commandParts[wordIndex].indexOf(",") == commandParts[wordIndex].length() - 1)) {
				throw new Exception("Start comma has no spacing");
			}
		}

		if (commandParts[wordIndex].contains(",")) {
			taskOn = commandParts[wordIndex].substring(0, commandParts[wordIndex].indexOf(","));
			comma = true;
		}

		if (taskOn.length() == 4) {
			newTask.setTaskStartTime(taskOn);
		} else if (taskOn.length() == 6) {
			newTask.setTaskStartDate(taskOn);
		}

		if (comma) {
			taskOn = commandParts[++wordIndex];
			if (taskOn.length() == 4) {
				newTask.setTaskStartTime(taskOn);
			} else if (taskOn.length() == 6) {
				newTask.setTaskStartDate(taskOn);
			}
			//wordIndex = wordIndex + 1;
		}
		return wordIndex;
	}

	private int splitCommaEnd(String[] commandParts, Task newTask, int wordIndex, Boolean comma) throws Exception {
		if (commandParts[wordIndex].contains(",")) {
			if (!(commandParts[wordIndex].indexOf(",") == commandParts[wordIndex].length() - 1)) {
				throw new Exception("End comma has no spacing");
			}
		}

		String taskTo = commandParts[wordIndex];
		// trim and comma is present
		if (commandParts[wordIndex].contains(",")) {
			taskTo = commandParts[wordIndex].substring(0, commandParts[wordIndex].indexOf(","));
			comma = true;
		}
		
		if (taskTo.length() == 4) {
			newTask.setTaskEndTime(taskTo);
		} else if (taskTo.length() == 6) {
			newTask.setTaskEndDate(taskTo);
		}

		if (comma) {
			taskTo = commandParts[++wordIndex];
			if (taskTo.length() == 4) {
				newTask.setTaskEndTime(taskTo);
			} else if (taskTo.length() == 6) {
				newTask.setTaskEndDate(taskTo);
			}
			wordIndex = wordIndex + 1;
		}
		return wordIndex;
	}
	public boolean endDatePassed(String currentDate, String endDate) {
		
		int currentDay = parseInt(getDay(currentDate));
		int currentMonth = parseInt(getMonth(currentDate));
		int currentYear = parseInt(getYear(currentDate));
		int endDay = parseInt(getDay(endDate));
		int endMonth = parseInt(getMonth(endDate));
		int endYear = parseInt(getYear(endDate));
		
		if ((currentYear > endYear)) {
			return true;
		} else if ((currentMonth > endMonth) && (currentYear == endYear)) {
			return true;
		} else if ((currentDay > endDay) && (currentMonth == endMonth) && (currentYear == endYear)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getDay(String date) {
		return date.substring(0,2);
	}
	
	public String getMonth(String date) {
		return date.substring(2,4);
	}
	
	public String getYear(String date) {
		return date.substring(4,6);
	}
	
	public String addOneYear(String date) {
		int day = parseInt(getDay(date));
		int month = parseInt(getMonth(date));
		int year = parseInt(getYear(date)) + 1;
		
		String newDate = "";
		newDate = newDate.concat(toTwoDigit(Integer.toString(day)));
		newDate = newDate.concat(toTwoDigit(Integer.toString(month)));
		newDate = newDate.concat((Integer.toString(year)));
		
		return newDate;
	}
	
	int parseInt(String str) {
		int i = 0;
	    int num = 0;
	    boolean isNeg = false;

	    //Check for negative sign; if it's there, set the isNeg flag
	    if (str.charAt(0) == '-') {
	        isNeg = true;
	        i = 1;
	    }

	    //Process each character of the string;
	    while( i < str.length()) {
	        num *= 10;
	        num += str.charAt(i++) - '0'; //Minus the ASCII code of '0' to get the value of the charAt(i++).
	    }

	    if (isNeg)
	        num = -num;
	    return num;
	}
}
