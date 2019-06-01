package com.company.dirsCreator;

import com.company.printer.Colors;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DirectoryCreator {

    public void clean() {
        if (verifyChoice()) {
            boolean successful = false;
            try {
                FileUtils.deleteDirectory(new File("local/screenshots"));
                successful = true;
            } catch (IOException e) {
                //ignore
            } finally {
                if (successful) {
                    System.out.println("Data removed successful");
                } else {
                    System.out.println("Error with remove Data");
                }
            }
        }
    }

    private boolean verifyChoice() {
        Boolean verification = null;
        System.out.println("Are you sure to delete all data? " + Colors.RED + "This operations will not be retreat ! " + Colors.RESET + "(y/n)");
        Scanner inputChoice = new Scanner(System.in);
        String choice;
        boolean correctInput = false;

        while (!correctInput) {
            choice = inputChoice.nextLine();

            choice = choice.toLowerCase();
            if (choice.equals("y")) {
                correctInput = true;
                verification = true;
            } else if (choice.equals("n")) {
                correctInput = true;
                verification = false;
            } else {
                System.out.println("Incorrect choice, try again: (y/n)");
                correctInput = false;
            }
        }

        return verification;
    }

    public void prepareDirs(String clientIP) {
        try {
            String strManyDirectories = "local/screenshots" + clientIP;
            boolean success = (new File(strManyDirectories)).mkdirs();
            if (success) {
                System.out.println("Directories: " + strManyDirectories + " created");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
