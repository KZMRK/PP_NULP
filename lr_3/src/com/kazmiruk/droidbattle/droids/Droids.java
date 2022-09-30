package com.kazmiruk.droidbattle.droids;

import com.kazmiruk.droidbattle.droid.Droid;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class of Droids
 * Maintains a list of droids
 */
public class Droids {
    /** List of droids */
    private final LinkedList<Droid> droids;

    public Droids() {
        droids = new LinkedList<>();
    }

    /**
     * Adds the droid object to the list
     *
     * @param droid Droid object
     */
    public void addDroid(Droid droid) {
        if (droid == null) {
            return;
        }
        droids.add(droid);
    }

    /**
     * Displays all droids in
     * visual form
     */
    public void printDroids() {
        System.out.printf("%-2c %-10s %-5s %-5s %-5s", '#', "Name", "HP", "ENR", "DMG\n\r");
        for (int i = 0; i < droids.size(); i++) {
            Droid droid = droids.get(i);
            System.out.println(i + 1 +". " + droid);
        }
    }

    /**
     * Removes the droid from the list
     *
     * @param droid The droid to remove from the list
     */
    public void removeDroid(Droid droid) {
        droids.remove(droid);
    }

    /**
     * Determines whether the list is empty
     *
     * @return Is the list empty
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Droid list size
     *
     * @return list size
     */
    public int size() {
        return droids.size();
    }
    public Droid getDroid(int index) { return droids.get(index); }
}
