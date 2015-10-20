import java.io.File;
import java.util.ArrayList;

public class CommandExecutor {
	 private static String path ="C:\\eclipse\\Your sdk your majesty\\main\\test.txt";
	//private static String path = "C:\\Users\\user\\workspace\\main\\test.txt";
	private Parser parser;

	// PRIORITY AND DESCRIPTION NOT DONE
	public void executeAdd(String[] commandParts) throws Exception {
		// assume floating first
		if (parser == null) {
			parser = new Parser();
		}
		Task newTask = parser.parseTask(commandParts);
		FileStorage.write(path, newTask);
		GUIConsole.successfulAdd(newTask.getTaskName());
	}

	public void executeEdit(String[] commandParts) throws Exception {
		String initialTaskName = commandParts[1];
		Task newTask = parser.parseTask(commandParts);
		// System.out.println(newTask);
		ArrayList<Task> fullList = FileStorage.read(path);
		FileStorage.clear(path);

		for (int j = 0; j < fullList.size(); j++) {
			Task currentTask = fullList.get(j);

			if (currentTask.getTaskName().equals(initialTaskName)) {
				newTask.merge(currentTask);
				FileStorage.write(path, newTask);
			} else {

				FileStorage.write(path, currentTask);
			}
		}
	}

	public void executeDelete(String[] commandParts) throws Exception {
		int deleteIndex = Integer.valueOf(commandParts[1]) - 1;
		ArrayList<Task> array = FileStorage.read(path);
		assert(array != null) : "unable to read from specified path";
		int i = 0;
		while (i < array.size()) {
			if (i == deleteIndex) {
				FileStorage.clear(path);
				array.remove(i);
				GUIConsole.successfulDelete(commandParts[1]);
				break;
			}
			i++;
		}
		int j = 0;
		while (j < array.size()) {
			FileStorage.write(path, array.get(j));
			j++;
		}
	}

	public void executeRecur(String[] commandParts) {
		// TODO get startdate

		/*
		 * get particular task retrieve taskDate get recurring frequency add
		 * task to respective dates and time (our own calendar?) e.g. recur eat
		 * daily
		 */
	}

	public void executeNavigate(String[] commandParts) {
		// TODO Auto-generated method stub

		/*
		 * get what user wants to view date e.g. navigate 01012001
		 */
	}

	public void executeHelp() {
		GUIConsole.displayHelp();
	}

	public static ArrayList<Task> display() throws Exception {
		ArrayList<Task> floatingTasks = new ArrayList<Task>();
		ArrayList<Task> fullList = new ArrayList<Task>();
		Task currentTask;

		fullList = FileStorage.read(path);

		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			//if (currentTask.getTaskType().equals("floating")) {
				floatingTasks.add(currentTask);
			//}
		}

		GUIConsole.displayFloatingTask();
		for (int j = 0; j < floatingTasks.size(); j++) {
			currentTask = floatingTasks.get(j);
			int taskIndex = j + 1;
			GUIConsole.displayTask(taskIndex + ". " + currentTask.getTaskType() + " " + currentTask.getTaskName() + ": " + currentTask.getTaskStartTime()
					+ " " + currentTask.getTaskStartDate() + "-" + currentTask.getTaskEndTime() + " "
					+ currentTask.getTaskEndDate() + " " + currentTask.getTaskPriority() + " "
					+ currentTask.getTaskDescription());
		}

		return floatingTasks;
	}

	public void parseInvalidCommand(String command) {
		GUIConsole.displayErrorMessage(command);
	}

	public String[] splitCommand(String command) {

		String[] commandParts = command.split(" ");
		return commandParts;
	}
}
