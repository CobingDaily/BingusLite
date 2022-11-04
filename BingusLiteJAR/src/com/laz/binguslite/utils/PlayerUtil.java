package com.laz.binguslite.utils;


import static com.laz.binguslite.mapping.Mappings.*;

public class PlayerUtil {

    public static void addChatComponentMessage(String text) {
        try {
            Object chatComponentText = chatComponentTextClass.getDeclaredConstructor(String.class).newInstance(text);
            addChatComponentMessageMethod.invoke(thePlayer(), chatComponentText);
        } catch (Exception ignored) { }
    }

    public static Object thePlayer() {
        try {
            return thePlayerField.get(getMinecraft());
        } catch (Exception ignored) {
            return null;
        }
    }

    public static Object getMinecraft() {
        try {
            return getMinecraftMethod.invoke(null);
        } catch (Exception ignored) {
            return null;
        }
    }
}
