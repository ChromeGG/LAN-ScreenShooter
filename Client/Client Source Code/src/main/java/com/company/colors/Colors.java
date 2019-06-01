package com.company.colors;

@SuppressWarnings("all")
public class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";

    public static void test_colors() {
        System.out.println();
        System.out.println(BLACK + "This text has BLACK color" + RESET);
        System.out.println(RED + "This text has RED color" + RESET);
        System.out.println(GREEN + "This text has GREEN color" + RESET);
        System.out.println(YELLOW + "This text has YELLOW color" + RESET);
        System.out.println(BLUE + "This text has BLUE color" + RESET);
        System.out.println(PURPLE + "This text has PURPLE color" + RESET);
        System.out.println(CYAN + "This text has CYAN color" + RESET);
        System.out.println(WHITE + "This text has WHITE color" + RESET);
        System.out.println();
    }

    public static void test_backgrounds() {
        System.out.println();
        System.out.println(BLACK_BG + "This text has BLACK BACKGROUND" + RESET);
        System.out.println(RED_BG + "This text has RED BACKGROUND" + RESET);
        System.out.println(GREEN_BG + "This text has GREEN BACKGROUND" + RESET);
        System.out.println(YELLOW_BG + "This text has YELLOW BACKGROUND" + RESET);
        System.out.println(BLUE_BG + "This text has BLUE BACKGROUND" + RESET);
        System.out.println(PURPLE_BG + "This text has PURPLE BACKGROUND" + RESET);
        System.out.println(CYAN_BG + "This text has CYAN BACKGROUND" + RESET);
        System.out.println(WHITE_BG + "This text has WHITE BACKGROUND" + RESET);
        System.out.println();
    }

}