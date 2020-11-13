package me.caneva20.messagedispatcher.tokenizing.tokens;

import me.caneva20.messagedispatcher.tokenizing.IToken;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public @NotNull String getContent() {
        return children.stream().map(IToken::getContent).collect(Collectors.joining());
    }
}
