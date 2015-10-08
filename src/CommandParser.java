import java.io.File;
import java.util.ArrayList;

public class CommandParser {
	private static File file = new File("C:\\eclipse\\workspace\\main\\test.txt");

	// PRIORITY AND DESCRIPTION NOT DONE
	public void parseAdd(String[] commandParts) throws Exception {
		String eventType = commandParts[1];
		Task newTask = new Task();

		switch (eventType) {
		case "floating":
			newTask.setTaskType(eventType);
			newTask.setTaskName(commandParts[2]);
			GUIConsole.successfulAdd(commandParts[2]);
			
			//Add description
			//for (int i = 3; i < commandParts.length; i++) {
			//	newTask.addDesc(commandParts[i]);
			//}
			//System.out.println(newTask.getDesc());
			break;
		case "deadline":
			newTask.setTaskType(eventType);
			newTask.setTaskDate(Integer.valueOf(commandParts[2]));
			newTask.setTaskName(commandParts[3]);
			GUIConsole.successfulAdd(commandParts[3]);
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

	public void parseDelete(String[] commandParts) throws Exception {
		String taskType = commandParts[1];
		String taskName = commandParts[2];
	  	ArrayList<Task> array = FileStorage.read(file);
        	int i = 0;
        	int x = 0;
		while (i < array.size()) {
            	if(array.get(i).getTaskType().equals(taskType)) {
                	if(array.get(i).getTaskName().equals(taskName)) {
                    		FileStorage.clear(file);
                	 	array.remove(i);
                		GUIConsole.successfulDelete(commandParts[2]);
                    		x = 1;
                    		break;
                		}
            		}
            		i++;
        	}
        	if (x == 1) {
        		int j = 0;
        		while (j < array.size()) {
            			FileStorage.write(file, array.get(j));
            			j++;
        			}
        		}
		if (x == 0) {
                	GUIConsole.failDelete(taskName);
        	}
	}

	public void parseRecur(String[] commandParts) {
		// TODO get startdate
		
		/* get particular task 
		 * retrieve taskDate
		 * get recurring frequency
		 * add task to respective dates and time (our own calendar?)
		 * e.g. recur eat daily
		 */
	}

	public void parseNavigate(String[] commandParts) {
		// TODO Auto-generated method stub
		
		/* get what user wants to view
		 * date e.g. navigate 01012001
		 */
	}
	
	public void parseHelp() {
		GUIConsole.displayHelp();	
	}
	
	public void parseInvalidCommand(String command) {
		GUIConsole.displayErrorMessage(command);
	}

	public String[] splitCommand(String command) {
		return command.split(" ");
	}
}
