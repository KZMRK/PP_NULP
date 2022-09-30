package com.kazmiruk.droidbattle.file;

import java.io.*;

public class FIleIO {

    private static PrintStream stdout;

    /**
     * Changes the stream to a file
     */
    public void fileStream() {
        stdout = System.out;
        PrintStream out = null;
        try {
            out = new PrintStream(new File("droid-battle.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.setOut(out);
    }

    /**
     * Changes the stream to console
     */
    public void consoleStream() {
        System.setOut(stdout);
    }

    /**
     * Reads battle from file
     */
    public void getBattleFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("droid-battle.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
