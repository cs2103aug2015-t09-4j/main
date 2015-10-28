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
			if (currentTask.getTaskType().equals(editType)) {
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

	public void executeNavigate(String[] commandParts) throws ClassNotFoundException, IOException {
		// get days related to day
		int timelineDate = Integer.valueOf(commandParts[1]);

		ArrayList<Task> deadlineTasks = new ArrayList<Task>();
		ArrayList<Task> eventTasks = new ArrayList<Task>();
		ArrayList<Task> fullList = new ArrayList<Task>();
		Task currentTask;

		fullList = FileStorage.readStringAsObject(path);
		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			if (currentTask.getTaskType().equals("deadline")) {
				if (currentTask.getTaskEndDate() == timelineDate) {
					deadlineTasks.add(currentTask);
					System.out.println("deadline: " + currentTask);
				}
			}
<<<<<<< HEAD
			if (currentTask.getTaskType().equals("event")) {
				if (currentTask.getTaskEndDate() >= timelineDate && currentTask.getTaskStartDate() <= timelineDate) {
					eventTasks.add(currentTask);
					System.out.println("event fromto: " + currentTask);
				}
				if(currentTask.getTaskEndDate() == -1 && currentTask.getTaskStartDate()==timelineDate){
					eventTasks.add(currentTask);
					System.out.println("event on: " + currentTask);
				}
=======
			switch (commandParts[0]) {

			// "add one task from 3030, 404040 to 2020, 101010 *high desc hue
			// hue hue"
			// deadline uses taskEndDate. Event On uses taskStartDate.
			case COMMAND_ADD:
				commandexecutor.executeAdd(commandParts);
				commandexecutor.executeSort();
				commandexecutor.executeSortFloating();
				break;

			case COMMAND_DELETE:
				commandexecutor.executeDelete(commandParts);
				break;
			// edit event 1 by 2020 *high desc huehuehue
			case COMMAND_EDIT:
				commandexecutor.executeEdit(commandParts);
				commandexecutor.executeSort();
				commandexecutor.executeSortFloating();
				break;

			case COMMAND_RECUR:
				commandexecutor.executeRecur(commandParts);
				break;

			case COMMAND_UNDO:
				commandexecutor.executeUndo();
				break;
			case COMMAND_REDO:
				commandexecutor.executeRedo();
				break;
			case COMMAND_NAVIGATE:
				commandexecutor.executeNavigate(commandParts);
				break;

			case COMMAND_LIST:
				commandexecutor.executeList(commandParts);
				break;

			case COMMAND_DISPLAY:
				commandexecutor.executeDisplay(commandParts);
				break;

			case COMMAND_DONE:
				commandexecutor.executeDone(commandParts);
				break;

			case COMMAND_HELP:
				commandexecutor.executeHelp();
				break;

			case COMMAND_UPDATE:
				commandexecutor.executeUpdate();
				break;

			default:
				commandexecutor.parseInvalidCommand(commandParts[0]);
				break;
>>>>>>> f01c5874dd0710235d2995f5c3606cff4b381346
			}
		}
		
		
		/*
		 * get what user wants to view date e.g. navigate 010101
		 */
	}

	public void executeHelp() {
		// GUIConsole.displayHelp();
	}

	// <<<<<<< HEAD

	// =======
	public void executeUpdate() throws IOException, ClassNotFoundException {
		ArrayList<Task> array = FileStorage.readStringAsObject(path);
		assert(array != null) : "unable to read from specified path";

		String currentDate = parser.getCurrentDate();
		System.out.println(currentDate);

		int i = 0;
		boolean anythingRemoved = false;

		for (i = 0; i < array.size(); i++) {
			//String endDate = Integer.valueOf(array.get(i).getTaskEndDate()).toString();
			String endDate = parser.toSixDigit(array.get(i).getTaskEndDate());
			System.out.println(endDate);
			if (endDate.length() == 6) {
				if (parser.endDatePassed(currentDate, endDate)) {
					FileStorage.clear();
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
		} else {
			System.out.println("Already at current");
		}
	}
}
