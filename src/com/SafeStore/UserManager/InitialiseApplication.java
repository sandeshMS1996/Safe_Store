package com.SafeStore.UserManager;

import static com.SafeStore.UserManager.UserDefaults.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public final class InitialiseApplication   {
	private static boolean isFirstRun = true;
	private static String welcomeString = ""; 
	Path userFile = null;
	static {
		Path userFile =  Paths.get(USER_DETAILS_FILENAME);
		if(Files.notExists(userFile)) {
			try {
				Files.createFile(userFile);
			} catch (IOException e) {
				fileOperationError(e);
			}
		}
		userFile = Paths.get(USER_DATA_PATH);
		if(Files.notExists(userFile)) {
			try {
				System.out.println("creating data derectory..");
				Files.createDirectory(userFile);
			} catch (IOException e) {
				fileOperationError(e);
			}
			
		}
	}
	
	
	public final static void Welcome() {
		if(isFirstRun) {
			isFirstRun = false;
			Path welcomePath = Paths.get(WELCOME_FILENAME);	
			try {	
				Files.readAllLines(welcomePath).forEach(a-> welcomeString+= a + "\n");
			}
			catch (IOException e) {
				if(DEBUG)
					e.printStackTrace();
				welcomeString = "**************************\n" 
						+   "Welcome to myLocker\n"
						+   "Developer: Sandesh MS\n"
						+ 	"**************************\n";
			}
			System.out.println(welcomeString);
		}
	}
	


	}


