package ru.valerit.command;
import ru.valerit.DataBaseManager;

import java.sql.SQLException;
import java.util.Map;

public class QueryCommand implements Command {
    private final String key;
    private final DataBaseManager dataBaseManager;

    public QueryCommand(String key, DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
        this.key = key;
    }

    @Override
    public void execute() throws SQLException {
        if(dataBaseManager.queryWithCondition(key)) {
            System.out.println("Query executed successfully");
        } else
        {
            System.out.println("Result is empty!");
        }
    }
}
