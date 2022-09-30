package com.kazmiruk.droidbattle.battle;


import com.kazmiruk.droidbattle.file.FIleIO;
import com.kazmiruk.droidbattle.arena.BattleArena;
import com.kazmiruk.droidbattle.controlers.Menu;
import com.kazmiruk.droidbattle.droid.Droid;

import java.io.*;
import java.util.Scanner;

public class DroidBattle {

    private final Droid firstDroid ;
    private final Droid secondDroid;

    private Droid winner = null;
    private Droid loser = null;

    public DroidBattle(Droid firstDroid, Droid secondDroid) {
        this.firstDroid = firstDroid;
        this.secondDroid = secondDroid;
    }

    /**
     * Simulates a battle between two droids and
     * outputs the winning droid
     * @throws InterruptedException
     */
    public void battle() throws InterruptedException {
        FIleIO fIleIO = new FIleIO();;
        int key = new Menu().selectWriteToFile();
        if (key == 1) {
            fIleIO.fileStream();
        }

        BattleArena arena = new BattleArena(firstDroid, secondDroid);
        Droid winner = arena.startFight();

        System.out.println("--------------");
        System.out.println("The winner is " + winner.getName());

        this.winner = winner;
        this.loser = winner == firstDroid ? secondDroid : firstDroid;

        if (key == 1) {
            fIleIO.consoleStream();
            fIleIO.getBattleFromFile();
        }
    }

    public Droid getWinner() {
        return winner;
    }

    public Droid getLoser() {
        return loser;
    }
}
