package com.laz.binguslite.command.commands;

import com.laz.binguslite.BingusLite;
import com.laz.binguslite.command.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerFinalsCommand implements Command {
    @Override
    public String getName() {
        return "playerfinals";
    }

    @Override
    public void execute(BingusLite bingusLite, String[] args) {
        try {
            Map<String, Integer> reverseSortedMap = bingusLite.getChatMessageParser().getAllPlayers()
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue,
                            LinkedHashMap::new));

            StringBuilder stringBuilder = new StringBuilder();

            int i = 1;
            for (Map.Entry<String, Integer> entry : reverseSortedMap.entrySet()) {
                String prefix = "";

                if (bingusLite.getChatMessageParser().getBlue().containsKey(entry.getKey())) {
                    prefix = bingusLite.getChatMessageParser().getBluePrefix();
                } else if (bingusLite.getChatMessageParser().getGreen().containsKey(entry.getKey())) {
                    prefix = bingusLite.getChatMessageParser().getGreenPrefix();
                } else if (bingusLite.getChatMessageParser().getRed().containsKey(entry.getKey())) {
                    prefix = bingusLite.getChatMessageParser().getRedPrefix();
                } else if (bingusLite.getChatMessageParser().getYellow().containsKey(entry.getKey())) {
                    prefix = bingusLite.getChatMessageParser().getYellowPrefix();
                }

                stringBuilder.append(i).append(". ").append(prefix).append(entry.getKey()).append(": ").append("\u00A7f").append(entry.getValue()).append("\n");
                i++;
            }

            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.replace(stringBuilder.lastIndexOf("\n"), stringBuilder.lastIndexOf("\n") + 1, "");
            }

            bingusLite.addChatComponentText(stringBuilder.toString());
        } catch (IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException
                 | InstantiationException exception) {
            exception.printStackTrace();
        }
    }
}
