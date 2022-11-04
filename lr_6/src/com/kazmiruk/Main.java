package com.kazmiruk;

import com.kazmiruk.menu.other.MainMenu;

import java.util.Scanner;

/**
 * A program that provides the user with the ability
 * to create travel tickets and work with a list of
 * tickets: delete tickets from the list, sort by
 * various parameters, save the link to a file and
 * read the list from a file
 */
public class Main {
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu();

        while (true) {
            mainMenu.showMenu();
            System.out.print("-> ");
            int command = scanner.nextInt();
            mainMenu.execute(command);
        }
    }
}
