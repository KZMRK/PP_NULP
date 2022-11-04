package com.kazmiruk.menu.sorting;

import com.kazmiruk.menu.other.MenuItem;
import com.kazmiruk.scan.Validator;
import com.kazmiruk.ticket.TicketList;
import com.kazmiruk.ticket.TouristTicket;

import java.util.*;

/**
 * Class for sorting tickets by
 * different parameters and in different orders
 */
public class SortTicketsCommand implements MenuItem {
    @Override
    public void execute() {
        Validator validator = new Validator();
        if (new TicketList().isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        ArrayList<TouristTicket> touristTickets = new TicketList().getTouristTickets();

        showSortMenu();
        int sortParamCode = validator.scanListIndex(SortMenuType.values().length);
        int orderCode = chooseOrder();

        SortMenuItem menuItem = SortMenuType.getOptionByCode(sortParamCode);

        if (menuItem != null) {
            if (orderCode == 1) {
                menuItem.sortInDescendingOrder(touristTickets);
            } else {
                menuItem.sortInAscendingOrder(touristTickets);
            }
        }
        showSortedList(touristTickets);
    }

    public void showSortMenu() {
        HashMap<Integer, String> menu = SortMenuType.getOptionCodeAndName();
        System.out.println("------------------------------------");
        for (Integer optionCode : menu.keySet()) {
            System.out.println(optionCode + ". " + menu.get(optionCode));
        }
        System.out.println("------------------------------------");
    }

    private int chooseOrder() {
        Validator validator = new Validator();
        System.out.println("""
                1. In descending order
                2. In ascending order""");
        return validator.scanListIndex(2);
    }

    private void showSortedList(ArrayList<TouristTicket> touristTickets) {
        System.out.printf("%-3c%-15s%-20s%-14s%-50s%-14s%-16s%-4s\n", '#', "Name", "Surname",
                "Ticket type", "Destination", "Transport", "Type of food", "Days");
        for (TouristTicket touristTicket : touristTickets) {
            System.out.println(touristTicket);
        }
    }
}
