package LemonBuddy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandController {
	private static final String TASKTYPE_EVENT = "Event";

	private static final String TASKTYPE_DEADLINE = "Deadline";

	private static final String TASKTYPE_FLOATING = "Floating";

	private static Logger logger = Logger.getLogger("CommandController");

	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_EDIT = "edit";
	private static final String COMMAND_RECUR = "recur";
	private static final String COMMAND_NAVIGATE = "view";
	private static final String COMMAND_LIST = "list";
	private static final String COMMAND_UNDO = "undo";
	private static final String COMMAND_REDO = "redo";
	private static final String COMMAND_DONE = "done";
	private static final String COMMAND_SEARCH = "search";
	private static final String MESSAGE_INVALID = "Invalid Command";
	private static CommandExecutor commandexecutor;
	private static CommandController commandcontroller;
	
	private static boolean isSuccesful;
	private static String commandType;
	private static String errorMessage;

	private CommandController() throws Exception{
		commandexecutor = CommandExecutor.getInstance();
		commandexecutor.saveLastState();
		commandexecutor.updateLists();
	}
	
	public static CommandController getInstance() throws IOException, Exception {
		if (commandcontroller == null) {
			commandcontroller = new CommandController();
		}
		return commandcontroller;
		
	}

	public void processCommand(String command) {
		logger.log(Level.INFO, "going to start processing");
		try {
			String[] commandParts = commandcontroller.splitCommand(command);
			commandType = commandParts[0];

			executeSaveLastState(commandType);
			
			switch (commandType) {
			case COMMAND_ADD:
				commandexecutor.executeAdd(commandParts);
				isSuccesful = true;
				break;

			case COMMAND_DELETE:
				commandexecutor.executeDelete(commandParts);
				isSuccesful = true;
				break;

			case COMMAND_EDIT:
				commandexecutor.executeEdit(commandParts);
				isSuccesful = true;
				break;

			case COMMAND_UNDO:
				commandexecutor.executeUndo();
				isSuccesful = true;
				break;

			case COMMAND_REDO:
				commandexecutor.executeRedo();
				isSuccesful = true;
				break;

			case COMMAND_NAVIGATE:
				commandexecutor.executeNavigate(commandParts);
				isSuccesful = true;
				break;

			case COMMAND_LIST:
				commandexecutor.executeList(commandParts);
				isSuccesful = true;
				break;

			case COMMAND_DONE:
				if (!commandcontroller.isValidTaskType(commandParts[1])) {
					throw new Exception("Invalid task type");
				}
				commandexecutor.executeDone(commandParts);
				isSuccesful = true;
				break;

			case COMMAND_SEARCH:
				commandexecutor.executeSearch(commandParts);
				isSuccesful = true;
				break;

			default:
				throw new Exception("Invalid Command");
			}
			
		} catch (Exception e) {
			logger.log(Level.WARNING, "processing error", e);
			errorMessage = (e.toString());
			isSuccesful = false;
		}

	}

	private static void executeSaveLastState(String commandType) throws Exception, IOException {
		if (commandType.equals(COMMAND_ADD) || commandType.equals(COMMAND_DELETE) || commandType.equals(COMMAND_EDIT)
				|| commandType.equals(COMMAND_RECUR) || commandType.equals(COMMAND_DONE)) {
			commandexecutor.saveLastState();
		}
	}

	public String[] splitCommand(String command) {
		String[] commandParts = command.split(" ");
		return commandParts;
	}

	private boolean isValidTaskType(String taskType) {
		if (taskType.equals(TASKTYPE_FLOATING) || taskType.equals(TASKTYPE_DEADLINE)
				|| taskType.equals(TASKTYPE_EVENT)) {
			return true;
		} else
			return false;
	}
	
	public ArrayList<ArrayList<Task>> passListsToGUI() throws Exception {
		return commandexecutor.passListsToGUI();
	}
	
	public String getDate() {
		return commandexecutor.passDate();
	}
	
	public String getListType() {
		return commandexecutor.passListType();
	}

	public String getNotificationMessage() {
		String temp = errorMessage;
		errorMessage = "";
		return temp;
	}
	
	public boolean checkForSuccessfulCommand() {
		return isSuccesful;
	}
	
	public String passCommand() {
		return commandType;
	}
	
	public Task getSelectedTask() {
		return commandexecutor.passSelectedTask();
	}
}
