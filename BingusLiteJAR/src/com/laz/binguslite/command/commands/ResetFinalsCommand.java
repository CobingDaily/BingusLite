package com.laz.binguslite.command.commands;

import com.laz.binguslite.BingusLite;
import com.laz.binguslite.command.Command;
import com.laz.binguslite.utils.PlayerUtil;

import java.lang.reflect.InvocationTargetException;

public class ResetFinalsCommand implements Command {
    @Override
    public String getName() {
        return "resetfinals";
    }

    @Override
    public void execute(BingusLite bingusLite, String[] args) {
        bingusLite.getChatMessageParser().reset();

        PlayerUtil.addChatComponentMessage("Reset finals");
    }
}
