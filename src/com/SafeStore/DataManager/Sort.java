package com.SafeStore.DataManager;


import java.util.List;

public class Sort {
	public static void selectionSort(List<String> list) {
		for(int last = list.size()-1; last > 0 ;last--) {
			int largest = 0;
			for(int i=0;i<last;i++) {
				if(list.get(i).compareTo(list.get(largest) ) > 0) {
					largest = i;
				}
			}
			swap(list,largest, last);
		}
		 
	}	
	
	private static void swap(List<String> list, int largest, int i) {
		String temp = list.get(i);
		list.set(i, list.get(largest));
		list.set(largest, temp);
		
	
	}
	
	/*
	 * public static void main(String[] args) throws IOException { List<String> list
	 * = Files.readAllLines(Paths.get(
	 * "E:\\myJava\\OCJP\\SafeStore\\AppData\\sandeshshanbog@gmail.com_storedPasswords.txt"
	 * )); selectionSort(list); list.forEach(a -> System.out.println(a));
	 * 
	 * 
	 * }
	 */
}	