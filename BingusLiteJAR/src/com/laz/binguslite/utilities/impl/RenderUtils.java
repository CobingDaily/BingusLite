package com.laz.binguslite.utilities.impl;


import com.laz.binguslite.utilities.Utility;

import static com.laz.binguslite.mapping.Mappings.*;

public class RenderUtils extends Utility {

    public static Object getFontRendererObj() {
        try {
            return fontRendererObjField.get(mc);
        } catch (Exception ignored) {
            return null;
        }
    }

}
