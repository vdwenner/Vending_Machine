package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

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
	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		VendingMachine snackMaster3000 = new VendingMachine();
		String[] activeMenu = MAIN_MENU_OPTIONS;
		while (true) {
			String userChoice = (String) menu.getChoiceFromOptions(activeMenu);

			if (userChoice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				snackMaster3000.displayItems();
			} else if (userChoice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				activeMenu = PURCHASE_MENU_OPTIONS;
				// do purchase
			} else if (userChoice.equals(MAIN_MENU_OPTION_EXIT)) {
				snackMaster3000.makeChange();
				snackMaster3000.exitDialogue();
				System.exit(1);
			} else if (userChoice.equals(PURCHASE_MENU_OPTIONS)){
				//placeholder
				snackMaster3000.takeMoney();
			} else if (userChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
				Slot slotValue = snackMaster3000.getSlotMap.get(userInput);
				slotValue.display();
				if (!snackMaster3000.getSlotMap.contains(wrongSlot)){ //if people put in a slot that doesnt exist
					System.out.println("That slot does not exist.");
					//return to purchasing menu
				} if (slotValue.getQuantity() == 0){
					System.out.println("OUT OF STOCK");
					//return to purchase menu
				}
				if (balance >= slotValue.getPrice()) {
					System.out.println(slotValue.getPhrase());
					balance = balance - slotValue.getPrice();
					//add a comma
				}
			} else if (userChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
				snackMaster3000.makeChange();
				//return to main menu
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

//	File log = new File("log.txt");
//	String purchaseLog = new PrintWriter("log.txt");
//	File.
//
}
