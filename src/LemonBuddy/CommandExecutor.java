package LemonBuddy;

import java.io.File;
import java.io.IOException;

import LemonBuddy.view.*;
import java.util.ArrayList;
import java.util.Stack;

public class CommandExecutor {
	private static String path = "C:\\eclipse\\Your sdk your majesty\\main\\test.txt";
	// private static String path =
	// "C:\\Users\\user\\workspace\\main\\test.txt";
	private Parser parser;
	String lastState;
	Stack<String> lastStates;
	Stack<String> undoneStates;

	public CommandExecutor() {
		if (parser == null) {
			parser = new Parser();
		}
		lastStates = new Stack<String>();
		undoneStates = new Stack<String>();
		lastState = "";
	}

	// PRIORITY AND DESCRIPTION NOT DONE
	public void executeAdd(String[] commandParts) throws Exception {
		// assume floating first

		Task newTask = parser.parseTask(commandParts);
		FileStorage.writeObjectAsString(newTask);
		LemonGUIController.setTask(newTask);
		LemonGUIController.setCommand(commandParts[0]);
	}

	public void executeEdit(String[] commandParts) throws Exception {
		LemonGUIController.setCommand(commandParts[0]);
		Task newTask = parser.parseTask(commandParts);
		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		FileStorage.clear();

		int taskToEditIndex = writeUntilTaskIndex(commandParts, fullList);
		Task taskToEdit = fullList.get(taskToEditIndex);
		FileStorage.writeObjectAsString(newTask.merge(taskToEdit));
		LemonGUIController.setTask(newTask);
		writeRestOfList(fullList, taskToEditIndex);
	}

	public void executeDelete(String[] commandParts) throws Exception {
		String deleteId = commandParts[2];
		ArrayList<Task> array = FileStorage.readStringAsObject(path);
		assert(array != null) : "unable to read from specified path";
		if (Integer.valueOf(deleteId) > array.size() || Integer.valueOf(deleteId) <= 0) {
			return;
		}
		LemonGUIController.setCommand(commandParts[0]);
		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		FileStorage.clear();

		int taskToDeleteIndex = writeUntilTaskIndex(commandParts, fullList);
		Task taskToDelete = fullList.get(taskToDeleteIndex);
		LemonGUIController.setTask(taskToDelete);
		writeRestOfList(fullList, taskToDeleteIndex);
	}

	private int writeUntilTaskIndex(String[] commandParts, ArrayList<Task> fullList) throws IOException {
		int indexToReturn = 0;
		int taskTypeIndex = 1;
		String editType = commandParts[1];
		String editId = commandParts[2];
		int j = 0;
		for (j = 0; j < fullList.size(); j++) {
			Task currentTask = fullList.get(j);
			if (currentTask.getTaskType().equals(editType)
					&& !(currentTask.getTaskIsOverdue() || currentTask.getTaskIsDone())) {
				if (editId.equals(String.valueOf(taskTypeIndex))) {
					break;
				} else {
					taskTypeIndex++;
					FileStorage.writeObjectAsString(currentTask);
				}
			} else {

				FileStorage.writeObjectAsString(currentTask);
			}
		}
		return j;
	}

	private void writeRestOfList(ArrayList<Task> fullList, int taskToEditIndex) throws IOException {
		for (int i = taskToEditIndex + 1; i < fullList.size(); i++) {
			FileStorage.writeObjectAsString(fullList.get(i));
		}
	}

	public void executeRecur(String[] commandParts) throws IOException, ClassNotFoundException {
		String recurType = commandParts[1];
		String recurID = commandParts[2];
		String recurFreq = commandParts[3];
		String recurEndDate = commandParts[4];

		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		int taskTypeIndex = 1;

		for (int i = 0; i < fullList.size(); i++) {
			Task currentTask = fullList.get(i);

			if (currentTask.getTaskType().equals(recurType)) {
				if (recurID.equals(String.valueOf(taskTypeIndex))) {
					taskTypeIndex++;
					Task recurringTask = currentTask;
					if (recurType.equals("deadline")) {
						// note: changed
						String currentRecurringDate = Integer.toString(currentTask.getTaskEndDate());
						if (recurFreq.equals("yearly")) {
							currentRecurringDate = parser.addOneYear(currentRecurringDate);
							while (!parser.endDatePassed(currentRecurringDate, recurEndDate)) {
								recurringTask.setTaskEndDate(currentRecurringDate);
								FileStorage.writeObjectAsString(recurringTask);
								currentRecurringDate = parser.addOneYear(currentRecurringDate);
							}
						}

						/*
						 * while (!parser.endDatePassed(currentRecurringDate,
						 * recurEndDate)) { System.out.println(
						 * "While loop entered"); if
						 * (recurFreq.equals("yearly")) { currentRecurringDate =
						 * parser.addOneYear(currentRecurringDate);
						 * recurringTask.setTaskEndDate(currentRecurringDate);
						 * System.out.println(currentRecurringDate);
						 * FileStorage.write(recurringTask); } }
						 */

					} else if (recurType == "event") {

					}

				} else {
					taskTypeIndex++;
				}
			}
		}
	}

