package com.laz.bingus.command;

import com.laz.bingus.BingusLite;
import com.laz.bingus.command.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {
    private final BingusLite bingusLite;
    private final List<Command> commands = new ArrayList<>();

    public CommandManager(BingusLite bingusLite) {
        this.bingusLite = bingusLite;

        commands.add(new FinalsCommand());
        commands.add(new PlayerFinalsCommand());
        commands.add(new ResetFinalsCommand());
        commands.add(new DisplayFinalsCounterCommand());
        commands.add(new SetPosCommand());
        commands.add(new SetScaleCommand());
        commands.add(new FinalsInTabCommand());
    }

    public boolean executeCommand(String commandString) {
        List<String> splitCommand = new ArrayList<>(Arrays.asList(commandString.split(" ")));
        String name = splitCommand.remove(0);
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(name)) {
                command.execute(bingusLite, splitCommand.toArray(new String[0]));
                return true;
            }
        }

        return false;
    }
}
