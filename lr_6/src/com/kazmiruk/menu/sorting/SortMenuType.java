package com.kazmiruk.menu.sorting;

import java.util.HashMap;

/**
 * Enum that stores the sort parameter
 * code, its name, and its object of the corresponding class
 */
public enum SortMenuType {
    BY_NAME(1, "By name", new SortByNameCommand()),
    BY_SURNAME(2, "By surname", new SortBySurnameCommand()),
    BY_TICKET_RATE(3, "By ticket type rate", new SortByTicketRateCommand()),
    BY_TRANSPORT_RATE(4, "By transport rate", new SortByTransportRateCommand()),
    BY_TYPE_OF_FOOD_RATE(5, "By type of food rate", new SortByTypeOfFoodRateCommand()),
    BY_NUM_DAYS(6, "By number of days", new SortByNumOfDaysCommand());

    private final int optionCode;
    private final String optionName;
    private final SortMenuItem menuItem;

    SortMenuType(int i, String name, SortMenuItem menuItem) {
        optionCode = i;
        optionName = name;
        this.menuItem = menuItem;
    }

    /**
     * Gives an object of the
     * corresponding sorting method
     *
     * @param code Sort parameter code
     * @return Sort parameter object
     */
    public static SortMenuItem getOptionByCode(int code) {
        for (SortMenuType type : SortMenuType.values()) {
            if (type.optionCode == code) {
                return type.menuItem;
            }
        }
        return null;
    }

    /**
     * Gives the names and codes of the sorting methods
     *
     * @return Names and codes
     */
    public static HashMap<Integer, String> getOptionCodeAndName() {
        HashMap<Integer, String> codeName = new HashMap<>();
        for (SortMenuType type : SortMenuType.values()) {
            codeName.put(type.optionCode, type.optionName);
        }
        return codeName;
    }
}
