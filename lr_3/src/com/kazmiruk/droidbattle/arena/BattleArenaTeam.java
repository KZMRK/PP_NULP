package com.kazmiruk.droidbattle.arena;

import com.kazmiruk.droidbattle.droid.Droid;
import com.kazmiruk.droidbattle.droids.DroidTeam;
import com.kazmiruk.droidbattle.interfeces.Colors;

import java.util.ArrayList;
import java.util.Random;

public class BattleArenaTeam implements Colors {
    private final DroidTeam firstTeam;
    private final DroidTeam secondTeam;

    private DroidTeam attackerTeam;
    private DroidTeam defenderTeam;

    private Droid attacker;
    private Droid defender;

    private int currentRound = 0;

    private ArrayList<Droid> winner;

    public BattleArenaTeam(DroidTeam firstTeam, DroidTeam secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }


    /**
     * Simulates a battle between two teams of droids.
     * One of the teams will probably defend and the other
     * will attack. Random droids will be selected from the
     * teams to fight
     *
     * @return The winning team
     * @throws InterruptedException
     */
    public DroidTeam startFight() throws InterruptedException {
        System.out.println(ANSI_RED + firstTeam.getName() + " VS " + secondTeam.getName() + ANSI_RESET);
        do {
            prepareRound();
            if (!attacker.hasUseSpecialFeature() && attacker.getHealth() < defender.getHealth() / 2) {
                System.out.print(ANSI_RED + attacker.getName() + ANSI_RESET + " uses ");
                System.out.println(ANSI_YELLOW + "!!!SPECIAL FEATURE!!!" + ANSI_RESET);
                attacker.useSpecialFeature(defender);
                printDroidsInfo();
            } else {
                int actualDamage = doFight();
                printRoundInfo(actualDamage);
            }
            if (!defender.isAlive()) {
                defenderTeam.removeDroid(defender);
            }
        }while (!defenderTeam.isEmpty());
        return attackerTeam;
    }

    /**
     * Task of hitting the defender droid
     * @return Damage
     */
    private int doFight() {
        return defender.getHit(attacker.getDamage());
    }

    /**
     * Preparing the round and the droids for battle
     */
    private void prepareRound() {
        initDroidTeamsRole();
        initDroidsRole();
        currentRound++;
        System.out.println("-------------------------------------");
        System.out.println("Round " + currentRound);
        System.out.println(ANSI_RED + attackerTeam.getName() + ANSI_RESET  + " team attacks "
                + ANSI_GREEN + defenderTeam.getName() + ANSI_RESET + " team");
    }

    /**
     * Initializes droid team roles.
     * With a probability of 50%, one of the teams will
     * be attacking, and the other will be defending
     */
    private void initDroidTeamsRole() {
        Random random = new Random();
        if (random.nextBoolean()) {
            attackerTeam = firstTeam;
            defenderTeam = secondTeam;
        } else {
            attackerTeam = secondTeam;
            defenderTeam = firstTeam;
        }
    }

    /**
     * Vibrating random droids determines their role in both teams
     */
    private void initDroidsRole() {
        attacker = attackerTeam.getDroid(new Random().nextInt(attackerTeam.size()));
        defender = defenderTeam.getDroid(new Random().nextInt(defenderTeam.size()));
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
            System.out.println(defender.getName() + " did not hit " + attacker.getName());
        }
        printDroidsInfo();
    }

    public void printDroidsInfo() {
        System.out.println("Defender " + ANSI_GREEN + defender + ANSI_RESET);
        System.out.println("Attacker " + ANSI_RED + attacker + ANSI_RESET);
    }
}
