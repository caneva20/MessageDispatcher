package me.caneva20.messagedispatcher.parsing.tokens;

import me.caneva20.messagedispatcher.ColorSwatch;
import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.parsing.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TagTokenParser extends TokenParser {
    private final String opening;
    private final String closing;
    private final String debugPrefix;
    private final ColorSwatch debugColor;

    public TagTokenParser(Map<MessageLevel, ColorSwatch> colorMap,
                          ColorSwatch defaultColor,
                          String opening,
                          String closing,
                          @Nullable String debugPrefix,
                          @Nullable ColorSwatch debugColor
    ) {
        super(colorMap, defaultColor);

        this.opening = opening;
        this.closing = closing;
        this.debugPrefix = debugPrefix;
        this.debugColor = debugColor;
    }

    @Override
    public String parse(@NotNull String content, MessageLevel level) {
        var color = getColor(level);

        var debugPrefix = "";

        if (this.debugPrefix != null && debugColor != null) {
            debugPrefix = String.format("&%s%s&%s%s&%s%s",
                    debugColor.detail,
                    opening,
                    debugColor.primary,
                    this.debugPrefix,
                    debugColor.detail,
                    closing
            );
        }

        return String.format("&%s%s&%s%s&%s%s%s&%s",
                color.detail,
                opening,
                color.primary,
                content,
                color.detail,
                closing,
                debugPrefix,
                color.primary
        );
    }
}
