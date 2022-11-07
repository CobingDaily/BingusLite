package com.laz.binguslite.modules.render;

import com.laz.binguslite.events.Event;
import com.laz.binguslite.events.listeners.EventRenderTick;
import com.laz.binguslite.modules.Module;
import com.laz.binguslite.utilities.impl.GlUtils;

public class HUD extends Module {
    public HUD() {
        super("HUD", Category.RENDER);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventRenderTick) {
            GlUtils.pushMatrix();
            GlUtils.drawStringWithShadow("Bingus Lite", 2, 2, -1);
            GlUtils.popMatrix();
        }
    }
}
