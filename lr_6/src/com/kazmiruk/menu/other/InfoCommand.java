package com.kazmiruk.menu.other;

/**
 * Displays information about the program
 */
public class InfoCommand implements MenuItem {
    public void execute() {
        System.out.println("""
                The program forms a set of proposals for the client on the choice of tourist
                vouchers of various types (vacation, excursions, treatment, shopping) for
                optimal choice. Take into account the possibility of choosing transport, food and
                number of days Sorting of vouchers according to certain parameters has been implemented.
                """);
    }
}
