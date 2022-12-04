package com.kazmiruk.enums;

/**
 * Types of tourist tickets
 */
public enum TicketType {
    VACATION("Vacation"),
    EXCURSION("Excursion"),
    TREATMENT("Treatment"),
    SHOPPING("Shopping"),
    CRUISE("Cruise");

    private final String name;

    TicketType(String name) {
        this.name = name;
    }

    public static TicketType getByName(String name) {
        for (TicketType ticketType : TicketType.values()) {
            if (ticketType.name.equals(name)) {
                return ticketType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
