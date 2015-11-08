package LemonBuddy;

import java.util.ArrayList;

class Sort {
	 
		public static ArrayList<Task> sortByTime(ArrayList<Task> sortList){
			return SortImplementation.SortTime(sortList);			
		}
		
		public static ArrayList<Task> normal_sort(ArrayList<Task> list) {
			return SortImplementation.SortEndDate(list);
		}
		
		public static ArrayList<Task> sortByName(ArrayList<Task> list) {
			return SortImplementation.SortName(list);
		}
		
		public static ArrayList<Task> sortByType(ArrayList<Task> list) {
			return SortImplementation.SortType(list);
		}

		public static ArrayList<Task> sortByStartDate(ArrayList<Task> list) {
			return SortImplementation.SortStartDate(list);
		}

		public static ArrayList<Task> sortByEndDate(ArrayList<Task> list) {
			return SortImplementation.SortEndDate(list);
		}
		
		public static ArrayList<Task> sortByStatus(ArrayList<Task> list) {
			return SortImplementation.SortStatus(list);
		}
		
		public static ArrayList<Task> sortByDateThenPriority(ArrayList<Task> list) {
			return SortImplementation.SortDateThenPriority(list);
		}
}
