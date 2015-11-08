package LemonBuddy;

import java.util.ArrayList;

class Sort {
	
		public static ArrayList<Task> sortByTime(ArrayList<Task> sortList){
			return SortImplementation.SortTime(sortList);			
		}
		
		public ArrayList<Task> normal_sort(ArrayList<Task> list) {
			return SortImplementation.SortEndDate(list);
		}
		
		public ArrayList<Task> sortByName(ArrayList<Task> list) {
			return SortImplementation.SortName(list);
		}
		
		public ArrayList<Task> sortByType(ArrayList<Task> list) {
			return SortImplementation.SortType(list);
		}

		public ArrayList<Task> sortByStartDate(ArrayList<Task> list) {
			return SortImplementation.SortStartDate(list);
		}

		public ArrayList<Task> sortByEndDate(ArrayList<Task> list) {
			return SortImplementation.SortEndDate(list);
		}
		
		public ArrayList<Task> sortByStatus(ArrayList<Task> list) {
			return SortImplementation.SortStatus(list);
		}

}
