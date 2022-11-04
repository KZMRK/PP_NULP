package com.kazmiruk.ticket;

import com.kazmiruk.enums.TicketType;
import com.kazmiruk.enums.Transport;
import com.kazmiruk.enums.TypeOfFood;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketList {
    /** List of objects of tourist tickets */
    private static final ArrayList<TouristTicket> touristTickets = new ArrayList<>();
    /**
     * The ticket type is the key, the number
     * of tickets of this type in the list is
     * the value
     */
    private static final HashMap<TicketType, Integer> ticketTypeRate = new HashMap<>();

    private static final HashMap<Transport, Integer> transportRate = new HashMap<>();

    private static final HashMap<TypeOfFood, Integer> typeOfFoodRate = new HashMap<>();

    static {
        makeRateMap(TicketType.values(), ticketTypeRate);
        makeRateMap(Transport.values(), transportRate);
        makeRateMap(TypeOfFood.values(), typeOfFoodRate);
    }

    /**
     * Key - parameter for sorting, value - rating
     *
     * @param list of keys
     * @param rate hashmap for rating
     * @param <T> type of keys
     */
    private static <T> void makeRateMap(T[] list, HashMap<T, Integer> rate) {
        for (T object : list) {
            rate.put(object, 0);
        }
    }

    public void add(ArrayList<TouristTicket> touristTickets) {
        for (TouristTicket touristTicket : touristTickets) {
            this.add(touristTicket);
        }
    }

    /**
     * Adds the ticket to the list
     * and increases the rating of this type
     *
     * @param touristTicket Tourist ticket to be added
     */
    public void add(TouristTicket touristTicket) {
        touristTickets.add(touristTicket);

        increaseRating(ticketTypeRate, touristTicket.getTicketType());
        increaseRating(transportRate, touristTicket.getTransport());
        increaseRating(typeOfFoodRate, touristTicket.getTypeOfFood());
    }

    /**
     * Increases the rating for a given keyword
     *
     * @param rate rating hashmap
     * @param key for which need to increase the rating
     * @param <T> key type
     */
    private <T> void increaseRating(HashMap<T, Integer> rate, T key) {
        rate.put(key, rate.get(key) + 1);
    }

    public void remove(TouristTicket touristTicket) {
        touristTickets.remove(touristTicket);
    }

    /**
     * Deletes the ticket under the
     * given index from the list
     *
     * @param index of the ticket to delete
     */
    public void remove(int index) {
        try {
            TouristTicket deletedTicket = touristTickets.remove(index);
            reduceRating(ticketTypeRate, deletedTicket.getTicketType());
            reduceRating(transportRate, deletedTicket.getTransport());
            reduceRating(typeOfFoodRate, deletedTicket.getTypeOfFood());
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Index not in range [%d, %d]\n", 1, touristTickets.size());
        }
    }

    private <T> void reduceRating(HashMap<T, Integer> rate, T key) {
        rate.put(key, rate.get(key) - 1);
    }


    public ArrayList<TouristTicket> getTouristTickets() {
        return new ArrayList<>(touristTickets);
    }

    /**
     * Gives a number - the rating of
     * the corresponding type of ticket in the list
     *
     * @param ticketType type for which
     *                  need to find out the rating
     * @return Ticket rating
     */
    public Integer getTicketTypeRate(TicketType ticketType) {
        return ticketTypeRate.get(ticketType);
    }

    public Integer getTransportRate(Transport transport) { return transportRate.get(transport); }

    public Integer getTypeOfFoodRate(TypeOfFood typeOfFood) { return typeOfFoodRate.get(typeOfFood); }

    public boolean isEmpty() { return touristTickets.isEmpty(); }

    public int size() { return touristTickets.size(); }

    /**
     * Displays information about
     * all tourist tickets that are in the list
     */
    public void printTouristTickets() {
        if (touristTickets.isEmpty()) {
            System.out.println("The list is empty\n");
            return;
        }
        System.out.printf("%-3c%-15s%-20s%-14s%-50s%-14s%-16s%-4s\n", '#', "Name", "Surname",
                "Ticket type", "Destination", "Transport", "Type of food", "Days");
        int i = 1;
        for (TouristTicket touristTicket : touristTickets) {
            System.out.println(i + ". " + touristTicket);
            i++;
        }
    }
}
