package me.caneva20.messagedispatcher;

import me.caneva20.messagedispatcher.exceptions.NoTokenRegistryDefined;

import java.util.HashMap;
import java.util.Map;

public class Messaging {
    private static final Map<MessageLevel, ColorSwatch> colors = new HashMap<>();

    public static ColorSwatch defaultColorSwatch = new ColorSwatch('2', 'a', '6', '0');

    private static ITokenRegistry tokenRegistry;

    public static String makeTag(String name, MessageLevel level) {
        ColorSwatch colors = getColorSet(level);

        return String.format("&%s[&%s%s&%s] ", colors.detail, colors.accent, name, colors.detail);
    }

    public static ColorSwatch getColorSet(MessageLevel level) {
        return colors.getOrDefault(level, defaultColorSwatch);
    }

    public static void registerColorSet(MessageLevel level, ColorSwatch set) {
        colors.put(level, set);
    }

    public static void setTokenRegistry(ITokenRegistry registry) {
        Messaging.tokenRegistry = registry;
    }

    public static ITokenRegistry getTokenRegistry() {
        if (tokenRegistry == null) {
            throw new NoTokenRegistryDefined();
        }

        return tokenRegistry;
    }
}
