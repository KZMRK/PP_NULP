package com.kazmiruk.ticket;

import com.kazmiruk.enums.TicketType;
import com.kazmiruk.enums.Transport;
import com.kazmiruk.enums.TypeOfFood;

/**
 * A class that stores information
 * about a tourist ticket
 *
 * The class uses a pattern builder
 */
public class TouristTicket {
    private String name;
    private String surname;
    private TicketType ticketType;
    private String destination;
    private Transport transport;
    private TypeOfFood typeOfFood;
    private Integer numberOfDays;

    public static Builder builder() {
        return new Builder();
    }

    public TicketType getTicketType() {
        return ticketType;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public String getDestination() {
        return destination;
    }

    public Transport getTransport() {
        return transport;
    }

    public TypeOfFood getTypeOfFood() {
        return typeOfFood;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public Integer getTicketTypeRate() {
        return new TicketList().getTicketTypeRate(this.getTicketType());
    }

    public Integer getTransportRate() { return new TicketList().getTransportRate(this.getTransport()); }

    public Integer getTypeOfFoodRate() { return new TicketList().getTypeOfFoodRate(this.typeOfFood); }

    public String toString() {
        return String.format("%-15s%-20s%-14s%-50s%-14s%-16s%-4d",
                name, surname, ticketType, destination, transport, typeOfFood, numberOfDays);
    }

    /**
     * Pattern builder
     */
    public static class Builder {
        private final TouristTicket touristTicket;

        Builder() {
            touristTicket = new TouristTicket();
        }

        public Builder setName(String name) {
            touristTicket.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            touristTicket.surname = surname;
            return this;
        }

        public Builder setTicketType(TicketType ticketType) {
            touristTicket.ticketType = ticketType;
            return this;
        }

        public Builder setDestination(String destination) {
            touristTicket.destination = destination;
            return this;
        }

        public Builder setTransport(Transport transport) {
            touristTicket.transport = transport;
            return this;
        }

        public Builder setTypeOfFood(TypeOfFood typesOfFood) {
            touristTicket.typeOfFood = typesOfFood;
            return this;
        }

        public Builder setNumberOfDays(Integer numberOfDays) {
            touristTicket.numberOfDays = numberOfDays;
            return this;
        }

        public TouristTicket build() {
            return touristTicket;
        }
    }
}
