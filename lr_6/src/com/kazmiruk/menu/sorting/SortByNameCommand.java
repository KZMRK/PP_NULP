package com.kazmiruk.menu.sorting;

import com.kazmiruk.ticket.TouristTicket;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByNameCommand implements SortMenuItem {
    @Override
    public void sortInDescendingOrder(ArrayList<TouristTicket> touristTickets) {
        touristTickets.sort(Comparator.comparing(TouristTicket::getName).reversed());
    }
    @Override
    public void sortInAscendingOrder(ArrayList<TouristTicket> touristTickets) {
        touristTickets.sort(Comparator.comparing(TouristTicket::getName));
    }
}
