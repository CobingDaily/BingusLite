package com.laz.binguslite.command.commands;

import com.laz.binguslite.BingusLite;
import com.laz.binguslite.command.Command;
import com.laz.binguslite.utils.PlayerUtil;

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

        String output = "Set scale to " + scale + "%";

        PlayerUtil.addChatComponentMessage(output);
    }
}
