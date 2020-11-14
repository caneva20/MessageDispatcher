package me.caneva20.messagedispatcher.tokenizing.tokens;

import me.caneva20.messagedispatcher.tokenizing.IToken;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class InvalidToken implements IToken {
    final String name;
    final List<IToken> partialContent;

    public InvalidToken(String name, List<IToken> content) {
        this.name = name;
        this.partialContent = content;
    }

    public InvalidToken(String name, IToken child) {
        this.name = name;
        this.partialContent = Collections.singletonList(child);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public @NotNull Iterable<IToken> getChildren() {
        return partialContent;
    }
}
