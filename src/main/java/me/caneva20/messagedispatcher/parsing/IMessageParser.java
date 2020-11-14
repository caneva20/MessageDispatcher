package me.caneva20.messagedispatcher.parsing;

import me.caneva20.messagedispatcher.MessageLevel;

public interface IMessageParser {
    String parse(String raw, MessageLevel level);
}
