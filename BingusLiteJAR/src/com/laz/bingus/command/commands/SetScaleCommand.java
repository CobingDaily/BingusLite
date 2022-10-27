package com.laz.bingus.command.commands;

import com.laz.bingus.BingusLite;
import com.laz.bingus.command.Command;

import java.lang.reflect.InvocationTargetException;

public class SetScaleCommand implements Command {
    @Override
    public String getName() {
        return "setscale";
    }

    @Override
    public void execute(BingusLite bingusLite, String[] args) {
        if (args.length != 1) {
            return;
        }

        double scale;

        try {
            scale = Double.parseDouble(args[0]);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
            return;
        }

        if (scale <= 0) {
            return;
        }

        bingusLite.getConfig().finalsCounterScale = scale;

        bingusLite.saveConfig();

        try {
            String output = "Set scale to " + scale + "%";

            bingusLite.addChatComponentText(output);
        } catch (IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException
                 | InstantiationException exception) {
            exception.printStackTrace();
        }
    }
}
