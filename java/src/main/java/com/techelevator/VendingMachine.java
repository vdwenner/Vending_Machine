package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.System.exit;
import static java.lang.System.nanoTime;

public class VendingMachine {
    public TreeMap<String, Slot> slotMap = new TreeMap<>();
    public int balance = 0;
    public String exitDialogue = "Thank you for your purchase! Have an amazing day!";


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
        int nickel = 5;
        int dime = 10;
        int quarter = 25;
        int numberOfPennies = 0;
        int numberOfNickels = 0;
        int numberOfDimes = 0;
        int numberOfQuarters = 0;
        int leftOver = 0;
        int coin = quarter;
        int numberOfCoins = 0;
        if (balance >= coin){
            leftOver = balance % coin;
            numberOfCoins = (balance - leftOver) / coin;
            balance = balance - (numberOfCoins * coin);
        }
        numberOfQuarters = numberOfCoins;
        numberOfCoins = 0;
        coin = dime;
        if (balance >= coin){
            leftOver = balance % coin;
            numberOfCoins = (balance - leftOver) / coin;
            balance = balance - (numberOfCoins * coin);
        }
        numberOfDimes = numberOfCoins;
        numberOfCoins = 0;
        coin = nickel;
        if (balance >= coin){
            leftOver = balance % coin;
            numberOfCoins = (balance - leftOver) / coin;
            balance = balance - (numberOfCoins * coin);
        }
        numberOfNickels = numberOfCoins;
        numberOfPennies = balance;
        int[] totalCoinsArray = new int[]{numberOfQuarters, numberOfDimes, numberOfNickels, numberOfPennies};
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


        return changeAsString;

    }

    public void takeMoney(int deposit){

        balance += (deposit * 100);
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
        for(Map.Entry<String, Slot> element: slotMap.entrySet()){
            info = info  + "\n" + element.getValue().getIdentifier();
            info = info + ", " + element.getValue().getBrandName();
            info = info + ", " + element.getValue().getPrice();
            info = info + ", " + element.getValue().getQuantity();
            //ordered Map like TreeMap to make it ordered
        }
            return info;
        }






}
