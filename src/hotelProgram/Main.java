package hotelProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		String roomName;
		String select = null;
		int roomNum = 0;
		// creating array hotel
		String hotel[] = new String[10];
		// set default value
		initialise(hotel);
		// display Hotel Name
		System.out.println("");
		System.out.println("-----------------------");
		System.out.println(" Welcome to Hotel Wanni");
		System.out.println("-----------------------");
		System.out.println(" ");

		// validation-room numbers
		while (roomNum < 10) {

			// asking for assistance
			System.out.print("Navigation Y/N : ");
			String nav = sc.next();

			// validation - Y/N
			if (!(nav.equalsIgnoreCase("y") || nav.equalsIgnoreCase("n"))) {
				System.out.println("");
				System.out.println("Please input 'Y' or 'N' ");
				System.out.println("");
				Main.main(args);
			}
			// display Navigation
			if (nav.equals("Y") || nav.equals("y")) {
				System.out.println("Select one of these options");
				System.out.println("*	Press V to View all rooms.");
				System.out.println("*	Press E to Display empty rooms.");
				System.out.println("*	Press A to Add customer to a room.");
				System.out.println("*	Press D to Delete customer from a room.");
				System.out.println("*	Press F to Find room from customer name.");
				System.out.println("*	Press O to View Customer names by alphabetically order.");
				System.out.println("*	Press S to Store data to a text file.");
				System.out.println("*	Press L to Load data from the text file.");
				System.out.println("*	Press X to Exit the program");
				System.out.println("");
			}
			// selecting operation
			System.out.print(" select : ");

			select = sc.next();
			switch (select.toUpperCase()) {

			case "A":
				System.out.println("");
				addCustomer(sc, hotel); // add Customer
				break;

			case "D":
				System.out.println("");
				deleteCustomer(sc, hotel); // deleting the customer
				break;

			case "E":
				System.out.println("");
				showEmpty(hotel); // viewing all rooms
				break;

			case "F":
				System.out.println("");
				findRoom(sc, hotel); // find the room by Customer's name
				break;

			case "S":
				System.out.println("");
				saveData(hotel); // save data to a text file;

			case "L":
				System.out.println("");
				loadData(hotel); // load data from the text file;

			case "O":
				System.out.println("");
				sortAscending(hotel); // sorting to ascending order
				break;

			case "V":
				System.out.println("");
				viewRoom(hotel); // view all data
				break;
			case "X":
				System.out.println("");
				break;
			default:
				System.out.println("");
				System.out.println("Don't recognize input.");

			}
		}
	}

	// set default value
	private static void initialise(String hotelRec[]) {
		for (int x = 0; x < 10; x++)
			hotelRec[x] = "E";

	}

	// show empty rooms
	private static void showEmpty(String hotelRecEmp[]) {
		for (int x = 0; x < 10; x++) {
			if (hotelRecEmp[x].equals("E"))
				System.out.println("room " + (x + 1) + " is empty");
		}
	}

	// add customer
	private static void addCustomer(Scanner input, String hotelRecAdd[]) throws Exception {
		try {
			int roomNum;
			String roomName = null;
			Scanner sc = new Scanner(System.in);
			System.out.print("enter room number (1-10) or 11 to stop: ");
			roomNum = sc.nextInt();
			if (roomNum > 10) {
				System.out.println("");
				System.out.println("Exit Adding");
				System.out.println("");
				Main.main(hotelRecAdd);
			}
			System.out.print("enter name for room " + roomNum + " : ");
			roomName = sc.next();
			hotelRecAdd[roomNum - 1] = roomName;
		} catch (Exception e) {
			System.out.println("please only input numbers");
			Main.addCustomer(input, hotelRecAdd);
		}
	}

	// view all rooms
	private static void viewRoom(String hotelRecView[]) {
		for (int x = 0; x < 10; x++) {
			if (hotelRecView[x].equals("E")) {
				System.out.println("room " + (x + 1) + " is empty");
			} else {
				System.out.println("room " + (x + 1) + " is occupied by " + hotelRecView[x]);
			}
		}
	}

	// delete customer
	private static void deleteCustomer(Scanner input, String hotelRecDel[]) {
		System.out.print("Enter the room number of customer to remove : ");
		int roomNo = input.nextInt();
		hotelRecDel[roomNo - 1] = "E";
		System.out.println(" Deleted the customer");
	}

	// find room from customer name
	// show customer is included or not
	private static void findRoom(Scanner input, String hotelRecFindRoom[]) {
		System.out.print("Enter the Customer name : ");
		String cusName = input.next();
		String found = "NO";
		// search all the rooms
		for (int x = 0; x < 10; x++) {
			if (hotelRecFindRoom[x].equals(cusName)) {
				System.out.println("Customer Found -- Room No :" + (x + 1));
				found = "Yes";
			}
		}
		// customer not found
		if ("NO".equals(found)) {
			System.out.println(" No match found");
		}
	}

	// show customer name by acending order
	private static void sortAscending(String hotelRecSortAscd[]) {
		 Arrays.sort(hotelRecSortAscd);

		System.out.println("Alphabetically Ordered Customer Names : ");
		  for (String ascd : hotelRecSortAscd) { System.out.println(ascd);
}

	}
	
	// Sort in alphabetical order
		public static void sortabcd(String sortabcd[]) {
			// To determine when the sort is finished
			boolean flag = true;
			String temp;

			while (flag) {
				flag = false;
				for (int j = 0; j < sortabcd.length - 1; j++) {
					// Sort in ascending order
					if (sortabcd[j].compareToIgnoreCase(sortabcd[j + 1]) > 0) {
						temp = sortabcd[j];
						// Swapping
						sortabcd[j] = sortabcd[j + 1];
						sortabcd[j + 1] = temp;
						flag = true;
					}
				}
			}
			System.out.println(sortabcd);
		}

	// save the data to a file
	private static void saveData(String hotelRecWrite[]) throws Exception {

		try {
			PrintWriter print = new PrintWriter(new FileOutputStream(new File("hotelRec.txt"), true));
			// validation- search customers to write
			if (hotelRecWrite.length <= 0) {
				System.out.println("Plese insert atleast one customer to Save data");
				return;
			}

			for (int i = 0; i < hotelRecWrite.length; i++) {
				print.println(hotelRecWrite[i]); // printing the room data to
													// the file
			}
			System.out.println("");
			System.out.println("Saving.....");
			System.out.println("");

			print.flush();
			print.close();
			// if file not found
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("File Not Found Exception!");
		}
	}

	// load data from file
	private static void loadData(String hotelRecLoad[]) throws FileNotFoundException, IOException {

		// buffer reader to increase reading speed
		BufferedReader in = new BufferedReader(new FileReader("hotelRec.txt"));
		String str;

		List<String> list = new ArrayList<String>();
		while ((str = in.readLine()) != null) {
			list.add(str);
		}

		String[] stringArr = list.toArray(new String[0]);
		hotelRecLoad = stringArr;
		String data = null;
		for (int x = 0; x < stringArr.length; x++) {
			data = stringArr[x];
			System.out.println("Room no Line : " + (x + 1) + " " + data);
		}
		System.out.println("Loding compleated");
	}

}
