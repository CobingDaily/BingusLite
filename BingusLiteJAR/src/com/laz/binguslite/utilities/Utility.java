package com.laz.binguslite.utilities;

import static com.laz.binguslite.mapping.Mappings.getMinecraftMethod;

public class Utility {
    public static final Object mc = getMinecraft();

    private static Object getMinecraft() {
        try {
            return getMinecraftMethod.invoke(null);
        } catch (Exception ignored) {
            return null;
        }
    }
}
