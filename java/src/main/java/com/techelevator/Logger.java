package com.techelevator;

import java.io.*;
import java.util.Scanner;

public class Logger {
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
            transactionLog.append(message);
        }
        catch (FileNotFoundException e) {

        }
    }
}
