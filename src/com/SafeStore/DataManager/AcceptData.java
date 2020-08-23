package com.SafeStore.DataManager;

import static com.SafeStore.DataManager.DataDefaults.*;

import java.util.Scanner;

public class AcceptData {
	public static String[] accept(Scanner scanner) {
		String[] data = new String[3];	
		try {
				System.out.println("Enter the website for which you want to store data");
				data[0] = acceptInputAsString(scanner, true);
				System.out.println("enter username for " + data[0]);
				data[1] = acceptInputAsString(scanner, true);
				System.out.println("enter password for " + data[1]);
				data[2] = acceptInputAsString(scanner, true);
				return data;
			}catch(CommaException e) {
				return null;
			}
	
	}	
}