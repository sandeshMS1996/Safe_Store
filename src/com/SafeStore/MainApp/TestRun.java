package com.SafeStore.MainApp;
import static com.SafeStore.DataManager.DataDefaults.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.SafeStore.UserManager.*;
public class TestRun {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		InitialiseApplication.Welcome();
		int ch =0;
		System.out.println("Please choose from the below options..\n");
		while(true) {
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				ch = acceptInputAsInt(scanner);
				while(true) {
					if(ch < 0 || ch > 3) {
						System.out.println("please enter correct details\n");
						ch = acceptInputAsInt(scanner);
					}	
					else break;
				}
			switch(ch) {
			case 1: {
				if(UserRegistractionAndLogin.Register(scanner,UserInteractions.forRegistration(scanner))) {
					System.out.println("Registration successful");
				}
				else System.out.println("Registration not successful");
				break;
			} 
			case 2: { 
					DataOperations.LoginOperations(scanner);
					break;
			}	
			case 3: {
				System.out.println("Bye...!");
				scanner.close();
				System.exit(0);
			}
			default : {
				System.out.println("invalid selection");
			}
			System.out.println("----------------------------------");
			
		}
		}
	}
}