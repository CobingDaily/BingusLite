package com.laz.binguslite.utils;


import static com.laz.binguslite.mapping.Mappings.*;

public class PlayerUtil {

    public static Object getMinecraft() {
        try {
            return getMinecraftMethod.invoke(null);
        } catch (Exception ignored) {
            return null;
        }
    }

    public static Object thePlayer() {
        try {
            return thePlayerField.get(getMinecraft());
        } catch (Exception ignored) {
            return null;
        }
    }

    public static Object theWorld() {
        try {
            return theWorldField.get(getMinecraft());
        } catch (Exception ignored) {
            return null;
        }
    }

    private static Object movementInput() {
        try {
            return movementInputField.get(thePlayer());
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


    public static void addChatComponentMessage(String text) {
        try {
            Object chatComponentText = chatComponentTextClass.getDeclaredConstructor(String.class).newInstance(text);
            addChatComponentMessageMethod.invoke(thePlayer(), chatComponentText);
        } catch (Exception ignored) { }
    }

    public static void jump() {
        try {
            jumpMethod.invoke(thePlayer());
        } catch (Exception ignored) { }
    }

    public static double getMotionX() {
        try {
            return motionXField.getDouble(thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }
    public static double getMotionY() {
        try {
            return motionYField.getDouble(thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }
    public static double getMotionZ() {
        try {
            return motionZField.getDouble(thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static void setMotionX(double motionX) {
        try {
            motionXField.setDouble(thePlayer(), motionX);
        } catch (Exception ignored) { }
    }

    public static void setMotionY(double motionY) {
        try {
            motionYField.setDouble(thePlayer(), motionY);
        } catch (Exception ignored) { }
    }

    public static void setMotionZ(double motionZ) {
        try {
            motionZField.setDouble(thePlayer(), motionZ);
        } catch (Exception ignored) { }
    }

    public static float rotationYaw() {
        try {
            return rotationYawField.getFloat(thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static float rotationPitch() {
        try {
            return rotationPitchField.getFloat(thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static boolean onGround() {
        try {
            return onGroundField.getBoolean(thePlayer());
        } catch (Exception ignored) {
            return false;
        }
    }
}
