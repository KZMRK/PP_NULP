package com.kazmiruk.main;

import com.kazmiruk.time.Time;
import com.kazmiruk.train.Train;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        /* declaration and initialization
         * of an array of objects of the Train class
         */
        ArrayList<Train> trains = readTrainsInfo();
        printTrains(trains, "\nEntered train data:");

        System.out.print("\nDestination: ");
        String dest = scan.nextLine();

        task1(trains, dest);
        task2(trains, dest);
        task3(trains, dest);

    }

    /**
     * Displays a list of trains heading
     * to the specified destination
     *
     * @param trains List of trains
     * @param dest Given destination
     */
    private static void task1(ArrayList<Train> trains, String dest) {
        ArrayList<Train> trainsToDest = trainsToDest(trains, dest);
        printTrains(trainsToDest, "\nTrains with destination " + dest + ":");
    }

    /**
     * Displays a list of trains bound for the
     * specified destination and departing after the
     * specified time
     *
     * @param trains List of trains
     * @param dest Given destination
     */
    private static void task2(ArrayList<Train> trains, String dest) {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nTime: ");
        Time time = new Time(scan.nextLine());
        ArrayList<Train> trainsAfterTime = trainsToDestAfterTime(trains, dest, time);
        printTrains(trainsAfterTime, "\nTrains after " + time + ":");
    }

    private static void task3(ArrayList<Train> trains, String dest) {
        ArrayList<Train> trainsWithGeneralSeats = trainsToDestHasGeneralSeats(trains, dest);
        printTrains(trainsWithGeneralSeats, "\nTrains that have general seats:");
    }

    /**
     * Reads information about each train and
     * forms a list of all trains
     *
     * @return List of all trains
     */
    private static ArrayList<Train> readTrainsInfo() {
        ArrayList<Train> trains = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        do {
            trains.add(readTrainInfo());
            System.out.print("Next train? (y/n): ");
        } while (scan.next().charAt(0) == 'y');
        return trains;
    }

    /**
     * Reads information about one train
     *
     * @return Train object
     */
    private static Train readTrainInfo() {
        Train train = new Train();
        Scanner scan = new Scanner(System.in);

        System.out.print("Train number: ");
        train.setTrainNumber(scan.nextInt());
        System.out.print("Destination: ");
        scan.nextLine();
        train.setDestination(scan.nextLine());
        System.out.print("Departure time (hh:mm:ss): ");
        train.setDepartureTime(scan.nextLine());
        System.out.print("General seats: ");
        train.setGeneralSeats(scan.nextInt());
        System.out.print("Platzkart seats: ");
        train.setPlatzkartSeats(scan.nextInt());
        System.out.print("Coupe seats: ");
        train.setCoupeSeats(scan.nextInt());
        System.out.print("Luxury seats: ");
        train.setLuxurySeats(scan.nextInt());
        scan.nextLine();
        return train;
    }

    /**
     * Displays list objects
     * in the form of a table
     *
     * @param trains Train list
     * @param capt the name of the table
     */
    @SuppressWarnings("SameParameterValue")
    private static void printTrains(ArrayList<Train> trains, String capt) {
        System.out.println(capt);

        if (trains.isEmpty()) {
            System.out.println("none");
            return;
        }
        for (Train train : trains) {
            System.out.println(train);
        }
    }

    /**
     * Specifies a list of trains bound for a given destination
     *
     * @param trains Train list
     * @param dest Given destination
     * @return Trains heading to the given destination
     */
    private static ArrayList<Train> trainsToDest(ArrayList<Train> trains, String dest) {
        ArrayList<Train> trainsToDestination = new ArrayList<>();

        for (Train train : trains) {
            if (train.getDest().equalsIgnoreCase(dest)) {
                trainsToDestination.add(train);
            }
        }

        return trainsToDestination;
    }

    /**
     * Determines the list of trains that go to a given
     * destination and depart after a given hour;
     *
     * @param trains Train list
     * @param time Given time
     * @param dest Given destination
     * @return Trains that depart after the specified time
     */
    private static ArrayList<Train> trainsToDestAfterTime(ArrayList<Train> trains, String dest, Time time) {
        ArrayList <Train> trainToDestAfterTime = new ArrayList<>();
        for (Train train : trains) {
            if (train.getDest().equalsIgnoreCase(dest) && train.getDepartureTimeInSecond() >= time.getTimeInSecond()) {
                trainToDestAfterTime.add(train);
            }
        }
        return trainToDestAfterTime;
    }

    /**
     * Determines the list of trains that go to a given
     * destination and have seats in the
     * general carriage of the train
     *
     * @param trains Train list
     * @param dest Given destination
     * @return Trains with general seats
     */
    private static ArrayList<Train> trainsToDestHasGeneralSeats(ArrayList<Train> trains, String dest) {
        ArrayList<Train> result = new ArrayList<>();
        for (Train train : trains) {
            if (train.getDest().equalsIgnoreCase(dest) && train.getGeneralSeats() > 0) {
                result.add(train);
            }
        }
        return result;
    }

}
