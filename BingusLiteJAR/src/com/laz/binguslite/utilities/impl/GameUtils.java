package com.laz.binguslite.utilities.impl;

import com.laz.binguslite.utilities.Utility;

import static com.laz.binguslite.mapping.Mappings.inGameHasFocusField;

public class GameUtils extends Utility {

    public static boolean inGameHasFocus() {
        try {
            return inGameHasFocusField.getBoolean(mc);
        } catch (Exception ignored) {
            return false;
        }
    }
}
