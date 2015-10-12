
public class TaskParser {
	public Task createNew(String[] commandParts) {
		Boolean floating = true;
		Task newTask = new Task();

		int wordIndex = 1;
		String taskName = "";

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
		if (floating == true)
			newTask.setTaskType("floating");
		else if (floating == false)
			newTask.setTaskType("deadline");

		return newTask;
	}

	private int splitCommaStart(String[] commandParts, Task newTask, int wordIndex, Boolean comma) {
		String taskOn = commandParts[wordIndex];
		// trim and comma is present
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
	
	private int splitCommaEnd(String[] commandParts, Task newTask, int wordIndex, Boolean comma) {
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
