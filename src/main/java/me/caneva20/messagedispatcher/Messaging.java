package me.caneva20.messagedispatcher;

import me.caneva20.messagedispatcher.exceptions.NoTokenRegistryDefined;
import me.caneva20.messagedispatcher.registries.ITokenRegistry;

import java.util.HashMap;
import java.util.Map;

public class Messaging {
    private static final Map<MessageLevel, ColorSwatch> colors = new HashMap<>();

    public static ColorSwatch defaultColorSwatch = new ColorSwatch('2', 'a', '6', '0');

    private static ITokenRegistry tokenRegistry;

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
