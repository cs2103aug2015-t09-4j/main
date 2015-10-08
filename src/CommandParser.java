import java.io.File;
import java.util.ArrayList;

public class CommandParser {
	private static File file = new File("C:\\eclipse\\workspace\\main\\test.txt");

	// PRIORITY AND DESCRIPTION NOT DONE
	public void parseAdd(String[] commandParts) throws Exception {
		String eventType = commandParts[1];
		Task newTask = new Task();

		switch (eventType) {
		case "deadline":
			newTask.setTaskType(eventType);
			newTask.setTaskDate(Integer.valueOf(commandParts[2]));
			newTask.setTaskName(commandParts[3]);
			GUIConsole.successfulAdd(commandParts[3]);
			break;
		case "floating":
			newTask.setTaskType(eventType);
			newTask.setTaskName(commandParts[2]);
			GUIConsole.successfulAdd(commandParts[1]);
			break;
		case "event":
			newTask.setTaskType(eventType);
			newTask.setTaskDate(Integer.valueOf(commandParts[2]));
			newTask.setTaskStartTime(Integer.valueOf(commandParts[3]));
			newTask.setTaskEndTime(Integer.valueOf(commandParts[4]));
			newTask.setTaskName(commandParts[5]);
			GUIConsole.successfulAdd(commandParts[5]);
			break;
		}
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
				 GUIConsole.successfulEditName(initialTaskName,newName);
				// commandParts[i]);
				break;
			case "date":
				newTask.setTaskDate(Integer.valueOf(commandParts[i++]));
				// GUIConsole.successfulEditDate(commandParts[1],
				// Integer.commandParts[i]);
				break;
			case "time":
				newTask.setTaskStartTime(Integer.valueOf(commandParts[i++]));
				newTask.setTaskEndTime(Integer.valueOf(commandParts[i++]));
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

	public void parseDelete(String[] commandParts) {
		String taskType = commandParts[1];
		String taskName = commandParts[2];
		GUIConsole.successfulDelete(commandParts[2]);

	}

	public void parseRecur(String[] commandParts) {
		// TODO get startdate

	}

	public void parseNavigate(String[] commandParts) {
		// TODO Auto-generated method stub

	}

	public String[] splitCommand(String command) {
		return command.split(" ");
	}
}
