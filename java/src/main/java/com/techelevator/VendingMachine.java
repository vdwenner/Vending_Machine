package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.System.*;

public class VendingMachine {
    public TreeMap<String, Slot> slotMap = new TreeMap<>();
    public Logger logger = new Logger();
    public int balance = 0;
    public String exitDialogue = "Thank you for your purchase! Have an amazing day!";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");


    public TreeMap<String, Slot> getSlotMap() {
        return slotMap;
    }

    public String getExitDialogue() {
        return exitDialogue;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setSlotMap(TreeMap<String, Slot> slotMap) {
        this.slotMap = slotMap;
    }

    public String makeChange(int balance){
        int balanceBeforeChange = balance;
        int[] coinValue = new int[]{25, 10, 5, 1};
        int[] totalCoinsArray = new int[]{0, 0, 0, 0};
        int leftOver = 0;
        for (int i = 0; i < 4; i++) {
            if (balance >= coinValue[i]) {
                leftOver = balance % coinValue[i];
                totalCoinsArray[i] = (balance - leftOver) / coinValue[i];
                balance = balance - (totalCoinsArray[i] * coinValue[i]);
            }
        }

        String[] coinNamesArray = new String[]{"quarter", "dime", "nickel", "penny", "quarters", "dimes", "nickels", "pennies"};
        String changeAsString = "";
        int firstCoin = 0;
        for (int i = 0; i < totalCoinsArray.length; i++){
            // length - 1?
            if (firstCoin > 0 && totalCoinsArray[i] > 0){
                changeAsString = changeAsString + ", ";
            }
            if (totalCoinsArray[i] > 0){
                firstCoin++;

                changeAsString = changeAsString + totalCoinsArray[i];
                        if (totalCoinsArray[i] > 1){
                            changeAsString = changeAsString + " " + coinNamesArray[i + 4];
                        }
                        else {
                            changeAsString = changeAsString + " " + coinNamesArray[i];
                        }
            }
        }

        String timeInfo = LocalDateTime.now().format(formatter);
        String makeChangeLog = timeInfo + " " + "GIVE CHANGE: " + showAsDollars(balanceBeforeChange) + " " + showAsDollars(balance);
        logger.writeToFile(makeChangeLog);
        return changeAsString;
    }


    public void takeMoney(int deposit){
        balance += deposit;
        int whichBalanceForFirst = 0;
        if (balance == 0) {
            whichBalanceForFirst = balance;
        }
        else {
            whichBalanceForFirst = balance - deposit;
        }
        String timeInfo = LocalDateTime.now().format(formatter);
        String feedMoneyLog = timeInfo + " " + "FEED MONEY: " + showAsDollars(whichBalanceForFirst) + " " + showAsDollars(balance);
        logger.writeToFile(feedMoneyLog);
    }

    public void getVendingInfo () {
        File vendingFile = new File("vendingmachine.csv");
        try (Scanner fileInput = new Scanner(vendingFile)){
            while(fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                if ((line != null) && (!line.isEmpty())) {
                    String[] parts = line.split("\\|");
                    String slotName = parts[0];
                    slotMap.put(slotName, new Slot(line));
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found, please try again.");
            exit(1);
        }
    }



        public String displayItems(){
        String info = "";
        for(Map.Entry<String, Slot> element: slotMap.entrySet()) {
            info = info + "\n" + element.getValue().getIdentifier();
            info = info + ", " + element.getValue().getBrandName();
            info = info + ", " + showAsDollars(element.getValue().getPrice());
            if (element.getValue().getQuantity() == 0){
                info = info + ", " + "SOLD OUT";
            }
            else {
                info = info + ", " + element.getValue().getQuantity() + " in stock";
            }
        }
            return info;
        }

        public String showAsDollars(int pennyMath){
            Double dollarMath = (double) pennyMath;
            dollarMath = dollarMath / 100.00;
            String stringMath = "$" + String.format("%.2f",dollarMath);
            return stringMath;
        }

    public void selectProduct(String slotName) {
    Slot slotValue = slotMap.get(slotName);
        if (!slotMap.keySet().contains(slotName)) { //if people put in a slot that doesn't exist
            System.out.println("That slot does not exist.");
            //return to purchasing menu
        } else {
            if (slotValue.getQuantity() == 0) {
                System.out.println("OUT OF STOCK");
                //return to purchase menu
            }
            if (balance >= slotValue.getPrice()) {
                String timeInfo = LocalDateTime.now().format(formatter);
                out.println(slotValue.getBrandName() + " " + showAsDollars(slotValue.getPrice()));
                System.out.println(slotValue.getPhrase());
                setBalance(balance - slotValue.getPrice());
                slotValue.setQuantity(slotValue.getQuantity() - 1);
                logger.writeToFile(timeInfo + " " + slotValue.getBrandName() + " " + slotValue.getIdentifier() + " "
                            + showAsDollars(balance + slotValue.getPrice()) + " " + showAsDollars(balance));
                }
                //print without ln for multiple uses. Put version for each purchase option
            }
        }


}
