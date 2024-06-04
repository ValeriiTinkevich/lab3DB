package ru.valerit.commands;

import ru.valerit.exceptions.WrongAmountOfElementsException;

import java.util.Map;

public class HelpCommand extends AbstractCommand {
    Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        super("help", "Shows all commands and usages", "help");
        this.commands = commands;
    }

    @Override
    public void execute(String ... arguments) {
        try {
            if (arguments.length != 0) throw new WrongAmountOfElementsException();
            for (String name : commands.keySet()) {
                String value = commands.get(name).toString();
                System.out.println(value);
            }
        } catch (WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            System.out.println(getUsage());
        }

    }
}
