package com.kazmiruk;

/**
 * Class with Lucas number
 *
 * The class fields are the index of the number and its value
 * The method of the isConditionTrue class checks whether the Luke
 * number is specified in the format w^2 - 1 (1 less than a certain square).
 */
public class LucasNumber {

    /** Lucas number index */
    private int lucasIndex;
    /** The value of the Lucas number */
    private int lucasValue;

    /**
     * Single constructor
     *
     * @param n Lucas number index
     */
    LucasNumber(int n) {
        lucasIndex = n;
        lucasValue = LucasNumbers.getAt(n);
    }

    /**
     * Determines whether the Luke number can be specified
     * in the form w^2 - 1 (1 less than a certain square).
     *
     * @return Whether Lucas number follow the format
     */
    boolean isConditionTrue() {
        return Math.sqrt(lucasValue + 1)  % 1 == 0;
    }

    /**
     * Gives the value of the Lucas number
     *
     * @return value of the Lucas number
     */
    int getLucasValue() {
        return lucasValue;
    }

    /**
     * Gives Lucas number index
     *
     * @return Lucas number index
     */
    int getLukasIndex() {
        return lucasIndex;
    }

    /**
     * Sets the index and value of the
     * Lucas number
     *
     * @param n Lucas number index
     */
    void setIndex(int n) {
        lucasIndex = n;
        lucasValue = LucasNumbers.getAt(n);
    }
}
