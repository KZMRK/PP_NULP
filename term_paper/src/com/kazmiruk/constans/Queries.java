package com.kazmiruk.constans;

public class Queries {
    public static final String SELECT_ALL_TICKETS =
            "select TicketID, Name, Surname, TicketType, Country, Destination, Transport, TypeOfFood, Date, NumberOfDays " +
            "from dbo.Tickets t                                                                                          " +
            "join dbo.TicketTypes tt                                                                                     " +
            " on t.TicketTypeID = tt.TicketTypeID                                                                        " +
            "join Countries c                                                                                            " +
            " on t.CountryID = c.CountryID                                                                               " +
            "join Destinations dest                                                                                      " +
            " on t.DestinationID = dest.DestinationID                                                                    " +
            "join Transports transp                                                                                      " +
            " on transp.TransportID = t.TransportID                                                                      " +
            "join TypesOfFood tof                                                                                        " +
            " on t.TypeOfFoodID = tof.TypeOfFoodID";
    public static final String DELETE_TICKET_BY_ID = "delete from dbo.Tickets where TicketID = ?";
    public static final String GET_TICKET_TYPE_ID_BY_NAME = "select TicketTypeID from TicketTypes where TicketType = ?";
    public static final String GET_COUNTRY_ID_BY_TICKET_TYPE_ID_AND_NAME = "select CountryID from Countries where TicketTypeID = ? and  Country = ?";
    public static final String GET_DESTINATION_ID_BY_NAME = "select DestinationID from Destinations where CountryID = ? and Destination = ?";
    public static final String GET_TRANSPORT_ID_BY_NAME = "select TransportID from Transports where Transport = ?";
    public static final String GET_TYPE_OF_FOOD_ID_BY_NAME = "select TypeOfFoodID from TypesOfFood where TypeOfFood = ?";
    public static final String INSERT_TICKET = "insert into Tickets (Name, Surname, TicketTypeID, CountryID, DestinationID, TransportID, TypeOfFoodID, Date, NumberOfDays) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_COUNTRIES_BY_TICKET_ID = "select Country from countries where TicketTypeID = ?";
    public static final String SELECT_DESTINATIONS_BY_COUNTRY_ID = "select Destination from Destinations where CountryID = ?";
    public static final String UPDATE_TICKET_BY_ID = "update dbo.Tickets set Name = ?, Surname = ?, TicketTypeID = ?, CountryID = ?, DestinationID = ?, TransportID = ?, TypeOfFoodID = ?, Date = ?, NumberOfDays = ? where TicketID = ?";
}
