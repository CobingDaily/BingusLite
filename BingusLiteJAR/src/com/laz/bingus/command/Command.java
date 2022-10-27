package com.laz.bingus.command;

import com.laz.bingus.BingusLite;

public interface Command {
    String getName();

    void execute(BingusLite bingusLite, String[] args);
}
