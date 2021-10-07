package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.exit;

public class VendingMachine {
    public void getVendingInfo () {



        File vendingFile = new File("vendingmachine.csv");
        try (Scanner input = new Scanner(vendingFile)){

        }
        catch (FileNotFoundException e){
            System.out.println("File not found, please try again.");
            exit(1);
        }



    }
}
