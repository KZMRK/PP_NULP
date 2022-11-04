package com.kazmiruk.menu.other;

public class ExitCommand implements MenuItem {
    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
