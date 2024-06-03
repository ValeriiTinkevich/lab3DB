package ru.valerit.command;

import java.sql.SQLException;

public interface Command {
    void execute() throws SQLException;
}
