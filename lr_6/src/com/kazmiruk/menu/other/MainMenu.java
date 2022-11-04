package com.kazmiruk.menu.other;

import java.util.HashMap;

/**
 * A menu class that uses the pattern command to
 * call the corresponding option by the entered
 * option number
 */
public class MainMenu {
    /**
     * Selects menu options and executes it
     *
     * @param code Option code
     */
    public void execute(int code) {
        MenuItem menuItem = Options.getOptionByCode(code);
        if (menuItem != null) {
            menuItem.execute();
        } else {
            System.out.println("Incorrect command. Try again");
        }
    }

    /**
     * Shows all possible menu options
     */
    public void showMenu() {
        HashMap<Integer, String> menu = Options.getOptionCodeAndName();
        System.out.println("------------------------------------");
        for (Integer optionCode : menu.keySet()) {
            System.out.println(optionCode + ". " + menu.get(optionCode));
        }
        System.out.println("------------------------------------");
    }

}
