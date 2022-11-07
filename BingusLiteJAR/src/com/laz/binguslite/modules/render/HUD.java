package com.laz.binguslite.modules.render;

import com.laz.binguslite.events.Event;
import com.laz.binguslite.events.listeners.EventRenderTick;
import com.laz.binguslite.modules.Module;

public class HUD extends Module {
    HUD() {
        super("HUD", Category.RENDER);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventRenderTick) {

        }
    }
}
