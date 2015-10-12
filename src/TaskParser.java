
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
				break;

			case "from":
				String from = commandParts[wordIndex++];
				wordIndex = wordIndex + 1;
				String to = commandParts[wordIndex++];
				if (from.length() == 4) {
					newTask.setTaskStartTime(from);
					newTask.setTaskEndTime(to);
				}
				if (from.length() == 6) {
					newTask.setRecurStartDate(from);
					newTask.setRecurEndDate(to);
				}
				break;

			}
		}
		if (floating == true)
			newTask.setTaskType("floating");
		else if (floating == false)
			newTask.setTaskType("deadline");

		return newTask;
	}
}
