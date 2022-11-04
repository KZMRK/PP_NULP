package com.kazmiruk.menu.sorting;

import com.kazmiruk.ticket.TouristTicket;

import java.util.ArrayList;

public interface SortMenuItem {
    void sortInDescendingOrder(ArrayList<TouristTicket> touristTickets);
    void sortInAscendingOrder(ArrayList<TouristTicket> touristTickets);
}
