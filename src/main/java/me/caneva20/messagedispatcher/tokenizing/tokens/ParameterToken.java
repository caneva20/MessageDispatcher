package me.caneva20.messagedispatcher.tokenizing.tokens;

import me.caneva20.messagedispatcher.tokenizing.IToken;
import org.jetbrains.annotations.Nullable;

public class ParameterToken implements IToken {
    final String name;

    public ParameterToken(String name) {
        this.name = name;
    }

    @Override
    public @Nullable String getName() {
        return name;
    }
}
