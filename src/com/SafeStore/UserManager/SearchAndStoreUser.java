package com.SafeStore.UserManager;

import java.util.List;

import static com.SafeStore.UserManager.UserDefaults.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;

class SearchAndStoreUser {
	public static UserData searchUserbyEmail(String email) {
		Path p = Paths.get(USER_DETAILS_FILENAME);
		BufferedReader input =  null;
		ZonedDateTime time = null; 
		String line = null;
		int c =0;
		if(Files.notExists(p)) 
			fileOperationError();
		try {
			input = Files.newBufferedReader(p);
			while((line= input.readLine()) != null) {
				//System.out.println(line);
				String[] values = line.split(",");
				if(values.length != 5) continue;
				if(values[2].equalsIgnoreCase(email)) {
					//System.out.println(values[4]);
					try {
						time = ZonedDateTime.parse(values[4]).withZoneSameInstant(DEFAULT_TIME_ZONE);
					} catch(Exception e) {
						time= null;
					}

					return new UserData(values[0],null,values[2].toLowerCase(),values[3], false, time);
				}
			}

		}
		catch(IOException e) {
			fileOperationError(e);
		}finally {
			try {
				if(input!=null) input.close();
				p = null; time = null;line=null;
			} catch (Exception e) {

			}
		} 

		return null;
	}


	static boolean StoreUser(UserData user) {
		Path userFile = Paths.get(USER_DETAILS_FILENAME);
		BufferedWriter userOutput = null;
		String u = null;
		if(Files.notExists(userFile)) 
			fileOperationError();
		try {	
			userOutput = Files.newBufferedWriter(userFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			u = user.getFirstName() + "," + user.getLastName() + "," + user.getEmail().toLowerCase() + 
					"," + user.getPassword() + "," + user.getLastLoginDateTime();
			userOutput.append(u);
			userOutput.newLine();
			createUserFile(user.getEmail());
			System.out.println("user details has been stored to the Database");
			return true;
		} catch (IOException e) {
			fileOperationError();
		}catch (Exception e) {
			fileOperationError(e);
		}
		finally {
			try {
				if(userOutput != null) userOutput.close();
				u = null;
				userFile = null;
			} catch (Exception e) {
				if(DEBUG)
					System.out.println("writers at Store data were not clased properly");
			}

		}
		return false;	
	}

	private static void createUserFile(String email) {
		String fileName = USER_DATA_PATH + email+"_storedPasswords.txt";
		Path filePath =  Paths.get(fileName);
		try {
		if(Files.notExists(filePath)) {
				Files.createFile(filePath);
				System.out.println("data file \""+ email+"_storedPasswords.txt" + "\" has been created sucessfully..!");
			}
		else {
			Files.delete(filePath);
			Files.createFile(filePath);
			System.out.println("data file \""+ email+"_storedPasswords.txt" + "\" has been created sucessfully..!");
		}
		}catch (Exception e) {
			System.out.println("could not create data File  at this time due to Unexpected error..");
		}
	}
	public static int removeUser(String email) {
		Path userFile = Paths.get(USER_DETAILS_FILENAME);
		BufferedWriter userOutput = null;
		try {
			List<String> userData1 = Files.readAllLines(userFile);
			Files.deleteIfExists(userFile);
			Files.createFile(userFile);
			userOutput = Files.newBufferedWriter(userFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
				for(String a: userData1) {
					if(a.split(",")[2].equals(email))
						continue;
					else  {
						userOutput.write(a);
						userOutput.newLine();
				}
			}
		System.out.println("Your Details have been removed from the Databace");		
		System.out.println("Data File \"" + (email+"_storedPasswords.txt") + "\" has been deleted from file system");
		Files.deleteIfExists(Paths.get((USER_DATA_PATH + email+ "_storedPasswords.txt")));
		return 0;
		}catch (IOException e) {
			System.out.println("Error detected while deleting the user account..");
			return -1;
		}catch(Exception e) {
			System.out.println("Error detected while deleting the user account..");
			return -1;
		}finally {
			try {
				if(userOutput != null)
					userOutput.close();
			}catch (Exception e) {
				if(DEBUG)
					System.out.println("writers at 'remove user' were not clased properly");
				}
		}	
		
	}

}






