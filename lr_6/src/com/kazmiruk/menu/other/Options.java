package com.kazmiruk.menu.other;

import com.kazmiruk.menu.file.LoadFromFile;
import com.kazmiruk.menu.file.WriteToFileCommand;
import com.kazmiruk.menu.sorting.SortTicketsCommand;
import com.kazmiruk.menu.work_with_list.AddTicketCommand;
import com.kazmiruk.menu.work_with_list.DeleteTicketCommand;
import com.kazmiruk.menu.work_with_list.PrintTicketsCommand;

import java.util.HashMap;

/**
 * An Enum that stores option codes, their
 * names, and corresponding option objects
 */
public enum Options {
    INFO(1, "Info", new InfoCommand()),
    ADD_TICKET(2, "Add ticket", new AddTicketCommand()),
    PRINT_TICKETS(3, "Print tickets", new PrintTicketsCommand()),
    DELETE_TICKET(4, "Delete ticket", new DeleteTicketCommand()),
    SORT_RICKETS(5, "Sort tickets", new SortTicketsCommand()),
    WRITE_TO_FILE(6, "Write tickets to file", new WriteToFileCommand()),
    LOAD_FROM_FILE(7, "Load tickets from file", new LoadFromFile()),
    EXIT(8, "Exit", new ExitCommand());

    private final int code;
    private final String name;
    private final MenuItem menuItem;

    Options(int code, String name, MenuItem menuItem) {
        this.code = code;
        this.name = name;
        this.menuItem = menuItem;
    }

    /**
     * Gives an option object for the given code
     *
     * @param code Option code
     * @return Option object
     */
    public static MenuItem getOptionByCode(int code) {
        for (Options type : Options.values()) {
            if (type.code == code) {
                return type.menuItem;
            }
        }
        return null;
    }

    /**
     * Gives option codes and their names
     *
     * @return key - code, value - name
     */
    public static HashMap<Integer, String> getOptionCodeAndName() {
        HashMap<Integer, String> codeName = new HashMap<>();
        for (Options type : Options.values()) {
            codeName.put(type.code, type.name);
        }
        return codeName;
    }

}
