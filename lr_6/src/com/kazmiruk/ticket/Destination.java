package com.kazmiruk.ticket;

import com.kazmiruk.enums.TicketType;

import java.util.HashMap;

/**
 * A class that stores the destinations
 * for the corresponding ticket type
 */
public class Destination {
    private final HashMap<TicketType, String[]> typeToDest;

    public Destination() {
        typeToDest = new HashMap<>();
        typeToDest.put(
                TicketType.TREATMENT,
                new String[] {
                        "Sanatorium \"Solva\" (Polyana)",
                        "Sanatorium \"Ostrech\" (near Chernigov)",
                        "Sanatorium \"Stozhary\" (Skhidnytsia)",
                        "Sanatorium \"Carpathia\" (Shayana)",
                        "Hotel and sanatorium complex \"Premium Podillia\" (Khmilnyk)",
                        "Sanatorium \"Odesa\" in Morshyn",
                        "Sanatorium \"Crystal Palace\" (Truskavtsi)",
                }
        );
        typeToDest.put(
                TicketType.EXCURSION,
                new String[] {
                        "Dendrological Park \"Sophiivka\" (Uman)",
                        "The pearls of Rivne region and the tunnel of love",
                        "Tour to Pochaiv + St. Anna's spring",
                        "Palaces and parks \"Kachanivka\" and \"Trostianets\"",
                        "Secrets of Radomysl Castle and Ukrainian Village",
                        "Tour to Mizhrichensky Park"
                }
        );
        typeToDest.put(
                TicketType.SHOPPING,
                new String[] {
                        "Khmelnytskyi commodity market",
                        "Tsum (Kyiv)",
                        "Sorochyn Fair (Poltava Oblast)"
                }
        );
        typeToDest.put(
                TicketType.VACATION,
                new String[] {
                        "SHATSKY LAKES (Volyn Region)",
                        "Henichesk (Kherson Region)",
                        "Morshyn (Lviv Region)",
                        "Yaremche (Ivano-Frankivsk Region)",
                }
        );
    }

    /**
     * Gives all destinations
     * for a given ticket type
     *
     * @param ticketType Which requires destinations
     * @return Array of destinations
     */
    public String[] getDestinations(TicketType ticketType) {
        return typeToDest.get(ticketType);
    }

    public void printDestinations(TicketType ticketType) {
        String[] destinations = typeToDest.get(ticketType);

        for (int i = 0; i < destinations.length; i++) {
            System.out.println(i + 1 + ". " + destinations[i]);
        }

    }
}
