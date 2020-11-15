package me.caneva20.messagedispatcher.configurations;

import me.caneva20.messagedispatcher.parsing.IMessageParser;
import me.caneva20.messagedispatcher.parsing.MessageParser;
import me.caneva20.messagedispatcher.registries.ITokenRegistry;
import me.caneva20.messagedispatcher.registries.TokenRegistry;
import me.caneva20.messagedispatcher.tokenizing.ITokenizer;
import me.caneva20.messagedispatcher.tokenizing.Tokenizer;

public class DefaultConfiguration implements IConfiguration {
    private ITokenizer tokenizer;
    private ITokenRegistry tokenRegistry;
    private IMessageParser messageParser;

    private boolean hasCustomMessageParser;

    public DefaultConfiguration() {
        tokenizer = new Tokenizer();
        tokenRegistry = new TokenRegistry((content, level) -> content);
        messageParser = new MessageParser(tokenRegistry, tokenizer);
    }

    public ITokenizer getTokenizer() {
        return tokenizer;
    }

    public ITokenRegistry getTokenRegistry() {
        return tokenRegistry;
    }

    public IMessageParser getMessageParser() {
        return messageParser;
    }

    public void setTokenizer(ITokenizer tokenizer) {
        this.tokenizer = tokenizer;

        if (hasCustomMessageParser) {
            System.out.println("[WARNING] You have a user defined message parser." +
                    "Changing the tokenizer in this case has no effect." +
                    "You should re-set the message parser with this new tokenizer if a tokenizer change is required"
            );
        } else {
            messageParser = new MessageParser(tokenRegistry, tokenizer);
        }
    }

    public void setTokenRegistry(ITokenRegistry tokenRegistry) {
        this.tokenRegistry = tokenRegistry;

        if (hasCustomMessageParser) {
            System.out.println("[WARNING] You have a user defined message parser." +
                    "Changing the token registry in this case has no effect." +
                    "You should re-set the message parser with this new token registry if a token registry change is required"
            );
        } else {
            messageParser = new MessageParser(tokenRegistry, tokenizer);
        }
    }

    public void setMessageParser(IMessageParser messageParser) {
        this.messageParser = messageParser;

        hasCustomMessageParser = true;
    }
}
