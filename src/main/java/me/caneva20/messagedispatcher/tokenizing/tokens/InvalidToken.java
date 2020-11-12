package me.caneva20.messagedispatcher.tokenizing.tokens;

import me.caneva20.messagedispatcher.tokenizing.IToken;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public String getContent() {
        return partialContent.stream().map(IToken::getContent).collect(Collectors.joining());
    }
}
