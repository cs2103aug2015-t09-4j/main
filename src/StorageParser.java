public class StorageParser {
	public static Task createTaskFromInformation(String s) {
		String[] array = s.split(";");
		Task t = new Task();
		for(int i = 0; i < array.length; i++) {
			String[] temp = array[i].split(":", 2);			
			switch(temp[0]) {
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
}

