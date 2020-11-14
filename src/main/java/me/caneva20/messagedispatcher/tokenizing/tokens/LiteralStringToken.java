package me.caneva20.messagedispatcher.tokenizing.tokens;

import me.caneva20.messagedispatcher.tokenizing.IToken;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class LiteralStringToken implements IToken {
    public final String content;

    public LiteralStringToken(String content) {
        this.content = content;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public @NotNull Iterable<IToken> getChildren() {
        return Collections.emptyList();
    }
}
