package com.company.printer;

public class MenuPrinter implements Printer {


    @Override
    public void printMenu() {
        System.out.println("MENU:");
        System.out.println("1. Make a screenshot");
        System.out.println("2. Show targets list");
        System.out.println("3. Refresh targets list manually");
        System.out.println("4. Clear all data");
        System.out.println("0. exit");
    }
}
