package com.laz.binguslite.command.commands;

import com.laz.binguslite.BingusLite;
import com.laz.binguslite.command.Command;
import com.laz.binguslite.utils.PlayerUtil;

import java.lang.reflect.InvocationTargetException;

public class FinalsCommand implements Command {
    @Override
    public String getName() {
        return "finals";
    }

    @Override
    public void execute(BingusLite bingusLite, String[] args) {
        int blueFinals = bingusLite.getChatMessageParser().getBlue()
                .values()
                .stream()
                .reduce(0, Integer::sum);

        int greenFinals = bingusLite.getChatMessageParser().getGreen()
                .values()
                .stream()
                .reduce(0, Integer::sum);

        int redFinals = bingusLite.getChatMessageParser().getRed()
                .values()
                .stream()
                .reduce(0, Integer::sum);

        int yellowFinals = bingusLite.getChatMessageParser().getYellow()
                .values()
                .stream()
                .reduce(0, Integer::sum);

        String finals = bingusLite.getChatMessageParser().getBluePrefix() + "BLUE: " + "\u00A7f" + blueFinals + "\n"
                + bingusLite.getChatMessageParser().getGreenPrefix() + "GREEN: " + "\u00A7f" + greenFinals + "\n"
                + bingusLite.getChatMessageParser().getRedPrefix() + "RED: " + "\u00A7f" + redFinals + "\n"
                + bingusLite.getChatMessageParser().getYellowPrefix() + "YELLOW: " + "\u00A7f" + yellowFinals;

        PlayerUtil.addChatComponentMessage(finals);
    }
}
