package com.laz.binguslite.settings;

public class KeybindSetting extends Setting {
    private int code;

    public KeybindSetting(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
