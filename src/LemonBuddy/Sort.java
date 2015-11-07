package LemonBuddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Sort {

		public static ArrayList<Task> normal_sort(ArrayList<Task> list) {
			Comparator<Task> comparator = new Comparator<Task>(){
				public int compare(Task t1, Task t2) {					
					 Integer end1, end2, p1, p2;
					 end1 = Integer.valueOf(t1.getTaskEndDate()).intValue();
					 end2 = Integer.valueOf(t2.getTaskEndDate()).intValue();
					 p1 = Integer.valueOf(t1.getTaskPriority()).intValue();
					 p2 = Integer.valueOf(t2.getTaskPriority()).intValue();
					 int flag = end1.compareTo(end2);
					 if(flag == 0) {
						 return p1.compareTo(p2);
					 } else {
						 return flag;
					 }				 
				}
			};				
			Collections.sort(list, comparator);
			return list;
		}
	
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
					time1 = Integer.parseInt(t1.getTaskEndTime());
				} else if(type1.equals(event)) {
					time1 = Integer.parseInt(t1.getTaskStartTime());
				} else {
					time1 = 0;
				}
		 
				if(type2.equals(deadline)) {
					time2 = Integer.parseInt(t2.getTaskEndTime());
				} else if(type2.equals(event)) {
					time2 = Integer.parseInt(t2.getTaskStartTime());
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
