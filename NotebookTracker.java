package cs1811Assignment1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class NotebookTracker {
    
	//GLOBAL SCANNER Introduced
    public static Scanner scanner = new Scanner(System.in);
	public static Scanner keyboard = new Scanner(System.in);
		
    	//Time Cross Method Introduced
    	public static boolean timesCross(int startP1, int endP1, int startP2, int endP2) {
        	return ((startP1 < endP2) && (startP1 > startP2) || (endP1 < endP2) && (endP1 > startP2));
    	}
	
    	//Getting the Time Crossing Staff
    	public static int getCrossingStaff(int missingStart, int missingEnd) {
	
        	System.out.println("Enter the number of staff in the lab:");
        	int staffMembers = scanner.nextInt();
        	int count = 0;
	
        	int i = 0;
        	while (i < staffMembers) {
            		System.out.println("Enter the staff member's name:");
	            	String staffMemberName;
	            	staffMemberName = keyboard.nextLine();
		
	            	System.out.println("Enter the entry time:");
	            	int staffEntryTime = scanner.nextInt();
		
	            	System.out.println("Enter the exit time:");
	            	int staffExitTime = scanner.nextInt();
	
	            	if (timesCross(missingStart, missingEnd, staffEntryTime, staffExitTime)) {
	                	System.out.println(staffMemberName + " might have the notebook.");
	                	count++;
	
	            	} else {
	                	System.out.println(staffMemberName + " will not have the notebook.");
	            	}
            		i++;
        	}
        	return count;
    	}

	public static boolean timesCrossLate(int startP1, int endP1, int startP2, int endP2){
		return ((startP1 > endP1) && ((startP2 > startP1) || (startP2 < endP1) || (endP2 > startP1) || (endP2 < endP1)) || ((startP2 > endP2) && ((startP1 > startP2) || (startP1 < endP2) || (endP1 > startP2) || (endP1 < endP2))));
	}

	public static int getStaffFile(int missingStart, int missingEnd, String[] args){
		int count = 0;
		try{
			int x = 0;
			while (x < args.length){
				File text = new File(args[x]);
				Scanner reader = new Scanner(text);
				while (reader.hasNext()){
					String staffMemberName = reader.next();
					int staffEntryTime = reader.nextInt();
					int staffExitTime = reader.nextInt();
					if (timesCross(missingStart, missingEnd, staffEntryTime, staffExitTime)){
						System.out.println(staffMemberName + " might have the notebook.");
						count ++;
					}
					reader.close();
				}x++;
			}
		}catch (FileNotFoundException e){
			System.out.println("Number of staff who might have the notebook: " + getCrossingStaff(missingStart, missingEnd));
		}
		return count;
	}

    	public static void main(String[] args) {
	
        	//Time when notebook could have been lost
        	System.out.println("What is the earliest the notebook could have been lost?");
        	int missingStart = scanner.nextInt();

        	//Time when u realised notebook was lost
        	System.out.println("When did you notice the notebook was missing?");
        	int missingEnd = scanner.nextInt();
		if (args.length == 0){
	        	int chance = getCrossingStaff(missingStart, missingEnd);
			System.out.println("Number of staff who might have the notebook: " + chance);
		}else{
			int chance = getStaffFile(missingStart, missingEnd, args);
			System.out.println("Number of staff who might have the notebook: " + chance);
		}

    	}
}