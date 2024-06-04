package ru.valerit.commands;
import ru.valerit.DataBaseManager;
import ru.valerit.exceptions.WrongAmountOfElementsException;

import java.sql.SQLException;

public class QueryCommand extends AbstractCommand {
    private final DataBaseManager dataBaseManager;

    public QueryCommand(DataBaseManager dataBaseManager) {
        super("query", "Executes a query to db with the key argument", "query <key>");
        this.dataBaseManager = dataBaseManager;
    }

    @Override
    public void execute(String ... arguments) {
        try {
            if (arguments.length != 1) throw new WrongAmountOfElementsException();
            if(dataBaseManager.queryWithCondition(arguments[0])) {
                System.out.println("Query executed successfully");
            } else
            {
                System.out.println("Result is empty!");
            }
        } catch (WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            System.out.println(getUsage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
