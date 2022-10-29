package com.laz.binguslite.command;

import com.laz.binguslite.BingusLite;

public interface Command {
    String getName();

    void execute(BingusLite bingusLite, String[] args);
}
