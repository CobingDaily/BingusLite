package com.laz.binguslite.utilities.impl;

import com.laz.binguslite.utilities.Utility;
import org.lwjgl.input.Mouse;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class MouseUtil extends Utility {
    private static Field buttonsField;

    static {
        try {
            buttonsField = Mouse.class.getDeclaredField("buttons");
        } catch (NoSuchFieldException exception) {
            exception.printStackTrace();
        }
    }

    public static void sendClick(int button, boolean state) {
        try {
            buttonsField.setAccessible(true);
            ByteBuffer buffer = (ByteBuffer) buttonsField.get(null);
            buttonsField.setAccessible(false);
            buffer.put(button, (byte) (state ? 1 : 0));
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

}
