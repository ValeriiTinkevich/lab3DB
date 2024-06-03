package ru.valerit.command;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Exiting...");
        System.exit(0);
    }
}