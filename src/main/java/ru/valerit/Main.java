package ru.valerit;

import ru.valerit.command.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class Main {

    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws UnsupportedEncodingException, SQLException {
        FileHandler fh;
        try {
            fh = new FileHandler("lab3.log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String user = System.getenv("dbuser");
        String password = System.getenv("dbpassword");
        logger.addHandler(fh);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.setUseParentHandlers(false);
        consoleHandler.setEncoding("UTF-8");
        logger.addHandler(consoleHandler);
        DataBaseManager dataBaseManager = new DataBaseManager(user, password);
        CommandInvoker invoker = new CommandInvoker();
        invoker.register("help", new HelpCommand());

        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");
            String command = tokens[0];

            switch (command) {
                case "exit":
                    invoker.register("exit", new ExitCommand());
                    invoker.execute("exit");
                    break;
                case "addkey":
                    if (tokens.length != 3) {
                        System.out.println("Usage: addkey <key> <value>");
                        continue;
                    }
                    String key = tokens[1];
                    String value = tokens[2];
                    invoker.register("addkey", new AddKeyCommand(dataBaseManager, key, value));
                    invoker.execute("addkey");
                    break;
                case "query":
                    if (tokens.length != 2) {
                        System.out.println("Usage: query <key>");
                        continue;
                    }
                    key = tokens[1];
                    invoker.register("query", new QueryCommand(key, dataBaseManager));
                    invoker.execute("query");
                    break;
                case "help":
                    invoker.execute("help");
                    break;
                case "getcondition":
                    invoker.register("getcondition", new GetConditionCommand(dataBaseManager));
                    invoker.execute("getcondition");
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    invoker.execute("help");
            }
        }
    }
}

