package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.exit;

public class VendingMachine {
    public HashMap<String, Slot> slotMap = new HashMap<>();

    public String makeChange(int balance){
        int nickel = 5;
        int dime = 10;
        int quarter = 25;
        int leftOver = 0;
        int coin = quarter;
        if (balance >= coin){
            leftOver = balance % quarter;
        }
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



}
