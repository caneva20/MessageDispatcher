package me.caneva20.messagedispatcher;

import me.caneva20.messagedispatcher.tokenizing.IToken;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TokenRegistry implements ITokenRegistry {
    private final Map<String, ITokenParser> parsers = new HashMap<>();

    private final ITokenParser defaultParser;

    public TokenRegistry(@NotNull ITokenParser defaultParser) {
        this.defaultParser = defaultParser;
    }

    private ITokenParser getParser(@NotNull IToken token) {
        return parsers.getOrDefault(token.getName(), defaultParser);
    }

    @Override
    public void registerParser(@NotNull String tokenName, @NotNull ITokenParser parser) {
        parsers.put(tokenName, parser);
    }

    @Override
    public @NotNull String parse(@NotNull IToken token, String childrenContent, MessageLevel level) {
        return getParser(token).parse(token, childrenContent, level);
    }
}
