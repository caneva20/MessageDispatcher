package me.caneva20.messagedispatcher.configurations;

import me.caneva20.messagedispatcher.ColorSwatch;
import me.caneva20.messagedispatcher.MessageLevel;

import java.util.HashMap;
import java.util.Map;

public class ColorConfiguration implements IConfiguration {
    private final Map<MessageLevel, ColorSwatch> colors = new HashMap<>();
    private ColorSwatch defaultSwatch;

    public ColorConfiguration() {
        defaultSwatch = new ColorSwatch('2', 'a', '6', '0');
    }

    public ColorSwatch get(MessageLevel level) {
        return colors.getOrDefault(level, defaultSwatch);
    }

    public void register(MessageLevel level, ColorSwatch set) {
        colors.put(level, set);
    }
}
