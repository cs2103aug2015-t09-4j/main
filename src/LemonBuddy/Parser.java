
//@@author A0124209N
package LemonBuddy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

	private static final String DATETIMEFORMAT = "ddMMyy";
	private static final String KEYWORD_TO = "to";
	private static final String SIMPLEDATETIMEFORMAT = "ddMMyy HHmm";
	private static final String KEYWORD_TOMORROW = "tomorrow";
	private static final String KEYWORD_BY = "by";
	private static final String KEYWORD_DESCRIPTION = "desc";
	private static final String KEYWORD_PRIORITY = "*";
	private static final String KEYWORD_FROM = "from";
	
	private static final String KEYWORD_ON = "on";
	private static final String TASKTYPE_DEADLINE = "deadline";
	private static final String TASKTYPE_EVENT = "event";
	private static Parser parser;
	private static Logger logger = Logger.getLogger("Parser");
	
	public static Parser getInstance(){
		if(parser == null)
			parser = new Parser();
		return parser;
	}
	/***************************************Create Task from string ******************************************************************/
	public Task parseTask(String[] commandParts) throws Exception {
		logger.log(Level.INFO, "Executing parse task");
		Task newTask = new Task();
		int wordIndex = 1;
		String taskName = "";
		parseTaskName(commandParts, newTask, wordIndex, taskName);
		parseDescription(commandParts, newTask, wordIndex);
		parsePriority(commandParts, newTask, wordIndex);
		parseTime(commandParts, newTask, wordIndex);

		return newTask;
	}

	/************************************** Builds name until first keyword ******************************************************************/
	private int parseTaskName(String[] commandParts, Task newTask, int wordIndex, String taskName) {
		logger.log(Level.INFO, "Parsing Name");
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

	/**************************************** Builds description after KEYWORD_DESCRIPTION *****************************************/
	private int parseDescription(String[] commandParts, Task newTask, int wordIndex) {
		logger.log(Level.INFO, "Parsing description");
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

	/********************************* Builds priority after detecting KEYWORD_PRIORITY *******************/
	private int parsePriority(String[] commandParts, Task newTask, int wordIndex) throws Exception {
		logger.log(Level.INFO, "Parsing priority");
		int initialIndex = wordIndex;
		while (wordIndex < commandParts.length) {
			if (commandParts[wordIndex].substring(0, 1).equals(KEYWORD_PRIORITY)) {
				newTask.setTaskPriority(commandParts[wordIndex].substring(1));
			}
			wordIndex++;
		}
		return initialIndex;
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

			SimpleDateFormat sdf = new SimpleDateFormat(DATETIMEFORMAT);
			Date date1 = sdf.parse(newTask.getTaskStartDate());
			Date date2 = sdf.parse(newTask.getTaskEndDate());
			if (date1.after(date2)) {
				throw new Exception("Start date is after end date");
			}
			if (newTask.getTaskStartDate().equals(newTask.getTaskEndDate())) {
				if (Integer.parseInt(newTask.getTaskStartTime()) > Integer.parseInt(newTask.getTaskEndTime())) {
					throw new Exception("Start time is after end time");
				}
			}
		}
	}

	private void setInputTime(String[] commandParts, Task newTask, int wordIndex) throws Exception {
		String[] timeInfo = new String[2];
		while (wordIndex < commandParts.length) {
			if (commandParts[wordIndex].equals(KEYWORD_DESCRIPTION)) {
				break;
			}
			switch (commandParts[wordIndex++]) {

			case KEYWORD_ON:
				timeInfo = getTimeInfo(commandParts, newTask, wordIndex);
				newTask.setTaskStartDate(timeInfo[0]);
				newTask.setTaskStartTime(timeInfo[1]);
				setOnDefaultTime(newTask);
				timeInfo = addOneHour(newTask.getTaskStartDate(), newTask.getTaskStartTime());
				newTask.setTaskEndDate(timeInfo[0]);
				newTask.setTaskEndTime(timeInfo[1]);

				newTask.setTaskType(TASKTYPE_EVENT);
				break;
			case KEYWORD_BY:
				timeInfo = getTimeInfo(commandParts, newTask, wordIndex);
				newTask.setTaskEndDate(timeInfo[0]);
				newTask.setTaskEndTime(timeInfo[1]);
				newTask.setTaskType(TASKTYPE_DEADLINE);
				break;
			case KEYWORD_FROM:
				timeInfo = getTimeInfo(commandParts, newTask, wordIndex);
				newTask.setTaskStartDate(timeInfo[0]);
				newTask.setTaskStartTime(timeInfo[1]);
				for (int i = wordIndex; i < commandParts.length; i++) {
					if (commandParts[i].equals(KEYWORD_TO)) {
						i++;
						wordIndex = i;
						break;
					}
				}
				timeInfo = getTimeInfo(commandParts, newTask, wordIndex);

				newTask.setTaskEndDate(timeInfo[0]);
				newTask.setTaskEndTime(timeInfo[1]);
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

	//@@author A0127147H
	private void detectFromToFormat(String[] commandParts) throws Exception {
		Boolean from = false;
		Boolean to = false;
		for (String part : commandParts) {
			if (part.contains(KEYWORD_FROM)) {
				from = true;
			}
		}
		for (String part : commandParts) {
			if (part.contains(KEYWORD_TO)) {
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

	private String addOneDay(String date, String time) {
		Calendar initialTime = Calendar.getInstance();
		SimpleDateFormat dateFormatter = new SimpleDateFormat(SIMPLEDATETIMEFORMAT);
		time = getCurrentTime();
		initialTime.set(Integer.parseInt(date.substring(4, 6)), Integer.parseInt(date.substring(2, 4)) - 1,
				Integer.parseInt(date.substring(0, 2)), Integer.parseInt(time.substring(0, 2)),
				Integer.parseInt(time.substring(2, 4)), 0);
		initialTime.add(Calendar.HOUR, 24);
		String[] timeInfo = dateFormatter.format(initialTime.getTime()).split(" ");
		return timeInfo[0];

	}

	private String[] addOneHour(String date, String time) {
		Calendar initialTime = Calendar.getInstance();
		initialTime.set(Integer.parseInt(date.substring(4, 6)), Integer.parseInt(date.substring(2, 4)) - 1,
				Integer.parseInt(date.substring(0, 2)), Integer.parseInt(time.substring(0, 2)),
				Integer.parseInt(time.substring(2, 4)), 0);
		initialTime.add(Calendar.HOUR, 1);
		SimpleDateFormat dateFormatter = new SimpleDateFormat(SIMPLEDATETIMEFORMAT);
		String[] timeInfo = dateFormatter.format(initialTime.getTime()).split(" ");
		return timeInfo;
	}

	public String getCurrentDate() {
		DateFormat df = new SimpleDateFormat(DATETIMEFORMAT);
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

	// 0 for date 1 for time
	private String[] getTimeInfo(String[] commandParts, Task newTask, int wordIndex) throws Exception {
		logger.log(Level.INFO, "Parsing time");
		String[] timeInfo = new String[2];
		timeInfo[0] = "-1";
		timeInfo[1] = "-1";

		String currentDate = getCurrentDate();
		String currentTime = getCurrentTime();
		String taskOn = commandParts[wordIndex];
		// trim and comma is present
		if (commandParts[wordIndex].contains(",")) {
			if (!(commandParts[wordIndex].indexOf(",") == commandParts[wordIndex].length() - 1)) {
				throw new Exception("Comma has no spacing");
			}
		}

		int parseCount = 1;
		for (int i = 0; i < parseCount; i++) {
			taskOn = commandParts[wordIndex];
			if (commandParts[wordIndex].contains(",")) {
				taskOn = commandParts[wordIndex].substring(0, commandParts[wordIndex].indexOf(","));
				parseCount = 2;
			}
			taskOn = removeSlashes(taskOn);
			if (!(taskOn.matches("[0-9]+") && ((taskOn.length() == 4) || (taskOn.length() == 6))
					|| (taskOn.equals(KEYWORD_TOMORROW)))) {
				throw new Exception("Invalid date/time format");
			}
			if (taskOn.length() == 4) {
				int taskOnInt = Integer.valueOf(taskOn);
				if (taskOnInt >= 2400 || taskOnInt < 0) {
					throw new Exception("Time out of range");
				}
				timeInfo[1] = taskOn;
			} else if (taskOn.length() == 6) {
				if (!isDateValid(taskOn)) {
					throw new Exception("Invalid Date");
				}
				timeInfo[0] = taskOn;
			} else if (taskOn.equals(KEYWORD_TOMORROW)) {
				String nextDate = addOneDay(currentDate, currentTime);
				timeInfo[0] = nextDate;
			}
			wordIndex++;
		}

		return timeInfo;
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

	protected int parseInt(String str) {
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
			DateFormat df = new SimpleDateFormat(DATETIMEFORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
