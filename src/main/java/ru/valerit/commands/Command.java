package ru.valerit.commands;

import java.sql.SQLException;

public interface Command {
    void execute(String ... arguments);
}
