package com.kazmiruk.scan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {

    /**
     * A regular expression to enter the first and last name in
     * the correct format. Surname and first name can be double
     * (must be written with a hyphen). For example, Oleg, Kyrylo,
     * Khrystyna-Olena
     */
    private static final String nameRegExp = "^[A-Z][a-z]+(-[A-Z][a-z]+)?$";
    private final Scanner scan = new Scanner(System.in);

    /**
     * Reading is carried out until the user enters an integer greater than 1
     *
     * @return the number that greater than 1
     */
    public int scanNumMoreThanOne() {
        int num;
        while (true) {
            try {
                num = scan.nextInt();

                if (num > 0) {
                    break;
                }

                System.out.println("The number must be greater than 1");
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer number");
                scan.next();
            }
        }
        return num;
    }

    /**
     * Reading occurs until the user enters an
     * index that is included in the dimension of the list
     *
     * @param listSize A value that cannot exceed the read number
     * @return List index
     */
    public int scanListIndex(int listSize) {
        int index;

        while (true) {
            try {
                System.out.print("-> ");
                index = scan.nextInt();
                scan.nextLine();
                if (index > 0 && index <= listSize) {
                    break;
                }

                System.out.printf("The index is not in the range [%d; %d]\n", 1, listSize);

            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer number");
                scan.next();
            }
        }
        return index;
    }

    /**
     * Input occurs until the user enters
     * a first or last name in the correct format
     *
     * @return Name or surname
     */
    public String scanNameOrSurname() {
        String line;
        while (!(line = scan.nextLine()).matches(nameRegExp)) {
            System.out.println("Wrong form. For example: Abcd, Abc-Abc");
            System.out.print("-> ");
        }
        return line;
    }

}
