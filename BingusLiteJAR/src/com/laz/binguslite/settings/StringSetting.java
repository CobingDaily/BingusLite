package com.laz.binguslite.settings;

public class StringSetting extends Setting {
    private String string;

    public StringSetting(String name, String defaultString) {
        this.name = name;
        this.string = defaultString;
    }

    public String getString() {
        return this.string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
