package com.laz.bingus.command.commands;

import com.laz.bingus.BingusLite;
import com.laz.bingus.command.Command;

import java.lang.reflect.InvocationTargetException;

public class ResetFinalsCommand implements Command {
    @Override
    public String getName() {
        return "resetfinals";
    }

    @Override
    public void execute(BingusLite bingusLite, String[] args) {
        bingusLite.getChatMessageParser().reset();

        try {
            bingusLite.addChatComponentText("Reset finals");
        } catch (IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException
                 | InstantiationException exception) {
            exception.printStackTrace();
        }
    }
}
