package com.kazmiruk.enums;

/**
 * Types of food for tourists
 */
public enum TypeOfFood {
    BUFFET("Buffet"),
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    THREE_MEALS("Three meals"),
    DIETARY("Dietary"),
    WITHOUT_FOOD("Without food");

    private final String name;

    TypeOfFood(String name) {
        this.name = name;
    }

    public static TypeOfFood getByName(String name) {
        for (TypeOfFood typeOfFood : TypeOfFood.values()) {
            if (typeOfFood.name.equals(name)) {
                return typeOfFood;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
