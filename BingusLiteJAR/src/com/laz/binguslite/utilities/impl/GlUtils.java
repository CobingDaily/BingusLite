package com.laz.binguslite.utilities.impl;


import com.laz.binguslite.utilities.Utility;

import static com.laz.binguslite.mapping.Mappings.*;

public class GlUtils extends Utility {

    public static Object getFontRendererObj() {
        try {
            return fontRendererObjField.get(mc);
        } catch (Exception ignored) {
            return null;
        }
    }

    public static void pushMatrix() {
        try {
            pushMatrixMethod.invoke(null);
        } catch (Exception ignored) { }
    }

    public static void popMatrix() {
        try {
            popMatrixMethod.invoke(null);
        } catch (Exception ignored) { }
    }

    public static void scale(double x, double y, double z) {
        try {
            scaleMethod.invoke(null, x, y, z);
        } catch (Exception ignored) { }
    }

    public static void drawString(String text, float x, float y, int color) {
        drawString(text, x, y, color, false);
    }

    public static void drawStringWithShadow(String text, float x, float y, int color) {
        drawString(text, x, y, color, true);
    }

    private static void drawString(String text, float x, float y, int color, boolean shadow) {
        try {
            drawStringMethod.invoke(getFontRendererObj(), text, x, y, color, shadow);
        } catch (Exception ignored) { }
    }

}
