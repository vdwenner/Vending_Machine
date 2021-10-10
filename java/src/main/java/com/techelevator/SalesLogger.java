package com.techelevator;

import java.io.*;

public class SalesLogger {
    private File salesFile = new File("salesreport.txt");

    private void createFile(){
        try {
            salesFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile (String message) {
        createFile();
        try (PrintWriter transactionLog = new PrintWriter(new FileOutputStream(salesFile, true))){
            transactionLog.append(message + "\n");
        }
        catch (FileNotFoundException e) {
            System.out.println("Output File Not Found");
            System.exit(404);
        }
    }
}
