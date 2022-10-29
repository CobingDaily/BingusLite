package com.laz.binguslite.command.commands;

import com.laz.binguslite.BingusLite;
import com.laz.binguslite.command.Command;

import java.lang.reflect.InvocationTargetException;

public class SetPosCommand implements Command {
    @Override
    public String getName() {
        return "setpos";
    }

    @Override
    public void execute(BingusLite bingusLite, String[] args) {
        if (args.length != 2) {
            return;
        }

        int x;
        int y;

        try {
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
            return;
        }

        if (x < 0 || y < 0) {
            return;
        }

        bingusLite.getConfig().finalsCounterX = x;
        bingusLite.getConfig().finalsCounterY = y;

        bingusLite.saveConfig();

        try {
            String output = "Set pos to X: " + x + ", Y: " + y;

            bingusLite.addChatComponentText(output);
        } catch (IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException
                 | InstantiationException exception) {
            exception.printStackTrace();
        }
    }
}
