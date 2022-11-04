package com.kazmiruk.menu.work_with_list;

import com.kazmiruk.menu.other.MenuItem;
import com.kazmiruk.scan.Validator;
import com.kazmiruk.ticket.TicketList;

/**
 * A class that deletes a ticket at
 * a given index from the list
 */
public class DeleteTicketCommand implements MenuItem {
    @Override
    public void execute() {
        Validator validator = new Validator();
        TicketList ticketList = new TicketList();
        if (ticketList.isEmpty()) {
            ticketList.printTouristTickets();
            return;
        }
        System.out.println("Ticket index: ");
        int ticketIndex = validator.scanListIndex(ticketList.size());
        ticketList.remove(ticketIndex - 1);
    }
}