	public static void executeNavigate(String[] commandParts) throws ClassNotFoundException, IOException {
		// get days related to day
		LemonGUIController.setTimeLineDate(commandParts[1]);
		int timelineDate = Integer.valueOf(commandParts[1]);

		int dayTimeLine = timelineDate / 10000;
		int monthTimeLine = (timelineDate / 100) % 100;
		int yearTimeLine = timelineDate % 100;
		int comparedTimeline = dayTimeLine + monthTimeLine * 100 + yearTimeLine * 10000;

		ArrayList<Task> deadlineTasks = new ArrayList<Task>();
		ArrayList<Task> eventTasks = new ArrayList<Task>();
		ArrayList<Task> fullList = new ArrayList<Task>();
		Task currentTask;

		fullList = FileStorage.readStringAsObject(path);
		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			int dateStart = currentTask.getTaskStartDate();
			int dateEnd = currentTask.getTaskEndDate();

			int dayStart = dateStart / 10000;
			int monthStart = (dateStart / 100) % 100;
			int yearStart = dateStart % 100;
			int comparedStartDate = dayStart + monthStart * 100 + yearStart * 10000;

			int dayEnd = dateEnd / 10000;
			int monthEnd = (dateEnd / 100) % 100;
			int yearEnd = dateEnd % 100;
			int comparedEndDate = dayEnd + monthEnd * 100 + yearEnd * 10000;
			if (currentTask.getTaskType().equals("deadline")) {
				if (currentTask.getTaskEndDate() == timelineDate) {
					deadlineTasks.add(currentTask);
				}
			}
			if (currentTask.getTaskType().equals("event")) {
				if (comparedEndDate >= comparedTimeline && comparedStartDate <= comparedTimeline) {
					eventTasks.add(currentTask);
				}
			}
		}

		LemonGUIController.setTimeLineDeadlineList(deadlineTasks);
		LemonGUIController.setTimeLineEventList(eventTasks);

