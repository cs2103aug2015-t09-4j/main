package LemonBuddy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Parser {

	private static final String KEYWORD_TOMORROW = "tomorrow";
	private static final String KEYWORD_BY = "by";
	private static final String KEYWORD_DESCRIPTION = "desc";
	private static final String KEYWORD_PRIORITY = "*";
	private static final String KEYWORD_FROM = "from";
	private static final String KEYWORD_ON = "on";
	private static final String TASKTYPE_FLOATING = "floating";
	private static final String TASKTYPE_DEADLINE = "deadline";
	private static final String TASKTYPE_EVENT = "event";

	public Task parseTask(String[] commandParts) throws Exception {
		Task newTask = new Task();
		int wordIndex = 1;
		String taskName = "";
		newTask.setTaskType(TASKTYPE_FLOATING);
		wordIndex = parseTaskName(commandParts, newTask, wordIndex, taskName);
		wordIndex = parseDescription(commandParts, newTask, wordIndex);
		wordIndex = parsePriority(commandParts, newTask, wordIndex);
		wordIndex = parseTime(commandParts, newTask, wordIndex);

		return newTask;

	}

	private int parseTaskName(String[] commandParts, Task newTask, int wordIndex, String taskName) {
		while (true) {

			if (commandParts[wordIndex].equals(KEYWORD_ON) || commandParts[wordIndex].equals(KEYWORD_FROM)
					|| commandParts[wordIndex].substring(0, 1).equals(KEYWORD_PRIORITY)
					|| commandParts[wordIndex].equals(KEYWORD_DESCRIPTION)
					|| commandParts[wordIndex].equals(KEYWORD_BY)) {
				break;
			}
			taskName = taskName + commandParts[wordIndex++];
			if (wordIndex >= commandParts.length) {
				break;
			}
			taskName = taskName + " ";
		}
		taskName = taskName.trim();
		newTask.setTaskName(taskName);
		return wordIndex;
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
			if (commandParts[wordIndex].equals(KEYWORD_DESCRIPTION)) {
				String taskDesc = "";
				wordIndex++;
				while (true) {
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
			if (commandParts[wordIndex].substring(0, 1).equals(KEYWORD_PRIORITY)) {
				newTask.setTaskPriority(commandParts[wordIndex].substring(1));
			}
			wordIndex++;
		}
		return initialIndex;
	}

	public static Task createTaskFromString(String s) {
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

	private int parseTime(String[] commandParts, Task newTask, int wordIndex) throws Exception {

		detectFromToFormat(commandParts);
		setInputTime(commandParts, newTask, wordIndex);
		setFromToDefaultTime(newTask);
		setDeadlineDefaultTime(newTask);

		checkValidDateTimeInput(newTask);
		return wordIndex;
	}

	private void checkValidDateTimeInput(Task newTask) throws ParseException, Exception {
		if (newTask.getTaskType().equals(TASKTYPE_EVENT)) {
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
			Date date1 = sdf.parse(newTask.getTaskStartDate());
			Date date2 = sdf.parse(newTask.getTaskEndDate());
			if (date1.after(date2)) {
				System.out.println("Date1 is after Date2");
				throw new Exception("Start date after end date");
			}

			if (newTask.getTaskStartDate() == newTask.getTaskEndDate()) {
				if (Integer.parseInt(newTask.getTaskStartTime()) > Integer.parseInt(newTask.getTaskEndTime())) {
					throw new Exception("Start time is after end time");
				}
			}
		}
	}

	private void setInputTime(String[] commandParts, Task newTask, int wordIndex) throws Exception {
		while (wordIndex < commandParts.length) {
			if (commandParts[wordIndex].equals("desc")) {
				break;
			}
			Boolean comma = false;
			switch (commandParts[wordIndex++]) {

			case KEYWORD_ON:
				wordIndex = splitCommaStart(commandParts, newTask, wordIndex, comma);
				// qSystem.out.println("time = " + newTask.getTaskStartTime());
				setOnDefaultTime(newTask);
				addOneHourToEnd(newTask);
				newTask.setTaskType(TASKTYPE_EVENT);
				break;
			case KEYWORD_BY:
				wordIndex = splitCommaEnd(commandParts, newTask, wordIndex, comma);
				newTask.setTaskType(TASKTYPE_DEADLINE);
				break;
			case KEYWORD_FROM:
				wordIndex = splitCommaStart(commandParts, newTask, wordIndex, comma);

				wordIndex++;
				wordIndex++;
				wordIndex = splitCommaEnd(commandParts, newTask, wordIndex, comma);

				newTask.setTaskType(TASKTYPE_EVENT);
				break;
			}

		}
	}

	private void setOnDefaultTime(Task newTask) {
		if (newTask.taskStartDateIsEmpty() && newTask.taskEndDateIsEmpty()) {
			newTask.setTaskStartDate(getCurrentDate());
			newTask.setTaskEndDate(getCurrentDate());
		}
		if (newTask.taskStartDateIsEmpty() && !newTask.taskEndDateIsEmpty()) {
			newTask.setTaskStartDate(newTask.getTaskEndDate());
		}
		if (!newTask.taskStartDateIsEmpty() && newTask.taskEndDateIsEmpty()) {
			newTask.setTaskEndDate(newTask.getTaskStartDate());
		}
		if (newTask.taskStartTimeIsEmpty() && newTask.taskEndTimeIsEmpty()) {
			newTask.setTaskStartTime(getCurrentTime());
			newTask.setTaskEndTime(getCurrentTime());
		}
	}

	private void detectFromToFormat(String[] commandParts) throws Exception {
		Boolean from = false;
		Boolean to = false;
		for (String part : commandParts) {
			if (part.contains(KEYWORD_FROM)) {
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
	}

	private void setDeadlineDefaultTime(Task newTask) {
		if (newTask.getTaskType().equals(TASKTYPE_DEADLINE)) {
			if (newTask.taskStartDateIsEmpty() && newTask.taskEndDateIsEmpty()) {
				newTask.setTaskEndDate(getCurrentDate());
			}
			if (newTask.taskStartTimeIsEmpty() && newTask.taskEndTimeIsEmpty()) {
				newTask.setTaskEndTime(getCurrentTime());
			}
		}
	}

	private void setFromToDefaultTime(Task newTask) {
		if (newTask.getTaskType().equals(TASKTYPE_EVENT)) {
			if (newTask.taskStartDateIsEmpty() && newTask.taskEndDateIsEmpty()) {
				newTask.setTaskStartDate(getCurrentDate());
				newTask.setTaskEndDate(getCurrentDate());
			}
			if (newTask.taskStartDateIsEmpty() && !newTask.taskEndDateIsEmpty()) {
				newTask.setTaskStartDate(newTask.getTaskEndDate());
			}
			if (!newTask.taskStartDateIsEmpty() && newTask.taskEndDateIsEmpty()) {
				newTask.setTaskEndDate(newTask.getTaskStartDate());
			}
			if (newTask.taskStartTimeIsEmpty() && newTask.taskEndTimeIsEmpty()) {
				newTask.setTaskStartTime("0000");
				newTask.setTaskEndTime("2359");
			}
			if (newTask.taskStartTimeIsEmpty() && !newTask.taskEndTimeIsEmpty()) {
				newTask.setTaskStartTime(newTask.getTaskEndTime());
			}
			if (!newTask.taskStartTimeIsEmpty() && newTask.taskEndTimeIsEmpty()) {
				newTask.setTaskEndTime(newTask.getTaskStartTime());
			}
		}
	}

	private String removeSlashes(String date) {
		date = date.replaceAll("/", "");
		return date;
	}

	private void addOneDayToStart(Task newTask) {
		Calendar startDate = Calendar.getInstance();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyy HHmm");

		retrieveCurrentCalendar(startDate);
		startDate.add(Calendar.HOUR, 24);
		String[] timeInfo = dateFormatter.format(startDate.getTime()).split(" ");
		newTask.setTaskStartDate(timeInfo[0]);

	}

	private void retrieveCurrentCalendar(Calendar startDate) {
		String date = getCurrentDate();
		String time = getCurrentTime();
		startDate.set(Integer.parseInt(date.substring(4,6)), Integer.parseInt(date.substring(2,4))-1, Integer.parseInt(date.substring(0,2)), Integer.parseInt(time.substring(0,2)), Integer.parseInt(time.substring(2,4)), 0);

	}

	private void addOneDayToEnd(Task newTask) {
		Calendar endDate = Calendar.getInstance();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyy HHmm");
		retrieveCurrentCalendar(endDate);
		endDate.add(Calendar.HOUR, 24);
		String[] timeInfo = dateFormatter.format(endDate.getTime()).split(" ");
		newTask.setTaskEndDate(timeInfo[0]);

	}

	private void addOneHourToEnd(Task newTask) {
		Calendar endTime = Calendar.getInstance();
		retrieveStartTimeCalendar(newTask, endTime);
		endTime.add(Calendar.HOUR, 1);
		setNewEndTime(newTask, endTime);
	}

	private void setNewEndTime(Task newTask, Calendar endTime) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyy HHmm");
		String[] timeInfo = dateFormatter.format(endTime.getTime()).split(" ");
		newTask.setTaskEndDate(timeInfo[0]);
		newTask.setTaskEndTime(timeInfo[1]);
	}

	private void retrieveStartTimeCalendar(Task newTask, Calendar endTime) {
		String date = newTask.getTaskStartDate();
		String time = newTask.getTaskStartTime();
		endTime.set(Integer.parseInt(date.substring(4, 6)), Integer.parseInt(date.substring(2, 4)) - 1,
				Integer.parseInt(date.substring(0, 2)), Integer.parseInt(time.substring(0, 2)),
				Integer.parseInt(time.substring(2, 4)), 0);
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
			if (!isDateValid(taskOn)) {
				throw new Exception("Invalid Date");
			}
			newTask.setTaskStartDate(taskOn);
		} else if (taskOn.equals(KEYWORD_TOMORROW)) {
			addOneDayToStart(newTask);
			addOneDayToEnd(newTask);
		}
		if (comma) {
			if (++wordIndex >= commandParts.length) {
				throw new Exception("No time specified after comma");
			}
			taskOn = commandParts[wordIndex];
			taskOn = removeSlashes(taskOn);
			if (taskOn.length() == 4) {
				int taskOnInt = Integer.valueOf(taskOn);
				if (taskOnInt > 2400 || taskOnInt < 0) {
					throw new Exception("Time out of range");
				}
				newTask.setTaskStartTime(taskOn);
			} else if (taskOn.length() == 6) {
				if (!isDateValid(taskOn)) {
					throw new Exception("Invalid Date");
				}
				newTask.setTaskStartDate(taskOn);
			} else if (taskOn.equals(KEYWORD_TOMORROW)) {
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
			if (!isDateValid(taskTo)) {
				throw new Exception("Invalid Date");
			}
			newTask.setTaskEndDate(taskTo);
		}

		else if (taskTo.equals(KEYWORD_TOMORROW)) {
			addOneDayToEnd(newTask);

		}

		if (comma) {
			if (++wordIndex >= commandParts.length) {
				throw new Exception("No time specified after comma");
			}
			taskTo = commandParts[wordIndex];
			taskTo = removeSlashes(taskTo);
			if (taskTo.length() == 4) {
				int taskToInt = Integer.valueOf(taskTo);
				if (taskToInt > 2400 || taskToInt < 0) {
					throw new Exception("Time out of range");
				}
				newTask.setTaskEndTime(taskTo);
			} else if (taskTo.length() == 6) {
				if (!isDateValid(taskTo)) {
					throw new Exception("Invalid Date");
				}
				newTask.setTaskEndDate(taskTo);
			} else if (taskTo.equals(KEYWORD_TOMORROW)) {
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
		} else if (nextPriority.equals("medium") && !currentPriority.equals("high")
				&& !currentPriority.equals("medium")) {
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
