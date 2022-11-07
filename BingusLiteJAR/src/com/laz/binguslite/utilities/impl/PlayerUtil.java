package com.laz.binguslite.utilities.impl;


import com.laz.binguslite.utilities.Utility;

import static com.laz.binguslite.mapping.Mappings.*;

public class PlayerUtil extends Utility {

    public static Object thePlayer() {
        try {
            return thePlayerField.get(mc);
        } catch (Exception ignored) {
            return null;
        }
    }

    public static Object theWorld() {
        try {
            return theWorldField.get(mc);
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

}
