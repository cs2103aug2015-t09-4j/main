package LemonBuddy;

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

	public static void processCommand(String command) {
		logger.log(Level.INFO, "going to start processing");
		if (commandcontroller == null) {
			commandcontroller = new CommandController();
		}

		if (commandexecutor == null) {
			commandexecutor = new CommandExecutor();
		}

		String[] commandParts = commandcontroller.splitCommand(command);
		String lastCommandType = commandParts[0];
		try {
			if (commandParts[0].equals(COMMAND_ADD) || commandParts[0].equals(COMMAND_DELETE)
					|| commandParts[0].equals(COMMAND_EDIT) || commandParts[0].equals(COMMAND_RECUR)
					|| commandParts[0].equals(COMMAND_DONE)) {
				commandexecutor.saveLastState();
			}
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
				if (!commandcontroller.isValidTaskType(commandParts[1])) {
					throw new Exception("Invalid task type");
				}
				commandexecutor.executeDelete(commandParts);
				break;
			// edit event 1 by 2020 *high desc huehuehue
			case COMMAND_EDIT:
				if (!commandcontroller.isValidTaskType(commandParts[1])) {
					throw new Exception("Invalid task type");
				}
				commandexecutor.executeDelete(commandParts);
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
				if (!commandcontroller.isValidTaskType(commandParts[1])) {
					throw new Exception("Invalid task type");
				}
				commandexecutor.executeDone(commandParts);
				break;

			case COMMAND_CLEAR:
				if(!commandcontroller.isValidClearType(commandParts[1])){
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
				commandexecutor.parseInvalidCommand(commandParts[0]);
				break;
			}
		} catch (Exception e) {
			logger.log(Level.WARNING, "processing error", e);
			e.printStackTrace();
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

	private boolean isValidClearType(String clearType){
		if(clearType.equals("overdue")||clearType.equals("done")){
			return true;
		}else
			return false;
	}
}
