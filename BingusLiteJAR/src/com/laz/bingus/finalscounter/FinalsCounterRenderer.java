package com.laz.bingus.finalscounter;

import com.laz.bingus.BingusLite;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.Map;

import static com.laz.bingus.mapping.Mappings.*;

public class FinalsCounterRenderer {
    private final BingusLite bingusLite;
    private String blue = "";
    private String green = "";
    private String red = "";
    private String yellow = "";

    public FinalsCounterRenderer(BingusLite bingusLite) {
        this.bingusLite = bingusLite;
    }

    public void update() {
        blue = printTeam("Blue", bingusLite.getChatMessageParser().getBluePrefix(), bingusLite.getChatMessageParser().getBlue());
        green = printTeam("Green", bingusLite.getChatMessageParser().getGreenPrefix(), bingusLite.getChatMessageParser().getGreen());
        red = printTeam("Red", bingusLite.getChatMessageParser().getRedPrefix(), bingusLite.getChatMessageParser().getRed());
        yellow = printTeam("Yellow", bingusLite.getChatMessageParser().getYellowPrefix(), bingusLite.getChatMessageParser().getYellow());
    }

    public void render() {
        try {
            Object minecraft = getMinecraftMethod.invoke(null);

            boolean inGameHasFocus = inGameHasFocusField.getBoolean(minecraft);

            Object gameSettings = gameSettingsField.get(minecraft);
            boolean showDebugInfo = showDebugInfoField.getBoolean(gameSettings);

            if (bingusLite.getConfig().displayFinalsCounter
                    && !bingusLite.getChatMessageParser().getAllPlayers().isEmpty()
                    && inGameHasFocus
                    && !showDebugInfo) {
                float x = bingusLite.getConfig().finalsCounterX;
                float y = bingusLite.getConfig().finalsCounterY;

                double scale = bingusLite.getConfig().finalsCounterScale / 100.0;

                x /= scale;
                y /= scale;

                pushMatrixMethod.invoke(null);

                scaleMethod.invoke(null, scale, scale, 1.0);

                Object fontRendererObj = fontRendererObjField.get(minecraft);

                drawStringMethod.invoke(fontRendererObj, blue, x, y, -1, false);
                drawStringMethod.invoke(fontRendererObj, green, x, y + 10, -1, false);
                drawStringMethod.invoke(fontRendererObj, red, x, y + 20, -1, false);
                drawStringMethod.invoke(fontRendererObj, yellow, x, y + 30, -1, false);

                popMatrixMethod.invoke(null);
            }
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    private String printTeam(String team, String prefix, Map<String, Integer> players) {
        int finals = players
                .values()
                .stream()
                .reduce(0, Integer::sum);

        Map.Entry<String, Integer> highestFinalsPlayer = players
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(new AbstractMap.SimpleImmutableEntry<>("", 0));

        return prefix + team + " "
                + "\u00A7f"
                + "(" + finals + ") - "
                + highestFinalsPlayer.getKey()
                + " (" + highestFinalsPlayer.getValue() + ")";
    }
}
