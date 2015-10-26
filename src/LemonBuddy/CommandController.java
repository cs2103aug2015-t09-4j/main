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
	private static final String MESSAGE_INVALID = "Invalid Command";
	private static CommandExecutor commandexecutor;
	
	public static void processCommand(String command) {
		logger.log(Level.INFO, "going to start processing");

		if (commandexecutor == null) {
			commandexecutor = new CommandExecutor();
		}

		String[] commandParts = commandexecutor.splitCommand(command);
		try {
			switch (commandParts[0]) {

			// "add one task from 3030, 404040 to 2020, 101010 *high desc hue hue hue"
			// deadline uses taskEndDate. Event On uses taskStartDate. 
			case COMMAND_ADD:
				commandexecutor.executeAdd(commandParts);
				break;

			case COMMAND_DELETE:
				commandexecutor.executeDelete(commandParts);
				break;
			//edit event 1 by 2020 *high desc huehuehue
			case COMMAND_EDIT:
				commandexecutor.executeEdit(commandParts);
				break;

			case COMMAND_RECUR:
				commandexecutor.executeRecur(commandParts);
				break;
				
			case COMMAND_UNDO:
				commandexecutor.executeUndo();
				
			case COMMAND_NAVIGATE:
				commandexecutor.executeNavigate(commandParts);
				break;
				
			case COMMAND_LIST:
				commandexecutor.executeList(commandParts);
				break;

			case COMMAND_DISPLAY:
				commandexecutor.executeDisplay(commandParts);
				break;

			case COMMAND_HELP:
				commandexecutor.executeHelp();
				break;
				
			case COMMAND_UPDATE:
				//commandexecutor.executeUpdate();
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

}
