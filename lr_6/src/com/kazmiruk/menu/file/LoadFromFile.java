package com.kazmiruk.menu.file;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazmiruk.menu.other.MenuItem;
import com.kazmiruk.ticket.TicketList;
import com.kazmiruk.ticket.TouristTicket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for downloading tickets from a
 * file and adding them to the list
 */
public class LoadFromFile implements MenuItem {
    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        System.out.print("File name: ");
        String fileName = scan.nextLine();

        ArrayList<TouristTicket> touristTickets = readTicketsFromFile(fileName);
        if (touristTickets == null) {
            return;
        }

        TicketList ticketList = new TicketList();
        ticketList.add(touristTickets);
    }

    private ArrayList<TouristTicket> readTicketsFromFile(String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(
                    new File(filename),
                    new TypeReference<>() {
                    }
            );
        } catch (StreamReadException e) {
            System.out.println("The file contains errors or input data not in JSON format");
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }
}
