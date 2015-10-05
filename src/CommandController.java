
public class CommandController {

	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_EDIT = "edit";
	private static final String COMMAND_RECUR = "recur";
	private static final String COMMAND_NAVIGATE = "view";
	private static final String MESSAGE_INVALID = "Invalid Command";
	
	public CommandController() {
	}
	
	//Note: will error if not followed with whitespace
	public static void processCommand(String command) {

		String commandType = command.substring(0, command.indexOf(" "));

		switch (commandType) {
		
		case COMMAND_ADD:
			CommandParser.parseAdd(command);
			break;

		case COMMAND_DELETE:
			CommandParser.parseDelete(command);
			break;

		case COMMAND_EDIT:
			CommandParser.parseEdit(command);
			break;
		
		case COMMAND_RECUR:
			CommandParser.parseRecur(command);
			break;
			
		case COMMAND_NAVIGATE:
			CommandParser.parseNavigate(command);
			break;
			
		default:
			System.out.println(MESSAGE_INVALID);
			break;
			
		}
	}
}
