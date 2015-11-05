package LemonBuddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Sort {
	public static ArrayList<Task> sortByTime(ArrayList<Task> sortList) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				
				String deadline = "deadline";
				String event = "event";
				
				String type1, type2;
				int time1, time2;
				type1 = t1.getTaskType();
				type2 = t2.getTaskType();	
					
				if(type1.equals(deadline)) {
					time1 = t1.getTaskEndTime();
				} else if(type1.equals(event)) {
					time1 = t1.getTaskStartTime();
				} else {
					time1 = 0;
				}
		 
				if(type2.equals(deadline)) {
					time2 = t2.getTaskEndTime();
				} else if(type2.equals(event)) {
					time2 = t2.getTaskStartTime();
				} else {
					time2 = 0;
				}

				if(time1 > time2){
					return 1;
				} else if (time1 == time2) {
					return 0;
				} else {
					return -1;
				}
			 
			}
		};	
		
		Collections.sort(sortList, comparator);
		return sortList;
	}
}
