package ru.valerit.command;

public class HelpCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Available commands:");
        System.out.println("  exit - Exit the application");
        System.out.println("  addkey <key> <value> - Add a key-value pair");
        System.out.println("  query <key> - Query the value of a key");
        System.out.println("  getcondition - Print all key: conditions");
        System.out.println("  help - Print this help message");
    }
}
