package ru.valerit.commands;

import ru.valerit.CommandInvoker;

import java.util.Arrays;
import java.util.Scanner;

public class InputHandler {
    Scanner userScanner;
    CommandInvoker commandInvoker;

    public InputHandler(Scanner userScanner, CommandInvoker commandInvoker) {
        this.userScanner = userScanner;
        this.commandInvoker = commandInvoker;
    }

    public void handle() {
        while (true) {
            System.out.print("> ");
            String input = userScanner.nextLine();
            String[] tokens = input.split(" ");
            String command = tokens[0].strip();
            commandInvoker.execute(command, Arrays.copyOfRange(tokens, 1, tokens.length));
        }
    }
}
