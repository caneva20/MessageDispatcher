package me.caneva20.messagedispatcher.tokenizing;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IToken {
    @Nullable
    String getName();

    @NotNull
    String getContent();
}
