import java.io.*;
import java.util.ArrayList;

public class CommandController {

	private static File file = new File("C:\\eclipse\\workspace\\main\\test.txt");
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_EDIT = "edit";
	private static final String COMMAND_RECUR = "recur";
	private static final String COMMAND_NAVIGATE = "view";
	private static final String COMMAND_HELP = "help";
	private static final String MESSAGE_INVALID = "Invalid Command";

	public static void processCommand(String command) {
		
		CommandParser commandparser = new CommandParser();
		String[] commandParts = commandparser.splitCommand(command);

		try {
		// AIDS FOR EVERYONE
		switch (commandParts[0]) {

		case COMMAND_ADD:
			commandparser.parseAdd(commandParts);
			break;

		case COMMAND_DELETE:
			commandparser.parseDelete(commandParts);
			break;

		case COMMAND_EDIT:
			commandparser.parseEdit(commandParts);
			break;

		case COMMAND_RECUR:
			commandparser.parseRecur(commandParts);
			break;

		case COMMAND_NAVIGATE:
			commandparser.parseNavigate(commandParts);
			break;
			
		case "display":
			display();
			break;
		
		case COMMAND_HELP:
			commandparser.parseHelp();
			break;
			
		default:
			//System.out.println(MESSAGE_INVALID);
			commandparser.parseInvalidCommand(commandParts[0]);
			break;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void display() throws Exception {

		ArrayList<Task> floatingTasks = new ArrayList<Task>();
		ArrayList<Task> deadlineTasks = new ArrayList<Task>();
		ArrayList<Task> fullList = new ArrayList<Task>();

		fullList = FileStorage.read(file);

		for (int j = 0; j < fullList.size(); j++) {
			Task currentTask = fullList.get(j);
			String[] taskParts = currentTask.toString().split(";");
			for (int i = 0; i < taskParts.length; i++) {
				if (taskParts[i].contains("TaskType:deadline")) {
					deadlineTasks.add(currentTask);
				}
			}
		}

		GUIConsole.displayDeadlineTask();
		for (int j = 0; j < deadlineTasks.size(); j++) {
			GUIConsole.displayTask(deadlineTasks.get(j));
			//System.out.println(deadlineTasks.get(j));
		}

		// transfer deadlineTasks and floatingTasks here
		for (int j = 0; j < fullList.size(); j++) {
			Task currentTask = fullList.get(j);
			String[] taskParts = currentTask.toString().split(";");
			for (int i = 0; i < taskParts.length; i++) {
				if (taskParts[i].contains("TaskType:floating")) {
					floatingTasks.add(currentTask);
				}
			}
		}

		GUIConsole.displayFloatingTask();
		for (int j = 0; j < floatingTasks.size(); j++) {
			GUIConsole.displayTask(floatingTasks.get(j));
			//System.out.println(floatingTasks.get(j));
		}
	}

}
