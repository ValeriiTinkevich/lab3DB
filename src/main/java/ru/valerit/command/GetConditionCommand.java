package ru.valerit.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.valerit.DataBaseManager;

import java.io.IOException;
import java.sql.SQLException;

public class GetConditionCommand implements Command {
    private final DataBaseManager dataBaseManager;

    public GetConditionCommand(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }


    @Override
    public void execute() throws SQLException {
        try {
            System.out.println(prettyPrintJsonUsingDefaultPrettyPrinter(dataBaseManager.getKeys()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String prettyPrintJsonUsingDefaultPrettyPrinter(String uglyJsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = objectMapper.readValue(uglyJsonString, Object.class);
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        return prettyJson;
    }
}
