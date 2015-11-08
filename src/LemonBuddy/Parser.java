package LemonBuddy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Parser extends TimeHandler {

	private static final String KEYWORD_TOMORROW = "tomorrow";
	private static final String KEYWORD_BY = "by";
	private static final String KEYWORD_DESCRIPTION = "desc";
	private static final String KEYWORD_PRIORITY = "*";
	private static final String KEYWORD_FROM = "from";
	private static final String KEYWORD_ON = "on";
	private static final String TASKTYPE_FLOATING = "floating";
	private static final String TASKTYPE_DEADLINE = "deadline";
	private static final String TASKTYPE_EVENT = "event";
	private static TimeHandler timehandler = new TimeHandler();

	public Task parseTask(String[] commandParts) throws Exception {
		Task newTask = new Task();
		int wordIndex = 1;
		String taskName = "";
		newTask.setTaskType(TASKTYPE_FLOATING);
		parseTaskName(commandParts, newTask, wordIndex, taskName);
		parseDescription(commandParts, newTask, wordIndex);
		parsePriority(commandParts, newTask, wordIndex);
		parseTime(commandParts, newTask, wordIndex);

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

	

	private void setInputTime(String[] commandParts, Task newTask, int wordIndex) throws Exception {
		String[] timeInfo = new String[2];
		while (wordIndex < commandParts.length) {
			if (commandParts[wordIndex].equals("desc")) {
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
					System.out.println(commandParts[i]);
					if (commandParts[i].equals("to")) {
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
			newTask.setTaskStartDate(timehandler.getCurrentDate());
			newTask.setTaskEndDate(timehandler.getCurrentDate());
		}
		if (newTask.taskStartDateIsEmpty() && !newTask.taskEndDateIsEmpty()) {
			newTask.setTaskStartDate(newTask.getTaskEndDate());
		}
		if (!newTask.taskStartDateIsEmpty() && newTask.taskEndDateIsEmpty()) {
			newTask.setTaskEndDate(newTask.getTaskStartDate());
		}
		if (newTask.taskStartTimeIsEmpty() && newTask.taskEndTimeIsEmpty()) {
			newTask.setTaskStartTime(timehandler.getCurrentTime());
			newTask.setTaskEndTime(timehandler.getCurrentTime());
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
				newTask.setTaskEndDate(timehandler.getCurrentDate());
			}
			if (newTask.taskStartTimeIsEmpty() && newTask.taskEndTimeIsEmpty()) {
				newTask.setTaskEndTime(timehandler.getCurrentTime());
			}
		}
	}

	private void setFromToDefaultTime(Task newTask) {
		if (newTask.getTaskType().equals(TASKTYPE_EVENT)) {
			if (newTask.taskStartDateIsEmpty() && newTask.taskEndDateIsEmpty()) {
				newTask.setTaskStartDate(timehandler.getCurrentDate());
				newTask.setTaskEndDate(timehandler.getCurrentDate());
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

	// 0 for date 1 for time
	private String[] getTimeInfo(String[] commandParts, Task newTask, int wordIndex) throws Exception {
		String[] timeInfo = new String[2];
		timeInfo[0] = "-1";
		timeInfo[1] = "-1";

		String currentDate = timehandler.getCurrentDate();
		String currentTime = timehandler.getCurrentTime();
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
			System.out.println(taskOn);
			if (commandParts[wordIndex].contains(",")) {
				taskOn = commandParts[wordIndex].substring(0, commandParts[wordIndex].indexOf(","));
				parseCount = 2;
			}
			taskOn = removeSlashes(taskOn);
			if (taskOn.length() == 4) {
				int taskOnInt = Integer.valueOf(taskOn);
				if (taskOnInt > 2400 || taskOnInt < 0) {
					throw new Exception("Time out of range");
				}
				timeInfo[1] = taskOn;
			} else if (taskOn.length() == 6) {
				if (!timehandler.isDateValid(taskOn)) {
					throw new Exception("Invalid Date");
				}
				timeInfo[0] = taskOn;
			} else if (taskOn.equals(KEYWORD_TOMORROW)) {
				String nextDate = timehandler.addOneDay(currentDate, currentTime);
				timeInfo[0] = nextDate;
			}
			wordIndex++;
		}

		return timeInfo;
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

}
