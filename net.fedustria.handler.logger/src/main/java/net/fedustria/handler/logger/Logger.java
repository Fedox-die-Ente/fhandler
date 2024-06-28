package net.fedustria.handler.logger;

import net.fedustria.handler.logger.exceptions.CommandException;

public class Logger {

    public static void main(String[] args) throws CommandException {
        throw new CommandException("Command already exists");
    }

}
