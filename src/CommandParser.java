import java.io.File;
import java.util.ArrayList;

public class CommandParser {
	private static File file = new File("C:\\eclipse\\Your sdk your majesty\\main\\test.txt");
	private static String path = "C:\\eclipse\\Your sdk your majesty\\main\\test.txt";
	private TaskParser taskparser = new TaskParser();

	// PRIORITY AND DESCRIPTION NOT DONE
	public void parseAdd(String[] commandParts) throws Exception {
		// assume floating first
		Task newTask = taskparser.createNew(commandParts);
		FileStorage.write(path, newTask);
		GUIConsole.successfulAdd(newTask.getTaskName());
	}

	public void parseEdit(String[] commandParts) throws Exception {
		String initialTaskName = commandParts[1];
		Task newTask = taskparser.createNew(commandParts);
		// System.out.println(newTask);
		ArrayList<Task> fullList = FileStorage.read(path);
		FileStorage.clear(file);
		
		for (int j = 0; j < fullList.size(); j++) {
			Task currentTask = fullList.get(j);

			if (currentTask.getTaskName().equals(initialTaskName)) {
				FileStorage.write(path, newTask);
			} else {
				FileStorage.write(path, currentTask);
			}
		}
	}

	public void parseDelete(String[] commandParts) throws Exception {
		String taskType = commandParts[1];
		String taskName = commandParts[2];
		ArrayList<Task> array = FileStorage.read(path);
		assert (array != null) : "unable to read from specified path";
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
				FileStorage.write(path, array.get(j));
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

		fullList = FileStorage.read(path);

		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			
			assert !currentTask.getTaskName().equals("");
			// System.out.println(currentTask.getTaskName() + " " +
			// currentTask.getTaskType());

			if (currentTask.getTaskType().equals("deadline")) {
				deadlineTasks.add(currentTask);
			}
		}

		GUIConsole.displayDeadlineTask();
		for (int j = 0; j < deadlineTasks.size(); j++) {
			currentTask = deadlineTasks.get(j);
			GUIConsole.displayTask(currentTask.getTaskName() + currentTask.getTaskStartTime().toString()
					+ currentTask.getTaskEndTime().toString());
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
			GUIConsole.displayTask(currentTask.getTaskName() + ": " +  currentTask.getTaskStartTime() +" "+ currentTask.getTaskStartDate() 
			+ "-" + currentTask.getTaskEndTime() + " " +currentTask.getTaskEndDate());
			// System.out.println(floatingTasks.get(j));
		}

		return floatingTasks;
	}

	public void parseInvalidCommand(String command) {
		GUIConsole.displayErrorMessage(command);
	}

	public String[] splitCommand(String command){ 

		
		String[] commandParts = command.split(" ");
		return commandParts;
	}
}
