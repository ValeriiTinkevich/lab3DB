package ru.valerit.commands;
import ru.valerit.DataBaseManager;
import ru.valerit.exceptions.WrongAmountOfElementsException;

import java.sql.SQLException;
import java.util.Arrays;

public class AddKeyCommand extends AbstractCommand {
    private final DataBaseManager dbManager;

    public AddKeyCommand(DataBaseManager dbManager) {
        super("addkey", "Adds a key and value to the condition table", "addkey <key> <value>");
        this.dbManager = dbManager;
    }

    @Override
    public void execute(String ... arguments) {
        try {
            if (arguments.length < 2) throw new WrongAmountOfElementsException();
            String key = arguments[0];
            String[] condition = Arrays.copyOfRange(arguments, 1, arguments.length);
            String conditions = String.join(" ", condition);
            if(dbManager.addKey(key, conditions)) {
                System.out.println("key added successfully");
            } else {
                System.out.println("key not added");
            }
        } catch (WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            System.out.println(getUsage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

