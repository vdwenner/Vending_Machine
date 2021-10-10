package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	// OVERLOAD SET 1: getChoiceFromOptions(Object[] options, boolean naturalKey)
	public Object getChoiceFromOptions(Object[] options, boolean naturalKey) {
		Object choice = null;
		while (choice == null) {
			// CALL OVERLOADED DISPLAY AND CHOICE METHODS. NOTE HARDCODED suppressList PARAMETER ...
			// TODO: ... THE HARDCODING NEEDS ADDITIONAL REFACTORING & SUPPPORT IN CALLER
			displayMenuOptions(options,naturalKey,true);
			choice = getChoiceFromUserInput(options, naturalKey);
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	//OVERLOAD SET 1: displayMenuOptions(Object[] options, boolean suppressLineNumbers, boolean suppressList)
	private void displayMenuOptions(Object[] options, boolean suppressLineNumbers, boolean suppressList) {
		out.println();
		if(!suppressList) {
			//DO NOT SHOW ANYTHING EXCEPT THE PROMPT
			for (int i = 0; i < options.length; i++) {
				int optionNum = i + 1;
				if (suppressLineNumbers) {
					//ONLY PRINT THE Option Value
					out.println(options[i]);
				} else {
					//GO AHEAD AND INCLUDE THE OPTION # WITH THE VALUE
					out.println(optionNum + ") " + options[i]);
				}
			}
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	//OVERLOAD SET 1: getChoiceFromUserInput(Object[] options, boolean useNaturalKey)
	private Object getChoiceFromUserInput(Object[] options, boolean useNaturalKey) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			if(!useNaturalKey) {
				// Run the default behavior to match Choice # to An Option by Index
				int selectedOption = Integer.valueOf(userInput);
				if (selectedOption > 0 && selectedOption <= options.length) {
					choice = options[selectedOption - 1];
				}
			}else{
				// Run the optional behavior to match Choice Text to Option by Value
				// TODO: String is assumed!
				String selectedOption = userInput.toUpperCase();

				//Create a 'latch' to indicate if the selected option is in the available option Obj Array
				//TODO: Using a List<Object> would have made this unnecessary

				boolean valid = false;

				for(Object obj:options){
					// If the valid match has not been found ...
					if(!valid){
						// keep testing
						valid = (obj.equals(selectedOption));
					}
				}
				// Only if a match was found should be assign the selection to the choice
				if(valid){choice = selectedOption;}
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

}
