package me.caneva20.messagedispatcher.parsing;

import me.caneva20.messagedispatcher.MessageLevel;
import org.jetbrains.annotations.NotNull;

public interface ITokenParser {
    String parse(@NotNull String content, MessageLevel level);
}
