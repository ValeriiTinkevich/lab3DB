package ru.valerit;


import ru.valerit.commands.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {
    private final Map<String, Command> commands = new HashMap<>();
    private final DataBaseManager dataBaseManager;

    public CommandInvoker(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public void initCommands() {
        commands.put("addkey", new AddKeyCommand(this.dataBaseManager));
        commands.put("exit", new ExitCommand());
        commands.put("help", new HelpCommand(this.commands));
        commands.put("query", new QueryCommand(this.dataBaseManager));
        commands.put("getcondition", new GetConditionCommand(this.dataBaseManager));

    }

    public void execute(String command, String ... arguments) {
        try {
            commands.get(command).execute(arguments);
        } catch (NullPointerException e) {
            System.out.println("Command not found");
        }
    }
}
