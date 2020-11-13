package me.caneva20.messagedispatcher;

import me.caneva20.messagedispatcher.tokenizing.IToken;

public interface ITokenParser {
    String parse(IToken token, MessageLevel level);
}
