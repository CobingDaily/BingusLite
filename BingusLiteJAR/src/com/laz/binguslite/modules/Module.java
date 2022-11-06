package com.laz.binguslite.modules;

import com.laz.binguslite.events.Event;
import com.laz.binguslite.settings.BooleanSetting;
import com.laz.binguslite.settings.KeybindSetting;
import com.laz.binguslite.settings.Setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Module {
    private final String name;
    private boolean toggled;
    private final Category category;
    private boolean expanded;
    private int index;
    private final BooleanSetting hidden = new BooleanSetting("Hidden", false);
    private final KeybindSetting keyCode = new KeybindSetting("Keybind", 0);
    private final List<Setting> settings = new ArrayList<>();
    protected static final Random random = new Random();

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
        addSettings(hidden, keyCode);
    }

    public String getName() {
        return name;
    }

    public String getSuffix() {
        return "";
    }

    public boolean isEnabled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public void toggle() {
        toggled = !toggled;
        if (toggled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public Category getCategory() {
        return category;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isHidden() {
        return hidden.isEnabled();
    }

    public void setHidden(boolean hidden) {
        this.hidden.setEnabled(hidden);
    }

    public int getKey() {
        return keyCode.getCode();
    }

    public void setKey(int code) {
        keyCode.setCode(code);
    }


    public List<Setting> getSettings() {
        return settings;
    }

    protected void addSettings(Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onEvent(Event event) {
    }

    public void onBackground() {
    }

    public enum Category {
        GHOST("Ghost"),
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        PLAYER("Player"),
        RENDER("Render");

        private final String name;
        private int moduleIndex;

        Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getModuleIndex() {
            return moduleIndex;
        }

        public void setModuleIndex(int moduleIndex) {
            this.moduleIndex = moduleIndex;
        }
    }
}
