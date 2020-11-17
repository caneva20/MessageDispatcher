package me.caneva20.messagedispatcher.registries;

import me.caneva20.messagedispatcher.parsing.ITokenParser;
import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.tokenizing.IToken;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TokenRegistry implements ITokenRegistry {
    private final Map<String, ITokenParser> parsers = new HashMap<>();

    private ITokenParser defaultParser;

    public TokenRegistry(@NotNull ITokenParser defaultParser) {
        this.defaultParser = defaultParser;
    }

    private ITokenParser getParser(@NotNull IToken token) {
        return parsers.getOrDefault(token.getName(), defaultParser);
    }

    @Override
    public void setDefaultParser(ITokenParser defaultParser) {
        this.defaultParser = defaultParser;
    }

    @Override
    public void registerParser(@NotNull String tokenName, @NotNull ITokenParser parser) {
        parsers.put(tokenName, parser);
    }

    @Override
    public @NotNull String parse(@NotNull IToken token, @NotNull String content, MessageLevel level) {
        return getParser(token).parse(content, level);
    }
}
