
public class CommandController {

	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_EDIT = "edit";
	private static final String COMMAND_RECUR = "recur";
	private static final String COMMAND_NAVIGATE = "view";
	private static final String MESSAGE_INVALID = "Invalid Command";

	
	public static void processCommand(String command) {
		
		CommandParser commandparser = new CommandParser();		
		String[] commandParts = commandparser.splitCommand(command);
		
		//AIDS FOR EVERYONE	
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
			
		default:
			System.out.println(MESSAGE_INVALID);
			break;
			
		}
	}
}
