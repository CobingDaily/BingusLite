package com.laz.binguslite.modules.render;

import com.laz.binguslite.BingusLite;
import com.laz.binguslite.events.Event;
import com.laz.binguslite.events.listeners.EventRenderTick;
import com.laz.binguslite.modules.Module;
import com.laz.binguslite.utilities.impl.GameUtils;
import com.laz.binguslite.utilities.impl.GlUtils;

import java.util.Collection;

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
            if (GameUtils.inGameHasFocus()) {
                GlUtils.pushMatrix();
                int i = 0;
                Collection<Module> modules = BingusLite.modules.values();
                for (Module module : modules) {
                    if (module.isEnabled()) {
                        GlUtils.drawStringWithShadow(module.getName(), 2, 2 + (i * 10), -1);
                        i++;
                    }
                }
                GlUtils.popMatrix();
            }
        }
    }
}
