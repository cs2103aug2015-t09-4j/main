package LemonBuddy;

import java.util.Comparator;

class SortByTime implements Comparator<Object> {
	  
	 public int compare(Object o1, Object o2) {
		 Task t1 = (Task)o1;
		 Task t2 = (Task)o2;
		 String type1, type2;
		 Integer time1, time2;
		 type1 = t1.getTaskType();
		 type2 = t2.getTaskType();
		 if(type1 == "deadline") {
			 time1 = t1.getTaskEndTime();
		 } else if(type1 == "event") {
			 time1 = t1.getTaskStartTime();
		 } else {
			 time1 = null;
		 }
		 
		 if(type2 == "deadline") {
			 time2 = t2.getTaskEndTime();
		 } else if(type2 == "event") {
			 time2 = t2.getTaskStartTime();
		 } else {
			 time2 = null;
		 }
		 
		 return time1.compareTo(time2);
	 }
}
