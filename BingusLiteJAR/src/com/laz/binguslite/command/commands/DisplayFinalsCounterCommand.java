package com.laz.binguslite.command.commands;

import com.laz.binguslite.BingusLite;
import com.laz.binguslite.command.Command;
import com.laz.binguslite.utils.PlayerUtil;

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

        String output = (bingusLite.getConfig().displayFinalsCounter ? "Enabled" : "Disabled") + " finals counter HUD";

        PlayerUtil.addChatComponentMessage(output);
    }
}
