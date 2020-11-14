package me.caneva20.messagedispatcher;

import me.caneva20.messagedispatcher.tokenizing.IToken;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ITokenRegistry {
    void registerParser(@NotNull String tokenName, @NotNull ITokenParser parser);

    @NotNull String parse(@NotNull IToken token, @Nullable String childrenContent, MessageLevel level);
}
