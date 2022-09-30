package com.kazmiruk.droidbattle.controlers;

import com.kazmiruk.droidbattle.droid.ChortDroid;
import com.kazmiruk.droidbattle.droid.Droid;
import com.kazmiruk.droidbattle.droid.MavkaDroid;
import com.kazmiruk.droidbattle.droid.ViyDroid;
import com.kazmiruk.droidbattle.interfeces.Colors;
import com.kazmiruk.droidbattle.interfeces.DroidsConstants;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu implements Colors, DroidsConstants {



    /**
     * Shows the available features
     * of the program to the user
     */
    public void showMenu()  {
        System.out.println
                (ANSI_BLUE + """
                ************************************
                *               Menu               *
                ************************************
                |1 - create a droid                |
                |2 - show a list of created droids |
                |3 - start a 1 on 1 battle         |
                |4 - start a team-on-team battle   |
                |5 - exit                          |
                ************************************""" + ANSI_RESET);
    }

    /**
     * Shows droid types
     */
    @SuppressWarnings("MalformedFormatString")
    public void showDroidsType() {
        System.out.printf("""
                __________________________
                |     Types of droids    |
                |________________________|
                |    Name  HP   ENR  DMG |
                |1 - Mavka %-4d %-4d %-4d|
                |2 - Viy   %-4d %-4d %-4d|
                |3 - Chort 300  9    70  |
                |------------------------|
                """,
                MAVKA_HEALTH, MAVKA_ENERGY, MAVKA_DAMAGE,
                VIY_HEALTH, VIY_ENERGY, VIY_DAMAGE,
                CHORT_HEALTH, CHORT_ENERGY, CHORT_DAMAGE
        );
    }

    /**
     * Selects a menu option
     * @return Option number
     */
    public int selectOption() {
        Scanner scan = new Scanner(System.in);
        int option;
        System.out.print("\nOption: ");
        return optionSelector(MENU_MIN, MENU_MAX);
    }

    /**
     * Checks if the option number is entered
     * correctly and returns it if true, otherwise returns 0
     *
     * @param minOption Minimum option number
     * @param maxOption Maximum option number
     * @return Option number
     */
    public int optionSelector(int minOption, int maxOption) {
        Scanner scan = new Scanner(System.in);
        int option;
        try {
            option = scan.nextInt();
            if (option < minOption || option > maxOption) {
                System.out.printf("Option number " +
                        "must be in the range [%d, %d]%n", minOption, maxOption);
                return 0;
            }
        } catch (InputMismatchException e) {
            System.out.println("You must enter an integer number");
            return 0;
        }
        return option;
    }

    /**
     * Selects droid type
     *
     * @return Droid
     */
    public Droid selectDroidType() {
        int droidType;

        showDroidsType();
        System.out.print("Type: ");
        droidType = optionSelector(1, 3);

        return switch (droidType) {
            case 1 -> new MavkaDroid();
            case 2 -> new ViyDroid();
            case 3 -> new ChortDroid();
            default -> null;
        };
    }

    /**
     * Reads the index of the list and checks it
     * for correct input. If the index is entered
     * incorrectly, the method is called recursively.
     * If the list consists of 0 elements, the method returns 0
     *
     * @param listSize List size
     * @return List index
     */
    public int selectIndex(int listSize) {
        if (listSize == 0) {
            return -1;
        }
        int index;
        Scanner scan = new Scanner(System.in);

        System.out.print("Index of the droid (end - 0): ");
        try {
            index = scan.nextInt();
            if (index < 0 || index > listSize) {
                System.out.println("Index is out of range [1, " + listSize + "]");
                index = selectIndex(listSize);
            }
        } catch (InputMismatchException e) {
            System.out.println("You must enter an integer number");
            index = selectIndex(listSize);
        }

        return index - 1;
    }

    public int selectWriteToFile() {
        System.out.print("""
                        Write fight to file?
                        1. Yes
                        2. No
                        """
        );
        System.out.print("Option: ");
        return optionSelector(1, 2);
    }

}
