package com.laz.binguslite.events.listeners;

import com.laz.binguslite.events.Event;

public class EventToggleModule extends Event {
    private final String name;
    private final boolean enabled;

    public EventToggleModule(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
