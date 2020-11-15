package me.caneva20.messagedispatcher.parsing.tokens;

import me.caneva20.messagedispatcher.ColorSwatch;
import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.parsing.ITokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TagTokenParser implements ITokenParser {
    private final Map<MessageLevel, ColorSwatch> colorMap;
    private final ColorSwatch defaultColor;
    private final char opening;
    private final char closing;
    private final String debugPrefix;
    private final ColorSwatch debugColor;

    public TagTokenParser(Map<MessageLevel, ColorSwatch> colorMap,
                          ColorSwatch defaultColor,
                          char opening,
                          char closing,
                          @Nullable String debugPrefix,
                          @Nullable ColorSwatch debugColor
    ) {
        this.colorMap = colorMap;
        this.defaultColor = defaultColor;
        this.opening = opening;
        this.closing = closing;
        this.debugPrefix = debugPrefix;
        this.debugColor = debugColor;
    }

    @Override
    public String parse(@NotNull String content, MessageLevel level) {
        ColorSwatch c = colorMap.getOrDefault(level, defaultColor);
        ColorSwatch d = this.debugColor;

        String debugPrefix = "";

        if (this.debugPrefix != null && debugColor != null) {
            debugPrefix = String.format("&%s%s&%s%s&%s%s",
                    d.detail,
                    opening,
                    d.primary,
                    this.debugPrefix,
                    d.detail,
                    closing
            );
        }


        return String.format("&%s%s&%s%s&%s%s%s",
                c.detail,
                opening,
                c.primary,
                content,
                c.detail,
                closing,
                debugPrefix
        );
    }
}
