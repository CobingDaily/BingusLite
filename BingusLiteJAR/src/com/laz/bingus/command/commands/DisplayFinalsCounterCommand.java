package com.laz.bingus.command.commands;

import com.laz.bingus.BingusLite;
import com.laz.bingus.command.Command;

import java.lang.reflect.InvocationTargetException;

public class DisplayFinalsCounterCommand implements Command {
    @Override
    public String getName() {
        return "displayfinalscounter";
    }

    @Override
    public void execute(BingusLite bingusLite, String[] args) {
        bingusLite.getConfig().displayFinalsCounter = !bingusLite.getConfig().displayFinalsCounter;

        bingusLite.saveConfig();

        try {
            String output = (bingusLite.getConfig().displayFinalsCounter ? "Enabled" : "Disabled") + " finals counter HUD";

            bingusLite.addChatComponentText(output);
        } catch (IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException
                 | InstantiationException exception) {
            exception.printStackTrace();
        }
    }
}
