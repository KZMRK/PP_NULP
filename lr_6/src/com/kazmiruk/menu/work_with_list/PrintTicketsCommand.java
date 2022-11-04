package com.kazmiruk.menu.work_with_list;

import com.kazmiruk.menu.other.MenuItem;
import com.kazmiruk.ticket.TicketList;

/**
 * Class that displays information
 * about all the tickets that are in the list
 */
public class PrintTicketsCommand implements MenuItem {
    @Override
    public void execute() {
        TicketList ticketList = new TicketList();
        ticketList.printTouristTickets();
    }
}
