package com.company.main;

import com.company.clientList.ClientList;
import com.company.dirsCreator.DirectoryCreator;
import com.company.optionsPicker.OptionsPicker;
import com.company.printer.MenuPrinter;
import com.company.printer.Printer;
import com.company.screenshooter.ScreenShoter;

public class MainThread extends Thread {

    @Override
    public void run() {
        currentThread().setName("MainThread");
        startMenu();
    }

    private void startMenu() {
        Printer menuPrinter = new MenuPrinter();
        menuPrinter.printMenu();

        OptionsPicker picker = new OptionsPicker(4);
        int choose;
        boolean exit = false;

        while (!exit) {
            choose = picker.inputOption();
            switch (choose) {
                case 1:
                    ScreenShoter screenShoter = new ScreenShoter();
                    screenShoter.doScreenShot();
                    menuPrinter.printMenu();
                    break;
                case 2:
                    ClientList.showClients();
                    menuPrinter.printMenu();
                    break;
                case 3:
                    ClientList.refreshWithLogs();
                    menuPrinter.printMenu();
                    break;
                case 4:
                    DirectoryCreator directoryCreator = new DirectoryCreator();
                    directoryCreator.clean();
                    menuPrinter.printMenu();
                    break;
                case 0:
                    System.out.println("Goodbye");
                    closeAllConnections();
                    exit = true;
                    break;
                default:
                    System.out.println("IMPOSSIBLE ERROR");
            }
        }
    }

    private void closeAllConnections() {
        ClientList.getInstance().close();
    }
}