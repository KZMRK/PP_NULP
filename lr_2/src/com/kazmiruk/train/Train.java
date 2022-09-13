package com.kazmiruk.train;

import com.kazmiruk.time.Time;

/**
 * Class that stores information about the train
 */
public class Train {
    /** Train number */
    private int trainNumber;
    /** Destination of the train */
    private String destination;
    /** Train departure time */
    private Time departureTime;
    /** The number of seats in the general carriage */
    private int generalSeats;
    /** The number of seats in the platzkart carriage */
    private int platzkartSeats;
    /** The number of seats in the coupe carriage */
    private int coupeSeats;
    /** The number of seats in the deluxe carriage */
    private int luxurySeats;

    /**
     * Constructor without parameters
     */
    public Train() {}

        /**
     * Constructor with parameters
     *
     * @param trainNumber Train number
     * @param destination Destination of the train
     * @param departureTime Train departure time
     * @param generalSeats The number of seats in the general carriage
     * @param platzkartSeats The number of seats in the platzkart carriage
     * @param coupeSeats The number of seats in the coupe carriage
     * @param luxurySeats The number of seats in the deluxe carriage
     */
    public Train(
            int trainNumber,
            String destination,
            String departureTime,
            int generalSeats,
            int platzkartSeats,
            int coupeSeats,
            int luxurySeats
    ) {
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.departureTime = new Time(departureTime);
        this.generalSeats = generalSeats;
        this.platzkartSeats = platzkartSeats;
        this.coupeSeats = coupeSeats;
        this.luxurySeats = luxurySeats;
    }

    /**
     * @param destination Destination of the train
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @param trainNumber Train number
     */
    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    /**
     * @param departureTime Train departure time
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = new Time(departureTime);
    }

    /**
     * @param generalSeats The number of seats in the general carriage
     */
    public void setGeneralSeats(int generalSeats) {
        this.generalSeats = generalSeats;
    }

    /**
     * @param platzkartSeats The number of seats in the platzkart carriage
     */
    public void setPlatzkartSeats(int platzkartSeats) {
        this.platzkartSeats = platzkartSeats;
    }

    /**
     * @param coupeSeats The number of seats in the coupe carriage
     */
    public void setCoupeSeats(int coupeSeats) {
        this.coupeSeats = coupeSeats;
    }

    /**
     * @param luxurySeats The number of seats in the deluxe carriage
     */
    public void setLuxurySeats(int luxurySeats) {
        this.luxurySeats = luxurySeats;
    }

    public String getDest() {
        return destination;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getDepartureTime() {
        return departureTime.getTime();
    }

    public int getDepartureTimeInSecond() { return departureTime.getTimeInSecond(); }

    public int getGeneralSeats() {
        return generalSeats;
    }

    public int getPlatzkartSeats() {
        return platzkartSeats;
    }

    public int getCoupeSeats() {
        return coupeSeats;
    }

    public int getLuxurySeats() {
        return luxurySeats;
    }

    @Override
    public String toString() {
        return String.format("%-5d%-14s%-11s%-5d%-5d%-5d%-5d", trainNumber, destination, departureTime,
                generalSeats, platzkartSeats, coupeSeats, luxurySeats);
    }
}