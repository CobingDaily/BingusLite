package com.laz.binguslite.settings;

import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting {
    private int index;
    private List<String> modes;

    public ModeSetting(String name, String defaultMode, String... modes) {
        this.name = name;
        this.modes = Arrays.asList(modes);
        index = this.modes.indexOf(defaultMode);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getModes() {
        return modes;
    }

    public String[] modesArray() {
        return modes.toArray(new String[0]);
    }

    public String getMode() {
        return modes.get(index);
    }

    public void setMode(String mode) {
        index = modes.indexOf(mode);
    }
    public void setModes(List<String> modes) {
        this.modes = modes;
    }

    public boolean is(String mode) {
        return index == modes.indexOf(mode);
    }

    public void cycle() {
        if (index < modes.size() - 1) {
            index++;
        } else {
            index = 0;
        }
    }
}
