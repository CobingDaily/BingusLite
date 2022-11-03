package com.laz.binguslite.events.listeners;

import com.laz.binguslite.events.Event;

public class EventKey extends Event {
    private int code;

    public EventKey(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
