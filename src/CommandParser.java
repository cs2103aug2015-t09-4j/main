import java.io.File;
import java.util.ArrayList;

public class CommandParser {
	private static File file = new File("C:\\Users\\user\\workspace\\main\\test.txt");

	// PRIORITY AND DESCRIPTION NOT DONE
	public void parseAdd(String[] commandParts) throws Exception {
		// assume floating first
		Boolean floating = true;
		Task newTask = new Task();

		int wordIndex = 1;
		String taskName = "";

		// this while loop gets task name
		while (true) {
			taskName = taskName + commandParts[wordIndex++];
			if (wordIndex >= commandParts.length) {
				break;
			}
			if (commandParts[wordIndex].equals("on") || commandParts[wordIndex].equals("from")) {
				break;
			}
			taskName = taskName + " ";
		}
		// System.out.println(taskName);
		newTask.setTaskName(taskName);

		while (wordIndex < commandParts.length) {
			Boolean testNotFloating = false;
			switch (commandParts[wordIndex++]) {
			case "on":
				String taskDate = commandParts[wordIndex++];
				newTask.setTaskDate(taskDate);
				testNotFloating = true;
				break;

			case "from":
				String from = commandParts[wordIndex++];
				wordIndex = wordIndex + 1;
				String to = commandParts[wordIndex++];
				if (String.valueOf(from).length() == 4) {
					newTask.setTaskStartTime(from);
					newTask.setTaskEndTime(to);
				}
				if (String.valueOf(from).length() == 6) {
					newTask.setRecurStartDate(from);
					newTask.setRecurEndDate(to);
				}
				testNotFloating = true;
				break;

			}
			if (testNotFloating == true)
				floating = false;
		}
		if (floating == true)
			newTask.setTaskType("floating");
		else if (floating == false)
			newTask.setTaskType("deadline");

		FileStorage.write(file, newTask);
	}

	public void parseEdit(String[] commandParts) throws Exception {
		Task newTask = new Task();
		String initialTaskName = commandParts[1];

		for (int i = 2; i < commandParts.length;) {
			switch (commandParts[i++]) {
			case "name":
				String namePart = commandParts[i++];
				String newName = "";
				while (true) {
					newName = newName + " " + namePart;
					if (namePart.substring(namePart.length() - 1).equals("\"")) {
						break;
					}
					namePart = commandParts[i++];
				}

				newName = newName.substring(2, newName.length() - 1);
				newTask.setTaskName(newName);
				GUIConsole.successfulEditName(initialTaskName, newName);
				break;
			case "date":
				newTask.setTaskDate(commandParts[i++]);
				// GUIConsole.successfulEditDate(commandParts[1],
				// Integer.commandParts[i]);
				break;
			case "time":
				newTask.setTaskStartTime(commandParts[i++]);
				newTask.setTaskEndTime(commandParts[i++]);
				// GUIConsole.successfulEditTime(commandParts[1],
				// Integer.commandParts[i], Integer.commandParts[i--]);
				break;
			}
			// System.out.println(newTask);
			ArrayList<Task> fullList = new ArrayList<Task>();
			fullList = FileStorage.read(file);

			FileStorage.clear(file);
			for (int j = 0; j < fullList.size(); j++) {
				Task currentTask = fullList.get(j);

				String[] taskParts = currentTask.toString().split(";");
				if (taskParts[0].toString().contains("Name:" + initialTaskName)) {
					currentTask.setTaskName(newTask.getTaskName());
					FileStorage.write(file, currentTask);
				} else {
					FileStorage.write(file, currentTask);
				}
			}
		}
	}

	public void parseDelete(String[] commandParts) throws Exception {
		String taskType = commandParts[1];
		String taskName = commandParts[2];
		ArrayList<Task> array = FileStorage.read(file);
		int i = 0;
		int x = 0;
		while (i < array.size()) {
			if (array.get(i).getTaskType().equals(taskType)) {
				if (array.get(i).getTaskName().equals(taskName)) {
					FileStorage.clear(file);
					array.remove(i);
					GUIConsole.successfulDelete(commandParts[2]);
					x = 1;
					break;
				}
			}
			i++;
		}
		if (x == 1) {
			int j = 0;
			while (j < array.size()) {
				FileStorage.write(file, array.get(j));
				j++;
			}
		}
		if (x == 0) {
			GUIConsole.failDelete(taskName);
		}
	}

	public void parseRecur(String[] commandParts) {
		// TODO get startdate

		/*
		 * get particular task retrieve taskDate get recurring frequency add
		 * task to respective dates and time (our own calendar?) e.g. recur eat
		 * daily
		 */
	}

	public void parseNavigate(String[] commandParts) {
		// TODO Auto-generated method stub

		/*
		 * get what user wants to view date e.g. navigate 01012001
		 */
	}

	public void parseHelp() {
		GUIConsole.displayHelp();
	}

	public static ArrayList<Task> display() throws Exception {
		// public static void display() throws Exception {
		ArrayList<Task> floatingTasks = new ArrayList<Task>();
		ArrayList<Task> deadlineTasks = new ArrayList<Task>();
		ArrayList<Task> fullList = new ArrayList<Task>();
		Task currentTask;

		fullList = FileStorage.read(file);

		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			// System.out.println(currentTask.getTaskName() + " " +
			// currentTask.getTaskType());

			if (currentTask.getTaskType().equals("deadline")) {
				deadlineTasks.add(currentTask);
			}
		}

		GUIConsole.displayDeadlineTask();
		for (int j = 0; j < deadlineTasks.size(); j++) {
			currentTask = deadlineTasks.get(j);
			GUIConsole.displayTask(currentTask.getTaskName() + currentTask.getStartTime().toString() + currentTask.getEndTime().toString());
			// System.out.println(deadlineTasks.get(j));
		}

		// transfer deadlineTasks and floatingTasks here
		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			// System.out.println(currentTask.getTaskName() + " " +
			// currentTask.getTaskType());

			if (currentTask.getTaskType().equals("floating")) {
				floatingTasks.add(currentTask);
			}
		}

		GUIConsole.displayFloatingTask();
		for (int j = 0; j < floatingTasks.size(); j++) {
			currentTask = floatingTasks.get(j);
			GUIConsole.displayTask(currentTask.getTaskName() + currentTask.getTaskType().toString());
			// System.out.println(floatingTasks.get(j));
		}

		return floatingTasks;
	}

	public void parseInvalidCommand(String command) {
		GUIConsole.displayErrorMessage(command);
	}

	public String[] splitCommand(String command) {
		return command.split(" ");
	}
}
