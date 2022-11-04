package com.laz.binguslite.command.commands;

import com.laz.binguslite.BingusLite;
import com.laz.binguslite.command.Command;
import com.laz.binguslite.utils.PlayerUtil;

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

        String output = (bingusLite.getConfig().finalsInTab ? "Enabled" : "Disabled") + " finals in tab";

        PlayerUtil.addChatComponentMessage(output);
    }
}
