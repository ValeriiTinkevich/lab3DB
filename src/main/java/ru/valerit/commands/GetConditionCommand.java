package ru.valerit.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.valerit.DataBaseManager;
import ru.valerit.exceptions.WrongAmountOfElementsException;

import java.io.IOException;
import java.sql.SQLException;

public class GetConditionCommand extends AbstractCommand {
    private final DataBaseManager dataBaseManager;

    public GetConditionCommand(DataBaseManager dataBaseManager) {
        super("getcondition","lists all conditions in the condition table", "getcondition");
        this.dataBaseManager = dataBaseManager;
    }


    @Override
    public void execute(String ... arguments) {
        try {
            if(arguments.length != 0) throw new WrongAmountOfElementsException();
            System.out.println(prettyPrintJsonUsingDefaultPrettyPrinter(dataBaseManager.getKeys()));
        } catch (IOException | SQLException | WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            System.out.println(getUsage());
        }
    }

    public String prettyPrintJsonUsingDefaultPrettyPrinter(String uglyJsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = objectMapper.readValue(uglyJsonString, Object.class);
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        return prettyJson;
    }
}
