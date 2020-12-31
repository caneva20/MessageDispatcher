package me.caneva20.messagedispatcher.parsing;

import me.caneva20.messagedispatcher.MessageLevel;
import me.caneva20.messagedispatcher.registries.ITokenRegistry;
import me.caneva20.messagedispatcher.tokenizing.IToken;
import me.caneva20.messagedispatcher.tokenizing.ITokenizer;
import me.caneva20.messagedispatcher.tokenizing.tokens.LiteralStringToken;
import me.caneva20.messagedispatcher.tokenizing.tokens.ParameterToken;
import me.caneva20.messagedispatcher.tokenizing.tokens.TagToken;

import java.util.Collections;
import java.util.Map;

public class MessageParser implements IMessageParser {
    private final ITokenRegistry registry;
    private final ITokenizer tokenizer;

    public MessageParser(ITokenRegistry registry, ITokenizer tokenizer) {
        this.registry = registry;
        this.tokenizer = tokenizer;
    }

    private String buildMessage(Iterable<IToken> tokens, MessageLevel level, Map<String, String> parameters) {
        var builder = new StringBuilder();

        for (var token : tokens) {
            if (token instanceof LiteralStringToken) {
                var parsed = registry.parse(null, ((LiteralStringToken) token).content, level);

                builder.append(parsed);
            } else if (token instanceof TagToken) {
                var children = ((TagToken) token).getChildren();

                var content = buildMessage(children, level, parameters);

                var parsed = registry.parse(token, content, level);

                builder.append(parsed);
            } else if (token instanceof ParameterToken) {
                var paramName = token.getName();

                if (parameters.containsKey(paramName)) {
                    builder.append(parameters.get(paramName));
                } else {
                    builder.append(parameters.get(String.format("{{INVALID_PARAM:%s}}", paramName)));
                }
            }
        }

        return builder.toString();
    }

    @Override
    public String parse(String raw, MessageLevel level, Map<String, String> parameters) {
        var tokens = tokenizer.tokenize(raw);

        return buildMessage(tokens, level, parameters);
    }

    @Override
    public String parse(String raw, MessageLevel level) {
        return parse(raw, level, Collections.emptyMap());
    }
}