		/*
		 * get what user wants to view date e.g. navigate 010101
		 */
	}

	public void executeHelp() {
		// GUIConsole.displayHelp();
	}

	public void executeUpdate() throws IOException, ClassNotFoundException {
		ArrayList<Task> array = FileStorage.readStringAsObject(path);
		assert(array != null) : "unable to read from specified path";

		String currentDate = parser.getCurrentDate();
		System.out.println(currentDate);

		int i = 0;
		boolean anythingRemoved = false;

		for (i = 0; i < array.size(); i++) {
			Task overdueTask = array.get(i);
			String endDate = parser.toSixDigit(array.get(i).getTaskEndDate());
			System.out.println(endDate);
			if (endDate.length() == 6) {
				if (parser.endDatePassed(currentDate, endDate) && (overdueTask.getTaskIsOverdue() == false)) {
					FileStorage.clear();
					overdueTask.setTaskIsOverdue();
					array.add(overdueTask);
					array.remove(i);
					anythingRemoved = true;
					i--;
				}
			}
		}

		if (anythingRemoved) {
			int j = 0;
			while (j < array.size()) {
				FileStorage.writeObjectAsString(array.get(j));
				j++;
			}
		}
		// GUI?>>?.successfulUpdate();
	}

	public static void executeList(String[] commandParts) throws Exception {
		String listType = commandParts[1];
		LemonGUIController.setCommand(commandParts[0]);
		LemonGUIController.setListType(listType);
		ArrayList<Task> floatingTasks = new ArrayList<Task>();
		ArrayList<Task> deadlineTasks = new ArrayList<Task>();
		ArrayList<Task> eventTasks = new ArrayList<Task>();

		ArrayList<Task> fullList = new ArrayList<Task>();
		Task currentTask;

		fullList = FileStorage.readStringAsObject(path);

		// 3 types of arraylist here
		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			if (currentTask.getTaskType().equals("floating")) {
				floatingTasks.add(currentTask);
			}
			if (currentTask.getTaskType().equals("deadline")) {
				deadlineTasks.add(currentTask);
			}
			if (currentTask.getTaskType().equals("event")) {
				eventTasks.add(currentTask);
			}
		}

		if (listType.equals("floating")) {
			LemonGUIController.setList(floatingTasks);
		} else if (listType.equals("deadline")) {
			LemonGUIController.setList(deadlineTasks);
		} else if (listType.equals("event")) {
			LemonGUIController.setList(eventTasks);
		} else if (listType.equals("all")) {
			LemonGUIController.setList(fullList);
		}
	}

	public void executeDisplay(String[] commandParts) throws Exception {
		ArrayList<Task> fullList = new ArrayList<Task>();
		LemonGUIController.setCommand(commandParts[0]);
		Task currentTask;

		int id = Integer.parseInt(commandParts[1]);
		fullList = FileStorage.readStringAsObject(path);
		currentTask = fullList.get(id - 1);
		LemonGUIController.setTask(currentTask);
	}

	public void parseInvalidCommand(String command) {
		// GUIConsole.displayErrorMessage(command);
	}

	public void executeUndo() throws IOException, Exception {
		// System.out.println(FileStorage.readStringAsString(path));
		undoneStates.push(FileStorage.readStringAsString(path));
		FileStorage.clear();
		FileStorage.writeStringAsString(lastStates.pop());
		LemonGUIController.setCommand("undo");
	}

	public void executeDone(String[] commandParts) throws Exception, IOException {
		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		FileStorage.clear();
		int taskDoneIndex = writeUntilTaskIndex(commandParts, fullList);
		Task taskDone = fullList.get(taskDoneIndex);
		taskDone.setTaskIsDone();
		FileStorage.writeObjectAsString(taskDone);
		writeRestOfList(fullList, taskDoneIndex);

	}

	public void executeSortFloating() throws ClassNotFoundException, IOException {
		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		System.out.println("Hello");
		int sizeToCheck = fullList.size();
		for (int i = 0; i < sizeToCheck; i++) {
			Task currentTask = fullList.get(i);
			System.out.println("tasktype: " + currentTask.getTaskType());
			if (currentTask.getTaskType().equals("floating")) {
				System.out.println("Entered if");
				fullList.add(fullList.remove(i));
				i--;
				sizeToCheck--;
			}
		}

		FileStorage.clear();
		for (int j = 0; j < fullList.size(); j++) {
			FileStorage.writeObjectAsString(fullList.get(j));
		}
	}

	public void executeClear(String[] commandParts) throws ClassNotFoundException, IOException {
		ArrayList<Task> fullList = new ArrayList<Task>();
		Task currentTask;
		fullList = FileStorage.readStringAsObject(path);
		FileStorage.clear();
		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			if (commandParts[1].equals("done")) {
				if(currentTask.getTaskIsDone()){
					continue;
				}
			}
			else if (commandParts[1].equals("overdue")){
				if(currentTask.getTaskIsOverdue()){
					continue;
				}
			}
			FileStorage.writeObjectAsString(currentTask);
		}
	}

	public void executeSort() throws ClassNotFoundException, IOException {
		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		for (int i = 0; i < fullList.size() - 1; i++) {
			Task currentTask = fullList.get(i);
			for (int j = i + 1; j < fullList.size(); j++) {
				Task nextTask = fullList.get(j);
				if (currentTask.getTaskType().equals("deadline")) {
					String currentDate = parser.toSixDigit(currentTask.getTaskEndDate());
					if (nextTask.getTaskType().equals("deadline")) {
						String nextDate = parser.toSixDigit(nextTask.getTaskEndDate());
						if (parser.endDatePassed(currentDate, nextDate)) {
							// fullList.add(j, fullList.remove(i));
							fullList.add(i, nextTask);
							fullList.add(j + 1, currentTask);
							fullList.remove(i + 1);
							fullList.remove(j + 1);
						} else if (currentDate.equals(nextDate)) {
							String currentPriority = currentTask.getTaskPriority();
							String nextPriority = nextTask.getTaskPriority();
							if (parser.nextPriorityIsHigher(currentPriority, nextPriority)) {
								// fullList.add(j, fullList.remove(i));
								fullList.add(i, nextTask);
								fullList.add(j + 1, currentTask);
								fullList.remove(i + 1);
								fullList.remove(j + 1);
							}
						}
					} else if (nextTask.getTaskType().equals("event")) {
						String nextDate = parser.toSixDigit(nextTask.getTaskStartDate());
						if (parser.endDatePassed(currentDate, nextDate)) {
							// fullList.add(j, fullList.remove(i));
							fullList.add(i, nextTask);
							fullList.add(j + 1, currentTask);
							fullList.remove(i + 1);
							fullList.remove(j + 1);
						} else if (currentDate.equals(nextDate)) {
							String currentPriority = currentTask.getTaskPriority();
							String nextPriority = nextTask.getTaskPriority();
							if (parser.nextPriorityIsHigher(currentPriority, nextPriority)) {
								// fullList.add(j, fullList.remove(i));
								fullList.add(i, nextTask);
								fullList.add(j + 1, currentTask);
								fullList.remove(i + 1);
								fullList.remove(j + 1);
							}
						}
					}
				} else if (currentTask.getTaskType().equals("event")) {
					String currentDate = parser.toSixDigit(currentTask.getTaskStartDate());
					if (nextTask.getTaskType().equals("event")) {
						String nextDate = parser.toSixDigit(nextTask.getTaskStartDate());
						if (parser.endDatePassed(currentDate, nextDate)) {
							// fullList.add(j, fullList.remove(i));
							fullList.add(i, nextTask);
							fullList.add(j + 1, currentTask);
							fullList.remove(i + 1);
							fullList.remove(j + 1);
						} else if (currentDate.equals(nextDate)) {
							String currentPriority = currentTask.getTaskPriority();
							String nextPriority = nextTask.getTaskPriority();
							if (parser.nextPriorityIsHigher(currentPriority, nextPriority)) {
								// fullList.add(j, fullList.remove(i));
								fullList.add(i, nextTask);
								fullList.add(j + 1, currentTask);
								fullList.remove(i + 1);
								fullList.remove(j + 1);
							}
						}
					} else if (nextTask.getTaskType().equals("deadline")) {
						String nextDate = parser.toSixDigit(nextTask.getTaskEndDate());
						if (parser.endDatePassed(currentDate, nextDate)) {
							// fullList.add(j, fullList.remove(i));
							fullList.add(i, nextTask);
							fullList.add(j + 1, currentTask);
							fullList.remove(i + 1);
							fullList.remove(j + 1);
						} else if (currentDate.equals(nextDate)) {
							String currentPriority = currentTask.getTaskPriority();
							String nextPriority = nextTask.getTaskPriority();
							if (parser.nextPriorityIsHigher(currentPriority, nextPriority)) {
								// fullList.add(j, fullList.remove(i));
								fullList.add(i, nextTask);
								fullList.add(j + 1, currentTask);
								fullList.remove(i + 1);
								fullList.remove(j + 1);
							}
						}
					}
				}
			}
		}

		FileStorage.clear();
		for (int j = 0; j < fullList.size(); j++) {
			FileStorage.writeObjectAsString(fullList.get(j));
		}
	}

	public void saveLastState() throws Exception, IOException {
		String currentState = FileStorage.readStringAsString(path);

		// System.out.println(currentState);
		lastStates.push(currentState);
		lastState = currentState;
		undoneStates = new Stack<String>();
	}

	public void executeRedo() throws IOException, Exception {
		if (!undoneStates.isEmpty()) {
			lastStates.push(FileStorage.readStringAsString(path));
			FileStorage.clear();
			FileStorage.writeStringAsString(undoneStates.pop());
			LemonGUIController.setCommand("redo");
		} else {
			System.out.println("Already at current");
			LemonGUIController.setCommand("redo maxed");
		}
	}
	
	public void executeSearch(String[] commandParts) throws ClassNotFoundException, IOException {
		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		ArrayList<Task> searchResult = new ArrayList<Task>();
		
		int phraseSize = commandParts.length - 1;
		String searchKeyword = commandParts[1];
		System.out.println("phrase size: " + phraseSize);
		
		if (phraseSize > 1) {
			for (int i = 2; i < phraseSize + 1; i++) {
				searchKeyword += " ";
				searchKeyword += commandParts[i];
				System.out.println("key phrase: " + searchKeyword);
			}
		}
		
		searchKeyword.toLowerCase();
		
		for (int j = 0; j < fullList.size(); j++) {
			Task searchedTask = fullList.get(j);
			if (searchedTask.getTaskName().toLowerCase().contains(searchKeyword) || searchedTask.getTaskDescription().toLowerCase().contains(searchKeyword)) {
				searchResult.add(searchedTask);
				System.out.println("result: " + searchedTask);
			}
		}
	}
}
