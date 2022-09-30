package com.kazmiruk.droidbattle.battle;

import com.kazmiruk.droidbattle.arena.BattleArenaTeam;
import com.kazmiruk.droidbattle.controlers.Menu;
import com.kazmiruk.droidbattle.droids.DroidTeam;
import com.kazmiruk.droidbattle.file.FIleIO;

import java.util.ArrayList;
import java.util.Scanner;

public class DroidTeamBattle {
    private final DroidTeam firstTeam;
    private final DroidTeam secondTeam;

    private DroidTeam winnerTeam;

    public DroidTeamBattle(DroidTeam firstTeam, DroidTeam secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    /**
     * Сreates a battle arena where two teams
     * of droids fight. Brings out the winning team
     * @throws InterruptedException
     */
    public void battle() throws InterruptedException {
        FIleIO fIleIO = new FIleIO();
        int key = new Menu().selectWriteToFile();
        if (key == 1) {
            fIleIO.fileStream();
        }

        BattleArenaTeam arena = new BattleArenaTeam(firstTeam, secondTeam);
        winnerTeam = arena.startFight();

        System.out.println("--------------");
        System.out.println("The winner is " + winnerTeam.getName());
        winnerTeam.printDroids();

        if (key == 1) {
            fIleIO.consoleStream();
            fIleIO.getBattleFromFile();
        }
    }

    public DroidTeam getWinnerTeam() {
        return winnerTeam;
    }
}
