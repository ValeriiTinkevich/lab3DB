package ru.valerit;


import ru.valerit.command.Command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {
    private final Map<String, Command> commands = new HashMap<>();

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public void execute(String commandName) throws SQLException {
        Command command = commands.get(commandName);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Command not found: " + commandName);
        }
    }
}
