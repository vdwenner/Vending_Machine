package com.techelevator;

import java.io.*;

public class EverythingLogger {
    private File log = new File("log.txt");

    private void createFile(){
        try {
            log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile (String message) {
        createFile();
        try (PrintWriter transactionLog = new PrintWriter(new FileOutputStream(log, true))){
            transactionLog.append(message + "\n");
        }
        catch (FileNotFoundException e) {
            System.out.println("Output File Not Found");
            System.exit(404);
        }
    }
}
