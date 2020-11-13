package me.caneva20.messagedispatcher;

import me.caneva20.messagedispatcher.exceptions.NoTokenRegistryDefined;

import java.util.HashMap;
import java.util.Map;

public class Messaging {
    private static final Map<MessageLevel, ColorSet> colors = new HashMap<>();

    public static ColorSet defaultColorSet = new ColorSet('2', 'a', '6', '0');

    private static TokenRegistry tokenRegistry;

    public static String makeTag(String name, MessageLevel level) {
        ColorSet colors = getColorSet(level);

        return String.format("&%s[&%s%s&%s] ", colors.detail, colors.accent, name, colors.detail);
    }

    public static ColorSet getColorSet(MessageLevel level) {
        return colors.getOrDefault(level, defaultColorSet);
    }

    public static void registerColorSet(MessageLevel level, ColorSet set) {
        colors.put(level, set);
    }

    public static void setTokenRegistry(TokenRegistry tokenRegistry) {
        Messaging.tokenRegistry = tokenRegistry;
    }

    public static TokenRegistry getTokenRegistry() {
        if (tokenRegistry == null) {
            throw new NoTokenRegistryDefined();
        }

        return tokenRegistry;
    }
}
