package ru.valerit;

import ru.valerit.commands.*;

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
        CommandInvoker invoker = new CommandInvoker(dataBaseManager);
        invoker.initCommands();

        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner, invoker);
        inputHandler.handle();


    }
}

