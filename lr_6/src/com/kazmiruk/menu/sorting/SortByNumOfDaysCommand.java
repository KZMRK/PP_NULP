package com.kazmiruk.menu.sorting;

import com.kazmiruk.ticket.TouristTicket;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByNumOfDaysCommand implements SortMenuItem {
    @Override
    public void sortInDescendingOrder(ArrayList<TouristTicket> touristTickets) {
        touristTickets.sort(Comparator.comparing(TouristTicket::getNumberOfDays).reversed());
    }
    @Override
    public void sortInAscendingOrder(ArrayList<TouristTicket> touristTickets) {
        touristTickets.sort(Comparator.comparing(TouristTicket::getNumberOfDays));
    }
}
