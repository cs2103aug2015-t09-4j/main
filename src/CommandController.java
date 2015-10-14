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
	private static final String COMMAND_HELP = "help";
	private static final String MESSAGE_INVALID = "Invalid Command";
	
	
	public static void processCommand(String command) {
		logger.log(Level.INFO, "going to start processing");
		
		
		CommandParser commandparser = new CommandParser();
		
		
		String[] commandParts = commandparser.splitCommand(command);
		
		try {
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
			ArrayList<Task> dummy = commandparser.display();
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
			logger.log(Level.WARNING, "processing error", e);
			e.printStackTrace();
		}
	}

	
}
