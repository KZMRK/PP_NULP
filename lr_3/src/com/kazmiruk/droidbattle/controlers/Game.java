package com.kazmiruk.droidbattle.controlers;

import com.kazmiruk.droidbattle.battle.DroidBattle;
import com.kazmiruk.droidbattle.battle.DroidTeamBattle;
import com.kazmiruk.droidbattle.droid.Droid;
import com.kazmiruk.droidbattle.droids.DroidTeam;
import com.kazmiruk.droidbattle.droids.Droids;
import com.kazmiruk.droidbattle.interfeces.Colors;

import java.util.Scanner;

/**
 * The class that starts the main loop of
 * the game and creates battles between droids
 */
public class Game implements Colors {
    private final Droids droids;
    private final Menu menu;

    public Game() {
        droids = new Droids();
        menu = new Menu();
    }

    /**
     * Starts the main game cycle
     * with the selection of menu options
     */
    public void playGame() {
        menu.showMenu();

        while (true) {
            int key = menu.selectOption();
            switch (key) {
                case 0:                                          break;
                case 1: droids.addDroid(menu.selectDroidType()); break;
                case 2: droids.printDroids();                    break;
                case 3: makeOneToOneFight();                     break;
                case 4: makeTeamToTeamFight();                   break;
                case 5: return;
            }
        }
    }

    /**
     * Requests battle droid numbers and initiates battle
     */
    private void makeOneToOneFight() {
        droids.printDroids();
        int droidIndex1 = menu.selectIndex(droids.size());
        if (droidIndex1 == -1) {
            return;
        }
        Droid droid1 = takeDroidFromList(droidIndex1);
        droids.printDroids();
        int droidIndex2 = menu.selectIndex(droids.size());
        if (droidIndex2 == -1) {
            return;
        }
        Droid droid2 = takeDroidFromList(droidIndex2);

        try {
            DroidBattle droidBattle = new DroidBattle(droid1, droid2);
            droidBattle.battle();
            droids.addDroid(droidBattle.getWinner());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Takes the droid from
     * the list under the entered index
     *
     * @return Droid from the list
     */
    private Droid takeDroidFromList(int droidIndex) {
        Droid droid = droids.getDroid(droidIndex);
        droids.removeDroid(droid);
        return droid;
    }

    /**
     * Creates two teams of droids and
     * starts a battle between them
     */
    private void makeTeamToTeamFight() {
        DroidTeam team1 = makeTeam(droids);
        DroidTeam team2 = makeTeam(droids);

        try {
            DroidTeamBattle droidTeamBattle = new DroidTeamBattle(team1, team2);
            droidTeamBattle.battle();
            DroidTeam winners = droidTeamBattle.getWinnerTeam();
            for (int i = 0; i < winners.size(); i++) {
                droids.addDroid(winners.getDroid(i));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a team of droids chosen by the user
     *
     * @param droids List of created droids
     * @return Team created
     */
    private DroidTeam makeTeam(Droids droids) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Team name: ");
        String name = scan.nextLine();
        int index;

        DroidTeam team = new DroidTeam(name);
        System.out.println("Choose droids for the team " + name + ":");
        while (true) {
            if (droids.size() != 0) {
                droids.printDroids();
            }
            index = menu.selectIndex(droids.size());
            System.out.println("\n");
            if (index == -1)
                break;
            team.addDroid(droids.getDroid(index));
            droids.removeDroid(droids.getDroid(index));
        }
        return team;
    }
}
