package com.kazmiruk;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class with Lucas numbers
 *
 * The field is an array of Luke numbers. Initially,
 * the array contains the first 2 values from Luke's series.
 * If you want to get a value under an index greater than 2,
 * then the addLukasNumbers method is called, which adds numbers
 * to the array and returns the required value.
 */
public class LucasNumbers {

    /** An array of Lucas numbers */
    static private final ArrayList<Integer> lukasNumbers = new ArrayList<>(Arrays.asList(2, 1));

    /**
     * Gives Lukas number
     *
     * @param n Lucas number index
     * @return  The value of the Lucas number
     */
    public static int getAt(int n) {
        if (n > lukasNumbers.size()) {
            addLukasNumbers(n);
        }
        return lukasNumbers.get(n - 1);
    }

    /**
     * Adds Luke numbers to an array
     *
     * @param n The new length of
     *          the Lucas number array
     */
    private static void addLukasNumbers(int n) {
        for (int i = lukasNumbers.size(); i < n; i++) {
            lukasNumbers.add(lukasNumbers.get(i - 1) + lukasNumbers.get(i - 2));
        }
    }
}
