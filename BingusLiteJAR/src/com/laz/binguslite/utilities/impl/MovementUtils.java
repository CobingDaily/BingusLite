package com.laz.binguslite.utilities.impl;


import com.laz.binguslite.utilities.Utility;

import static com.laz.binguslite.mapping.Mappings.*;

public class MovementUtils extends Utility {

    private static Object movementInput() {
        try {
            return movementInputField.get(PlayerUtil.thePlayer());
        } catch (Exception ignored) {
            return null;
        }
    }

    public static float moveStrafe() {
        try {
            return moveStrafeField.getFloat(movementInput());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static float moveForward() {
        try {
            return moveForwardField.getFloat(movementInput());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static boolean isMoving() {
        return  moveForward() != 0 || moveStrafe() != 0;
    }

    public static boolean isMovingOnGround() {
        return  isMoving() && onGround();
    }

    public static boolean isJumping() {
        try {
            return jumpField.getBoolean(movementInput());
        } catch (Exception ignored) {
            return false;
        }
    }

    public static boolean isSneaking() {
        try {
            return sneakField.getBoolean(movementInput());
        } catch (Exception ignored) {
            return false;
        }
    }

    public static void jump() {
        try {
            jumpMethod.invoke(PlayerUtil.thePlayer());
        } catch (Exception ignored) { }
    }

    public static double getMotionX() {
        try {
            return motionXField.getDouble(PlayerUtil.thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static double getMotionY() {
        try {
            return motionYField.getDouble(PlayerUtil.thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static double getMotionZ() {
        try {
            return motionZField.getDouble(PlayerUtil.thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static void setMotionX(double motionX) {
        try {
            motionXField.setDouble(PlayerUtil.thePlayer(), motionX);
        } catch (Exception ignored) { }
    }

    public static void setMotionY(double motionY) {
        try {
            motionYField.setDouble(PlayerUtil.thePlayer(), motionY);
        } catch (Exception ignored) { }
    }

    public static void setMotionZ(double motionZ) {
        try {
            motionZField.setDouble(PlayerUtil.thePlayer(), motionZ);
        } catch (Exception ignored) { }
    }

    public static boolean onGround() {
        try {
            return onGroundField.getBoolean(PlayerUtil.thePlayer());
        } catch (Exception ignored) {
            return false;
        }
    }
}
