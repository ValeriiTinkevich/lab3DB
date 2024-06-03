package ru.valerit.command;
import ru.valerit.DataBaseManager;

import java.sql.SQLException;
import java.util.Map;

public class AddKeyCommand implements Command {
    private final DataBaseManager dbManager;
    private final String key;
    private final String value;

    public AddKeyCommand(DataBaseManager dbManager, String key, String value) {
        this.dbManager = dbManager;
        this.key = key;
        this.value = value;
    }

    @Override
    public void execute() throws SQLException {
        if(dbManager.addKey(key, value)) {
            System.out.println("key added successfully");
        } else {
            System.out.println("key not added");
        }
    }
}

