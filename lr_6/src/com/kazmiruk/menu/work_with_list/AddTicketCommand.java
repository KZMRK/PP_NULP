package com.kazmiruk.menu.work_with_list;

import com.kazmiruk.enums.TicketType;
import com.kazmiruk.enums.Transport;
import com.kazmiruk.enums.TypeOfFood;
import com.kazmiruk.menu.other.MenuItem;
import com.kazmiruk.scan.Validator;
import com.kazmiruk.ticket.Destination;
import com.kazmiruk.ticket.TicketList;
import com.kazmiruk.ticket.TouristTicket;

import java.util.Scanner;

/**
 * A class that reads information about a
 * ticket and adds it to the list of tickets
 */
public class AddTicketCommand implements MenuItem {

    private final Validator validator = new Validator();

    @Override
    public void execute() {

        TicketType ticketType = inputTicketType();

        TouristTicket touristTicket = TouristTicket.builder()
                .setTicketType(ticketType)
                .setName(inputName())
                .setSurname(inputSurname())
                .setDestination(inputDestination(ticketType))
                .setTransport(inputTypeOfTransport())
                .setTypeOfFood(inputTypeOfFood())
                .setNumberOfDays(inputNumberOfDays())
                .build();
        TicketList ticketList = new TicketList();
        ticketList.add(touristTicket);
    }

    /**
     * Reads the ticket type
     *
     * @return ticket type
     */
    private TicketType inputTicketType() {
        TicketType[] availableTicketType = TicketType.values();

        printList(availableTicketType, "Ticket types:");

        int ticketTypeIndex = validator.scanListIndex(availableTicketType.length);
        return availableTicketType[ticketTypeIndex - 1];
    }

    /**
     * Reads the tourist's name
     *
     * @return tourist's name
     */
    private String inputName() {
        System.out.print("Name: ");
        return validator.scanNameOrSurname();
    }

    /**
     * Reads the tourist's surname
     *
     * @return tourist's surname
     */
    private String inputSurname() {
        System.out.print("Surname: ");
        return validator.scanNameOrSurname();
    }

    /**
     * Reads the destination
     *
     * @param ticketType for which the available destinations are displayed
     * @return destination
     */
    private String inputDestination(TicketType ticketType) {
        Destination dest = new Destination();
        String[] destinations = dest.getDestinations(ticketType);

        printList(destinations, "Destinations:");

        int destIndex = validator.scanListIndex(destinations.length);
        return destinations[destIndex - 1];
    }

    /**
     * Reads the type of transport
     *
     * @return type of transport
     */
    private Transport inputTypeOfTransport() {
        Transport[] transports = Transport.values();

        printList(transports, "Transports:");

        int transportIndex = validator.scanListIndex(transports.length);
        return transports[transportIndex - 1];
    }

    /**
     * Reads the food type
     *
     * @return type of food
     */
    private TypeOfFood inputTypeOfFood() {
        TypeOfFood[] typesOfFoods = TypeOfFood.values();
        printList(typesOfFoods, "Type of foods:");
        int typeOfFoodIndex = validator.scanListIndex(typesOfFoods.length);
        return typesOfFoods[typeOfFoodIndex - 1];
    }

    /**
     * Reads the number of days the tourist
     * wants to spend in the selected destination
     *
     * @return Number of days
     */
    private int inputNumberOfDays() {
        System.out.print("Number of days: ");
        return validator.scanNumMoreThanOne();
    }

    /**
     * Displays a list
     * @param objects List of objects
     * @param title The name of the list
     * @param <T> List type
     */
    private <T> void printList(T[] objects, String title) {
        System.out.println(title);
        for (int i = 0; i < objects.length; i++) {
            System.out.println(i + 1 + ". " + objects[i]);
        }
    }
}
