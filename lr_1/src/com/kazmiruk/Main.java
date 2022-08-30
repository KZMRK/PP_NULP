package com.kazmiruk;

import java.util.Scanner;

/**
 * Main class
 *
 * Input data is read through the command line or from the keyboard.
 * In the main method, an array of objects of the LukasNumber class is created.
 * The program outputs numbers among the first n Luke numbers, which can
 * be represented in the form w^2-1
 */
public class Main {
    public static void main(String[] args) {
        int n; // the number of Lucas numbers in the array

        /* console input */
        if (args.length == 1)  {
            n = Integer.parseInt(args[0]);
            if (n < 1 || n > 45) {
                System.out.println("The number is not in the range [1; 45]");
            }
        }
        /* keyboard input */
        else {
            n = scanNum("Print num [1; 45]: ");
        }

        LucasNumber[] lucasNumbers = new LucasNumber[n];

        /* fills the array with Lucas numbers */
        for (int i = 0; i < n; i++) {
            lucasNumbers[i] = new LucasNumber(i + 1);
        }

        System.out.println("Numbers that can be represented in the format w^2-1:");
        for (int i = 0; i < n; i++) {
            if (lucasNumbers[i].isConditionTrue()) {
                System.out.println("\tIndex: " + lucasNumbers[i].getLukasIndex());
                System.out.println("\tValue: " + lucasNumbers[i].getLucasValue());
            }
        }
    }

    /**
     * Reads a number and checks if it is in the range
     *
     * @param msg message before entering the number
     * @return a number entered by the user
     */
    @SuppressWarnings("SameParameterValue")
    private static int scanNum(String msg) {
        Scanner scan = new Scanner(System.in);
        int num;

        while (true) {
            System.out.print(msg);
            num = scan.nextInt();
            if (num > 0 && num <= 45) {
                break;
            }
            System.out.println("The number is not in the range [1; 45]\n");
        }

        return num;
    }
}
