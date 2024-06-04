package ru.valerit.commands;

public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", "exits the app", "exit");
    }

    @Override
    public void execute(String ... arguments) {
        System.out.println("Exiting...");
        System.exit(0);
    }
}