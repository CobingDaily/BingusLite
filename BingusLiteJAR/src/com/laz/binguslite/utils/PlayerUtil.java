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

    public static double motionX() {
        try {
            return (double) motionXField.get(thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }
    public static double motionY() {
        try {
            return (double) motionYField.get(thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }
    public static double motionZ() {
        try {
            return (double) motionZField.get(thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static float rotationYaw() {
        try {
            return (float) rotationYawField.get(thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static float rotationPitch() {
        try {
            return (float) rotationPitchField.get(thePlayer());
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static boolean onGround() {
        try {
            return (boolean) onGroundField.get(thePlayer());
        } catch (Exception ignored) {
            return false;
        }
    }
}
