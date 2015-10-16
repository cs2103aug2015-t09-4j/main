
public class Parser {
	private int taskIdCount = 1;
	
	public Task parseTask(String[] commandParts) {
		Boolean floating = true;
		Task newTask = new Task();
		
		int wordIndex = 1;
		String taskName = "";
		newTask.setTaskId(Integer.toString(taskIdCount++));
		// this while loop gets task name
		while (true) {
			taskName = taskName + commandParts[wordIndex++];
			if (wordIndex >= commandParts.length) {
				break;
			}
			if (commandParts[wordIndex].equals("on") || commandParts[wordIndex].equals("from")) {
				break;
			}
			taskName = taskName + " ";
		}
		// System.out.println(taskName);
		newTask.setTaskName(taskName);
		
		try{
		wordIndex = parseTime(commandParts, newTask, wordIndex);
		}catch(Exception e){
			System.err.println("Invalid input: " + e.getMessage());
		}
		if (floating == true)
			newTask.setTaskType("floating");
		//else if (floating == false)
		//	newTask.setTaskType("deadline");

		return newTask;
	}
	public static Task createTaskFromInformation(String s) {
		String[] array = s.split(";");
		Task t = new Task();
		for(int i = 0; i < array.length; i++) {
			String[] temp = array[i].split(":", 2);			
			switch(temp[0]) {
			case "taskId":
				t.setTaskId(temp[1]);
				break;
			case "taskname":
				t.setTaskName(temp[1]);
				break;
			case "tasktype":
				t.setTaskType(temp[1]);
				break;
			case "taskStartDate":
				t.setTaskStartDate(temp[1]);
				break;
			case "taskEndDate":
				t.setTaskEndDate(temp[1]);
				break;
			case "taskPriority":
				t.setTaskPriority(temp[1]);
				break;
			case "taskDescription":
				t.setTaskDescription(temp[1]);
				break;
			case "taskStartTime":
				t.setTaskStartTime(temp[1]);
				break;
			case "taskEndTime":
				t.setTaskEndTime(temp[1]);
				break;
			case "recurType":
				t.setRecurType(temp[1]);
				break;
			case "recurStartDate":
				t.setRecurStartDate(temp[1]);
				break;
			case "recurEndDate":
				t.setRecurEndDate(temp[1]);
				break;
			default:
				break;
			}
		}
		return t;		
	}
	private int parseTime(String[] commandParts, Task newTask, int wordIndex) throws Exception {
		Boolean from = false;
		Boolean to = false;
		for(String part:commandParts){
			if(part.contains("from")){
				from = true;
			}
		}
		for(String part:commandParts){
			if(part.contains("to")){
				to = true;
			}
		}
		if(from){
			if(!to){
				throw new Exception("From has no to");
			}
		}
		
		while (wordIndex < commandParts.length) {
			Boolean comma = false;
			switch (commandParts[wordIndex++]) {
			case "on":
				wordIndex = splitCommaStart(commandParts, newTask, wordIndex, comma);
				break;

			case "from":
				wordIndex = splitCommaStart(commandParts, newTask, wordIndex, comma);
				wordIndex++;
				wordIndex = splitCommaEnd(commandParts, newTask, wordIndex, comma);
				break;

			}
		}
		return wordIndex;
	}

	private int splitCommaStart(String[] commandParts, Task newTask, int wordIndex, Boolean comma) throws Exception {
		String taskOn = commandParts[wordIndex];
		// trim and comma is present
		if(commandParts[wordIndex].contains(",")){
			if(!(commandParts[wordIndex].indexOf(",")==commandParts[wordIndex].length()-1)){
				throw new Exception("Start comma has no spacing");
			}
		}
		
		if (commandParts[wordIndex].contains(",")) {
			taskOn = commandParts[wordIndex].substring(0, commandParts[wordIndex].indexOf(","));
			comma = true;
		}

		if (taskOn.length() == 4) {
			newTask.setTaskStartTime(taskOn);
		} else if (taskOn.length() == 6) {
			newTask.setTaskStartDate(taskOn);
		}

		if (comma) {
			taskOn = commandParts[++wordIndex];
			System.out.println(taskOn);
			if (taskOn.length() == 4) {
				newTask.setTaskStartTime(taskOn);
			} else if (taskOn.length() == 6) {
				newTask.setTaskStartDate(taskOn);
			}
			wordIndex = wordIndex+1;
		}
		return wordIndex;
	}
	
	private int splitCommaEnd(String[] commandParts, Task newTask, int wordIndex, Boolean comma)throws Exception {
		if(commandParts[wordIndex].contains(",")){
			if(!(commandParts[wordIndex].indexOf(",")==commandParts[wordIndex].length()-1)){
				throw new Exception("End comma has no spacing");
			}
		}
		
		String taskOn = commandParts[wordIndex];
		// trim and comma is present
		if (commandParts[wordIndex].contains(",")) {
			taskOn = commandParts[wordIndex].substring(0, commandParts[wordIndex].indexOf(","));
			comma = true;
		}

		if (taskOn.length() == 4) {
			newTask.setTaskEndTime(taskOn);
		} else if (taskOn.length() == 6) {
			newTask.setTaskEndDate(taskOn);
		}

		if (comma) {
			taskOn = commandParts[++wordIndex];
			System.out.println(taskOn);
			if (taskOn.length() == 4) {
				newTask.setTaskEndTime(taskOn);
			} else if (taskOn.length() == 6) {
				newTask.setTaskEndDate(taskOn);
			}
			wordIndex = wordIndex+1;
		}
		return wordIndex;
	}
}
