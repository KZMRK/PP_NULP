package com.kazmiruk.menu.sorting;

import com.kazmiruk.ticket.TouristTicket;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByTransportRateCommand implements SortMenuItem{
    @Override
    public void sortInDescendingOrder(ArrayList<TouristTicket> touristTickets) {
        touristTickets.sort(Comparator.comparing(TouristTicket::getTransport));
        touristTickets.sort(Comparator.comparing(TouristTicket::getTransportRate).reversed());
    }
    @Override
    public void sortInAscendingOrder(ArrayList<TouristTicket> touristTickets) {
        touristTickets.sort(Comparator.comparing(TouristTicket::getTransport));
        touristTickets.sort(Comparator.comparing(TouristTicket::getTransportRate));
    }
}
