
public class CommandParser {

	//PRIORITY AND DESCRIPTION NOT DONE
	public  void parseAdd(String[] commandParts) {
		String eventType = commandParts[1];
		Task newTask = new Task();
		
		switch(eventType){
		case "deadline": 
			newTask.setTaskType(eventType);
			newTask.setTaskDate(Integer.valueOf(commandParts[2]));
			newTask.setTaskName(commandParts[3]);
			break;
		case "floating": 
			newTask.setTaskType(eventType);
			newTask.setTaskName(commandParts[1]);
			break;
		case "event": 
			newTask.setTaskType(eventType);
			newTask.setTaskDate(Integer.valueOf(commandParts[2]));
			newTask.setTaskStartTime(Integer.valueOf(commandParts[3]));
			newTask.setTaskEndTime(Integer.valueOf(commandParts[4]));
			newTask.setTaskName(commandParts[5]);
			break;
		}
	}

	public void parseEdit(String[] commandParts) {
		Task newTask = new Task();
		String initialTaskName = commandParts[1];
		
		for(int i=2;i<commandParts.length;i++){
			switch(commandParts[i++]){
			case "name":
				newTask.setTaskName(commandParts[i++]);
				break;
			case "date":
				newTask.setTaskDate(Integer.valueOf(commandParts[i++]));
				break;
			case "time":
				newTask.setTaskStartTime(Integer.valueOf(commandParts[i++]));
				newTask.setTaskEndTime(Integer.valueOf(commandParts[i++]));
				break;
			}
		}

	}

	public void parseDelete(String[] commandParts) {
		String taskType = commandParts[1];
		String taskName = commandParts[2];

	}

	public void parseRecur(String[] commandParts) {
		// TODO get startdate

	}

	public void parseNavigate(String[] commandParts) {
		// TODO Auto-generated method stub

	}
	
	public String[] splitCommand(String command){
		return command.split(" ");
	}
}

