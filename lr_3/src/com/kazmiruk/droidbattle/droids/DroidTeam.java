package com.kazmiruk.droidbattle.droids;

/**
 * A class of droid team that has its own name
 */
public class DroidTeam extends Droids {
    /** The name of the droid team */
    private final String name;

    public DroidTeam(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
