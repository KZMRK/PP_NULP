package com.kazmiruk.menu.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazmiruk.menu.other.MenuItem;
import com.kazmiruk.ticket.TicketList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class to write a list of
 * tickets to a file
 */
public class WriteToFileCommand implements MenuItem {
    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();

        System.out.println("File name to record: ");
        String fileName = scan.nextLine();

        File file = new File(fileName);

        TicketList ticketList = new TicketList();

        try {
            mapper.writeValue(file, ticketList.getTouristTickets());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Tickets successfully written to the \"%s\" file\n", fileName);
    }
}
