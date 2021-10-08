package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class VendingMachineCLI {
//	public String currentBalance =
	//main options
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	//purchase options
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	//DEPOSIT_OPTIONS
	private static final String DEPOSIT_MENU_OPTION_ONE = "$1";
	private static final String DEPOSIT_MENU_OPTION_TWO = "$2";
	private static final String DEPOSIT_MENU_OPTION_FIVE = "$5";
	private static final String DEPOSIT_MENU_OPTION_TEN = "$10";
	private static final String[] DEPOSIT_MENU_OPTIONS = { DEPOSIT_MENU_OPTION_ONE,DEPOSIT_MENU_OPTION_TWO,DEPOSIT_MENU_OPTION_FIVE,DEPOSIT_MENU_OPTION_TEN};

//for user putting in product code, for each loop, get keySet turn to array
	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}


	public void run() {
		VendingMachine snackMaster3000 = new VendingMachine();
		snackMaster3000.getVendingInfo();
		String balanceLine = "";
		//need to show option every option every time main menu is accessed.
		String[] activeMenu = MAIN_MENU_OPTIONS;
		while (true) {
			File log = new File("log.txt");
			try (PrintWriter purchaseLog = new PrintWriter("log.txt");) {
				String userChoice = (String) menu.getChoiceFromOptions(activeMenu);
				LocalDateTime timeInfo = LocalDateTime.now();

				if (userChoice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
					// display vending machine items
					System.out.println(snackMaster3000.displayItems());
				} else if (userChoice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					activeMenu = PURCHASE_MENU_OPTIONS;
					// do purchase
				} else if (userChoice.equals(MAIN_MENU_OPTION_EXIT)) {
					snackMaster3000.makeChange(snackMaster3000.getBalance());
					System.out.println(snackMaster3000.getExitDialogue());
					exit(1);
				} else if (userChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					activeMenu = DEPOSIT_MENU_OPTIONS;
				} else if (userChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
					Slot slotValue = snackMaster3000.getSlotMap().get(getUserInput(snackMaster3000.slotMap));
					snackMaster3000.displayItems();





					if (!snackMaster3000.getSlotMap().entrySet().contains(slotValue)) { //if people put in a slot that doesn't exist
						System.out.println("That slot does not exist.");
						//return to purchasing menu
					}
					if (slotValue.getQuantity() == 0) {
						System.out.println("OUT OF STOCK");
						//return to purchase menu
					}
					if (snackMaster3000.getBalance() >= slotValue.getPrice()) {
						System.out.println(slotValue.getPhrase());
						snackMaster3000.setBalance(snackMaster3000.getBalance() - slotValue.getPrice());
						//add a comma
						purchaseLog.println(timeInfo + slotValue.getBrandName() + " " + slotValue.getIdentifier() + " " + slotValue.getPrice());
						//print without ln for multiple uses. Put version for each purchase option
					}

				} else if (userChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
					String coinsGiven = snackMaster3000.makeChange(snackMaster3000.getBalance());
					purchaseLog.println(timeInfo + "GIVE CHANGE:" + coinsGiven);
					//print without ln for multiple uses. Put version for each purchase option
					//return to main menu
				} else if (userChoice.equals(DEPOSIT_MENU_OPTION_ONE)) {
					purchaseLog.println(snackMaster3000.takeMoney(1));

				}
			}
			catch (FileNotFoundException e){
				System.out.println("Log file was not found.");
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public String getUserInput(TreeMap<String, Slot> slotMap){
		String [] options = slotMap.keySet().toArray(new String[slotMap.size()]);
		System.out.println("Please choose slot number: ");
		String userInput = (String) menu.getChoiceFromOptions(options);
		return userInput;
	}

}
