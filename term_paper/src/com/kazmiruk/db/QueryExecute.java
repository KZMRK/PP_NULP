package com.kazmiruk.db;

import com.kazmiruk.constans.Queries;
import com.kazmiruk.enums.TicketType;
import com.kazmiruk.enums.Transport;
import com.kazmiruk.enums.TypeOfFood;
import com.kazmiruk.ticket.TouristTicket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class QueryExecute {
    private final Connection connection;
    PreparedStatement preparedStatement;

    public QueryExecute() {
        connection = DBConnection.connection();
    }

    public ObservableList<TouristTicket> selectTickets() throws SQLException {
        ObservableList<TouristTicket> data = FXCollections.observableArrayList();
        preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_TICKETS);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            data.add(
                    TouristTicket.builder()
                            .setTicketId(resultSet.getInt(1))
                            .setName(resultSet.getString(2))
                            .setSurname(resultSet.getString(3))
                            .setTicketType(TicketType.getByName(resultSet.getString(4)))
                            .setCountry(resultSet.getString(5))
                            .setDestination(resultSet.getString(6))
                            .setTransport(Transport.getByName(resultSet.getString(7)))
                            .setTypeOfFood(TypeOfFood.getByName(resultSet.getString(8)))
                            .setDate(resultSet.getDate(9).toLocalDate())
                            .setNumberOfDays(resultSet.getInt(10))
                            .build()
            );
        }
        return data;
    }

    public void deleteTicketByID(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(Queries.DELETE_TICKET_BY_ID);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void updateTicketByID(int id, TouristTicket touristTicket) throws SQLException {
        fillQueryTicketColumn(touristTicket, Queries.UPDATE_TICKET_BY_ID);
        preparedStatement.setInt(10, id);
        preparedStatement.executeUpdate();
    }

    public void addTicket(TouristTicket touristTicket) throws SQLException {
        fillQueryTicketColumn(touristTicket, Queries.INSERT_TICKET);
        preparedStatement.executeUpdate();
    }

    private void fillQueryTicketColumn(TouristTicket touristTicket, String query) throws SQLException {
        int ticketTypeID = getTicketTypeIDByValue(touristTicket.getTicketType().toString());
        int countryID = getCountryIDByTicketTypeIDAndValue(ticketTypeID, touristTicket.getCountry());
        int destinationID = getDestinationIDByCountryIDAndValue(countryID, touristTicket.getDestination());
        int transportID = getTransportIDByValue(touristTicket.getTransport().toString());
        int typeOfFoodID = getTypeOfFoodIDbyValue(touristTicket.getTypeOfFood().toString());

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, touristTicket.getName());
        preparedStatement.setString(2, touristTicket.getSurname());
        preparedStatement.setInt(3, ticketTypeID);
        preparedStatement.setInt(4, countryID);
        preparedStatement.setInt(5, destinationID);
        preparedStatement.setInt(6, transportID);
        preparedStatement.setInt(7, typeOfFoodID);
        preparedStatement.setDate(8, Date.valueOf(touristTicket.getDate()));
        preparedStatement.setInt(9, touristTicket.getNumberOfDays());
    }

    public ArrayList<String> getCountriesByTicketID(int ticketTypeID) throws SQLException {
        ArrayList<String> countries = new ArrayList<>();

        preparedStatement = connection.prepareStatement(Queries.SELECT_COUNTRIES_BY_TICKET_ID);
        preparedStatement.setInt(1, ticketTypeID);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            countries.add(rs.getString(1));
        }
        return countries;
    }

    public ArrayList<String> getDestinationsByCountryID(int countryID) throws SQLException {
        ArrayList<String> destinations = new ArrayList<>();

        preparedStatement = connection.prepareStatement(Queries.SELECT_DESTINATIONS_BY_COUNTRY_ID);
        preparedStatement.setInt(1, countryID);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            destinations.add(rs.getString(1));
        }

        return destinations;
    }

    public int getTicketTypeIDByValue(String value) throws SQLException {
        return getIDByValue(Queries.GET_TICKET_TYPE_ID_BY_NAME, value);
    }

    public int getCountryIDByTicketTypeIDAndValue(int ticketTypeID, String value) throws SQLException {
        return getIDByIDAndValue(Queries.GET_COUNTRY_ID_BY_TICKET_TYPE_ID_AND_NAME, ticketTypeID, value);
    }

    public int getDestinationIDByCountryIDAndValue(int countryID, String value) throws SQLException {
        return getIDByIDAndValue(Queries.GET_DESTINATION_ID_BY_NAME, countryID, value);
    }

    public int getTransportIDByValue(String value) throws SQLException {
        return getIDByValue(Queries.GET_TRANSPORT_ID_BY_NAME, value);
    }

    public int getTypeOfFoodIDbyValue(String value) throws SQLException {
        return getIDByValue(Queries.GET_TYPE_OF_FOOD_ID_BY_NAME, value);
    }

    private int getIDByValue(String query, String value) throws SQLException {
        ResultSet rs;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, value);
        rs = preparedStatement.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    private int getIDByIDAndValue(String query, int id, String value) throws SQLException {
        ResultSet rs;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, value);

        rs = preparedStatement.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
