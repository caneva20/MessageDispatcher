package me.caneva20.messagedispatcher;

import me.caneva20.messagedispatcher.tokenizing.IToken;
import me.caneva20.messagedispatcher.tokenizing.ITokenizer;
import me.caneva20.messagedispatcher.tokenizing.tokens.LiteralStringToken;

import java.util.List;

public class MessageParser implements IMessageParser {
    private final ITokenRegistry registry;
    private final ITokenizer tokenizer;

    public MessageParser(ITokenRegistry registry, ITokenizer tokenizer) {
        this.registry = registry;
        this.tokenizer = tokenizer;
    }

    private String buildMessage(Iterable<IToken> tokens, MessageLevel level) {
        StringBuilder builder = new StringBuilder();

        for (IToken token : tokens) {
            if (token instanceof LiteralStringToken) {
                builder.append(((LiteralStringToken) token).content);

                continue;
            }

            Iterable<IToken> children = token.getChildren();

            String content = buildMessage(children, level);

            String parsed = registry.parse(token, content, level);

            builder.append(parsed);
        }

        return builder.toString();
    }

    @Override
    public String parse(String raw, MessageLevel level) {
        List<IToken> tokens = tokenizer.tokenize(raw);

        return buildMessage(tokens, level);
    }
}
