package me.caneva20.messagedispatcher.parsing;

import me.caneva20.messagedispatcher.ColorSwatch;
import me.caneva20.messagedispatcher.MessageLevel;

import java.util.Map;

public abstract class TokenParser implements ITokenParser {
    protected final Map<MessageLevel, ColorSwatch> colorMap;
    protected final ColorSwatch defaultColor;

    protected TokenParser(
            Map<MessageLevel, ColorSwatch> colorMap,
            ColorSwatch defaultColor
    ) {
        this.colorMap = colorMap;
        this.defaultColor = defaultColor;
    }

    protected ColorSwatch getColor(MessageLevel level) {
        return colorMap.getOrDefault(level, defaultColor);
    }
}
