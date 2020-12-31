package me.caneva20.messagedispatcher.registries;

import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.parsing.ITokenParser;
import me.caneva20.messagedispatcher.tokenizing.IToken;
import org.jetbrains.annotations.NotNull;

public interface ITokenRegistry {
    void setDefaultParser(ITokenParser defaultParser);

    void registerParser(@NotNull String tokenName, @NotNull ITokenParser parser);

    @NotNull String parse(@NotNull IToken token, @NotNull String content, MessageLevel level);

    @NotNull String parseRoot(@NotNull String content, MessageLevel level);
}
