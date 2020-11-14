package me.caneva20.messagedispatcher.tokenizing.tokens;

import me.caneva20.messagedispatcher.tokenizing.IToken;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class TagToken implements IToken {
    public final String name;

    public final List<IToken> children;

    public TagToken(String name, List<IToken> children) {
        this.name = name;
        this.children = children;
    }

    public TagToken(String name, IToken child) {
        this.name = name;
        this.children = Collections.singletonList(child);
    }

    @Override
    public String getName() {
        return name;
    }

    public @NotNull Iterable<IToken> getChildren() {
        return children != null ? children : Collections.emptyList();
    }
}
