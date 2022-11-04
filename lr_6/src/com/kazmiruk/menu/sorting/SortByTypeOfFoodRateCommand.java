package com.kazmiruk.menu.sorting;

import com.kazmiruk.ticket.TouristTicket;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByTypeOfFoodRateCommand implements SortMenuItem {
    @Override
    public void sortInDescendingOrder(ArrayList<TouristTicket> touristTickets) {
        touristTickets.sort(Comparator.comparing(TouristTicket::getTypeOfFood));
        touristTickets.sort(Comparator.comparing(TouristTicket::getTypeOfFoodRate).reversed());
    }
    @Override
    public void sortInAscendingOrder(ArrayList<TouristTicket> touristTickets) {
        touristTickets.sort(Comparator.comparing(TouristTicket::getTypeOfFood));
        touristTickets.sort(Comparator.comparing(TouristTicket::getTypeOfFoodRate));
    }
}
