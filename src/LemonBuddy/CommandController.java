package LemonBuddy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandController {
	private static Logger logger = Logger.getLogger("CommandController");

	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_EDIT = "edit";
	private static final String COMMAND_RECUR = "recur";
	private static final String COMMAND_NAVIGATE = "view";
	private static final String COMMAND_LIST = "list";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_HELP = "help";
	private static final String COMMAND_UPDATE = "update";
	private static final String COMMAND_UNDO = "undo";
	private static final String COMMAND_REDO = "redo";
	private static final String COMMAND_DONE = "done";
	private static final String COMMAND_SEARCH = "search";
	private static final String MESSAGE_INVALID = "Invalid Command";
	private static final String COMMAND_CLEAR = "clear";
	private static CommandExecutor commandexecutor;
	private static CommandController commandcontroller;

	public CommandController() throws IOException, Exception {

		if (commandexecutor == null) {
			commandexecutor = new CommandExecutor();
		}
		commandexecutor.saveLastState();
	}

	public static void processCommand(String command) {
		logger.log(Level.INFO, "going to start processing");
		try {
			if (commandcontroller == null) {
				commandcontroller = new CommandController();
			}
			String[] commandParts = commandcontroller.splitCommand(command);
			String commandType = commandParts[0];
			
			executeSaveLastState(commandType);

			switch (commandType) {
			case COMMAND_ADD:
				commandexecutor.executeAdd(commandParts);
				break;

			case COMMAND_DELETE:
				if (!commandcontroller.isValidTaskType(commandParts[1])) {
					throw new Exception("Invalid task type");
				}
				commandexecutor.executeDelete(commandParts);
				break;

			case COMMAND_EDIT:
				if (!commandcontroller.isValidTaskType(commandParts[1])) {
					throw new Exception("Invalid task type");
				}
				commandexecutor.executeEdit(commandParts);
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
				if (!commandcontroller.isValidTaskType(commandParts[1])) {
					throw new Exception("Invalid task type");
				}
				commandexecutor.executeDone(commandParts);
				break;

			case COMMAND_CLEAR:
				if (!commandcontroller.isValidClearType(commandParts[1])) {
					throw new Exception("Invalid clear type");
				}
				commandexecutor.executeClear(commandParts);
				break;

			case COMMAND_HELP:
				commandexecutor.executeHelp();
				break;

			case COMMAND_UPDATE:
				commandexecutor.executeUpdate();
				break;

			case COMMAND_SEARCH:
				commandexecutor.executeSearch(commandParts);
				break;

			default:
				commandexecutor.parseInvalidCommand(commandType);
				break;
			}
		} catch (Exception e) {
			logger.log(Level.WARNING, "processing error", e);
			e.printStackTrace();
		}
	}

	private static void executeSaveLastState(String commandType) throws Exception, IOException {
		if (commandType.equals(COMMAND_ADD) || commandType.equals(COMMAND_DELETE)
				|| commandType.equals(COMMAND_EDIT) || commandType.equals(COMMAND_RECUR)
				|| commandType.equals(COMMAND_DONE)) {
			commandexecutor.saveLastState();
		}
	}

	public String[] splitCommand(String command) {
		String[] commandParts = command.split(" ");
		return commandParts;
	}

	private boolean isValidTaskType(String taskType) {
		if (taskType.equals("floating") || taskType.equals("deadline") || taskType.equals("event")) {
			return true;
		} else
			return false;
	}

	private boolean isValidClearType(String clearType) {
		if (clearType.equals("overdue") || clearType.equals("done")) {
			return true;
		} else
			return false;
	}
}
