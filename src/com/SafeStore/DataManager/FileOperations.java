package com.SafeStore.DataManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.SafeStore.DataManager.DataDefaults.*;
import static com.SafeStore.UserManager.UserDefaults.USER_DATA_PATH;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.SafeStore.UserManager.UserData;

public class FileOperations {
	public static void storeData(UserData user, String[] data) {
		if(data == null) return;
		Path file = null;
		String storeFilename = null; 
		BufferedWriter writer = null;
		Map<String, String> checkForDuplicate = new HashMap<String, String>();
		if(user != null && user.getLoginToken() == true) {

			try {
				storeFilename =  USER_DATA_PATH + user.getEmail()+"_storedPasswords.txt";
				file = Paths.get(storeFilename);
				if(Files.notExists(file)) {
					System.out.println("file " + storeFilename + "does not exist");
					System.out.println("creating the file now");
					Files.createFile(file);
				}
				retriveData(user).forEach(a->checkForDuplicate.put(a.split(",")[0], a.split(",")[1]));
				writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
				if(checkForDuplicate.containsKey(data[0]) && checkForDuplicate.get(data[0]).equals(data[1])) {
					System.out.println("[duplicate]: There already exist a entry for website \""
							+ data[0] +"\" with username \"" + data[1] + "\"" );
					System.out.println("              " + "Store request rejected..");
					return;
				}
				writer.append(data[0] + "," + data[1] + "," + data[2]);
				writer.newLine();
				System.out.println("");
				System.out.println("Your details have been Successfuly stored to a file");
			}
			catch (IOException e) {
				fileOperationError(e);
			} catch(Exception e) {
				if(DEBUG) e.printStackTrace();
				System.out.println("Store request rejected due to Unexpected error");
				System.out.println("please contact administrator for your Assistance");
			}
			finally {
				try {
					if(writer != null) writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				storeFilename = null;
				file=null;
			}
		}
	}

	public static  List<String> retriveData(UserData user) {
		Path file = null;
		String storeFilename = null;
		storeFilename =  USER_DATA_PATH + user.getEmail()+"_storedPasswords.txt";
		file = Paths.get(storeFilename);
		if(Files.notExists(file)) {
			System.out.println("file " + storeFilename + "does not exist");
			System.out.println("looks like you have not stored any data yet");
			return null; 
			}
		try {
			List<String> sorted = Files.readAllLines(file);
			//System.out.println(sorted);
			sorted.removeIf(a->a.split(",").length != 3);
			Collections.sort(sorted, (a,b)-> a.split(",")[0].compareTo(b.split(",")[0]));
			Sort.quickSort(sorted);
			return sorted;
		} catch (IOException e) {
			fileOperationError(e);
		}
		return null;
		}
}

