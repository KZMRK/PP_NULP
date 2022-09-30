package com.kazmiruk.droidbattle.arena;

import com.kazmiruk.droidbattle.droid.Droid;
import com.kazmiruk.droidbattle.interfeces.Colors;

import java.util.Random;

public class BattleArena implements Colors {
    private final Droid firstDroid;
    private final Droid secondDroid;

    /** A droid that defends itself */
    private Droid defender;
    /** A droid that attacks */
    private Droid attacker;

    /** Number of the current round */
    private int currentRound = 0;

    public BattleArena(Droid firstDroid, Droid secondDroid) {
        this.firstDroid = firstDroid;
        this.secondDroid = secondDroid;
    }

    /**
     * Simulates a battle between two droids
     *
     * @return the winning droid
     * @throws InterruptedException
     */
    public Droid startFight() throws InterruptedException {
        System.out.println(ANSI_RED + firstDroid.getName() + " VS " + secondDroid.getName() + ANSI_RESET);
        do {
            prepareRound();
            doFight();
        }while (defender.isAlive());
        return attacker;
    }

    /**
     * Battle of the round
     * If the attacking droid hasn't used a
     * special ability yet and its health is half
     * the defender's health, then it uses its
     * special ability, otherwise it just hits
     */
    private void doFight() {
        if (!attacker.hasUseSpecialFeature() && attacker.getHealth() < defender.getHealth() / 2) {
            System.out.print(ANSI_RED + attacker.getName() + ANSI_RESET + " uses ");
            System.out.println(ANSI_YELLOW + "!!!SPECIAL FEATURE!!!" + ANSI_RESET);
            attacker.useSpecialFeature(defender);
            printDroidsInfo();
            return;
        }
        int actualDamage = defender.getHit(attacker.getDamage());
        printRoundInfo(actualDamage);
    }

    /**
     * Prepares the droids for the round
     * and displays the necessary information
     */
    private void prepareRound() {
        initDroidsRole();
        currentRound++;
        System.out.println("-------------------------------------");
        System.out.println("Round " + currentRound);
    }

    /**
     * Initializes the droid roles. With a probability of 50%,
     * one of them will be a defender and the other will be an
     * attacker
     */
    private void initDroidsRole() {
        Random random = new Random();
        if (random.nextBoolean()) {
            attacker = firstDroid;
            defender = secondDroid;
        } else {
            attacker = secondDroid;
            defender = firstDroid;
        }
    }

    /**
     * Displays information about the current round
     *
     * @param actualDamage A hit made by the attacker
     */
    private void printRoundInfo(int actualDamage) {
        if (actualDamage != 0) {
            System.out.println(ANSI_GREEN + defender.getName() + ANSI_RESET + " get hit with " + actualDamage + " damage");
        } else {
            System.out.println(ANSI_GREEN + defender.getName() + ANSI_RESET +
                    " did not hit " + ANSI_RED + attacker.getName() + ANSI_RESET);
        }
        printDroidsInfo();
    }


    private void printDroidsInfo() {
        System.out.println("Defender " + ANSI_GREEN + defender + ANSI_RESET);
        System.out.println("Attacker " + ANSI_RED + attacker + ANSI_RESET);
    }
}
