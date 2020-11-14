package me.caneva20.messagedispatcher;

import org.jetbrains.annotations.NotNull;

public interface ITokenParser {
    String parse(@NotNull String content, MessageLevel level);
}
