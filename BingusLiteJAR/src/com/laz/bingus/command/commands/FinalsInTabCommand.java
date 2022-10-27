package com.laz.bingus.command.commands;

import com.laz.bingus.BingusLite;
import com.laz.bingus.command.Command;

import java.lang.reflect.InvocationTargetException;

public class FinalsInTabCommand implements Command {
    @Override
    public String getName() {
        return "finalsintab";
    }

    @Override
    public void execute(BingusLite bingusLite, String[] args) {
        bingusLite.getConfig().finalsInTab = !bingusLite.getConfig().finalsInTab;

        bingusLite.saveConfig();

        try {
            String output = (bingusLite.getConfig().finalsInTab ? "Enabled" : "Disabled") + " finals in tab";

            bingusLite.addChatComponentText(output);
        } catch (IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException
                 | InstantiationException exception) {
            exception.printStackTrace();
        }
    }
}
