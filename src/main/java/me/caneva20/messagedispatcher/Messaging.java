package me.caneva20.messagedispatcher;

import java.util.HashMap;
import java.util.Map;

public class Messaging {
    private static final Map<MessageLevel, ColorSet> colors = new HashMap<>();

    public static ColorSet defaultColorSet = new ColorSet('2', 'a', '6', '0');

    public static ColorSet getColorSet(MessageLevel level) {
        return colors.getOrDefault(level, defaultColorSet);
    }

    public static void registerColorSet(MessageLevel level, ColorSet set) {
        colors.put(level, set);
    }
}
