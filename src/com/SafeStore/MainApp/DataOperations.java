package com.SafeStore.MainApp;


import static com.SafeStore.DataManager.DataDefaults.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.SafeStore.DataManager.AcceptData;
import com.SafeStore.DataManager.FileOperations;
import com.SafeStore.UserManager.UserData;
import com.SafeStore.UserManager.UserInteractions;
import com.SafeStore.UserManager.UserRegistractionAndLogin;


public class DataOperations {
	public static int LoginOperations(Scanner scanner)  {
		System.out.println("please login");
		UserData data =  UserRegistractionAndLogin.Login(scanner, UserInteractions.forLogin(scanner));
		if(data == null) {
			System.out.println("invalid credencials..");
			return -1;
		}	
		//System.out.println("token " + data.getLoginToken());
		if(data != null && data.getLoginToken() == true) {
					System.out.println("******************************************************");
					System.out.println("[Note] Application cannot store data that contains \",\" due to design limitations..");
					System.out.println("       System will reject data that contains \",\" in it...");
					System.out.println("******************************************************");
					//System.out.println("Please select from the below options..\n");
					while(true) {
						System.out.println("----------------------------------");
						System.out.println("\nPlease select from below options..\n");
						System.out.println("1. store Credencials");
						System.out.println("2. Search stored Credencials");
						System.out.println("3. Display all Credencials");
						System.out.println("4. Logoff and return to main menu");
						System.out.println("5. Delet My account");
						System.out.println("6. Logoff Exit Application");	
						int ch = acceptInputAsInt(scanner);
						while(true) {
							if(ch < 0 || ch > 6) {
								System.out.println("please enter correct Choice\n");
								ch = acceptInputAsInt(scanner);
							}	
							else break;
						} 

				switch(ch) {
				case 1: 
					FileOperations.storeData(data, AcceptData.accept(scanner));
					break;
				case 2:	{
					System.out.println("Enter the Website for which you want to retrive Credencials");
					String searchString;
					searchString = acceptInputAsString(scanner);
					
					boolean found = false;
					boolean first = true;
					List<String> matches = new ArrayList<String>();
					String[] vals = null;
					for(String a :FileOperations.retriveData(data)) {
						vals  = a.split(",");
						if(vals[0].equals(searchString)) {
							found = true;
							if(first) {
								System.out.println("Matching Website found ");
								first = false;
							}
							System.out.println(vals[0] + "->");
							System.out.println("\tUsername: " + vals[1] + "\n\tPassword: " + vals[2]);
							//break;
						}
						else matches.add(vals[0]);
					}
					first = true;
					//System.out.println(matches.toString());	
					if(!found) {
							System.out.println("No passowrd found for the Website \"" + searchString + "\"");
							Set<String> relate = new HashSet<String>();
							for(String i : matches) {
								if(i.substring(0,1).equalsIgnoreCase(searchString.substring(0, 1))) 
									relate.add(i);
							}
							if(!relate.isEmpty()) {
								System.out.println("You may be looking for..");
								relate.forEach(a->System.out.println("\t-" + a ));
							}
						}
						break;
					}
				case 3: {
					List<String>  credencials =  FileOperations.retriveData(data);
					if(credencials.size() == 0) {
						System.out.println("No credencails have been stored yet..");
						credencials =  null;
						break;
					}
					//Collections.sort(credencials, (a,b)->a.split(",")[0].compareTo(b.split(",")[0]));
					for(String a :credencials) {
						String[] vals = a.split(","); 
						System.out.println(vals[0] + ": ");
						System.out.println("\t-username: " + vals[1]);
						System.out.println("\t-password: " + vals[2]);
					}
					credencials =  null;
					break;
				}
				
				case 4 : {
					UserRegistractionAndLogin.logoff(data);
					return 3;
				}
				case 5: {
					UserRegistractionAndLogin.deleteAccount(data);
					return 0;
				}
				case 6 : {
					UserRegistractionAndLogin.logoff(data);
					System.out.println("Bye..");
					scanner.close();
					System.exit(0);
				}
				default : {
					System.out.println("invalied selection..!");
				}
				}
				}
		}	
		return 0;
	
	

}
}