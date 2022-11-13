package com.laz.binguslite.modules.movement;

import com.laz.binguslite.events.Event;
import com.laz.binguslite.events.listeners.EventJump;
import com.laz.binguslite.modules.Module;
import com.laz.binguslite.settings.NumberSetting;
import com.laz.binguslite.utilities.impl.MovementUtils;
import org.lwjgl.input.Keyboard;

public class LegitSpeed extends Module {
    NumberSetting speed = new NumberSetting("Speed", 60, 0, 100, 1);

    public LegitSpeed() {
        super("Legit Speed", Category.MOVEMENT);
    }

    @Override
    public String getSuffix() {
        return String.valueOf(speed.getValue());
    }

    @Override
    public int getKey() {
        return Keyboard.KEY_J;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventJump) {
            if (MovementUtils.isMoving() && MovementUtils.onGround() && MovementUtils.moveForward() > 0) {
                double coefficient = 1 + (speed.getValue() * 0.01);
                MovementUtils.setMotionX(MovementUtils.getMotionX() * coefficient);
                MovementUtils.setMotionZ(MovementUtils.getMotionZ() * coefficient);
            }
        }
    }
}

