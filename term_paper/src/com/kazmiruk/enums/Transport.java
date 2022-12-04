package com.kazmiruk.enums;

/**
 * Types of transport to get to the destination
 */
public enum Transport {
    BUS("Bus"),
    TRAIN("Train"),
    PLANE("Plane");

    private final String name;

    Transport(String name) {
        this.name = name;
    }

    public static Transport getByName(String name) {
        for (Transport transport : Transport.values()) {
            if (transport.name.equals(name)) {
                return transport;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
