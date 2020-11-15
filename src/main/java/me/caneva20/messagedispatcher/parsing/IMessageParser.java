package me.caneva20.messagedispatcher.parsing;

import me.caneva20.messagedispatcher.MessageLevel;

import java.util.Map;

public interface IMessageParser {
    String parse(String raw, MessageLevel level);

    String parse(String raw, MessageLevel level, Map<String, String> parameters);
}
