package com.company.optionsPicker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OptionsPicker {
    private final Integer optionsAmount;

    public OptionsPicker(Integer optionsAmount) {
        this.optionsAmount = optionsAmount;

    }

    public int inputOption() {
        Integer input;

        boolean needValidation;
        do {
            input = null;
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                needValidation = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type, try again: ");
                needValidation = true;
            }


            if (input == null) {
                needValidation = true;
            } else if (input > optionsAmount || input < 0) {
                System.out.println("Invalid range, try again: ");
                needValidation = true;
            } else if (input <= optionsAmount) {
                needValidation = false;
            }


        } while (needValidation);
        return input;
    }


    int pickOption() {
        return inputOption();
    }
}
