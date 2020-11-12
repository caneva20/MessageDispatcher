package me.caneva20.messagedispatcher.tokenizing;

import java.util.List;

public interface ITokenizer {
    List<IToken> tokenize(String raw);
}
