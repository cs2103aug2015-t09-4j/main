package LemonBuddy;

import java.text.DateFormat;
import java.text.ParseException;
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
					|| commandParts[wordIndex].equals("by")) {
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
					if (wordIndex == commandParts.length) {
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
			case "taskIsDone":
				if (temp[1].equals("true"))
					t.setTaskIsDone();
				break;
			case "taskIsOverdue":
				if (temp[1].equals("true"))
					t.setTaskIsOverdue();
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
		// int detailIndex = wordIndex;
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
				throw new Exception("Missing \"to\" from command");
			}
		}

		while (wordIndex < commandParts.length) {
			Boolean comma = false;
			switch (commandParts[wordIndex++]) {
			case "on":
				wordIndex = splitCommaStart(commandParts, newTask, wordIndex, comma);

				addOneHourToEnd(newTask);
				newTask.setTaskType("event");
				if (newTask.getTaskStartDate() == -1 && !(newTask.getTaskEndDate() == -1)) {
					newTask.setTaskStartDate(getCurrentDate());
					newTask.setTaskEndDate(getCurrentDate());
				}
				if (newTask.getTaskStartTime() == -1 && !(newTask.getTaskEndTime() == -1)) {
					newTask.setTaskStartTime(0);
				}
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

				if (!(newTask.getTaskStartDate() == -1) && newTask.getTaskEndDate() == -1) {
					newTask.setTaskEndDate(newTask.getTaskStartDate());
				}
				newTask.setTaskType("event");
				break;

			}
			if (newTask.getTaskType().equals("event")) {
				if (newTask.getTaskStartDate() == -1 && newTask.getTaskEndDate() == -1) {
					newTask.setTaskStartDate(getCurrentDate());
					newTask.setTaskEndDate(getCurrentDate());
				}
				if (newTask.getTaskStartTime() == -1 && newTask.getTaskEndTime() == -1) {
					newTask.setTaskStartTime(0);
					newTask.setTaskEndTime(2359);
				}
			}
			if (newTask.getTaskType().equals("deadline")) {
				if (newTask.getTaskStartDate() == -1 && newTask.getTaskEndDate() == -1) {
					newTask.setTaskEndDate(getCurrentDate());
				}
				if (newTask.getTaskStartTime() == -1 && newTask.getTaskEndTime() == -1) {
					newTask.setTaskEndTime(getCurrentTime());
				}
			}

		}
		return wordIndex;
	}

	private String removeSlashes(String date) {
		date = date.replaceAll("/", "");
		return date;
	}

	private void addOneDayToStart(Task newTask) {
		int date = Integer.valueOf(getCurrentDate());
		int time = Integer.valueOf(getCurrentTime());
		Calendar endDate = Calendar.getInstance();

		SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyy HHmm");
		endDate.set((date % 100) + 2000, (date / 100) % 100 - 1, (date / 10000), time / 100, time % 100, 0);
		endDate.add(Calendar.HOUR, 24);
		String[] timeInfo = dateFormatter.format(endDate.getTime()).split(" ");
		newTask.setTaskStartDate(timeInfo[0]);

	}

	private void addOneDayToEnd(Task newTask) {
		int date = Integer.valueOf(getCurrentDate());
		int time = Integer.valueOf(getCurrentTime());
		Calendar endDate = Calendar.getInstance();

		SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyy HHmm");
		endDate.set((date % 100) + 2000, (date / 100) % 100 - 1, (date / 10000), time / 100, time % 100, 0);
		endDate.add(Calendar.HOUR, 24);
		String[] timeInfo = dateFormatter.format(endDate.getTime()).split(" ");
		newTask.setTaskEndDate(timeInfo[0]);

	}

	private void addOneHourToEnd(Task newTask) {
		int date = newTask.getTaskStartDate();
		int time = newTask.getTaskStartTime();
		Calendar endTime = Calendar.getInstance();
		endTime.set(((date % 100) + 2000), (date / 100) % 100 - 1, (date / 10000), time / 100, time % 100, 0);
		endTime.add(Calendar.HOUR, 1);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyy HHmm");
		String[] timeInfo = dateFormatter.format(endTime.getTime()).split(" ");
		newTask.setTaskEndDate(timeInfo[0]);
		newTask.setTaskEndTime(timeInfo[1]);
	}

	public String getCurrentDate() {
		DateFormat df = new SimpleDateFormat("ddMMyy");
		Date dateobj = new Date();
		String currentDate = df.format(dateobj);
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
		taskOn = removeSlashes(taskOn);

		if (taskOn.length() == 4) {
			int taskOnInt = Integer.valueOf(taskOn);
			if (taskOnInt > 2400 || taskOnInt < 0) {
				throw new Exception("Time out of range");
			}
			newTask.setTaskStartTime(taskOn);
		} else if (taskOn.length() == 6) {
			if(!isDateValid(taskOn)){
				throw new Exception("Invalid Date");
			}
			newTask.setTaskStartDate(taskOn);
		} else if (taskOn.equals("tomorrow")) {
			addOneDayToStart(newTask);
			addOneDayToEnd(newTask);
		}
		if (comma) {
			taskOn = commandParts[++wordIndex];
			if (taskOn.length() == 4) {
				int taskOnInt = Integer.valueOf(taskOn);
				if (taskOnInt > 2400 || taskOnInt < 0) {
					throw new Exception("Time out of range");
				}
				newTask.setTaskStartTime(taskOn);
			} else if (taskOn.length() == 6) {
				if(!isDateValid(taskOn)){
					throw new Exception("Invalid Date");
				}
				newTask.setTaskStartDate(taskOn);
			} else if (taskOn.equals("tomorrow")) {
				addOneDayToStart(newTask);
				addOneDayToEnd(newTask);
			}
			// wordIndex = wordIndex + 1;
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
		taskTo = removeSlashes(taskTo);
		if (taskTo.length() == 4) {
			int taskToInt = Integer.valueOf(taskTo);
			if (taskToInt > 2400 || taskToInt < 0) {
				throw new Exception("Time out of range");
			}
			newTask.setTaskEndTime(taskTo);
		} else if (taskTo.length() == 6) {
			if(!isDateValid(taskTo)){
				throw new Exception("Invalid Date");
			}
			newTask.setTaskEndDate(taskTo);
		}

		else if (taskTo.equals("tomorrow")) {
			addOneDayToEnd(newTask);

		}

		if (comma) {
			taskTo = commandParts[++wordIndex];
			if (taskTo.length() == 4) {
				int taskToInt = Integer.valueOf(taskTo);
				if (taskToInt > 2400 || taskToInt < 0) {
					throw new Exception("Time out of range");
				}
				newTask.setTaskEndTime(taskTo);
			} else if (taskTo.length() == 6) {
				if(!isDateValid(taskTo)){
					throw new Exception("Invalid Date");
				}
				newTask.setTaskEndDate(taskTo);
			} else if (taskTo.equals("tomorrow")) {
				addOneDayToEnd(newTask);

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
		return date.substring(0, 2);
	}

	public String getMonth(String date) {
		return date.substring(2, 4);
	}

	public String getYear(String date) {
		return date.substring(4, 6);
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

	String toTwoDigit(String num) {
		if (num.length() == 1) {
			num = "0" + num;
		}

		return num;
	}

	String toSixDigit(int num) {
		String numString = Integer.toString(num);
		if (numString.length() == 5) {
			numString = "0" + numString;
		}

		return numString;
	}

	public boolean nextPriorityIsHigher(String currentPriority, String nextPriority) {
		if (nextPriority.equals("high") && !currentPriority.equals("high")) {
			return true;
		} else
			if (nextPriority.equals("medium") && !currentPriority.equals("high") && !currentPriority.equals("medium")) {
			return true;
		} else {
			return false;
		}
	}

	int parseInt(String str) {
		int i = 0;
		int num = 0;
		boolean isNeg = false;

		// Check for negative sign; if it's there, set the isNeg flag
		if (str.charAt(0) == '-') {
			isNeg = true;
			i = 1;
		}

		// Process each character of the string;
		while (i < str.length()) {
			num *= 10;
			num += str.charAt(i++) - '0'; // Minus the ASCII code of '0' to get
											// the value of the charAt(i++).
		}

		if (isNeg)
			num = -num;
		return num;
	}

	public static boolean isDateValid(String date) {
		try {
			DateFormat df = new SimpleDateFormat("ddMMyy");
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
