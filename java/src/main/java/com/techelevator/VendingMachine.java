package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

public class VendingMachine {
    public HashMap<String, Slot> slotMap = new HashMap<>();
    public int balance = 0;
    public String exitDialogue = "Thank you for your purchase! Have an amazing day!";

    public String getExitDialogue() {
        return exitDialogue;
    }

    public int getBalance() {
        return balance;
    }

    public int[] makeChange(int balance){
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
        int remainingToMakeChange = 0;
        if (balance >= coin){
            leftOver = balance % coin;
            numberOfCoins = (balance - leftOver) / coin;
            balance = balance - (numberOfCoins * coin);
        }
        numberOfQuarters = numberOfCoins;
        coin = dime;
        if (balance >= coin){
            leftOver = balance % coin;
            numberOfCoins = (balance - leftOver) / coin;
            balance = balance - (numberOfCoins * coin);
        }
        numberOfDimes = numberOfCoins;
        coin = nickel;
        if (balance >= coin){
            leftOver = balance % coin;
            numberOfCoins = (balance - leftOver) / coin;
            balance = balance - (numberOfCoins * coin);
        }
        numberOfNickels = numberOfCoins;
        numberOfPennies = balance;
        int[] coinsChange = new int[]{numberOfQuarters, numberOfDimes, numberOfNickels, numberOfPennies};
        return coinsChange;
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
        public HashMap<String, Slot> getSlotMap() {
            return slotMap;
        }


        public String displayItems(){
        String info = "";
        for(Map.Entry<String, Slot> element: slotMap.entrySet()){
            info += slotMap.get(element).getIdentifier();
            info += slotMap.get(element).getBrandName();
            info += slotMap.get(element).getPrice();
            info += slotMap.get(element).getQuantity();
        }
            return info;
        }






}
