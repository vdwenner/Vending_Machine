package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

import static java.lang.System.exit;

public class VendingMachineCLI {
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


	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		VendingMachine snackMaster3000 = new VendingMachine();
		String[] activeMenu = MAIN_MENU_OPTIONS;
		while (true) {
			File log = new File("log.txt");
			try (PrintWriter purchaseLog = new PrintWriter("log.txt");) {
				String userChoice = (String) menu.getChoiceFromOptions(activeMenu);
				LocalDateTime timeInfo = LocalDateTime.now();

				if (userChoice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
					// display vending machine items
					snackMaster3000.displayItems();
				} else if (userChoice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					activeMenu = PURCHASE_MENU_OPTIONS;
					// do purchase
				} else if (userChoice.equals(MAIN_MENU_OPTION_EXIT)) {
					snackMaster3000.makeChange(balance);
					snackMaster3000.exitDialogue();
					exit(1);
				} else if (userChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					//placeholder
					String deposit = (String) menu.getChoiceFromOptions(DEPOSIT_MENU_OPTIONS);
					snackMaster3000.takeMoney(deposit);
					purchaseLog.println(timeInfo + "FEED MONEY:" + deposit);
					//print without ln for multiple uses. Put version for each purchase option
				} else if (userChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
					Slot slotValue = snackMaster3000.getSlotMap.get(userInput);
					slotValue.display();




					if (!snackMaster3000.getSlotMap.contains(wrongSlot)) { //if people put in a slot that doesnt exist
						System.out.println("That slot does not exist.");
						//return to purchasing menu
					}
					if (slotValue.getQuantity() == 0) {
						System.out.println("OUT OF STOCK");
						//return to purchase menu
					}
					if (balance >= slotValue.getPrice()) {
						System.out.println(slotValue.getPhrase());
						balance = balance - slotValue.getPrice();
						//add a comma
						purchaseLog.println(timeInfo + slotValue.getBrandName() + " " + slotValue.getIdentifier() + " " + slotValue.getPrice());
						//print without ln for multiple uses. Put version for each purchase option
					}

				} else if (userChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
					snackMaster3000.makeChange();
					purchaseLog.println(timeInfo + "GIVE CHANGE:" + snackMaster3000.makeChange());
					//print without ln for multiple uses. Put version for each purchase option
					//return to main menu
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

}
